package com.android.countdownapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final long START_TIME_IN_MILLIS = 300000;
    TextView textView;
    Button startBtn;
    Button resetBtn;
    private CountDownTimer countDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long mEndTime;
    CountryListViewModel viewModel;
    private AlertDialog failureDialog;
    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textView_countDown);
        startBtn = findViewById(R.id.start_button);
        resetBtn = findViewById(R.id.reset_button);



        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTimerRunning){
                    pauseTimer();
                }else{
                    startTimer();
                }
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        updateCountDownText();

        ViewModelProvider viewModelProvider = new ViewModelProvider(
                this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
        viewModel = viewModelProvider.get(CountryListViewModel.class);
        setUpLiveData();
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        updateButton();
    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        countDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                startBtn.setText("Start");
                updateButton();
            }
        }.start();
        mTimerRunning = true;
        updateButton();
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis /1000)/60;
        int seconds = (int) (mTimeLeftInMillis /1000)%60;
        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        textView.setText(timeLeftFormatted);

    }

    private void pauseTimer() {
        countDownTimer.cancel();
        mTimerRunning = false;
        updateButton();
    }
    private void updateButton(){
        if(mTimerRunning){
            resetBtn.setVisibility(View.INVISIBLE);
            startBtn.setText("Pause");
        }else{
            startBtn.setText("Start");
            if(mTimeLeftInMillis<1000){
                startBtn.setVisibility(View.INVISIBLE);

            }else{
                startBtn.setVisibility(View.VISIBLE);
            }
            if(mTimeLeftInMillis < START_TIME_IN_MILLIS){
                resetBtn.setVisibility(View.VISIBLE);
            }else{
                resetBtn.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("millisLeft",mTimeLeftInMillis);
        outState.putBoolean("timerRunning",mTimerRunning);
        outState.putLong("endTime",mEndTime);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mTimeLeftInMillis = savedInstanceState.getLong("millisLeft");
        mTimerRunning  = savedInstanceState.getBoolean("timerRunning");
        updateCountDownText();
        updateButton();
        if(mTimerRunning){
            mEndTime = savedInstanceState.getLong("endTime");
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
            startTimer();
        }else{

        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (failureDialog != null) {
            failureDialog.dismiss();
        }
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    private void setUpLiveData() {
        viewModel.getCountriesLiveData().observe(this, new Observer<List<DataModel>>() {
            @Override
            public void onChanged(List<DataModel> gameModels) {
                handleGamesList(gameModels);
            }
        });
        viewModel.getRequestStatusLiveData().observe(this, new Observer<CountryListViewModel.RequestStatus>() {
            @Override
            public void onChanged(CountryListViewModel.RequestStatus requestStatus) {
                handleRequestStatus(requestStatus);
            }
        });
    }

    private void handleGamesList(List<DataModel> gameModels) {
        CountryListAdapter adapter = new CountryListAdapter(gameModels);
        RecyclerView gamesRecyclerView = findViewById(R.id.recyclerview);
        gamesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        gamesRecyclerView.setAdapter(adapter);
    }

    private void handleRequestStatus(CountryListViewModel.RequestStatus requestStatus) {
        switch (requestStatus) {
            case IN_PROGRESS:
                showSpinner();
                break;
            case SUCCEEDED:
                hideSpinner();
                break;
            case FAILED:
                showError();
                break;
        }
    }

    private void showSpinner() {
        if (loadingDialog == null) {
            loadingDialog = new ProgressDialog(this);
            loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loadingDialog.setTitle("Fetching games");
            loadingDialog.setMessage("Please wait...");
            loadingDialog.setIndeterminate(true);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        loadingDialog.show();
    }

    private void hideSpinner() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    private void showError() {
        hideSpinner();
        if (failureDialog == null) {
            failureDialog = getFailureDialog();
        }
        failureDialog.show();
    }

    private AlertDialog getFailureDialog() {
        return new AlertDialog.Builder(this)
                .setTitle("Game list request failed")
                .setMessage("Game list fetching is failed, do you want to retry?")
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        viewModel.refetchCountries();
                    }
                })
                .setNegativeButton("Close app", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .create();
    }
}

