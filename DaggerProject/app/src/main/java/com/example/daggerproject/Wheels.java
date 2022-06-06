package com.example.daggerproject;

import javax.inject.Inject;

public class Wheels {

//
//    @Inject
//    public Wheels(){
//
//    }
    Tires tires;
    Rims rims;

    public Wheels(Tires tires, Rims rims) {
        this.tires = tires;
        this.rims = rims;
    }
}
