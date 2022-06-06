package com.example.daggerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CarComponent component = DaggerCarComponent.create();
//        car = component.getCar();
        component.inject(this);
        car.drive();

    }
}