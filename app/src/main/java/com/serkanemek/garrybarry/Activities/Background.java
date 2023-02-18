package com.serkanemek.garrybarry.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.serkanemek.garrybarry.R;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForMainMenu;
import com.serkanemek.garrybarry.data.SharedPForAdvAndTotalQuestionCounter;

public class Background extends AppCompatActivity {

    MyMediaPlayerForMainMenu myMediaPlayerForMainMenu;
    Integer checkOnOffPause;

    SharedPForAdvAndTotalQuestionCounter sharedPForAdvAndTotalQuestionCounter;
    Integer checkMusicOnOff;

    private AdView mAdView;

    //Setting Statistic Background Banner TEST id: ca-app-pub-3940256099942544/6300978111

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_background);

        getAdvertisement();

        sharedPForAdvAndTotalQuestionCounter = new SharedPForAdvAndTotalQuestionCounter();
        checkMusicOnOff = sharedPForAdvAndTotalQuestionCounter.getCheckMusicForOnOff(this);

        myMediaPlayerForMainMenu = new MyMediaPlayerForMainMenu();
        checkOnOffPause = 0;

    }

    private void getAdvertisement() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adViewBackground);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
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