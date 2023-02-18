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
import com.serkanemek.garrybarry.operations.Divide;
import com.serkanemek.garrybarry.operations.Multi;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForBackground;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForCorrectAnswer;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForLast5Min;
import com.serkanemek.garrybarry.data.SharedPForAdvAndTotalQuestionCounter;
import com.serkanemek.garrybarry.data.SharedPForBackgrounds;
import com.serkanemek.garrybarry.data.SharedPForCoins;
import com.serkanemek.garrybarry.operations.Subs;
import com.serkanemek.garrybarry.operations.Sum;
import com.serkanemek.garrybarry.util.Vibration;

import java.util.Random;

public class MathGameForMainActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;

    Sum sum;
    Subs subs;
    Multi multi;
    Divide divide;

    TextView textView_MathGame_Question1,textView_MathGame_Question2,textView_MathGame_Question3;
    TextView textView_MathGame_Operation1,textView_MathGame_Operation2;
    TextView textView_ForMathGame_result;

    ImageView imageView_MathGame_keyboard1,imageView_MathGame_keyboard2,imageView_MathGame_keyboard3,
            imageView_MathGame_keyboard4,imageView_MathGame_keyboard5,imageView_MathGame_keyboard6,
            imageView_MathGame_keyboard7,imageView_MathGame_keyboard8,imageView_MathGame_keyboard9,
            imageView_MathGame_keyboard0,imageView_MathGame_keyboardback,imageView_MathGame_keyboardok;

    ImageView imageView_MathgameForMain_Changebutton,imageView_MathGame_For_HomeButton;

    TextView textView_ForMathGame_timer;
    CountDownTimer countDownTimer;
    Integer numberForResult;
    int randomCheckOperation; // 1=sum& 2=subs& 3=mult& 4=divide.
    Random randomforOps;
    int result;
    int level;

    SharedPForBackgrounds sharedPForBackgrounds;
    SharedPreferences sharedPreferencesForLanguage, sharedPreferencesForLevel;
    Integer languageInteger;

    SharedPreferences sharedPreferencesForSettingLevel;//With this: we asking from level1-2-3 2questions, and level4-5-6 1question.
    int setLevel;

    Random rnd;
    Integer randomNumberForAlertDialog;

    TextView textView_ForMathGame_Coins;
    SharedPForCoins sharedPForEnergy;
    Integer checkForCoins;

    static Toast toastForWrongAnswers,toastForNewQuestions,toastForChangeButton;

    SharedPForAdvAndTotalQuestionCounter sharedPForAdvAndTotalQuestionCounter;

    Integer getMessageForCalculator;//For Calculator message

    MyMediaPlayerForBackground myMediaPlayerForBackground;
    MyMediaPlayerForCorrectAnswer myMediaPlayerForCorrectAnswer;
    MyMediaPlayerForLast5Min myMediaPlayerForLast5Min;
    Integer checkMusicOnOff;

    AnimationForKeyboard animationForKeyboard;
    int checkAnimationIsItWork;//When startingimage showing if we'll go back to desktop its making broke the program. So for fixing this we are checking.

    Boolean checkOnPause;//For MediaPlayerForLast5Mins.

    Vibration vibration;

    private AdView mAdView;

    //MathGameBanner TEST id:ca-app-pub-3940256099942544/6300978111

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_math_game_for_main);
        vibration = new Vibration();
        checkBackground();

        getAdvertisement();

        level = 1;
        sharedPreferencesForLanguage = this.getSharedPreferences("com.serkanemek.garrybarry.Activities" , Context.MODE_PRIVATE);
        languageInteger = sharedPreferencesForLanguage.getInt("storedLanguage",1);

        sharedPreferencesForSettingLevel = this.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
        setLevel = sharedPreferencesForSettingLevel.getInt("storedSetLevelForMathGame",1);

        sharedPreferencesForLevel = this.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
        sharedPForAdvAndTotalQuestionCounter = new SharedPForAdvAndTotalQuestionCounter();
        getMessageForCalculator = sharedPForAdvAndTotalQuestionCounter.getMathGameTotalQuestionData(getApplicationContext());




        imageView_MathgameForMain_Changebutton = findViewById(R.id.imageView_MathgameForMain_Changebutton);
        imageView_MathGame_For_HomeButton = findViewById(R.id.imageView_MathGame_For_HomeButton);

        if(getMessageForCalculator == 0){
            calculatorMessageOnFirstTimeOpening();
        }else{
            startMathGame();
            getAnimationEffect();
        }

        myMediaPlayerForBackground = new MyMediaPlayerForBackground();
        myMediaPlayerForCorrectAnswer = new MyMediaPlayerForCorrectAnswer();
        myMediaPlayerForLast5Min = new MyMediaPlayerForLast5Min();
        checkMusicOnOff = sharedPForAdvAndTotalQuestionCounter.getCheckMusicForOnOff(this);



    }

    private void getAdvertisement() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adViewMathGameForMain);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void checkBackground() {
        sharedPForBackgrounds = new SharedPForBackgrounds();
        constraintLayout = findViewById(R.id.background_MathGame);
        Integer numberOfBackground = -1;
        numberOfBackground =  sharedPForBackgrounds.getMathGameBackground(getApplicationContext());
        if(numberOfBackground == 1){
            constraintLayout.setBackgroundResource(R.drawable.bg_math_main_01);
        }else if(numberOfBackground == 2){
            constraintLayout.setBackgroundResource(R.drawable.bg_math_main_02);
        }else if(numberOfBackground == 3){
            constraintLayout.setBackgroundResource(R.drawable.bg_math_main_03);
        }else if(numberOfBackground == 4){
            constraintLayout.setBackgroundResource(R.drawable.bg_darkmood);
        }
    }

    private void calculatorMessageOnFirstTimeOpening() {

        try {
            checkAnimationIsItWork = 0;
            toastMessageForWrongAnswers();
            toastMessageForNewQuestions();
            toastMessageForChangeQuestion();
            if(languageInteger == 1){
                AlertDialog.Builder builder =new AlertDialog.Builder(this);
                builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>  </b></font>")).setCancelable(false);
                builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>                            <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            </b></font>"));

                builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>                  </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startMathGame();
                        getAnimationEffect();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.calculatormessage_english);
            }else if(languageInteger == 2){
                AlertDialog.Builder builder =new AlertDialog.Builder(this);
                builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>  </b></font>")).setCancelable(false);
                builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>                            <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            </b></font>"));

                builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>                  </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startMathGame();
                        getAnimationEffect();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.calculatormessage_turkish);
            }else if(languageInteger == 3){
                AlertDialog.Builder builder =new AlertDialog.Builder(this);
                builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>  </b></font>")).setCancelable(false);
                builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>                            <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            </b></font>"));

                builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>                  </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startMathGame();
                        getAnimationEffect();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.calculatormessage_russian);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void startMathGame(){
        level = sharedPreferencesForLevel.getInt("storedLevelForMathGame", 1);
        initializing();
        checkOperations();
        setfonts();
        timer();
        keyboards();

    }

    private void timer(){

        try {
           final AlertDialog.Builder builderForTimer = new AlertDialog.Builder(this);
            countDownTimer =   new CountDownTimer(15_000, 1_000) {
                @Override
                public void onTick(long l) {
                    imageView_MathGame_keyboardok.setEnabled(true);

                    if(languageInteger == 1) {
                        textView_ForMathGame_timer.setText("" + (l / 1000));
                    }else if(languageInteger == 2){
                        textView_ForMathGame_timer.setText("" + (l / 1000));
                    }else if(languageInteger == 3){
                        textView_ForMathGame_timer.setText("" + (l / 1000));
                    }
                    setTimerForOnTick(l);
                }
                @Override
                public void onFinish() {
                    myMediaPlayerForLast5Min.stopLast5Min();
                    vibration.getVibrator(75,getApplicationContext());
                    imageView_MathGame_keyboardok.setEnabled(false);

                    sharedPForAdvAndTotalQuestionCounter.updateMathGameTotalQuestionData(getApplicationContext(),1);

                    checkForCoins = sharedPForEnergy.getCoinsData(getApplicationContext());
                    if(checkForCoins > 2){
                        sharedPForEnergy.updateCoinsData(getApplicationContext(),-2);
                    }
                    textView_ForMathGame_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));

                    if(languageInteger == 1){
                        builderForTimer.setTitle(Html.fromHtml("<font color='#FF8B00'><b>RESULT</b></font>")).setCancelable(false);
                        builderForTimer.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>You are late! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Please try again</b></font>"));
                        builderForTimer.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                textView_ForMathGame_result.setText("");
                                startMathGame();
                                toastForNewQuestions.show();
                            }
                        });


                    }else if(languageInteger == 2){
                        builderForTimer.setTitle(Html.fromHtml("<font color='#FF8B00'><b>SONUÇ</b></font>")).setCancelable(false);
                        builderForTimer.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Geç Kaldın! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Yeniden Deneyiniz</b></font>"));
                        builderForTimer.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                textView_ForMathGame_result.setText("");
                                startMathGame();
                                toastForNewQuestions.show();
                            }
                        });


                    }else if(languageInteger == 3){
                        builderForTimer.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Результат</b></font>")).setCancelable(false);
                        builderForTimer.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Ты опоздал! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Попробуй еще раз</b></font>"));
                        builderForTimer.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                textView_ForMathGame_result.setText("");
                                startMathGame();
                                toastForNewQuestions.show();
                            }
                        });

                    }

                    AlertDialog dialog = builderForTimer.create();
                    dialog.show();

                    if(randomNumberForAlertDialog == 0){
                        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_next_7);
                    }else if(randomNumberForAlertDialog == 1){
                        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_next_8);
                    }else if(randomNumberForAlertDialog == 2){
                        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_next_9);
                    }


                }
            };
            countDownTimer.start();

        }catch (Exception e){
            e.printStackTrace();
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

    private void finalstate(){
        try {

            sharedPForAdvAndTotalQuestionCounter.updateMathGameTotalQuestionData(getApplicationContext(),1);

            AlertDialog.Builder builder =new AlertDialog.Builder(this);
            if(result == Integer.parseInt(textView_ForMathGame_result.getText().toString())){
                myMediaPlayerForLast5Min.stopLast5Min();
                myMediaPlayerForCorrectAnswer.playCorrectAnswerSound(getApplicationContext());
                countDownTimer.cancel();
                imageView_MathGame_keyboardok.setEnabled(false);
                sharedPForEnergy.updateCoinsData(getApplicationContext(),5);
                textView_ForMathGame_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
                sharedPForAdvAndTotalQuestionCounter.updateMathGameCorrectAnswersData(getApplicationContext(),1);
                sharedPForAdvAndTotalQuestionCounter.updateAdvertisementCounterData(getApplicationContext(),1);
                addData();

                if(languageInteger == 1){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>RESULT</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Correct answer <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Congratulations</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            toastForNewQuestions.show();
                            textView_ForMathGame_result.setText("");
                            startMathGame();
                        }
                    });


                } else if( languageInteger == 2){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>SONUÇ</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Doğru Cevap <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tebrikler</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            toastForNewQuestions.show();
                            textView_ForMathGame_result.setText("");
                            startMathGame();
                        }
                    });

                } else if(languageInteger == 3){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Результат</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Правильный ответ <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Поздравления</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            toastForNewQuestions.show();
                            textView_ForMathGame_result.setText("");
                            startMathGame();
                        }
                    });

                }
                AlertDialog dialog = builder.create();
                dialog.show();

                if(randomNumberForAlertDialog == 0){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_next_7);
                }else if(randomNumberForAlertDialog == 1){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_next_8);
                }else if(randomNumberForAlertDialog == 2){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_next_9);
                }



                vibration.getVibrator(75,this);
            } else { //if Wrong Answer.
                vibration.getVibrator(150,this);


                checkForCoins = sharedPForEnergy.getCoinsData(getApplicationContext());
                if(checkForCoins > 2){
                    sharedPForEnergy.updateCoinsData(getApplicationContext(),-2);
                }

                textView_ForMathGame_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
                toastForWrongAnswers.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getAnimationEffect() {
        checkAnimationIsItWork = 1;
        animationForKeyboard = new AnimationForKeyboard();
        animationForKeyboard.animationForKeyboardInitializingforRotationForHomeButton(getApplicationContext(),800,"rotation",imageView_MathGame_For_HomeButton);
        animationForKeyboard.animationForKeyboardInitializingforRotationForChangeButton(getApplicationContext(),800,"rotation",imageView_MathgameForMain_Changebutton);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MathGame_keyboard2);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MathGame_keyboard7);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MathGame_keyboard4);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MathGame_keyboard9);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MathGame_keyboard1);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MathGame_keyboard6);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MathGame_keyboard5);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MathGame_keyboard0);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MathGame_keyboardback);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MathGame_keyboardok);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MathGame_keyboard3);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_MathGame_keyboard8);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"push_down_in",textView_ForMathGame_result);
    }

    private void checkOperations(){

            if(randomCheckOperation == 1){ //Sum
                textView_MathGame_Question1.setText(sum.getNumber1() + "");
                textView_MathGame_Question2.setText(sum.getNumber2() + "");
                textView_MathGame_Operation1.setText("+");
                result = sum.calculater(level);

                if(level == 3 || level == 4 || level == 5 || level == 6){
                    textView_MathGame_Question3.setText(sum.getNumber3() + "");
                    textView_MathGame_Question3.setAlpha(1.0f);
                    textView_MathGame_Operation2.setText("+");
                    textView_MathGame_Operation2.setAlpha(1.0f);
                }
            }else if(randomCheckOperation == 2){ //Subs
                textView_MathGame_Question1.setText(subs.getNumber1() + "");
                textView_MathGame_Question2.setText(subs.getNumber2() + "");
                textView_MathGame_Operation1.setText("-");
                result = subs.calculater(level);
            } else if(randomCheckOperation == 3){ //Multipy
                textView_MathGame_Question1.setText(multi.getNumber1() + "");
                textView_MathGame_Question2.setText(multi.getNumber2() + "");
                textView_MathGame_Operation1.setText("x");
                result = multi.calculater(level);
                if(level == 3 || level == 5 || level == 6){
                    textView_MathGame_Question3.setText(multi.getNumber3() + "");
                    textView_MathGame_Question3.setAlpha(1.0f);
                    textView_MathGame_Operation2.setText("x");
                    textView_MathGame_Operation2.setAlpha(1.0f);
                }
            } else if(randomCheckOperation == 4){ //Divide
                textView_MathGame_Question1.setText(divide.getNumber1() + "");
                textView_MathGame_Question2.setText(divide.getNumber2() + "");
                textView_MathGame_Operation1.setText("÷");
                result = divide.calculater(level);
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

    private void initializing(){
        sum = new Sum(level);
        subs = new Subs(level);
        multi = new Multi(level);
        divide = new Divide(level);

        textView_MathGame_Question1 = findViewById(R.id.textView_MathGame_Question1);
        textView_MathGame_Question2 = findViewById(R.id.textView_MathGame_Question2);
        textView_MathGame_Question3 = findViewById(R.id.textView_MathGame_Question3);
        textView_MathGame_Question3.setAlpha(0.0f);
        textView_MathGame_Operation1 = findViewById(R.id.textView_MathGame_Operation1);
        textView_MathGame_Operation2 = findViewById(R.id.textView_MathGame_Operation2);
        textView_MathGame_Operation2.setAlpha(0.0f);
        textView_ForMathGame_result = findViewById(R.id.textView_ForMathGame_result);
        imageView_MathGame_keyboard1 = findViewById(R.id.imageView_MathGame_keyboard1);
        imageView_MathGame_keyboard2 = findViewById(R.id.imageView_MathGame_keyboard2);
        imageView_MathGame_keyboard3 = findViewById(R.id.imageView_MathGame_keyboard3);
        imageView_MathGame_keyboard4 = findViewById(R.id.imageView_MathGame_keyboard4);
        imageView_MathGame_keyboard5 = findViewById(R.id.imageView_MathGame_keyboard5);
        imageView_MathGame_keyboard6 = findViewById(R.id.imageView_MathGame_keyboard6);
        imageView_MathGame_keyboard7 = findViewById(R.id.imageView_MathGame_keyboard7);
        imageView_MathGame_keyboard8 = findViewById(R.id.imageView_MathGame_keyboard8);
        imageView_MathGame_keyboard9 = findViewById(R.id.imageView_MathGame_keyboard9);
        imageView_MathGame_keyboard0 = findViewById(R.id.imageView_MathGame_keyboard0);
        imageView_MathGame_keyboardback = findViewById(R.id.imageView_MathGame_keyboardback);
        imageView_MathGame_keyboardok = findViewById(R.id.imageView_MathGame_keyboardok);
        textView_ForMathGame_timer = findViewById(R.id.textView_ForMathGame_timer);
        randomforOps = new Random();
        randomCheckOperation = randomforOps.nextInt(4) + 1;
        result = -1;
        numberForResult = -1;
        checkOnPause = false;
        textView_ForMathGame_Coins = findViewById(R.id.textView_ForMathGame_Coins);
        sharedPForEnergy = new SharedPForCoins();
        textView_ForMathGame_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
        checkForCoins = 0;

        rnd = new Random();
        randomNumberForAlertDialog = rnd.nextInt(3);

        toastMessageForWrongAnswers();
        toastMessageForNewQuestions();
        toastMessageForChangeQuestion();
    }

    private void addData(){

        if(level == 6){
            level = 1;
            sharedPreferencesForLevel.edit().putInt("storedLevelForMathGame", level).apply();
            return;
        }else if(level == 5){
            level++;
            sharedPreferencesForLevel.edit().putInt("storedLevelForMathGame", level).apply();
            return;
        }else if(level == 4){
            level++;
            sharedPreferencesForLevel.edit().putInt("storedLevelForMathGame", level).apply();
            return;
        }else if(level == 3 || level == 2 || level == 1){
            if(setLevel == 1){
                level++;
                sharedPreferencesForLevel.edit().putInt("storedLevelForMathGame", level).apply();
                setLevel = 0;
                sharedPreferencesForSettingLevel.edit().putInt("storedSetLevelForMathGame", setLevel).apply();
                return;
            }else if(setLevel == 0){
                setLevel = 1;
                sharedPreferencesForSettingLevel.edit().putInt("storedSetLevelForMathGame", setLevel).apply();
                return;
            }
        }
    }

    private void setfonts() {
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/FredokaOne-Regular.ttf");
        textView_MathGame_Question1.setTypeface(typeface);
        textView_MathGame_Question2.setTypeface(typeface);
        textView_MathGame_Question3.setTypeface(typeface);
        textView_MathGame_Operation1.setTypeface(typeface);
        textView_MathGame_Operation2.setTypeface(typeface);
        textView_ForMathGame_result.setTypeface(typeface);
        textView_ForMathGame_Coins.setTypeface(typeface);
        textView_ForMathGame_timer.setTypeface(typeface);
    }

    public void changebutton(View view){
        myMediaPlayerForLast5Min.stopLast5Min();
        vibration.getVibrator(75,this);

        sharedPForAdvAndTotalQuestionCounter.updateMathGameTotalQuestionData(getApplicationContext(),1);

        checkForCoins = sharedPForEnergy.getCoinsData(getApplicationContext());
        if(checkForCoins > 1){
            sharedPForEnergy.updateCoinsData(getApplicationContext(),-1);
        }

        textView_ForMathGame_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
        toastForWrongAnswers.cancel();
        toastForNewQuestions.cancel();
        toastForChangeButton.cancel();
        countDownTimer.cancel();
        startMathGame();
        textView_ForMathGame_result.setText("");
        toastForChangeButton.show();

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

    public boolean onKeyDown(int keyCode, KeyEvent event) { //Geri tuşu basıldığında,
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            animationForKeyboard.cancelAnimation();
            myMediaPlayerForBackground.stopBackgroundMusic();
            myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
            myMediaPlayerForLast5Min.stopLast5Min();
            toastForWrongAnswers.cancel();
            toastForNewQuestions.cancel();
            toastForChangeButton.cancel();
            countDownTimer.cancel();
            Intent intent = new Intent(MathGameForMainActivity.this, MainMenuActivity2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void keyboards(){
        imageView_MathGame_keyboard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 1;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 1;
                }

                textView_ForMathGame_result.setText( numberForResult + "");
            }
        });
        imageView_MathGame_keyboard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 2;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 2;
                }

                textView_ForMathGame_result.setText( numberForResult + "");
            }
        });
        imageView_MathGame_keyboard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 3;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 3;
                }

                textView_ForMathGame_result.setText( numberForResult + "");
            }
        });
        imageView_MathGame_keyboard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 4;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 4;
                }

                textView_ForMathGame_result.setText( numberForResult + "");
            }
        });
        imageView_MathGame_keyboard5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 5;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 5;
                }

                textView_ForMathGame_result.setText( numberForResult + "");
            }
        });
        imageView_MathGame_keyboard6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 6;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 6;
                }

                textView_ForMathGame_result.setText( numberForResult + "");
            }
        });
        imageView_MathGame_keyboard7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 7;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 7;
                }

                textView_ForMathGame_result.setText( numberForResult + "");
            }
        });
        imageView_MathGame_keyboard8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 8;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 8;
                }

                textView_ForMathGame_result.setText( numberForResult + "");
            }
        });
        imageView_MathGame_keyboard9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 9;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 9;
                }

                textView_ForMathGame_result.setText( numberForResult + "");
            }
        });
        imageView_MathGame_keyboard0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 0;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 0;
                }

                textView_ForMathGame_result.setText( numberForResult + "");
            }
        });
        imageView_MathGame_keyboardback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                if(numberForResult > 9){
                    numberForResult = numberForResult / 10;
                    textView_ForMathGame_result.setText( numberForResult + "");
                }else if(numberForResult >= 0 && numberForResult <= 9){
                    numberForResult = 0;
                    textView_ForMathGame_result.setText("");
                }

            }
        });
        imageView_MathGame_keyboardok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getApplicationContext());
                finalstate();
            }
        });
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

    @Override
    protected void onStart() {
        super.onStart();
        if(checkMusicOnOff == 1){
            myMediaPlayerForBackground.playBackgroundMusic(getApplicationContext(),4);
        }

        checkOnPause = false;
    }
}