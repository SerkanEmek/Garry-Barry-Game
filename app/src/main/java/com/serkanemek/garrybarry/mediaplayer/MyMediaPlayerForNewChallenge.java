package com.serkanemek.garrybarry.mediaplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.serkanemek.garrybarry.R;

public class MyMediaPlayerForNewChallenge extends Service {

    static MediaPlayer mediaPlayer;

    Integer maxVolume = 50;
    Integer currVolume = 25;
    float log1 = (float) (Math.log(maxVolume - currVolume) / Math.log(maxVolume));


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
       try {
           if (mediaPlayer == null) {
               mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.summer_house);
           }
           mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
               @Override
               public void onCompletion(MediaPlayer mediaPlayer) {
                   mediaPlayer.stop();
               }
           });

           mediaPlayer.setVolume(log1, log1);
           mediaPlayer.setLooping(true);

       }catch (Exception e){
           e.printStackTrace();
       }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopBackgroundMusic();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        if (intent.getAction().equals("PLAY")) {
            mediaPlayer.start();
        }
        if (intent.getAction().equals("PAUSE")) {
            pauseBackgroundMusic();
        }

        return super.onStartCommand(intent, flags, startId);
    }


    public void pauseBackgroundMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void stopBackgroundMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}