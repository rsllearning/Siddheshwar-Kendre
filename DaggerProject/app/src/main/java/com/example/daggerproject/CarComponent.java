package com.example.daggerproject;

import java.util.WeakHashMap;

import dagger.Component;

@Component (modules = WheelsModule.class)
public interface CarComponent {
    Car getCar();
    void inject(MainActivity mainActivity);
}
