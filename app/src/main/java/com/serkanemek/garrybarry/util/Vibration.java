package com.serkanemek.garrybarry.util;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import static android.content.Context.VIBRATOR_SERVICE;

import com.serkanemek.garrybarry.data.SharedPForAdvAndTotalQuestionCounter;

public class Vibration {

    SharedPForAdvAndTotalQuestionCounter sharedPForAdvAndTotalQuestionCounter;

    public void getVibrator(int number, Context context){

        try {
            sharedPForAdvAndTotalQuestionCounter = new SharedPForAdvAndTotalQuestionCounter();
            Integer i = sharedPForAdvAndTotalQuestionCounter.getCheckVibrationForOnOff(context);
            if(i == 1){
                if (Build.VERSION.SDK_INT >= 26) {
                    ((Vibrator)  context.getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(number, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    ((Vibrator) context.getSystemService(VIBRATOR_SERVICE)).vibrate(number);
                }
            }

        }catch (Exception e){

        }
    }
}
