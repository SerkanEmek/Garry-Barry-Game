package com.serkanemek.garrybarry.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPForDiamondCoins {


    SharedPreferences sharedPreferences;


    public void updateDiamondCoinsData(Context context, Integer platiniumCoins){
        try {
            Integer storedPlatinium = -1;
            sharedPreferences = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedPlatinium = sharedPreferences.getInt("storedPlatinium",2);
            storedPlatinium = storedPlatinium + platiniumCoins;
            sharedPreferences.edit().putInt("storedPlatinium" , storedPlatinium).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Integer getDiamondCoinsData(Context context){
        Integer storedPlatinium = -1;
        try {
            sharedPreferences = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedPlatinium = sharedPreferences.getInt("storedPlatinium",2);
            return storedPlatinium;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedPlatinium;

    }
}
