package com.serkanemek.garrybarry.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.serkanemek.garrybarry.R;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForNewChallenge;
import com.serkanemek.garrybarry.data.SharedPForAdvAndTotalQuestionCounter;

public class NewEpisodeActivity extends AppCompatActivity {

    MyMediaPlayerForNewChallenge myMediaPlayerForNewChallenge;
    SharedPForAdvAndTotalQuestionCounter sharedPForAdvAndTotalQuestionCounter;
    Integer checkMusicOnOff;

    private AdView mAdView;

    //NEw episode Banner TEST id: ca-app-pub-3940256099942544/6300978111


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_new_episode);

        getAdvertisement();

        sharedPForAdvAndTotalQuestionCounter = new SharedPForAdvAndTotalQuestionCounter();
        checkMusicOnOff = sharedPForAdvAndTotalQuestionCounter.getCheckMusicForOnOff(this);



    }

    private void getAdvertisement() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adViewNewChallenge);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(checkMusicOnOff == 1){
            String action = "PLAY";
            Intent myService = new Intent(getApplicationContext(), MyMediaPlayerForNewChallenge.class);
            myService.setAction(action);
            startService(myService);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(checkMusicOnOff == 1){

            String action = "PAUSE";
            Intent myService = new Intent(getApplicationContext(), MyMediaPlayerForNewChallenge.class);
            myService.setAction(action);
            startService(myService);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(checkMusicOnOff == 1){
            Intent intent = new Intent(getApplicationContext(),MyMediaPlayerForNewChallenge.class);
            stopService(intent);
        }


    }

}
