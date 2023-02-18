package com.serkanemek.garrybarry.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.serkanemek.garrybarry.R;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForMainMenu;
import com.serkanemek.garrybarry.data.SharedPForAdvAndTotalQuestionCounter;
import com.serkanemek.garrybarry.util.Vibration;

public class MainMenuActivity1 extends AppCompatActivity {


    ImageView mainmenu_playgame_icon,mainmenu_allstatistic_icon,mainmenu_challengestatistic_icon
            ,mainmenu_settings_icon,mainmenu_bg_icon;

    private static final int TRANSPARENT_GREY = Color.argb(0, 185, 185, 185);
    private static final int FILTERED_GREY = Color.argb(155, 185, 185, 185);

    SharedPreferences sharedPreferences;
    Integer languageInteger;

    Vibration vibration;

    MyMediaPlayerForMainMenu myMediaPlayerForMainMenu;
    Integer checkMusicOnOff;
    Integer checkOnOffPause;//For statistics mediaplayer will not pause.
    SharedPForAdvAndTotalQuestionCounter sharedPForAdvAndTotalQuestionCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu1);

        initializing();
        checkLanguage();
        checkOnOffPause = 0;
        sharedPForAdvAndTotalQuestionCounter = new SharedPForAdvAndTotalQuestionCounter();

        checkMusicOnOff = sharedPForAdvAndTotalQuestionCounter.getCheckMusicForOnOff(this);
        myMediaPlayerForMainMenu = new MyMediaPlayerForMainMenu();
    }

    private void checkLanguage() {
        sharedPreferences = this.getSharedPreferences("com.serkanemek.garrybarry.Activities" , Context.MODE_PRIVATE);
        languageInteger = sharedPreferences.getInt("storedLanguage",0);

        if(languageInteger == 1){
            mainmenu_playgame_icon.setImageResource(R.drawable.playgame_english);
            mainmenu_allstatistic_icon.setImageResource(R.drawable.statistics_all_english);
            mainmenu_challengestatistic_icon.setImageResource(R.drawable.statistics_challenge_english);
        }else if(languageInteger == 2){
            mainmenu_playgame_icon.setImageResource(R.drawable.playgame_turkish);
            mainmenu_allstatistic_icon.setImageResource(R.drawable.statistics_all_turkish);
            mainmenu_challengestatistic_icon.setImageResource(R.drawable.statistics_challenge_turkish);
        }else if(languageInteger == 3){
            mainmenu_playgame_icon.setImageResource(R.drawable.playgame_russian);
            mainmenu_allstatistic_icon.setImageResource(R.drawable.statistics_all_russian);
            mainmenu_challengestatistic_icon.setImageResource(R.drawable.statistics_challenge_russian);
        }

    }

    private void initializing() {
        mainmenu_playgame_icon = findViewById(R.id.mainmenu_playgame_icon);
        mainmenu_playgame_icon.setColorFilter(TRANSPARENT_GREY);
        mainmenu_allstatistic_icon = findViewById(R.id.mainmenu_allstatistic_icon);
        mainmenu_allstatistic_icon.setColorFilter(TRANSPARENT_GREY);
        mainmenu_challengestatistic_icon = findViewById(R.id.mainmenu_challengestatistic_icon);
        mainmenu_challengestatistic_icon.setColorFilter(TRANSPARENT_GREY);
        mainmenu_settings_icon = findViewById(R.id.mainmenu_settings_icon);
        mainmenu_settings_icon.setColorFilter(TRANSPARENT_GREY);
        mainmenu_bg_icon = findViewById(R.id.mainmenu_bg_icon);
        mainmenu_bg_icon.setColorFilter(TRANSPARENT_GREY);
        vibration = new Vibration();
    }

    public void playgame(View view){
        setButtonClickEnable();
        vibration.getVibrator(75,getApplicationContext());
        mainmenu_playgame_icon.setColorFilter(FILTERED_GREY);
        checkOnOffPause = 1;

        Intent intent = new Intent(MainMenuActivity1.this, MainMenuActivity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void allstatistic(View view){
        setButtonClickEnable();
        vibration.getVibrator(75,getApplicationContext());
        mainmenu_allstatistic_icon.setColorFilter(FILTERED_GREY);
        checkOnOffPause = 1;

        Intent intentToStatistic = new Intent(getApplicationContext(),CheckStatisticForMainGames.class);
        intentToStatistic.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentToStatistic);
        finish();

    }

    public void challengestatistic(View view){
        setButtonClickEnable();
        vibration.getVibrator(75,getApplicationContext());
        mainmenu_challengestatistic_icon.setColorFilter(FILTERED_GREY);
        checkOnOffPause = 1;

        Intent intentToStatistic = new Intent(getApplicationContext(), CheckStatisticForNewChallengeActivity.class);
        intentToStatistic.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentToStatistic);
        finish();

    }

    public void settings(View view){
        setButtonClickEnable();
        vibration.getVibrator(75,getApplicationContext());
        mainmenu_settings_icon.setColorFilter(FILTERED_GREY);
        checkOnOffPause = 1;

        Intent intent = new Intent(MainMenuActivity1.this,SettingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();


    }

    public void backgrounds(View view){
        setButtonClickEnable();
        vibration.getVibrator(75,getApplicationContext());
        mainmenu_bg_icon.setColorFilter(FILTERED_GREY);
        checkOnOffPause = 1;

        Intent intentToStatistic = new Intent(getApplicationContext(),Background.class);
        intentToStatistic.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentToStatistic);
        finish();

    }

    private void setButtonClickEnable(){
        mainmenu_playgame_icon.setEnabled(false);
        mainmenu_bg_icon.setEnabled(false);
        mainmenu_allstatistic_icon.setEnabled(false);
        mainmenu_challengestatistic_icon.setEnabled(false);
        mainmenu_settings_icon.setEnabled(false);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(checkMusicOnOff == 1){
            myMediaPlayerForMainMenu.playBackgroundMusic(getApplicationContext());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(checkOnOffPause == 0){
            myMediaPlayerForMainMenu.pauseBackgroundMusic();
        }

    }



}