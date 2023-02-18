package com.serkanemek.garrybarry.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.serkanemek.garrybarry.R;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForCorrectAnswer;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForMainMenu;
import com.serkanemek.garrybarry.data.SharedPForAdvAndTotalQuestionCounter;
import com.serkanemek.garrybarry.util.Vibration;

public class SettingActivity extends AppCompatActivity {

    private static final int FILTERED_GREY = Color.argb(155, 185, 185, 185);
    private static final int TRANSPARENT_GREY = Color.argb(0, 185, 185, 185);

    ImageView imageView_MusicOnOff,imageView_Setting_SoundOnOff,imageView_Credits,
            imageView_ChangeLanguage,imageView_Setting_homeIcon,imageView_Setting_VibrationOnOff;

    SharedPreferences sharedPreferences;
    Integer languageInteger;

    SharedPForAdvAndTotalQuestionCounter sharedPForAdvAndTotalQuestionCounter;

    MyMediaPlayerForMainMenu myMediaPlayerForMainMenu;
    Integer checkMusicOnOff;
    Integer checkSoundOnOff;
    Integer checkVibrationOnOff;
    Integer checkOnOffPause;

    MyMediaPlayerForCorrectAnswer myMediaPlayerForCorrectAnswer;
    Vibration vibration;

    private AdView mAdView;

