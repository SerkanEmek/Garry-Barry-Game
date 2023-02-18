package com.serkanemek.garrybarry.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import com.serkanemek.garrybarry.operations.MemoryNumbers;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForBackground;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForCorrectAnswer;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForLast5Min;
import com.serkanemek.garrybarry.data.SharedPForAdvAndTotalQuestionCounter;
import com.serkanemek.garrybarry.data.SharedPForBackgrounds;
import com.serkanemek.garrybarry.data.SharedPForCoins;
import com.serkanemek.garrybarry.data.SharedPForDiamondCoins;
import com.serkanemek.garrybarry.util.Vibration;

import java.util.Random;


public class MemoryGame_Activity extends AppCompatActivity {

    ConstraintLayout constraintLayout;

    TextView textView_Remember1,textView_Remember2,textView_Remember3,textView_Remember4,textView_Remember5;
    TextView editTextNumber_Result1,editTextNumber_Result2,editTextNumber_Result3,editTextNumber_Result4,editTextNumber_Result5;
    Integer level;
    TextView textView_timer,textView_level,textView_For_Comment,textViewForTitle;
    MemoryNumbers memorygame;
    boolean result;
    CountDownTimer countDownTimer, secondCountDownTimer;
    Integer secondCountDownTimerCounter;

    Integer counterForLevel;//For making levels double. it was 30levels. with this we will make it 60 levels. Every levels will show 2 times.
    Integer showTextViewLevel;

    Random rnd;
    Integer randomNumberForAlertDialog;


    Integer bestLevelData;

    TextView textView_For_BestScore;

    ImageView imageView_MemoryGame_Keyboard1,imageView_MemoryGame_Keyboard2,imageView_MemoryGame_Keyboard3,
            imageView_MemoryGame_Keyboard4,imageView_MemoryGame_Keyboard5,imageView_MemoryGame_Keyboard6,
            imageView_MemoryGame_Keyboard7,imageView_MemoryGame_Keyboard8,imageView_MemoryGame_Keyboard9,
            imageView_MemoryGame_Keyboard0,imageView_MemoryGame_Keyboardback,imageView_MemoryGame_Keyboardok;

    ImageView imageView_For_MemoryGame_ChangeButton,imageView_ForMemoryGame_Homeicon;

    Integer numberForResult;
    Integer checkKeyboardForResult;
    TextView editText;

    SharedPForBackgrounds sharedPForBackgrounds;

    SharedPreferences sharedPreferencesForBestLevel;

    SharedPreferences sharedPreferences;
    Integer languageInteger;

    SharedPForCoins sharedPForEnergy;
    Integer checkForCoins;

    SharedPForDiamondCoins sharedPForDiamondCoins;

    SharedPForAdvAndTotalQuestionCounter sharedPForAdvAndTotalQuestionCounter;

    static Toast toastForNewQuestions,toastForChangeButton;

    boolean[] numberForResultView = new boolean[6];

    MyMediaPlayerForBackground myMediaPlayerForBackground;
    MyMediaPlayerForCorrectAnswer myMediaPlayerForCorrectAnswer;
    MyMediaPlayerForLast5Min myMediaPlayerForLast5Min;
    Integer checkMusicOnOff;

    AnimationForKeyboard animationForKeyboard;
    int checkAnimationIsItWork;//When startingimage showing if we'll go back to desktop its making broke the program. So for fixing this we are checking.


    Boolean checkOnPause;

    Vibration vibration;

    private AdView mAdView;

