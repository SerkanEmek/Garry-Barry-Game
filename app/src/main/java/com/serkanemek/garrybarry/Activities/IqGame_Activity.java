package com.serkanemek.garrybarry.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


import com.serkanemek.garrybarry.R;
import com.serkanemek.garrybarry.util.AnimationForKeyboard;
import com.serkanemek.garrybarry.operations.IqGame;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForBackground;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForCorrectAnswer;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForLast5Min;
import com.serkanemek.garrybarry.data.SharedPForAdvAndTotalQuestionCounter;
import com.serkanemek.garrybarry.data.SharedPForBackgrounds;
import com.serkanemek.garrybarry.data.SharedPForCoins;
import com.serkanemek.garrybarry.util.Vibration;

import java.util.Random;

public class IqGame_Activity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    TextView textView_Number1,textView_Number2,textView_Number3,textView_Number4,textView_Number5,textView_Number6
            ,textView_Number7,textView_Number8,textView_Number9,textView_Number10,textView_Number11,textView_Number12;

    ImageView imageView_keyboard_ForIqGame1,imageView_keyboard_ForIqGame2,imageView_keyboard_ForIqGame3,
            imageView_keyboard_ForIqGame4,imageView_keyboard_ForIqGame5,imageView_keyboard_ForIqGame6,
            imageView_keyboard_ForIqGame7,imageView_keyboard_ForIqGame8,imageView_keyboard_ForIqGame9,
            imageView_keyboard_ForIqGame0,imageView_keyboard_ForIqGameback,imageView_keyboard_ForIqGameok;

    ImageView imageView_For_IqGame_ChangeButton,imageView_For_IqGame_HomeButton;

    TextView textView_ForIqGame_Result;
    CountDownTimer countDownTimer;
    Random randomNumber;
    IqGame iqGame;
    Integer combination, level, result;
    TextView textView_timer;

    Integer numberForResult;

    SharedPreferences sharedPreferences; //For Language
    Integer languageInteger;

    TextView textView_IqGame_For_Coins;
    SharedPForCoins sharedPForEnergy;
    Integer checkForCoins;

    Random rnd;
    Integer randomNumberForAlertDialog;

    SharedPForBackgrounds sharedPForBackgrounds;
    SharedPForAdvAndTotalQuestionCounter sharedPForAdvAndTotalQuestionCounter;

    static Toast toastForWrongAnswers,toastForNewQuestions,toastForChangeButton;

    MyMediaPlayerForBackground myMediaPlayerForBackground;
    MyMediaPlayerForCorrectAnswer myMediaPlayerForCorrectAnswer;
    MyMediaPlayerForLast5Min myMediaPlayerForLast5Min;
    Integer checkMusicOnOff;

    AnimationForKeyboard animationForKeyboard;
    int checkAnimationIsItWork;//When startingimage showing if we'll go back to desktop its making broke the program. So for fixing this we are checking.

    Boolean checkOnPause;

    Vibration vibration;

    private AdView mAdView;

    //IqGameBanner TEST id: ca-app-pub-3940256099942544/6300978111

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_iqgame);
        checkBackground();
        vibration = new Vibration();

        getAdvertisement();

        sharedPreferences = this.getSharedPreferences("com.serkanemek.garrybarry.Activities" , Context.MODE_PRIVATE);
        languageInteger = sharedPreferences.getInt("storedLanguage",0);

        toastMessageForWrongAnswers();
        toastMessageForNewQuestions();
        toastMessageForChangeQuestion();


        dialogForSolutionExample();

        sharedPForAdvAndTotalQuestionCounter = new SharedPForAdvAndTotalQuestionCounter();
        myMediaPlayerForBackground = new MyMediaPlayerForBackground();
        myMediaPlayerForCorrectAnswer = new MyMediaPlayerForCorrectAnswer();
        myMediaPlayerForLast5Min = new MyMediaPlayerForLast5Min();
        checkMusicOnOff = sharedPForAdvAndTotalQuestionCounter.getCheckMusicForOnOff(this);

        imageView_For_IqGame_ChangeButton = findViewById(R.id.imageView_For_IqGame_ChangeButton);
        imageView_For_IqGame_HomeButton = findViewById(R.id.imageView_For_IqGame_HomeButton);

    }

    private void getAdvertisement() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adViewIqGameActivity);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void checkBackground() {
        sharedPForBackgrounds = new SharedPForBackgrounds();
        constraintLayout = findViewById(R.id.background_IqGame);
        Integer numberOfBackground = -1;
        numberOfBackground =  sharedPForBackgrounds.getIqGameBackground(getApplicationContext());
        if(numberOfBackground == 1){
            constraintLayout.setBackgroundResource(R.drawable.bg_iq_01);
        }else if(numberOfBackground == 2){
            constraintLayout.setBackgroundResource(R.drawable.bg_iq_02);
        }else if(numberOfBackground == 3){
            constraintLayout.setBackgroundResource(R.drawable.bg_iq_03);
        }else if(numberOfBackground == 4){
            constraintLayout.setBackgroundResource(R.drawable.bg_darkmood);
        }

    }

    private void startIqGame(){
        initializing();
        iqGame = new IqGame(combination,level);
        checkCombinations();
        setFonts();
        timer();
        keyboards();

    }

    private void timer(){

        try {

            final AlertDialog.Builder builderforTimer =new AlertDialog.Builder(this);

            countDownTimer =   new CountDownTimer(25_000, 1_000) {
                @Override
                public void onTick(long l) {
                    imageView_keyboard_ForIqGameok.setEnabled(true);
                    if(languageInteger == 1){
                       // textView_timer.setText("Time: " + (l/1000));
                        textView_timer.setText("" + (l/1000));
                    } else if(languageInteger == 2){
                       // textView_timer.setText("Süre: " + (l/1000));
                        textView_timer.setText("" + (l/1000));
                    } else if(languageInteger == 3){
                      //  textView_timer.setText("Время " + (l/1000) );
                        textView_timer.setText("" + (l/1000) );
                    }

                    setTimerForOnTick(l);
                }

                @Override
                public void onFinish() {
                    myMediaPlayerForLast5Min.stopLast5Min();
                    vibration.getVibrator(75,getApplicationContext());
                    textView_ForIqGame_Result.setText("");

                    sharedPForAdvAndTotalQuestionCounter.updateIqGameTotalQuestionData(getApplicationContext(),1);

                    checkForCoins = sharedPForEnergy.getCoinsData(getApplicationContext());
                    if(checkForCoins > 2){
                        sharedPForEnergy.updateCoinsData(getApplicationContext(), -2);
                    }

                    textView_IqGame_For_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
                    if(languageInteger == 1){
                        builderforTimer.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Time</b></font>")).setCancelable(false);
                        builderforTimer.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>You are late! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Please try again</b></font>"));
                        builderforTimer.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                toastForNewQuestions.show();
                                startIqGame();
                            }
                        });


                    } else if(languageInteger == 2){
                        builderforTimer.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Süre</b></font>")).setCancelable(false);
                        builderforTimer.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Geç Kaldın! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Yeniden Deneyiniz</b></font>"));
                        builderforTimer.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                toastForNewQuestions.show();
                                startIqGame();
                            }
                        });


                    } else if(languageInteger == 3){
                        builderforTimer.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Время</b></font>")).setCancelable(false);
                        builderforTimer.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Ты опоздал! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Попробуй еще раз</b></font>"));
                        builderforTimer.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                toastForNewQuestions.show();
                                startIqGame();
                            }
                        });

                    }
                    AlertDialog dialog = builderforTimer.create();
                    dialog.show();

                    if(randomNumberForAlertDialog == 0){
                        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_next_4);
                    }else if(randomNumberForAlertDialog == 1){
                        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_next_5);
                    }else if(randomNumberForAlertDialog == 2){
                        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_next_6);
                    }else {
                        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_next_5);
                    }
                }
            };
            countDownTimer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getAnimationEffect() {
        checkAnimationIsItWork = 1;
        animationForKeyboard = new AnimationForKeyboard();
        animationForKeyboard.animationForKeyboardInitializingforRotationForHomeButton(getApplicationContext(),800,"rotation",imageView_For_IqGame_HomeButton);
        animationForKeyboard.animationForKeyboardInitializingforRotationForChangeButton(getApplicationContext(),800,"rotation",imageView_For_IqGame_ChangeButton);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard_ForIqGame2);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard_ForIqGame7);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard_ForIqGame4);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard_ForIqGame9);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard_ForIqGame1);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard_ForIqGame6);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard_ForIqGame5);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard_ForIqGame0);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard_ForIqGameback);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard_ForIqGameok);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard_ForIqGame3);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard_ForIqGame8);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"push_down_in",textView_ForIqGame_Result);
    }

    private void setTimerForOnTick(Long l) {
        if (l / 1_000 == 5) {
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);//PowerManager and this method we just check screen on/off
            boolean screenOn;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
                screenOn = pm.isInteractive();
            } else {
                screenOn = pm.isScreenOn();
            }

            if (screenOn) {
                if(!checkOnPause){
                    myMediaPlayerForLast5Min.playLast5Min(getApplicationContext());
                }

            }

        }
    }

    private void checkCombinations() {
        if(combination == 1){
            textView_Number1.setText(iqGame.getNumber1() + "");
            textView_Number2.setText(iqGame.getNumber2() + "");
            textView_Number3.setText("?");
            textView_Number4.setAlpha(0.0f);
            textView_Number5.setText(iqGame.getNumber5() + "");
            textView_Number6.setText(iqGame.getNumber6() + "");
            textView_Number7.setText(iqGame.getNumber7() + "");
            textView_Number8.setAlpha(0.0f);
            textView_Number9.setAlpha(0.0f);
            textView_Number10.setAlpha(0.0f);
            textView_Number11.setAlpha(0.0f);
            textView_Number12.setAlpha(0.0f);
            result = iqGame.getResult();
        }
        if(combination == 2){
            textView_Number1.setText(iqGame.getNumber1() + "");
            textView_Number2.setText(iqGame.getNumber2() + "");
            textView_Number3.setText(iqGame.getNumber3() + "");
            textView_Number4.setAlpha(0.0f);
            textView_Number5.setText(iqGame.getNumber5() + "");
            textView_Number6.setText(iqGame.getNumber6() + "");
            textView_Number7.setText("?");
            textView_Number8.setAlpha(0.0f);
            textView_Number9.setAlpha(0.0f);
            textView_Number10.setAlpha(0.0f);
            textView_Number11.setAlpha(0.0f);
            textView_Number12.setAlpha(0.0f);
            result = iqGame.getResult();
        }
        if(combination == 3){
            textView_Number1.setText("?");
            textView_Number2.setText(iqGame.getNumber2() + "");
            textView_Number3.setText(iqGame.getNumber3() + "");
            textView_Number4.setAlpha(0.0f);
            textView_Number5.setText(iqGame.getNumber5() + "");
            textView_Number6.setText(iqGame.getNumber6() + "");
            textView_Number7.setText(iqGame.getNumber7() + "");
            textView_Number8.setAlpha(0.0f);
            textView_Number9.setAlpha(0.0f);
            textView_Number10.setAlpha(0.0f);
            textView_Number11.setAlpha(0.0f);
            textView_Number12.setAlpha(0.0f);
            result = iqGame.getResult();
        }
        if(combination == 4){
            textView_Number1.setText(iqGame.getNumber1() + "");
            textView_Number2.setText(iqGame.getNumber2() + "");
            textView_Number3.setText(iqGame.getNumber3() + "");
            textView_Number4.setText("?");
            textView_Number5.setText(iqGame.getNumber5() + "");
            textView_Number6.setText(iqGame.getNumber6() + "");
            textView_Number7.setText(iqGame.getNumber7() + "");
            textView_Number8.setText(iqGame.getNumber8() + "");
            textView_Number9.setAlpha(0.0f);
            textView_Number10.setAlpha(0.0f);
            textView_Number11.setAlpha(0.0f);
            textView_Number12.setAlpha(0.0f);
            result = iqGame.getResult();

        }
        if(combination == 5){
            textView_Number1.setText(iqGame.getNumber1() + "");
            textView_Number2.setText(iqGame.getNumber2() + "");
            textView_Number3.setText(iqGame.getNumber3() + "");
            textView_Number4.setText(iqGame.getNumber4() + "");
            textView_Number5.setText(iqGame.getNumber5() + "");
            textView_Number6.setText(iqGame.getNumber6() + "");
            textView_Number7.setText(iqGame.getNumber7() + "");
            textView_Number8.setText("?");
            textView_Number9.setAlpha(0.0f);
            textView_Number10.setAlpha(0.0f);
            textView_Number11.setAlpha(0.0f);
            textView_Number12.setAlpha(0.0f);
            result = iqGame.getResult();

        }
        if(combination == 6){
            textView_Number1.setText(iqGame.getNumber1() + "");
            textView_Number2.setText(iqGame.getNumber2() + "");
            textView_Number3.setText(iqGame.getNumber3() + "");
            textView_Number4.setText(iqGame.getNumber4() + "");
            textView_Number5.setText("?");
            textView_Number6.setText(iqGame.getNumber6() + "");
            textView_Number7.setText(iqGame.getNumber7() + "");
            textView_Number8.setText(iqGame.getNumber8() + "");
            textView_Number9.setAlpha(0.0f);
            textView_Number10.setAlpha(0.0f);
            textView_Number11.setAlpha(0.0f);
            textView_Number12.setAlpha(0.0f);
            result = iqGame.getResult();
        }
        if(combination == 7){
            textView_Number1.setText(iqGame.getNumber1() + "");
            textView_Number2.setText(iqGame.getNumber2() + "");
            textView_Number3.setText(iqGame.getNumber3() + "");
            textView_Number4.setAlpha(0.0f);
            textView_Number5.setText(iqGame.getNumber5() + "");
            textView_Number6.setText(iqGame.getNumber6() + "");
            textView_Number7.setText(iqGame.getNumber7() + "");
            textView_Number8.setAlpha(0.0f);
            textView_Number9.setText("?");
            textView_Number10.setText(iqGame.getNumber10() + "");
            textView_Number11.setText(iqGame.getNumber11() + "");
            textView_Number12.setAlpha(0.0f);
            result = iqGame.getResult();
        }
        if(combination == 8){
            textView_Number1.setText(iqGame.getNumber1() + "");
            textView_Number2.setText(iqGame.getNumber2() + "");
            textView_Number3.setText(iqGame.getNumber3() + "");
            textView_Number4.setAlpha(0.0f);
            textView_Number5.setText(iqGame.getNumber5() + "");
            textView_Number6.setText("?");
            textView_Number7.setText(iqGame.getNumber7() + "");
            textView_Number8.setAlpha(0.0f);
            textView_Number9.setText(iqGame.getNumber9() + "");
            textView_Number10.setText(iqGame.getNumber10() + "");
            textView_Number11.setText(iqGame.getNumber11() + "");
            textView_Number12.setAlpha(0.0f);
            result = iqGame.getResult();
        }
        if(combination == 9){
            textView_Number1.setText(iqGame.getNumber1() + "");
            textView_Number2.setText(iqGame.getNumber2() + "");
            textView_Number3.setText(iqGame.getNumber3() + "");
            textView_Number4.setAlpha(0.0f);
            textView_Number5.setText("?");
            textView_Number6.setText(iqGame.getNumber6() + "");
            textView_Number7.setText(iqGame.getNumber7() + "");
            textView_Number8.setAlpha(0.0f);
            textView_Number9.setText(iqGame.getNumber9() + "");
            textView_Number10.setText(iqGame.getNumber10() + "");
            textView_Number11.setText(iqGame.getNumber11() + "");
            textView_Number12.setAlpha(0.0f);
            result = iqGame.getResult();
        }
        if(combination == 10){
            textView_Number1.setText(iqGame.getNumber1() + "");
            textView_Number2.setText(iqGame.getNumber2() + "");
            textView_Number3.setText(iqGame.getNumber3() + "");
            textView_Number4.setText(iqGame.getNumber4() + "");
            textView_Number5.setText(iqGame.getNumber5() + "");
            textView_Number6.setText(iqGame.getNumber6() + "");
            textView_Number7.setText(iqGame.getNumber7() + "");
            textView_Number8.setText("?");
            textView_Number9.setText(iqGame.getNumber9() + "");
            textView_Number10.setText(iqGame.getNumber10() + "");
            textView_Number11.setText(iqGame.getNumber11() + "");
            textView_Number12.setText(iqGame.getNumber12() + "");
            result = iqGame.getResult();
        }
    }

    private void initializing() {
        textView_Number1 = findViewById(R.id.textView_Number1);
        textView_Number2 = findViewById(R.id.textView_Number2);
        textView_Number3 = findViewById(R.id.textView_Number3);
        textView_Number4 = findViewById(R.id.textView_Number4);
        textView_Number5 = findViewById(R.id.textView_Number5);
        textView_Number6 = findViewById(R.id.textView_Number6);
        textView_Number7 = findViewById(R.id.textView_Number7);
        textView_Number8 = findViewById(R.id.textView_Number8);
        textView_Number9 = findViewById(R.id.textView_Number9);
        textView_Number10 = findViewById(R.id.textView_Number10);
        textView_Number11 = findViewById(R.id.textView_Number11);
        textView_Number12 = findViewById(R.id.textView_Number12);

        textView_Number1.setAlpha(1.0f);
        textView_Number2.setAlpha(1.0f);
        textView_Number3.setAlpha(1.0f);
        textView_Number4.setAlpha(1.0f);
        textView_Number5.setAlpha(1.0f);
        textView_Number6.setAlpha(1.0f);
        textView_Number7.setAlpha(1.0f);
        textView_Number8.setAlpha(1.0f);
        textView_Number9.setAlpha(1.0f);
        textView_Number10.setAlpha(1.0f);
        textView_Number11.setAlpha(1.0f);
        textView_Number12.setAlpha(1.0f);

        imageView_keyboard_ForIqGame1 = findViewById(R.id.imageView_keyboard_ForIqGame1);
        imageView_keyboard_ForIqGame2 = findViewById(R.id.imageView_keyboard_ForIqGame2);
        imageView_keyboard_ForIqGame3 = findViewById(R.id.imageView_keyboard_ForIqGame3);
        imageView_keyboard_ForIqGame4 = findViewById(R.id.imageView_keyboard_ForIqGame4);
        imageView_keyboard_ForIqGame5 = findViewById(R.id.imageView_keyboard_ForIqGame5);
        imageView_keyboard_ForIqGame6 = findViewById(R.id.imageView_keyboard_ForIqGame6);
        imageView_keyboard_ForIqGame7 = findViewById(R.id.imageView_keyboard_ForIqGame7);
        imageView_keyboard_ForIqGame8 = findViewById(R.id.imageView_keyboard_ForIqGame8);
        imageView_keyboard_ForIqGame9 = findViewById(R.id.imageView_keyboard_ForIqGame9);
        imageView_keyboard_ForIqGame0 = findViewById(R.id.imageView_keyboard_ForIqGame0);
        imageView_keyboard_ForIqGameback = findViewById(R.id.imageView_keyboard_ForIqGameback);
        imageView_keyboard_ForIqGameok = findViewById(R.id.imageView_keyboard_ForIqGameok);

        checkOnPause = false;
        textView_ForIqGame_Result = findViewById(R.id.textView_ForIqGame_Result);
        randomNumber = new Random();
        combination = randomNumber.nextInt(10) + 1;
        level = randomNumber.nextInt(5) + 1;
        result = -1;
        textView_timer = findViewById(R.id.textView_timer);
        numberForResult = -1;

        rnd = new Random();
        randomNumberForAlertDialog = rnd.nextInt(4);

        textView_IqGame_For_Coins = findViewById(R.id.textView_IqGame_For_Coins);
        sharedPForEnergy = new SharedPForCoins();
        textView_IqGame_For_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
        checkForCoins = 0;

        toastMessageForWrongAnswers();
        toastMessageForNewQuestions();
        toastMessageForChangeQuestion();


    }

    private void finalstate(View view){

        try {

            sharedPForAdvAndTotalQuestionCounter.updateIqGameTotalQuestionData(getApplicationContext(),1);

            AlertDialog.Builder builder =new AlertDialog.Builder(this);

            if(result == Integer.parseInt(textView_ForIqGame_Result.getText().toString())){
                myMediaPlayerForLast5Min.stopLast5Min();
                myMediaPlayerForCorrectAnswer.playCorrectAnswerSound(getApplicationContext());
                vibration.getVibrator(75,this);
                countDownTimer.cancel();
                imageView_keyboard_ForIqGameok.setEnabled(false);

                sharedPForEnergy.updateCoinsData(getApplicationContext(),5);
                textView_IqGame_For_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));

                sharedPForAdvAndTotalQuestionCounter.updateIqGameCorrectAnswersData(getApplicationContext(),1);
                sharedPForAdvAndTotalQuestionCounter.updateAdvertisementCounterData(getApplicationContext(),1);

                if(languageInteger == 1){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>RESULT</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Correct answer <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Congratulations</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            toastForNewQuestions.show();
                            startIqGame();
                            textView_ForIqGame_Result.setText("");

                        }
                    });


                } else if(languageInteger == 2){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>SONUÇ</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Doğru Cevap <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tebrikler</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            toastForNewQuestions.show();
                            startIqGame();
                            textView_ForIqGame_Result.setText("");

                        }
                    });


                }else if(languageInteger == 3){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Результат</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Правильный ответ <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Поздравления</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            toastForNewQuestions.show();
                            startIqGame();
                            textView_ForIqGame_Result.setText("");
                        }
                    });

                }
                AlertDialog dialog = builder.create();
                dialog.show();

                if(randomNumberForAlertDialog == 0){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_next_4);
                }else if(randomNumberForAlertDialog ==1){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_next_5);
                }else if(randomNumberForAlertDialog ==2){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_next_6);
                }else if(randomNumberForAlertDialog ==3){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_next_4_1);
                }


            } else { //if it's wrong answer
                vibration.getVibrator(150,this);
                checkForCoins = sharedPForEnergy.getCoinsData(getApplicationContext());
                if(checkForCoins > 2){
                    sharedPForEnergy.updateCoinsData(getApplicationContext(), -2);
                }

                textView_IqGame_For_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
                toastForWrongAnswers.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        }

    private void dialogForSolutionExample(){
        checkAnimationIsItWork = 0;
        try {

            Random randomNumber = new Random();
            Integer randomForDialog = randomNumber.nextInt(3);

            if(randomForDialog == 0){
                AlertDialog.Builder builderForBegin =new AlertDialog.Builder(this);
                if(languageInteger == 1){
                    builderForBegin.setTitle(Html.fromHtml("<font color='#FF8B00'><b>How to Solve?</b></font>")).setCancelable(false);
                    builderForBegin.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>          </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startIqGame();
                            getAnimationEffect();

                        }
                    });
                    AlertDialog dialogForBegin = builderForBegin.create();
                    dialogForBegin.show();
                    dialogForBegin.getWindow().setBackgroundDrawableResource(R.drawable.iqgameexample_0);

                }else if(languageInteger == 2){
                    builderForBegin.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Nasıl Çözülür?</b></font>")).setCancelable(false);
                    builderForBegin.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>          </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startIqGame();
                            getAnimationEffect();

                        }
                    });
                    AlertDialog dialogForBegin = builderForBegin.create();
                    dialogForBegin.show();
                    dialogForBegin.getWindow().setBackgroundDrawableResource(R.drawable.iqgameexample_0);
                }else if(languageInteger == 3){
                    builderForBegin.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Как решать?</b></font>")).setCancelable(false);
                    builderForBegin.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>          </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startIqGame();
                            getAnimationEffect();

                        }
                    });
                    AlertDialog dialogForBegin = builderForBegin.create();
                    dialogForBegin.show();
                    dialogForBegin.getWindow().setBackgroundDrawableResource(R.drawable.iqgameexample_0);
                }


            }else if(randomForDialog == 1){
                AlertDialog.Builder builderForBegin =new AlertDialog.Builder(this);
                if(languageInteger == 1){
                    builderForBegin.setTitle(Html.fromHtml("<font color='#FF8B00'><b>How to Solve?</b></font>")).setCancelable(false);
                    builderForBegin.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>          </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startIqGame();
                            getAnimationEffect();

                        }
                    });
                    AlertDialog dialogForBegin = builderForBegin.create();
                    dialogForBegin.show();
                    dialogForBegin.getWindow().setBackgroundDrawableResource(R.drawable.iqgameexample_1);

                }else if(languageInteger == 2){
                    builderForBegin.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Nasıl Çözülür?</b></font>")).setCancelable(false);
                    builderForBegin.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>          </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startIqGame();
                            getAnimationEffect();

                        }
                    });
                    AlertDialog dialogForBegin = builderForBegin.create();
                    dialogForBegin.show();
                    dialogForBegin.getWindow().setBackgroundDrawableResource(R.drawable.iqgameexample_1);
                }else if(languageInteger == 3){
                    builderForBegin.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Как решать?</b></font>")).setCancelable(false);
                    builderForBegin.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>          </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startIqGame();
                            getAnimationEffect();

                        }
                    });
                    AlertDialog dialogForBegin = builderForBegin.create();
                    dialogForBegin.show();
                    dialogForBegin.getWindow().setBackgroundDrawableResource(R.drawable.iqgameexample_1);
                }


            }else if(randomForDialog == 2){
                AlertDialog.Builder builderForBegin =new AlertDialog.Builder(this);
                if(languageInteger == 1){
                    builderForBegin.setTitle(Html.fromHtml("<font color='#FF8B00'><b>How to Solve?</b></font>")).setCancelable(false);
                    builderForBegin.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>          </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startIqGame();
                            getAnimationEffect();

                        }
                    });
                    AlertDialog dialogForBegin = builderForBegin.create();
                    dialogForBegin.show();
                    dialogForBegin.getWindow().setBackgroundDrawableResource(R.drawable.iqgameexample_2);

                }else if(languageInteger == 2){
                    builderForBegin.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Nasıl Çözülür?</b></font>")).setCancelable(false);
                    builderForBegin.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>          </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startIqGame();
                            getAnimationEffect();

                        }
                    });
                    AlertDialog dialogForBegin = builderForBegin.create();
                    dialogForBegin.show();
                    dialogForBegin.getWindow().setBackgroundDrawableResource(R.drawable.iqgameexample_2);
                }else if(languageInteger == 3){
                    builderForBegin.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Как решать?</b></font>")).setCancelable(false);
                    builderForBegin.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>          </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startIqGame();
                            getAnimationEffect();

                        }
                    });
                    AlertDialog dialogForBegin = builderForBegin.create();
                    dialogForBegin.show();
                    dialogForBegin.getWindow().setBackgroundDrawableResource(R.drawable.iqgameexample_2);
                }

            }else{
                startIqGame();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void toastMessageForWrongAnswers(){
        toastForWrongAnswers = Toast.makeText(this,"Wrong Answer, Try Again",Toast.LENGTH_SHORT);
        if(languageInteger == 1){
            toastForWrongAnswers.setText("-2 Coins.. Wrong Answer, Try Again!");
        }else if(languageInteger == 2){
            toastForWrongAnswers.setText("-2 Coins.. Yanlış Cevap, Tekrar Deneyiniz!");
        }else if(languageInteger == 3){
            toastForWrongAnswers.setText("-2 Coins.. Неверный ответ, Попробуй еще раз!");
        }
    }

    private void toastMessageForNewQuestions(){

        toastForNewQuestions = Toast.makeText(this,"New Question",Toast.LENGTH_SHORT);
        if(languageInteger == 1){
            toastForNewQuestions.setText("New Question");
        }else if(languageInteger == 2){
            toastForNewQuestions.setText("Yeni Soru");
        }else if(languageInteger == 3){
            toastForNewQuestions.setText("Новый вопрос");
        }
    }

    private void toastMessageForChangeQuestion(){

        toastForChangeButton = Toast.makeText(this,"New Question",Toast.LENGTH_SHORT);
        if(languageInteger == 1){
            toastForChangeButton.setText("-1 Coins.. New Question");
        }else if(languageInteger == 2){
            toastForChangeButton.setText("-1 Coins.. Yeni Soru");
        }else if(languageInteger == 3){
            toastForChangeButton.setText("-1 Coins.. Новый вопрос");
        }
    }

    public void change_button(View view){
        myMediaPlayerForLast5Min.stopLast5Min();
        vibration.getVibrator(75,this);

        sharedPForAdvAndTotalQuestionCounter.updateIqGameTotalQuestionData(getApplicationContext(),1);

        checkForCoins = sharedPForEnergy.getCoinsData(getApplicationContext());
        if(checkForCoins > 1){
            sharedPForEnergy.updateCoinsData(getApplicationContext(), -1);
        }

        textView_IqGame_For_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
        toastForWrongAnswers.cancel();
        toastForNewQuestions.cancel();
        toastForChangeButton.cancel();
        countDownTimer.cancel();
        startIqGame();
        textView_ForIqGame_Result.setText("");
        toastForChangeButton.show();
    }

    private void setFonts(){
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/FredokaOne-Regular.ttf");
        textView_Number1.setTypeface(typeface);
        textView_Number2.setTypeface(typeface);
        textView_Number3.setTypeface(typeface);
        textView_Number4.setTypeface(typeface);
        textView_Number5.setTypeface(typeface);
        textView_Number6.setTypeface(typeface);
        textView_Number7.setTypeface(typeface);
        textView_Number8.setTypeface(typeface);
        textView_Number9.setTypeface(typeface);
        textView_Number10.setTypeface(typeface);
        textView_Number11.setTypeface(typeface);
        textView_Number12.setTypeface(typeface);
        textView_ForIqGame_Result.setTypeface(typeface);
        textView_timer.setTypeface(typeface);

        textView_IqGame_For_Coins.setTypeface(typeface);
    }

    public void homeicon(View view){
        animationForKeyboard.cancelAnimation();
        myMediaPlayerForBackground.stopBackgroundMusic();
        myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
        myMediaPlayerForLast5Min.stopLast5Min();
        vibration.getVibrator(75,this);
        countDownTimer.cancel();
        toastForNewQuestions.cancel();
        toastForWrongAnswers.cancel();
        toastForChangeButton.cancel();
        Intent intenttoMainMenu = new Intent(getApplicationContext(), MainMenuActivity2.class);
        intenttoMainMenu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intenttoMainMenu);
        finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) { //Geri tuşu basıldığında ne olacağı burada yazıyoruz.
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            animationForKeyboard.cancelAnimation();
            myMediaPlayerForBackground.stopBackgroundMusic();
            myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
            myMediaPlayerForLast5Min.stopLast5Min();
            countDownTimer.cancel();
            toastForNewQuestions.cancel();
            toastForWrongAnswers.cancel();
            toastForChangeButton.cancel();
            Intent intent = new Intent(IqGame_Activity.this, MainMenuActivity2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void keyboards(){
        imageView_keyboard_ForIqGame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 1;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 1;
                }

                textView_ForIqGame_Result.setText( numberForResult + "");
            }
        });
        imageView_keyboard_ForIqGame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 2;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 2;
                }

                textView_ForIqGame_Result.setText( numberForResult + "");
            }
        });
        imageView_keyboard_ForIqGame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 3;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 3;
                }

                textView_ForIqGame_Result.setText( numberForResult + "");
            }
        });
        imageView_keyboard_ForIqGame4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 4;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 4;
                }

                textView_ForIqGame_Result.setText( numberForResult + "");
            }
        });
        imageView_keyboard_ForIqGame5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 5;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 5;
                }

                textView_ForIqGame_Result.setText( numberForResult + "");
            }
        });
        imageView_keyboard_ForIqGame6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 6;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 6;
                }

                textView_ForIqGame_Result.setText( numberForResult + "");
            }
        });
        imageView_keyboard_ForIqGame7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 7;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 7;
                }

                textView_ForIqGame_Result.setText( numberForResult + "");
            }
        });
        imageView_keyboard_ForIqGame8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 8;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 8;
                }

                textView_ForIqGame_Result.setText( numberForResult + "");
            }
        });
        imageView_keyboard_ForIqGame9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 9;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 9;
                }

                textView_ForIqGame_Result.setText( numberForResult + "");
            }
        });
        imageView_keyboard_ForIqGame0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 0;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 0;
                }

                textView_ForIqGame_Result.setText( numberForResult + "");
            }
        });
        imageView_keyboard_ForIqGameback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult > 9){
                    numberForResult = numberForResult / 10;
                    textView_ForIqGame_Result.setText( numberForResult + "");
                }else if(numberForResult >= 0 && numberForResult <= 9){
                    numberForResult = 0;
                    textView_ForIqGame_Result.setText("");
                }

            }
        });
        imageView_keyboard_ForIqGameok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                finalstate(view);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(checkMusicOnOff == 1){
            myMediaPlayerForBackground.playBackgroundMusic(getApplicationContext(),2);
        }

        checkOnPause = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        toastForWrongAnswers.cancel();
        toastForNewQuestions.cancel();
        toastForChangeButton.cancel();
        myMediaPlayerForBackground.pauseBackgroundMusic();
        myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
        myMediaPlayerForLast5Min.stopLast5Min();
        checkOnPause = true;

        if(checkAnimationIsItWork == 1){
            animationForKeyboard.cancelAnimation();
        }
    }
}