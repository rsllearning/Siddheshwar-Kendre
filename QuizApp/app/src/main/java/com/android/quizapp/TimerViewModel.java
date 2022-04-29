package com.android.quizapp;

import android.app.Application;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TimerViewModel extends AndroidViewModel {
    private MutableLiveData<String> timerLiveData=new MutableLiveData<>();
    private MutableLiveData<String> timeTakenLiveData=new MutableLiveData<>();

    public TimerViewModel(@NonNull Application application) {
        super(application);
        fetchTimerCountDown();
    }
    public LiveData<String> getTimerLiveData() {
        return timerLiveData; }
    public LiveData<String> getTimeTakenLiveData(){
        return timeTakenLiveData;
    }
    private void fetchTimerCountDown(){
        new CountDownTimer(600000,1000)//600sec=10min(timer)
        {
            @Override
            public void onTick(long l) {
                String min=Long.toString(l/(1000*60));
                String sec=Long.toString(l%(1000*60)/1000);
                timerLiveData.postValue(min+"min "+sec+"sec" );
                String taken_min=Long.toString((600000-l)/(1000*60));
                String taken_sec=Long.toString((600000-l)%(1000*60)/1000);
                timeTakenLiveData.postValue(taken_min+"min "+taken_sec+"sec");
            }
            @Override
            public void onFinish() {
                timerLiveData.postValue("0");
                timeTakenLiveData.postValue("10min 0sec");
            }
        }.start();
    }
}
