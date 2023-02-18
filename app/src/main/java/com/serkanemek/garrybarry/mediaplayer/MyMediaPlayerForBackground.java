package com.serkanemek.garrybarry.mediaplayer;

import android.content.Context;
import android.media.MediaPlayer;

import com.serkanemek.garrybarry.R;

public class MyMediaPlayerForBackground {

   static  MediaPlayer mediaPlayer;


    Integer maxVolume = 50;
    Integer currVolume = 25;
    float log1=(float)(Math.log(maxVolume-currVolume)/Math.log(maxVolume));



    public void playBackgroundMusic(Context context, Integer gameNumber){
        // 1-MemoryGame & 2-iqGame & 3-newChallenge & 4-MathGame & 5-MathPractice

        try {

            if(mediaPlayer == null){
                if(gameNumber == 1){
                    mediaPlayer = MediaPlayer.create(context, R.raw.creation);
                }else if(gameNumber == 2){
                    mediaPlayer = MediaPlayer.create(context, R.raw.little_prankster);
                }else if(gameNumber == 3){
                    mediaPlayer = MediaPlayer.create(context, R.raw.summer_house);
                }else if(gameNumber == 4){
                    mediaPlayer = MediaPlayer.create(context, R.raw.feeling_good);
                }else if(gameNumber == 5){
                    mediaPlayer = MediaPlayer.create(context, R.raw.christmas_fever);
                }
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        stopBackgroundMusic();
                    }
                });
            }
            mediaPlayer.setVolume(log1,log1);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void pauseBackgroundMusic(){
        if(mediaPlayer != null){
            mediaPlayer.pause();
        }
    }

    public void stopBackgroundMusic(){
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}
