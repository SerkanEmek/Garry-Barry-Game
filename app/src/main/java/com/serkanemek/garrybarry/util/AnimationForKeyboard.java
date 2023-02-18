package com.serkanemek.garrybarry.util;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.serkanemek.garrybarry.R;

public class AnimationForKeyboard extends Animation {

    Animation animation;
    Animation animation2;
    private ObjectAnimator anim;
    private ObjectAnimator anim2;

    public void animationForKeyboardInitializing(Context context, Integer integer, String string, ImageView imageView) {

        if(integer == 300){
            if(string.matches("left")){
                animation = AnimationUtils.loadAnimation(context, R.anim.push_left_in300);
            }else if(string.matches("right")){
                animation = AnimationUtils.loadAnimation(context,R.anim.push_right_in300);
            }
        }else if(integer == 500){
            if(string.matches("left")){
                animation = AnimationUtils.loadAnimation(context, R.anim.push_left_in500);
            }else if(string.matches("right")){
                animation = AnimationUtils.loadAnimation(context, R.anim.push_right_in500);
            }
        }else if(integer == 800){
            if(string.matches("fadein")){
                animation = AnimationUtils.loadAnimation(context,R.anim.fadein800);
            }else if(string.matches("rotation")){
                animation = AnimationUtils.loadAnimation(context,R.anim.rotation);
            }
        }

        imageView.startAnimation(animation);
    }

    public void animationForKeyboardInitializing(Context context, Integer integer, String string, TextView textView) {

        if (integer == 800) {
            if (string.matches("push_down_in")) {
                animation = AnimationUtils.loadAnimation(context, R.anim.push_down_in);
            }

            textView.startAnimation(animation);
        }
    }

    public void animationForKeyboardInitializingforRotationForChangeButton(Context context, Integer integer, String string, ImageView imageView) {

        if (integer == 800) {
            if (string.matches("rotation")) {
                anim = ObjectAnimator.ofFloat(imageView, "rotation", 0, 360);
                anim.setDuration(25000);
                anim.setRepeatCount(15);
                anim.setRepeatMode(ObjectAnimator.RESTART);
            }

            anim.start();
        }
    }

    public void animationForKeyboardInitializingforRotationForHomeButton(Context context, Integer integer, String string, ImageView imageView) {

        if (integer == 800) {
            if (string.matches("rotation")) {
                anim2 = ObjectAnimator.ofFloat(imageView, "rotation", 0, 360);
                anim2.setDuration(25000);
                anim2.setRepeatCount(15);
                anim2.setRepeatMode(ObjectAnimator.RESTART);
            }

            anim2.start();
        }
    }


    public void cancelAnimation(){
        anim.end();
        anim2.end();
    }

    public void pauseAnimation(){
        anim.pause();
        anim2.pause();
    }

    public void resumeAnimation(){
        anim.resume();
        anim2.resume();
    }

}
