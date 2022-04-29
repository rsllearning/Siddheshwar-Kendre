package com.android.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestionListActivity extends AppCompatActivity {

    private static final String PREF_NAME = "com.example.quizApplication";
    public TextView countdownTimer;
    private Button submitBtn;
    Fragment fragment1;
    SharedPreferences sharedPreferences;
    String timeTaken;
    private TimerViewModel timerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        submitBtn = findViewById(R.id.submit_button);

        ViewModelProvider viewModelProvider = new ViewModelProvider(
                this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
        timerViewModel = viewModelProvider.get(TimerViewModel.class);

        fragment1 = new RecyclerViewFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment1).commit();
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        sharedPreferences.edit().putInt("marks", 0).commit();

        setDataToViews();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialogBox();
            }
        });
    }

    private void setDataToViews() {
        timerViewModel.getTimerLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String str) {
                countdownTimer = findViewById(R.id.timer);
                countdownTimer.setText(str);
                if (str.equals("0")) {
                    Intent intent = new Intent(QuestionListActivity.this, SummaryActivity.class);
                    intent.putExtra("time_taken", timeTaken);
                    intent.putExtra("marks", sharedPreferences.getInt("marks", 0));
                    startActivity(intent);
                    finish();
                }
            }
        });

        timerViewModel.getTimeTakenLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String str) {
                timeTaken = str;
            }
        });
    }

    private void displayDialogBox(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Do you want to submit test ?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(QuestionListActivity.this, SummaryActivity.class);
                intent.putExtra("time_taken", timeTaken);
                intent.putExtra("marks", sharedPreferences.getInt("marks", 0));
                startActivity(intent);
                finish();
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        dialog.create().show();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}