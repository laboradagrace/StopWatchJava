/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grasya;
//package com.example;

import java.util.Timer;
import java.util.TimerTask;

public class Grasya {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new App(), 0, 1000);
    }
}

class App extends TimerTask {

    int countdown = 100;

    @Override
    public void run() {
        countdown = countdown - 1;
        //System.out.println(countdown);
        //label.setText(countdown +"second's left");
    }

}