    //MemoryGameBanner TEST id: ca-app-pub-3940256099942544/6300978111

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_memorygame);
        checkBackground();
        vibration = new Vibration();

        getAdvertisement();

        level = 1;
        //bestLevelData = 1;
        showTextViewLevel = 1;
        counterForLevel = 0;

        sharedPreferences = this.getSharedPreferences("com.serkanemek.garrybarry.Activities" , Context.MODE_PRIVATE);
        languageInteger = sharedPreferences.getInt("storedLanguage",0);

        sharedPreferencesForBestLevel = this.getSharedPreferences("com.serkanemek.garrybarry.Activities" , MODE_PRIVATE);
        bestLevelData = sharedPreferencesForBestLevel.getInt("storedForMemoryGameBestLevel" , 1);

        imageView_For_MemoryGame_ChangeButton = findViewById(R.id.imageView_For_MemoryGame_ChangeButton);
        imageView_ForMemoryGame_Homeicon = findViewById(R.id.imageView_ForMemoryGame_Homeicon);

        sharedPForAdvAndTotalQuestionCounter = new SharedPForAdvAndTotalQuestionCounter();
        myMediaPlayerForBackground = new MyMediaPlayerForBackground();
        myMediaPlayerForCorrectAnswer = new MyMediaPlayerForCorrectAnswer();
        myMediaPlayerForLast5Min = new MyMediaPlayerForLast5Min();
        checkMusicOnOff = sharedPForAdvAndTotalQuestionCounter.getCheckMusicForOnOff(this);
        showStartingImage();

    }

    private void getAdvertisement() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adViewMemoryGameActivity);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void checkBackground() {
        sharedPForBackgrounds = new SharedPForBackgrounds();
        constraintLayout = findViewById(R.id.background_MemoryGame);
        Integer numberOfBackground = -1;
        numberOfBackground =  sharedPForBackgrounds.getMemoryGameBackground(getApplicationContext());
        if(numberOfBackground == 1){
            constraintLayout.setBackgroundResource(R.drawable.bg_memory_01);
        }else if(numberOfBackground == 2){
            constraintLayout.setBackgroundResource(R.drawable.bg_memory_02);
        }else if(numberOfBackground == 3){
            constraintLayout.setBackgroundResource(R.drawable.bg_memory_03);
        }else if(numberOfBackground == 4){
            constraintLayout.setBackgroundResource(R.drawable.bg_darkmood);
        }


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

    private void showStartingImage() {
        initializing();
        Integer checkFirstQuestion = sharedPForAdvAndTotalQuestionCounter.getMemoryGameTotalQuestionData(getApplicationContext());
        if(checkFirstQuestion > 0){
            checkAnimationIsItWork = 1;
            startMemoryGame();
            getAnimationEffect();
        }else{
            checkAnimationIsItWork = 0;
            try {
                AlertDialog.Builder builderForBegin =new AlertDialog.Builder(this);
                if(languageInteger == 1){
                    builderForBegin.setTitle(Html.fromHtml("<font color='#FF8B00'><b></b></font>")).setCancelable(false);
                    builderForBegin.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b> <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   </b></font>"));
                    builderForBegin.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>              </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startMemoryGame();
                            getAnimationEffect();
                        }
                    });
                    AlertDialog dialogForBegin = builderForBegin.create();
                    dialogForBegin.show();
                    dialogForBegin.getWindow().setBackgroundDrawableResource(R.drawable.memorygame_startimage);

                }else if(languageInteger == 2){
                    builderForBegin.setTitle(Html.fromHtml("<font color='#FF8B00'><b></b></font>")).setCancelable(false);
                    builderForBegin.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b> <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   </b></font>"));
                    builderForBegin.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>              </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startMemoryGame();
                            getAnimationEffect();
                        }
                    });
                    AlertDialog dialogForBegin = builderForBegin.create();
                    dialogForBegin.show();
                    dialogForBegin.getWindow().setBackgroundDrawableResource(R.drawable.memorygame_startimage_turkish);
                }else if(languageInteger == 3) {
                    builderForBegin.setTitle(Html.fromHtml("<font color='#FF8B00'><b></b></font>")).setCancelable(false);
                    builderForBegin.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b> <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   </b></font>"));
                    builderForBegin.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>              </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startMemoryGame();
                            getAnimationEffect();
                        }
                    });
                    AlertDialog dialogForBegin = builderForBegin.create();
                    dialogForBegin.show();
                    dialogForBegin.getWindow().setBackgroundDrawableResource(R.drawable.memorygame_startimage_russian);

                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    private void startMemoryGame(){

        try {
            initializing();
            if(showTextViewLevel >= bestLevelData){
                sharedPreferencesForBestLevel.edit().putInt("storedForMemoryGameBestLevel",showTextViewLevel).apply();
                bestLevelData = sharedPreferencesForBestLevel.getInt("storedForMemoryGameBestLevel" , 1);
            }

            textView_level.setTextSize(18);
            textView_level.setTextColor(Color.WHITE);

            if(languageInteger == 1){
                textView_For_BestScore.setText("Level " + bestLevelData);
                textView_level.setText("Level: " + showTextViewLevel);
                textViewForTitle.setText("Best Score");
            }else if(languageInteger == 2){
                textView_For_BestScore.setText("Bölüm " + bestLevelData);
                textView_level.setText("Bölüm: " + showTextViewLevel);
                textViewForTitle.setText("En iyi Skor");
            }else if(languageInteger == 3){
                textView_For_BestScore.setText("Уровень " + bestLevelData);
                textView_For_BestScore.setTextSize(14);
                textView_level.setText("Уровень: " + showTextViewLevel);
                textView_level.setTextSize(14);
                textViewForTitle.setText("Лучший Счёт");
                textViewForTitle.setTextSize(12);
            }

            setFonts();
            memorygame = new MemoryNumbers(level);

            getRememberNumbers();
            timer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void timer(){
        secondCountDownTimerCounter = 0;
        selectResultViewForKeyboardMakeFalseAllNumbers();//For Keyboard.
        try {
            countDownTimer =  new CountDownTimer(5_000,1_000){

                @Override
                public void onTick(long l) {

                    textView_timer.setTextSize(26);
                    textView_timer.setTextColor(Color.WHITE);

                    if(languageInteger == 1){
                        textView_timer.setText("" + (l/1_000));
                        textView_For_Comment.setText("Keep the Numbers in mind");
                    } else if(languageInteger == 2){
                        textView_timer.setText("" + (l/1_000));
                        textView_For_Comment.setText("Sayıları aklında tut!");
                    } else if(languageInteger == 3){
                        textView_timer.setText("" + (l/1_000));
                        textView_timer.setTextSize(20);
                        textView_For_Comment.setText("Запомните эти числа!");
                    }

                    editTextNumber_Result1.setBackgroundResource(R.drawable.circleforremember);
                    editTextNumber_Result2.setBackgroundResource(R.drawable.circleforremember);
                    editTextNumber_Result3.setBackgroundResource(R.drawable.circleforremember);
                    editTextNumber_Result4.setBackgroundResource(R.drawable.circleforremember);
                    editTextNumber_Result5.setBackgroundResource(R.drawable.circleforremember);

                    checkForClickListener(0);
                }
                @Override
                public void onFinish() {
                    selectResultViewForKeyboard2();
                    imageView_MemoryGame_Keyboardok.setEnabled(true);
                    if(languageInteger == 1){
                        textView_For_Comment.setText("Please Fill Same Numbers");
                    }else if(languageInteger == 2){
                        textView_For_Comment.setText("Aynı Sayıları Yazalım");
                    }else if(languageInteger == 3){
                        textView_For_Comment.setText("Напишем те же числа");
                    }

                    getEditTextResultNumbers();
                    checkForClickListener(1);

                    try {
                        secondCountDownTimer = new CountDownTimer(15_000,1_000) {
                            @Override
                            public void onTick(long l) {
                                secondCountDownTimerCounter = 1;
                                textView_timer.setTextSize(26);

                                if(languageInteger == 1){
                                    textView_timer.setText("" + (l/1_000));
                                } else if(languageInteger == 2){
                                    textView_timer.setText("" + (l/1_000));
                                } else if(languageInteger == 3){
                                    textView_timer.setTextSize(20);
                                    textView_timer.setText("" + (l/1_000));
                                }

                                setTimerForOnTick(l);
                            }

                            @Override
                            public void onFinish() {

                                myMediaPlayerForLast5Min.stopLast5Min();
                                imageView_MemoryGame_Keyboardok.setEnabled(false);
                                makeEmptyToEditResultTexts();

                                sharedPForAdvAndTotalQuestionCounter.updateMemoryGameTotalQuestionData(getApplicationContext(),1);//add to total question.

                                checkForCoins = sharedPForEnergy.getCoinsData(getApplicationContext());
                                if(checkForCoins > 2){
                                    sharedPForEnergy.updateCoinsData(getApplicationContext(),-2);
                                }

                                textView_level.setTextSize(14);
                                textView_level.setTextColor(Color.RED);
                                textView_level.setText("  Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
                                vibration.getVibrator(150,getApplicationContext());
                                counterForLevel = 0;
                                showTextViewLevel = 1;
                                AlertDialog.Builder builderForFinish = new AlertDialog.Builder(MemoryGame_Activity.this);

                                    if(languageInteger == 1){
                                        builderForFinish.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Time</b></font>")).setCancelable(false);
                                        builderForFinish.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>You are late! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Please try again</b></font>"));
                                        builderForFinish.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                level = 1;
                                                startMemoryGame();
                                                toastForNewQuestions.show();

                                            }
                                        });

                                    }else if(languageInteger == 2){
                                        builderForFinish.setTitle(Html.fromHtml("<font color='#FF8B00'><b>SONUÇ</b></font>")).setCancelable(false);
                                        builderForFinish.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Geç Kaldın! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Yeniden Deneyiniz</b></font>"));
                                        builderForFinish.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                level = 1;
                                                startMemoryGame();
                                                toastForNewQuestions.show();
                                            }
                                        });


                                    }else if(languageInteger == 3){
                                        builderForFinish.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Время</b></font>")).setCancelable(false);
                                        builderForFinish.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Ты опоздал! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Попробуй еще раз</b></font>"));
                                        builderForFinish.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                level = 1;
                                                startMemoryGame();
                                                toastForNewQuestions.show();
                                            }
                                        });

                                    }
                                AlertDialog dialogforfinish = builderForFinish.create();
                                dialogforfinish.show();

                                    if(randomNumberForAlertDialog == 0){
                                        dialogforfinish.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_next_1);

                                    }else if(randomNumberForAlertDialog == 1){
                                        dialogforfinish.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_next_2);

                                    }else if(randomNumberForAlertDialog == 2){
                                        dialogforfinish.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_next_3);
                                    }

                                }

                        };
                        secondCountDownTimer.start();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };
            countDownTimer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getAnimationEffect() {
        animationForKeyboard = new AnimationForKeyboard();
        animationForKeyboard.animationForKeyboardInitializingforRotationForHomeButton(getApplicationContext(),800,"rotation",imageView_ForMemoryGame_Homeicon);
        animationForKeyboard.animationForKeyboardInitializingforRotationForChangeButton(getApplicationContext(),800,"rotation",imageView_For_MemoryGame_ChangeButton);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MemoryGame_Keyboard2);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MemoryGame_Keyboard7);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MemoryGame_Keyboard4);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MemoryGame_Keyboard9);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MemoryGame_Keyboard1);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MemoryGame_Keyboard6);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MemoryGame_Keyboard5);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MemoryGame_Keyboard0);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MemoryGame_Keyboardback);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MemoryGame_Keyboardok);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MemoryGame_Keyboard3);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MemoryGame_Keyboard8);
    }

    private void checkResult(){

        try {
            if(level == 1 || level == 5){
                if(memorygame.getNumb2() == Integer.parseInt(editTextNumber_Result2.getText().toString())
                        && memorygame.getNumb3() == Integer.parseInt(editTextNumber_Result3.getText().toString())){
                    result = true;
                }
            }else if(level == 2 || level == 3 || level == 4 || level == 6 || level == 7 || level == 15){
                if(memorygame.getNumb2() == Integer.parseInt(editTextNumber_Result2.getText().toString())
                        && memorygame.getNumb3() == Integer.parseInt(editTextNumber_Result3.getText().toString())
                        && memorygame.getNumb4() == Integer.parseInt(editTextNumber_Result4.getText().toString())){
                    result = true;
                }
            }else if(level == 8 || level == 9 || level == 10 || level == 11 || level == 12 || level == 13 || level == 14 || level == 16 || level == 17 || level == 24){
                if( memorygame.getNumb1() == Integer.parseInt(editTextNumber_Result1.getText().toString())
                        && memorygame.getNumb2() == Integer.parseInt(editTextNumber_Result2.getText().toString())
                        && memorygame.getNumb3() == Integer.parseInt(editTextNumber_Result3.getText().toString())
                        && memorygame.getNumb4() == Integer.parseInt(editTextNumber_Result4.getText().toString())){
                    result = true;
                }
            }else if(level == 18 || level == 19 || level == 20 || level == 21 || level == 22 || level == 23 || level == 25 || level == 26 || level == 27 || level == 28 || level == 29 || level == 30) {
                if( memorygame.getNumb1() == Integer.parseInt(editTextNumber_Result1.getText().toString())
                        && memorygame.getNumb2() == Integer.parseInt(editTextNumber_Result2.getText().toString())
                        && memorygame.getNumb3() == Integer.parseInt(editTextNumber_Result3.getText().toString())
                        && memorygame.getNumb4() == Integer.parseInt(editTextNumber_Result4.getText().toString())
                        && memorygame.getNumb5() == Integer.parseInt(editTextNumber_Result5.getText().toString()) ){
                    result = true;
                }
            } else {
                result = false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        }

    private void getRememberNumbers() {
        hideEditTexts();
        if(level == 1 || level == 5){
            textView_Remember1.setAlpha(0.0f);
            textView_Remember2.setText(memorygame.getNumb2() + "");
            textView_Remember3.setText(memorygame.getNumb3() + "");
            textView_Remember4.setAlpha(0.0f);
            textView_Remember5.setAlpha(0.0f);
        }
        if(level == 2 || level == 3 || level == 4 || level == 6 || level == 7 || level == 15){
            textView_Remember1.setAlpha(0.0f);
            textView_Remember2.setText(memorygame.getNumb2() + "");
            textView_Remember3.setText(memorygame.getNumb3() + "");
            textView_Remember4.setText(memorygame.getNumb4() + "");
            textView_Remember5.setAlpha(0.0f);
        }
        if(level == 8 || level == 9 || level == 10 || level == 11 || level == 12 || level == 13 || level == 14 || level == 16 || level == 17 || level == 24){
            textView_Remember1.setText(memorygame.getNumb1() + "");
            textView_Remember2.setText(memorygame.getNumb2() + "");
            textView_Remember3.setText(memorygame.getNumb3() + "");
            textView_Remember4.setText(memorygame.getNumb4() + "");
            textView_Remember5.setAlpha(0.0f);
        }
        if(level == 18 || level == 19 || level == 20 || level == 21 || level == 22 || level == 23 || level == 25 || level == 26 || level == 27 || level == 28 || level == 29 || level == 30){
            textView_Remember1.setText(memorygame.getNumb1() + "");
            textView_Remember2.setText(memorygame.getNumb2() + "");
            textView_Remember3.setText(memorygame.getNumb3() + "");
            textView_Remember4.setText(memorygame.getNumb4() + "");
            textView_Remember5.setText(memorygame.getNumb5() + "");
        }
    }

    private void getEditTextResultNumbers(){
        hideTextViews();
        if(level == 1 || level == 5){
           editTextNumber_Result2.setAlpha(1.0f);
           editTextNumber_Result3.setAlpha(1.0f);
           editTextNumber_Result2.setEnabled(true);
           editTextNumber_Result3.setEnabled(true);
        }
        if(level == 2 || level == 3 || level == 4 || level == 6 || level == 7 || level == 15){
            editTextNumber_Result2.setAlpha(1.0f);
            editTextNumber_Result3.setAlpha(1.0f);
            editTextNumber_Result4.setAlpha(1.0f);
            editTextNumber_Result2.setEnabled(true);
            editTextNumber_Result3.setEnabled(true);
            editTextNumber_Result4.setEnabled(true);
        }
        if(level == 8 || level == 9 || level == 10 || level == 11 || level == 12 || level == 13 || level == 14 || level == 16 || level == 17 || level == 24){
            editTextNumber_Result1.setAlpha(1.0f);
            editTextNumber_Result2.setAlpha(1.0f);
            editTextNumber_Result3.setAlpha(1.0f);
            editTextNumber_Result4.setAlpha(1.0f);
            editTextNumber_Result1.setEnabled(true);
            editTextNumber_Result2.setEnabled(true);
            editTextNumber_Result3.setEnabled(true);
            editTextNumber_Result4.setEnabled(true);
        }
        if(level == 18 || level == 19 || level == 20 || level == 21 || level == 22 || level == 23 || level == 25 || level == 26 || level == 27 || level == 28 || level == 29 || level == 30){
            editTextNumber_Result1.setAlpha(1.0f);
            editTextNumber_Result2.setAlpha(1.0f);
            editTextNumber_Result3.setAlpha(1.0f);
            editTextNumber_Result4.setAlpha(1.0f);
            editTextNumber_Result5.setAlpha(1.0f);
            editTextNumber_Result1.setEnabled(true);
            editTextNumber_Result2.setEnabled(true);
            editTextNumber_Result3.setEnabled(true);
            editTextNumber_Result4.setEnabled(true);
            editTextNumber_Result5.setEnabled(true);
        }

    }

    private void hideTextViews(){
        textView_Remember1.setAlpha(0.0f);
        textView_Remember2.setAlpha(0.0f);
        textView_Remember3.setAlpha(0.0f);
        textView_Remember4.setAlpha(0.0f);
        textView_Remember5.setAlpha(0.0f);
    }

    private void hideEditTexts(){
        editTextNumber_Result1.setAlpha(0.0f);
        editTextNumber_Result2.setAlpha(0.0f);
        editTextNumber_Result3.setAlpha(0.0f);
        editTextNumber_Result4.setAlpha(0.0f);
        editTextNumber_Result5.setAlpha(0.0f);
        editTextNumber_Result1.setEnabled(false);
        editTextNumber_Result2.setEnabled(false);
        editTextNumber_Result3.setEnabled(false);
        editTextNumber_Result4.setEnabled(false);
        editTextNumber_Result5.setEnabled(false);
    }

    private void initializing() {
        textView_Remember1 = findViewById(R.id.textView_Remember1);
        textView_Remember2 = findViewById(R.id.textView_Remember2);
        textView_Remember3 = findViewById(R.id.textView_Remember3);
        textView_Remember4 = findViewById(R.id.textView_Remember4);
        textView_Remember5 = findViewById(R.id.textView_Remember5);
        editTextNumber_Result1 = findViewById(R.id.editTextNumber_Result1);
        editTextNumber_Result2 = findViewById(R.id.editTextNumber_Result2);
        editTextNumber_Result3 = findViewById(R.id.editTextNumber_Result3);
        editTextNumber_Result4 = findViewById(R.id.editTextNumber_Result4);
        editTextNumber_Result5 = findViewById(R.id.editTextNumber_Result5);
        textView_timer = findViewById(R.id.textView_timer);
        textView_level = findViewById(R.id.textView_level);
        textView_For_Comment = findViewById(R.id.textView_For_Comment);
        textViewForTitle = findViewById(R.id.textViewForTitle);

        result = false;
        textView_Remember1.setAlpha(1.0f);
        textView_Remember2.setAlpha(1.0f);
        textView_Remember3.setAlpha(1.0f);
        textView_Remember4.setAlpha(1.0f);
        textView_Remember5.setAlpha(1.0f);
        editTextNumber_Result1.setAlpha(1.0f);
        editTextNumber_Result2.setAlpha(1.0f);
        editTextNumber_Result3.setAlpha(1.0f);
        editTextNumber_Result4.setAlpha(1.0f);
        editTextNumber_Result5.setAlpha(1.0f);
        textView_For_BestScore = findViewById(R.id.textView_For_BestScore);

        imageView_MemoryGame_Keyboard1 = findViewById(R.id.imageView_MemoryGame_Keyboard1);
        imageView_MemoryGame_Keyboard2 = findViewById(R.id.imageView_MemoryGame_Keyboard2);
        imageView_MemoryGame_Keyboard3 = findViewById(R.id.imageView_MemoryGame_Keyboard3);
        imageView_MemoryGame_Keyboard4 = findViewById(R.id.imageView_MemoryGame_Keyboard4);
        imageView_MemoryGame_Keyboard5 = findViewById(R.id.imageView_MemoryGame_Keyboard5);
        imageView_MemoryGame_Keyboard6 = findViewById(R.id.imageView_MemoryGame_Keyboard6);
        imageView_MemoryGame_Keyboard7 = findViewById(R.id.imageView_MemoryGame_Keyboard7);
        imageView_MemoryGame_Keyboard8 = findViewById(R.id.imageView_MemoryGame_Keyboard8);
        imageView_MemoryGame_Keyboard9 = findViewById(R.id.imageView_MemoryGame_Keyboard9);
        imageView_MemoryGame_Keyboard0 = findViewById(R.id.imageView_MemoryGame_Keyboard0);
        imageView_MemoryGame_Keyboardback = findViewById(R.id.imageView_MemoryGame_Keyboardback);
        imageView_MemoryGame_Keyboardok = findViewById(R.id.imageView_MemoryGame_Keyboardok);

        rnd = new Random();
        randomNumberForAlertDialog = rnd.nextInt(3);

        checkOnPause = false;
        numberForResult = -1;
        checkKeyboardForResult = 1;

        checkForCoins = 0;
        sharedPForEnergy = new SharedPForCoins();
        sharedPForDiamondCoins = new SharedPForDiamondCoins();

        toastMessageForNewQuestions();
        toastMessageForChangeQuestion();



    }

    private void finalstate(){

        try {
            myMediaPlayerForLast5Min.stopLast5Min();
            sharedPForAdvAndTotalQuestionCounter.updateMemoryGameTotalQuestionData(getApplicationContext(),1);//add to total question.

            imageView_MemoryGame_Keyboardok.setEnabled(false);
            checkResult();
            makeEmptyToEditResultTexts();
            if(result == false){
                vibration.getVibrator(150,getApplicationContext());
                countDownTimer.cancel();
                secondCountDownTimer.cancel();
                counterForLevel = 0;
                showTextViewLevel = 1;

                checkForCoins = sharedPForEnergy.getCoinsData(getApplicationContext());
                if(checkForCoins > 2){
                    sharedPForEnergy.updateCoinsData(getApplicationContext(),-2);
                }

                textView_level.setTextSize(14);
                textView_level.setTextColor(Color.RED);
                textView_level.setText("  Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
                AlertDialog.Builder builder =new AlertDialog.Builder(this);

                if(languageInteger == 1){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>RESULT</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Wrong Answer! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Please Try again</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            level = 1;
                            startMemoryGame();
                            toastForNewQuestions.show();
                        }
                    });


                }else if(languageInteger == 2){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>SONUÇ</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Yanlış Cevap! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Yeniden Deneyiniz</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            level = 1;
                            startMemoryGame();
                            toastForNewQuestions.show();
                        }
                    });



                }else if(languageInteger == 3){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Результат</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Неверный ответ! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Попробуй еще раз</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            level = 1;
                            startMemoryGame();
                            toastForNewQuestions.show();
                        }
                    });


                }
                AlertDialog dialog = builder.create();
                dialog.show();

                if(randomNumberForAlertDialog == 0){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_next_1);
                }else  if(randomNumberForAlertDialog == 1){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_next_2);
                }else  if(randomNumberForAlertDialog == 2){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_next_3);
                }





            }

            if(result == true){
                myMediaPlayerForCorrectAnswer.playCorrectAnswerSound(getApplicationContext());
                vibration.getVibrator(75,getApplicationContext());
                countDownTimer.cancel();
                secondCountDownTimer.cancel();
                every5QuestionGivingExtraCoins();
                showTextViewLevel++;
                sharedPForEnergy.updateCoinsData(getApplicationContext(),5);
                textView_level.setTextSize(14);
                textView_level.setTextColor(Color.YELLOW);
                textView_level.setText("  Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
                sharedPForAdvAndTotalQuestionCounter.updateMemoryGameCorrectAnswersData(getApplicationContext(),1);
                sharedPForAdvAndTotalQuestionCounter.updateAdvertisementCounterData(getApplicationContext(),1);

                if(level == 30){
                    AlertDialog.Builder builder =new AlertDialog.Builder(this);

                    sharedPForEnergy.updateCoinsData(getApplicationContext(),600);
                    sharedPForDiamondCoins.updateDiamondCoinsData(getApplicationContext(),10);

                    if(languageInteger == 1){
                        builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>RESULT</b></font>")).setCancelable(false);
                        builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>You are Awesome. <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Congratulations, You Finished all Levels..</b></font>"));
                        builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getApplicationContext(), MainMenuActivity2.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }else if(languageInteger == 2){
                        builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>SONUÇ</b></font>")).setCancelable(false);
                        builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>Sen Bir Dahi'sin! Böyle Devam Et! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tebrikler bütün bölümleri bitirdin..</b></font>"));
                        builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getApplicationContext(), MainMenuActivity2.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }else if(languageInteger == 3){
                            builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Результат</b></font>")).setCancelable(false);
                            builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>Ты гений! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Поздравления, Вы закончили все серии..</b></font>"));
                            builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(getApplicationContext(), MainMenuActivity2.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                    }
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_mainmenu_english);
                }
                if(counterForLevel == 1){
                    level++;
                    counterForLevel = 0;
                }else if(counterForLevel == 0){
                    counterForLevel++;
                }

                AlertDialog.Builder builder =new AlertDialog.Builder(this);

                if(languageInteger == 1){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>RESULT</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Correct answer <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Congratulations</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startMemoryGame();
                            toastForNewQuestions.show();
                        }
                    });

                }else if(languageInteger == 2){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>SONUÇ</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Doğru Cevap <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tebrikler</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startMemoryGame();
                            toastForNewQuestions.show();
                        }
                    });

                }else if(languageInteger == 3){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Результат</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Правильный ответ <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Поздравления</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startMemoryGame();
                            toastForNewQuestions.show();
                        }
                    });

                }

                AlertDialog dialog = builder.create();
                dialog.show();

                if(randomNumberForAlertDialog == 0){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_next_1);
                }else if(randomNumberForAlertDialog == 1){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_next_2);
                }else if(randomNumberForAlertDialog == 2){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_next_3);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void changequestion_button(View view){
        myMediaPlayerForLast5Min.stopLast5Min();
        sharedPForAdvAndTotalQuestionCounter.updateMemoryGameTotalQuestionData(getApplicationContext(),1);//add to total question.

        checkForCoins = sharedPForEnergy.getCoinsData(getApplicationContext());
        if(checkForCoins > 1){
            sharedPForEnergy.updateCoinsData(getApplicationContext(),-1);
        }

        toastForNewQuestions.cancel();
        toastForChangeButton.cancel();
        countDownTimer.cancel();
        if(secondCountDownTimerCounter == 1){
            secondCountDownTimer.cancel();
        }
        vibration.getVibrator(75,getApplicationContext());
        counterForLevel = 0;
        showTextViewLevel = 1;
        level = 1;
        makeEmptyToEditResultTexts();
        startMemoryGame();
        toastForChangeButton.show();
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

    private void setFonts(){
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/FredokaOne-Regular.ttf");
        textView_Remember1.setTypeface(typeface);
        textView_Remember2.setTypeface(typeface);
        textView_Remember3.setTypeface(typeface);
        textView_Remember4.setTypeface(typeface);
        textView_Remember5.setTypeface(typeface);
        editTextNumber_Result1.setTypeface(typeface);
        editTextNumber_Result2.setTypeface(typeface);
        editTextNumber_Result3.setTypeface(typeface);
        editTextNumber_Result4.setTypeface(typeface);
        editTextNumber_Result5.setTypeface(typeface);
        textView_timer.setTypeface(typeface);
        textView_level.setTypeface(typeface);
        textView_For_Comment.setTypeface(typeface);
        textView_For_BestScore.setTypeface(typeface);
        textViewForTitle.setTypeface(typeface);
    }

    private void makeEmptyToEditResultTexts(){
        editTextNumber_Result1.setText("");
        editTextNumber_Result2.setText("");
        editTextNumber_Result3.setText("");
        editTextNumber_Result4.setText("");
        editTextNumber_Result5.setText("");
    }

    private void every5QuestionGivingExtraCoins(){
        if(showTextViewLevel % 5 == 0){
            sharedPForEnergy.updateCoinsData(getApplicationContext(),showTextViewLevel);

        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            animationForKeyboard.cancelAnimation();
            myMediaPlayerForBackground.stopBackgroundMusic();
            myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
            myMediaPlayerForLast5Min.stopLast5Min();
            toastForNewQuestions.cancel();
            toastForChangeButton.cancel();
            vibration.getVibrator(75,getApplicationContext());
            countDownTimer.cancel();
            if(secondCountDownTimerCounter == 1){//if this working we can cancel.
                secondCountDownTimer.cancel();
            }
            Intent intent = new Intent(MemoryGame_Activity.this, MainMenuActivity2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void home_button(View view){
        animationForKeyboard.cancelAnimation();
        myMediaPlayerForBackground.stopBackgroundMusic();
        myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
        myMediaPlayerForLast5Min.stopLast5Min();
        toastForNewQuestions.cancel();
        toastForChangeButton.cancel();
        vibration.getVibrator(75,getApplicationContext());
        countDownTimer.cancel();
        if(secondCountDownTimerCounter == 1){ //if this working we can cancel.
            secondCountDownTimer.cancel();
        }
        Intent intentToMainmenu = new Intent(getApplicationContext(), MainMenuActivity2.class);
        intentToMainmenu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentToMainmenu);
        finish();

    }

    private void selectResultViewForKeyboardMakeFalseAllNumbers(){
        numberForResultView[1] = false;
        numberForResultView[2] = true;
        numberForResultView[3] = false;
        numberForResultView[4] = false;
        numberForResultView[5] = false;
    }

    private void selectResultViewForKeyboard2(){  //2=Res2   3=Res3    1=Res1   4=Res4   5=Res5
        editTextNumber_Result1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberForResultView[1] = true;
                numberForResultView[2] = false;
                numberForResultView[3] = false;
                numberForResultView[4] = false;
                numberForResultView[5] = false;
                editTextNumber_Result1.setBackgroundResource(R.drawable.circleforremember2);
                editTextNumber_Result2.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_Result3.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_Result4.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_Result5.setBackgroundResource(R.drawable.circleforremember);
                numberForResult = -1;
            }
        });
        editTextNumber_Result2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberForResultView[1] = false;
                numberForResultView[2] = true;
                numberForResultView[3] = false;
                numberForResultView[4] = false;
                numberForResultView[5] = false;
                editTextNumber_Result1.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_Result2.setBackgroundResource(R.drawable.circleforremember2);
                editTextNumber_Result3.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_Result4.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_Result5.setBackgroundResource(R.drawable.circleforremember);
                numberForResult = -1;
            }
        });
        editTextNumber_Result3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberForResultView[1] = false;
                numberForResultView[2] = false;
                numberForResultView[3] = true;
                numberForResultView[4] = false;
                numberForResultView[5] = false;
                editTextNumber_Result1.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_Result2.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_Result3.setBackgroundResource(R.drawable.circleforremember2);
                editTextNumber_Result4.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_Result5.setBackgroundResource(R.drawable.circleforremember);
                numberForResult = -1;
            }
        });
        editTextNumber_Result4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberForResultView[1] = false;
                numberForResultView[2] = false;
                numberForResultView[3] = false;
                numberForResultView[4] = true;
                numberForResultView[5] = false;
                editTextNumber_Result1.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_Result2.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_Result3.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_Result4.setBackgroundResource(R.drawable.circleforremember2);
                editTextNumber_Result5.setBackgroundResource(R.drawable.circleforremember);
                numberForResult = -1;
            }
        });
        editTextNumber_Result5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberForResultView[1] = false;
                numberForResultView[2] = false;
                numberForResultView[3] = false;
                numberForResultView[4] = false;
                numberForResultView[5] = true;
                editTextNumber_Result1.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_Result2.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_Result3.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_Result4.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_Result5.setBackgroundResource(R.drawable.circleforremember2);
                numberForResult = -1;

            }
        });
    }

    private TextView selectResultViewForKeyboard(){

        if(numberForResultView[1]){
            return editTextNumber_Result1;
        }else if(numberForResultView[2]){
            return editTextNumber_Result2;
        }else if(numberForResultView[3]){
            return editTextNumber_Result3;
        }else if(numberForResultView[4]){
            return editTextNumber_Result4;
        }else if(numberForResultView[5]){
            return editTextNumber_Result5;
        }else {
            return editTextNumber_Result2;
        }
    }

    private void keyboards(){

            imageView_MemoryGame_Keyboard1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText = selectResultViewForKeyboard();
                    vibration.getVibrator(75,getApplicationContext());
                    if(numberForResult == -1){
                        numberForResult = 1;
                    }else if(numberForResult <= 999  && numberForResult >= 0){
                        numberForResult = (numberForResult * 10) + 1;
                    }
                    editText.setText( numberForResult + "");
                }
            });
            imageView_MemoryGame_Keyboard2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText = selectResultViewForKeyboard();
                    vibration.getVibrator(75,getApplicationContext());
                    if(numberForResult == -1){
                        numberForResult = 2;
                    }else if(numberForResult <= 999  && numberForResult >= 0){
                        numberForResult = (numberForResult * 10) + 2;
                    }

                    editText.setText( numberForResult + "");
                }
            });
            imageView_MemoryGame_Keyboard3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText = selectResultViewForKeyboard();
                    vibration.getVibrator(75,getApplicationContext());
                    if(numberForResult == -1){
                        numberForResult = 3;
                    }else if(numberForResult <= 999  && numberForResult >= 0){
                        numberForResult = (numberForResult * 10) + 3;
                    }

                    editText.setText( numberForResult + "");
                }
            });
            imageView_MemoryGame_Keyboard4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText = selectResultViewForKeyboard();
                    vibration.getVibrator(75,getApplicationContext());
                    if(numberForResult == -1){
                        numberForResult = 4;
                    }else if(numberForResult <= 999  && numberForResult >= 0){
                        numberForResult = (numberForResult * 10) + 4;
                    }

                    editText.setText( numberForResult + "");
                }
            });
            imageView_MemoryGame_Keyboard5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText = selectResultViewForKeyboard();
                    vibration.getVibrator(75,getApplicationContext());
                    if(numberForResult == -1){
                        numberForResult = 5;
                    }else if(numberForResult <= 999  && numberForResult >= 0){
                        numberForResult = (numberForResult * 10) + 5;
                    }

                    editText.setText( numberForResult + "");
                }
            });
            imageView_MemoryGame_Keyboard6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText = selectResultViewForKeyboard();
                    vibration.getVibrator(75,getApplicationContext());
                    if(numberForResult == -1){
                        numberForResult = 6;
                    }else if(numberForResult <= 999  && numberForResult >= 0){
                        numberForResult = (numberForResult * 10) + 6;
                    }

                    editText.setText( numberForResult + "");
                }
            });
            imageView_MemoryGame_Keyboard7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText = selectResultViewForKeyboard();
                    vibration.getVibrator(75,getApplicationContext());
                    if(numberForResult == -1){
                        numberForResult = 7;
                    }else if(numberForResult <= 999  && numberForResult >= 0){
                        numberForResult = (numberForResult * 10) + 7;
                    }

                    editText.setText( numberForResult + "");
                }
            });
            imageView_MemoryGame_Keyboard8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText = selectResultViewForKeyboard();
                    vibration.getVibrator(75,getApplicationContext());
                    if(numberForResult == -1){
                        numberForResult = 8;
                    }else if(numberForResult <= 999  && numberForResult >= 0){
                        numberForResult = (numberForResult * 10) + 8;
                    }

                    editText.setText( numberForResult + "");
                }
            });
            imageView_MemoryGame_Keyboard9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText = selectResultViewForKeyboard();
                    vibration.getVibrator(75,getApplicationContext());
                    if(numberForResult == -1){
                        numberForResult = 9;
                    }else if(numberForResult <= 999  && numberForResult >= 0){
                        numberForResult = (numberForResult * 10) + 9;
                    }

                    editText.setText( numberForResult + "");
                }
            });
            imageView_MemoryGame_Keyboard0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText = selectResultViewForKeyboard();
                    vibration.getVibrator(75,getApplicationContext());
                    if(numberForResult == -1){
                        numberForResult = 0;
                    }else if(numberForResult <= 999  && numberForResult >= 0){
                        numberForResult = (numberForResult * 10) + 0;
                    }

                    editText.setText( numberForResult + "");
                }
            });
            imageView_MemoryGame_Keyboardback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText = selectResultViewForKeyboard();
                    vibration.getVibrator(75,getApplicationContext());
                    if(numberForResult > 9){
                        numberForResult = numberForResult / 10;
                        editText.setText( numberForResult + "");
                    }else if(numberForResult >= 0 && numberForResult <= 9){
                        numberForResult = 0;
                        editText.setText("");
                    }

                }
            });
            imageView_MemoryGame_Keyboardok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vibration.getVibrator(75,getApplicationContext());
                    finalstate();
                }
            });


    }

    private void checkForClickListener(int i){ //For Keyboard
        if(i == 0){
            imageView_MemoryGame_Keyboard1.setOnClickListener(null);
            imageView_MemoryGame_Keyboard2.setOnClickListener(null);
            imageView_MemoryGame_Keyboard3.setOnClickListener(null);
            imageView_MemoryGame_Keyboard4.setOnClickListener(null);
            imageView_MemoryGame_Keyboard5.setOnClickListener(null);
            imageView_MemoryGame_Keyboard6.setOnClickListener(null);
            imageView_MemoryGame_Keyboard7.setOnClickListener(null);
            imageView_MemoryGame_Keyboard8.setOnClickListener(null);
            imageView_MemoryGame_Keyboard9.setOnClickListener(null);
            imageView_MemoryGame_Keyboard0.setOnClickListener(null);
            imageView_MemoryGame_Keyboardback.setOnClickListener(null);
            imageView_MemoryGame_Keyboardok.setOnClickListener(null);

        }
        if(i == 1){
            keyboards();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(checkMusicOnOff == 1){
            myMediaPlayerForBackground.playBackgroundMusic(getApplicationContext(),1);
        }
        checkOnPause = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
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