    //Setting Statistic Background Banner TEST id: ca-app-pub-3940256099942544/6300978111


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_setting);

        initializing();

        getAdvertisement();


    }


    private void getAdvertisement() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adViewSetting);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    private void initializing(){
        vibration = new Vibration();
        myMediaPlayerForCorrectAnswer = new MyMediaPlayerForCorrectAnswer();
        imageView_MusicOnOff = findViewById(R.id.imageView_Setting_MusicOnOff);
        imageView_Setting_SoundOnOff = findViewById(R.id.imageView_Setting_SoundOnOff);
        imageView_Credits = findViewById(R.id.imageView_Setting_Credits);
        imageView_ChangeLanguage = findViewById(R.id.imageView_Setting_CgangeLanguage);
        imageView_Setting_homeIcon = findViewById(R.id.imageView_Setting_homeIcon);
        imageView_Setting_VibrationOnOff = findViewById(R.id.imageView_Setting_VibrationOnOff);

        checkLanguage();

        sharedPForAdvAndTotalQuestionCounter = new SharedPForAdvAndTotalQuestionCounter();
        myMediaPlayerForMainMenu = new MyMediaPlayerForMainMenu();

        checkMusicOnOff = sharedPForAdvAndTotalQuestionCounter.getCheckMusicForOnOff(this);
        if(checkMusicOnOff == 1){
            imageView_MusicOnOff.setImageResource(R.drawable.musiciconon);
        }else{
            imageView_MusicOnOff.setImageResource(R.drawable.musiciconoff);
        }

        checkSoundOnOff = sharedPForAdvAndTotalQuestionCounter.getCheckSoundForOnOff(this);
        if(checkSoundOnOff == 1){
            imageView_Setting_SoundOnOff.setImageResource(R.drawable.soundon);
        }else{
            imageView_Setting_SoundOnOff.setImageResource(R.drawable.soundoff);
        }

        checkVibrationOnOff = sharedPForAdvAndTotalQuestionCounter.getCheckVibrationForOnOff(this);
        if(checkVibrationOnOff == 1){
            imageView_Setting_VibrationOnOff.setImageResource(R.drawable.vibrationon);
        }else{
            imageView_Setting_VibrationOnOff.setImageResource(R.drawable.vibrationoff);
        }

        checkOnOffPause = 0;
    }

    private void setButtonClickEnable(){
        imageView_MusicOnOff.setEnabled(false);
        imageView_Credits.setEnabled(false);
        imageView_ChangeLanguage.setEnabled(false);
    }

    private void checkLanguage() {
        sharedPreferences = this.getSharedPreferences("com.serkanemek.garrybarry.Activities" , Context.MODE_PRIVATE);
        languageInteger = sharedPreferences.getInt("storedLanguage",0);
        if(languageInteger == 1){
            imageView_ChangeLanguage.setImageResource(R.drawable.changelanguageimagelogo);
        }else if(languageInteger == 2){
            imageView_ChangeLanguage.setImageResource(R.drawable.dildegistirimagelogo);
        }else if(languageInteger == 3){
            imageView_ChangeLanguage.setImageResource(R.drawable.changelanguageimagelogorussian);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) { //Geri tuşu basıldığında ne olacağı burada yazıyoruz.
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
            setButtonClickEnable();
            checkOnOffPause = 1;
            Intent intent = new Intent(getApplicationContext(), MainMenuActivity1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void buttonCheckMusic(View view){
        myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
        vibration.getVibrator(75,this);
        Integer integer = sharedPForAdvAndTotalQuestionCounter.getCheckMusicForOnOff(this);
        if(integer == 1){
            sharedPForAdvAndTotalQuestionCounter.updateCheckMusicForOnOff(this, 0);
            myMediaPlayerForMainMenu.pauseBackgroundMusic();
            imageView_MusicOnOff.setImageResource(R.drawable.musiciconoff);
        }else{
            sharedPForAdvAndTotalQuestionCounter.updateCheckMusicForOnOff(this,1);
            myMediaPlayerForMainMenu.playBackgroundMusic(this);
            imageView_MusicOnOff.setImageResource(R.drawable.musiciconon);
        }
    }

    public void buttonCheckSound(View view){
        myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
        vibration.getVibrator(75,this);
        Integer integer = sharedPForAdvAndTotalQuestionCounter.getCheckSoundForOnOff(this);
        if(integer == 1){
            sharedPForAdvAndTotalQuestionCounter.updateCheckSoundForOnOff(this, 0);
            imageView_Setting_SoundOnOff.setImageResource(R.drawable.soundoff);
        }else{
            sharedPForAdvAndTotalQuestionCounter.updateCheckSoundForOnOff(this, 1);
            imageView_Setting_SoundOnOff.setImageResource(R.drawable.soundon);
            myMediaPlayerForCorrectAnswer.playCorrectAnswerSound(getApplicationContext());
        }
    }

    public void vibration_Setting(View view){
        myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
        Integer integer = sharedPForAdvAndTotalQuestionCounter.getCheckVibrationForOnOff(this);
        if(integer == 1){
            sharedPForAdvAndTotalQuestionCounter.updateCheckVibrationForOnOff(this, 0);
            imageView_Setting_VibrationOnOff.setImageResource(R.drawable.vibrationoff);
        }else {
            sharedPForAdvAndTotalQuestionCounter.updateCheckVibrationForOnOff(this, 1);
            imageView_Setting_VibrationOnOff.setImageResource(R.drawable.vibrationon);
        }
        vibration.getVibrator(200,this);
    }

    public void home_Setting_icon(View view){
        myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
        checkOnOffPause = 1;
        setButtonClickEnable();
        vibration.getVibrator(75,this);
        Intent intentToMainMenu = new Intent(getApplicationContext(), MainMenuActivity1.class);
        intentToMainMenu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentToMainMenu);
        finish();
    }

    public void changeLanguage_Setting(View view){
        setButtonClickEnable();
        myMediaPlayerForMainMenu.stopBackgroundMusic();
        myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
        languageInteger = 0;
        sharedPreferences.edit().putInt("storedLanguage",languageInteger).apply();
        imageView_ChangeLanguage.setColorFilter(FILTERED_GREY);
        Intent intentToMainActivity = new Intent(this,MainActivity.class);
        intentToMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentToMainActivity);
        finish();
        vibration.getVibrator(75,this);
    }

    public void credits_Setting(View view){
        myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
        checkOnOffPause = 1;
        setButtonClickEnable();
        vibration.getVibrator(75,this);
        imageView_Credits.setColorFilter(FILTERED_GREY);
        Intent intentToCredits = new Intent(getApplicationContext(),CreditsActivity.class);
        intentToCredits.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentToCredits);
        finish();

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
        myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
        if(checkOnOffPause == 0){
            myMediaPlayerForMainMenu.pauseBackgroundMusic();
        }
    }
}