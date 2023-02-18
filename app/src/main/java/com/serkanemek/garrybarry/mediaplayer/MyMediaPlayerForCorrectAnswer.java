package com.serkanemek.garrybarry.mediaplayer;

import android.content.Context;
import android.media.MediaPlayer;

import com.serkanemek.garrybarry.R;
import com.serkanemek.garrybarry.data.SharedPForAdvAndTotalQuestionCounter;


public class MyMediaPlayerForCorrectAnswer {

    MediaPlayer mediaPlayer;
    SharedPForAdvAndTotalQuestionCounter sharedPForAdvAndTotalQuestionCounter;

    public void playCorrectAnswerSound(Context context){
        try {
            Integer i;
            sharedPForAdvAndTotalQuestionCounter = new SharedPForAdvAndTotalQuestionCounter();
            i = sharedPForAdvAndTotalQuestionCounter.getCheckSoundForOnOff(context);
            if(i == 1){
                if(mediaPlayer == null){
                    mediaPlayer = MediaPlayer.create(context, R.raw.correctanswer);

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            stopCorrectAnswerSound();
                        }
                    });
                }
                mediaPlayer.start();
            }else{
                mediaPlayer = null;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void pauseCorrectAnswerSound(){
        if(mediaPlayer != null){
            mediaPlayer.pause();
        }
    }

    public void stopCorrectAnswerSound(){
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


}
