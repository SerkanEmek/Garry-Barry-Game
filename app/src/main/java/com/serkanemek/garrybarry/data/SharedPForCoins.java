package com.serkanemek.garrybarry.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPForCoins {

    SharedPreferences sharedPreferences;


    public void updateCoinsData(Context context, Integer userCoins){
        try {
            Integer storedCoins = -1;
            sharedPreferences = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedCoins = sharedPreferences.getInt("storedCoins",40);
            storedCoins = storedCoins + userCoins;
            sharedPreferences.edit().putInt("storedCoins" , storedCoins).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Integer getCoinsData(Context context){
        Integer storedCoins = -1;
        try {
            sharedPreferences = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedCoins = sharedPreferences.getInt("storedCoins",40);
            return storedCoins;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedCoins;
    }
}
