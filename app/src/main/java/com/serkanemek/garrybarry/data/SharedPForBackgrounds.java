package com.serkanemek.garrybarry.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPForBackgrounds {

    SharedPreferences sharedPreferencesForMemoryGame,sharedPreferencesForMathGameForPractice,
            sharedPreferencesForMIqGame,sharedPreferencesForMathGame;

    SharedPreferences sPCheckSellMemory2,sPCheckSellMemory3,sPCheckSellMemory4,
            sPCheckSellIq2,sPCheckSellIq3,sPCheckSellIq4,
            sPCheckSellMath2, sPCheckSellMath3,sPCheckSellMath4,
            sPCheckSellMathPractice2,sPCheckSellMathPractice3,sPCheckSellMathPractice4;



    public void updateMemoryGameBackground(Context context, Integer numberOfBackground){
        try {
            Integer storedMemoryGameBackground = -1;
            sharedPreferencesForMemoryGame = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedMemoryGameBackground = numberOfBackground;
            sharedPreferencesForMemoryGame.edit().putInt("storedMemoryGameBackgroundData" , storedMemoryGameBackground).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getMemoryGameBackground(Context context){
        Integer storedMemoryGameBackground = -1;
        try {
            sharedPreferencesForMemoryGame = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedMemoryGameBackground = sharedPreferencesForMemoryGame.getInt("storedMemoryGameBackgroundData",1);
            return storedMemoryGameBackground;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedMemoryGameBackground;

    }

    public void updateIqGameBackground(Context context, Integer numberOfBackground){
        try {
            Integer storedIqGameBackground = -1;
            sharedPreferencesForMIqGame = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedIqGameBackground = numberOfBackground;
            sharedPreferencesForMIqGame.edit().putInt("storedIqGameBackgroundData" , storedIqGameBackground).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getIqGameBackground(Context context){
        Integer storedIqGameBackground = -1;
        try {
            sharedPreferencesForMIqGame = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedIqGameBackground = sharedPreferencesForMIqGame.getInt("storedIqGameBackgroundData",1);
            return storedIqGameBackground;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedIqGameBackground;

    }

    public void updateMathGameBackground(Context context, Integer numberOfBackground){
        try {
            Integer storedMathGameBackground = -1;
            sharedPreferencesForMathGame = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedMathGameBackground = numberOfBackground;
            sharedPreferencesForMathGame.edit().putInt("storedMathGameBackgroundData" , storedMathGameBackground).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getMathGameBackground(Context context){
        Integer storedMathGameBackground = -1;
        try {
            sharedPreferencesForMathGame = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedMathGameBackground = sharedPreferencesForMathGame.getInt("storedMathGameBackgroundData",1);
            return storedMathGameBackground;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedMathGameBackground;

    }

    public void updateMathGamePracticeBackground(Context context, Integer numberOfBackground){
        try {
            Integer storedMathGamePracticeBackground = -1;
            sharedPreferencesForMathGameForPractice = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedMathGamePracticeBackground = numberOfBackground;
            sharedPreferencesForMathGameForPractice.edit().putInt("storedMathGamePracticeBackgroundData" , storedMathGamePracticeBackground).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getMathGamePracticeBackground(Context context){
        Integer storedMathGamePracticeBackground = -1;
        try {
            sharedPreferencesForMathGameForPractice = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedMathGamePracticeBackground = sharedPreferencesForMathGameForPractice.getInt("storedMathGamePracticeBackgroundData",1);
            return storedMathGamePracticeBackground;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedMathGamePracticeBackground;

    }


    public void updatesPCheckSellMemory2(Context context, Integer numberOfBackground){ // Give values 1-not sell 2-sell
        try {
            Integer storedSPCheckSell = -1;
            sPCheckSellMemory2 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = numberOfBackground;
            sPCheckSellMemory2.edit().putInt("sPCheckSellMemory2" , storedSPCheckSell).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getsPCheckSellMemory2(Context context){
        Integer storedSPCheckSell = -1;
        try {
            sPCheckSellMemory2 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = sPCheckSellMemory2.getInt("sPCheckSellMemory2",1);
            return storedSPCheckSell;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedSPCheckSell;

    }

    public void updatesPCheckSellMemory3(Context context, Integer numberOfBackground){ // Give values 1-not sell 2-sell
        try {
            Integer storedSPCheckSell = -1;
            sPCheckSellMemory3 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = numberOfBackground;
            sPCheckSellMemory3.edit().putInt("sPCheckSellMemory3" , storedSPCheckSell).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getsPCheckSellMemory3(Context context){
        Integer storedSPCheckSell = -1;
        try {
            sPCheckSellMemory3 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = sPCheckSellMemory3.getInt("sPCheckSellMemory3",1);
            return storedSPCheckSell;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedSPCheckSell;

    }

    public void updatesPCheckSellMemory4(Context context, Integer numberOfBackground){ // Give values 1-not sell 2-sell
        try {
            Integer storedSPCheckSell = -1;
            sPCheckSellMemory4 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = numberOfBackground;
            sPCheckSellMemory4.edit().putInt("sPCheckSellMemory4" , storedSPCheckSell).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getsPCheckSellMemory4(Context context){
        Integer storedSPCheckSell = -1;
        try {
            sPCheckSellMemory4 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = sPCheckSellMemory4.getInt("sPCheckSellMemory4",1);
            return storedSPCheckSell;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedSPCheckSell;

    }

    public void updatesPCheckSelliq2(Context context, Integer numberOfBackground){ // Give values 1-not sell 2-sell
        try {
            Integer storedSPCheckSell = -1;
            sPCheckSellIq2 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = numberOfBackground;
            sPCheckSellIq2.edit().putInt("sPCheckSellIq2" , storedSPCheckSell).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getsPCheckSelliq2(Context context){
        Integer storedSPCheckSell = -1;
        try {
            sPCheckSellIq2 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = sPCheckSellIq2.getInt("sPCheckSellIq2",1);
            return storedSPCheckSell;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedSPCheckSell;

    }

    public void updatesPCheckSelliq3(Context context, Integer numberOfBackground){ // Give values 1-not sell 2-sell
        try {
            Integer storedSPCheckSell = -1;
            sPCheckSellIq3 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = numberOfBackground;
            sPCheckSellIq3.edit().putInt("sPCheckSellIq3" , storedSPCheckSell).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getsPCheckSelliq3(Context context){
        Integer storedSPCheckSell = -1;
        try {
            sPCheckSellIq3 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = sPCheckSellIq3.getInt("sPCheckSellIq3",1);
            return storedSPCheckSell;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedSPCheckSell;

    }

    public void updatesPCheckSelliq4(Context context, Integer numberOfBackground){ // Give values 1-not sell 2-sell
        try {
            Integer storedSPCheckSell = -1;
            sPCheckSellIq4 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = numberOfBackground;
            sPCheckSellIq4.edit().putInt("sPCheckSellIq4" , storedSPCheckSell).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getsPCheckSelliq4(Context context){
        Integer storedSPCheckSell = -1;
        try {
            sPCheckSellIq4 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = sPCheckSellIq4.getInt("sPCheckSellIq4",1);
            return storedSPCheckSell;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedSPCheckSell;

    }

    public void updatesPCheckSellMath2(Context context, Integer numberOfBackground){ // Give values 1-not sell 2-sell
        try {
            Integer storedSPCheckSell = -1;
            sPCheckSellMath2 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = numberOfBackground;
            sPCheckSellMath2.edit().putInt("sPCheckSellMath2" , storedSPCheckSell).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getsPCheckSellMath2(Context context){
        Integer storedSPCheckSell = -1;
        try {
            sPCheckSellMath2 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = sPCheckSellMath2.getInt("sPCheckSellMath2",1);
            return storedSPCheckSell;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedSPCheckSell;

    }

    public void updatesPCheckSellMath3(Context context, Integer numberOfBackground){ // Give values 1-not sell 2-sell
        try {
            Integer storedSPCheckSell = -1;
            sPCheckSellMath3 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = numberOfBackground;
            sPCheckSellMath3.edit().putInt("sPCheckSellMath3" , storedSPCheckSell).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getsPCheckSellMath3(Context context){
        Integer storedSPCheckSell = -1;
        try {
            sPCheckSellMath3 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = sPCheckSellMath3.getInt("sPCheckSellMath3",1);
            return storedSPCheckSell;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedSPCheckSell;

    }

    public void updatesPCheckSellMath4(Context context, Integer numberOfBackground){ // Give values 1-not sell 2-sell
        try {
            Integer storedSPCheckSell = -1;
            sPCheckSellMath4 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = numberOfBackground;
            sPCheckSellMath4.edit().putInt("sPCheckSellMath4" , storedSPCheckSell).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getsPCheckSellMath4(Context context){
        Integer storedSPCheckSell = -1;
        try {
            sPCheckSellMath4 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = sPCheckSellMath4.getInt("sPCheckSellMath4",1);
            return storedSPCheckSell;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedSPCheckSell;

    }

    public void updatesPCheckSellMathPractice2(Context context, Integer numberOfBackground){ // Give values 1-not sell 2-sell
        try {
            Integer storedSPCheckSell = -1;
            sPCheckSellMathPractice2 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = numberOfBackground;
            sPCheckSellMathPractice2.edit().putInt("sPCheckSellMathPractice2" , storedSPCheckSell).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getsPCheckSellMathPractice2(Context context){
        Integer storedSPCheckSell = -1;
        try {
            sPCheckSellMathPractice2 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = sPCheckSellMathPractice2.getInt("sPCheckSellMathPractice2",1);
            return storedSPCheckSell;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedSPCheckSell;

    }

    public void updatesPCheckSellMathPractice3(Context context, Integer numberOfBackground){ // Give values 1-not sell 2-sell
        try {
            Integer storedSPCheckSell = -1;
            sPCheckSellMathPractice3 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = numberOfBackground;
            sPCheckSellMathPractice3.edit().putInt("sPCheckSellMathPractice3" , storedSPCheckSell).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getsPCheckSellMathPractice3(Context context){
        Integer storedSPCheckSell = -1;
        try {
            sPCheckSellMathPractice3 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = sPCheckSellMathPractice3.getInt("sPCheckSellMathPractice3",1);
            return storedSPCheckSell;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedSPCheckSell;

    }

    public void updatesPCheckSellMathPractice4(Context context, Integer numberOfBackground){ // Give values 1-not sell 2-sell
        try {
            Integer storedSPCheckSell = -1;
            sPCheckSellMathPractice4 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = numberOfBackground;
            sPCheckSellMathPractice4.edit().putInt("sPCheckSellMathPractice4" , storedSPCheckSell).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getsPCheckSellMathPractice4(Context context){
        Integer storedSPCheckSell = -1;
        try {
            sPCheckSellMathPractice4 = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedSPCheckSell = sPCheckSellMathPractice4.getInt("sPCheckSellMathPractice4",1);
            return storedSPCheckSell;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedSPCheckSell;

    }


}
