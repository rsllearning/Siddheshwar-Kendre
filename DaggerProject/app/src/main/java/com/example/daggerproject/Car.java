package com.example.daggerproject;

import android.util.Log;

import javax.inject.Inject;

public class Car {

    private static final String TAG = "Car";

    // field injection
    @Inject Engine engine;
    Wheels wheels;

    // constructor injection
    @Inject
    public Car( Wheels wheels) {
//        this.engine = engine;
        this.wheels = wheels;
    }

    // method injection
    @Inject
    public void enabledRemote(Remote remote){
        remote.setListener(this);
    }
    public  void drive(){
        Log.d(TAG, "drive");
    }
}
