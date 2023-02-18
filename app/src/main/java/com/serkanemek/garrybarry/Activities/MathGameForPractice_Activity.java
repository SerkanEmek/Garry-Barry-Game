package com.serkanemek.garrybarry.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
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
import com.serkanemek.garrybarry.data.SharedPForAdvAndTotalQuestionCounter;
import com.serkanemek.garrybarry.data.SharedPForBackgrounds;
import com.serkanemek.garrybarry.data.SharedPForCoins;
import com.serkanemek.garrybarry.operations.Subs;
import com.serkanemek.garrybarry.operations.Sum;
import com.serkanemek.garrybarry.util.Vibration;

import java.util.Random;

public class MathGameForPractice_Activity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    boolean[] maths;
    int level;

    Sum sum;
    Subs subs;
    Multi multi;
    Divide divide;

    TextView textView_Number1, textView_Number2,textView_Number3, textView_operation, textView_operation2;
    TextView textView_result;

    ImageView imageView_keyboard1,imageView_keyboard2,imageView_keyboard3,imageView_keyboard4,
            imageView_keyboard5,imageView_keyboard6,imageView_keyboard7,imageView_keyboard8,
            imageView_keyboard9,imageView_keyboard0,imageView_keyboardback,imageView_keyboardok;

    ImageView imageView_changequestion,imageView_ForMathGamePractice_HomeButton;


    Integer numberForResult;
    int randomCheckOperation;
    Random randomforOps;
    int result;

    Random rnd;
    Integer randomNumberForAlertDialog;

    SharedPreferences sharedPreferences;
    Integer languageInteger;

    TextView textView_MathGame_For_Coins;
    SharedPForCoins sharedPForEnergy;
    SharedPForAdvAndTotalQuestionCounter sharedPForAdvAndTotalQuestionCounter;

    SharedPForBackgrounds sharedPForBackgrounds;

    static Toast toastForWrongAnswers,toastForNewQuestions;

    MyMediaPlayerForBackground myMediaPlayerForBackground;
    MyMediaPlayerForCorrectAnswer myMediaPlayerForCorrectAnswer;
    Integer checkMusicOnOff;


    Integer rewardedAdCounter;

    AnimationForKeyboard animationForKeyboard;

    Vibration vibration;

    private AdView mAdView;

    //MathGamePractice TEST id: ca-app-pub-3940256099942544/6300978111


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mathgameforpractice_);
        checkBackground();

        rewardedAdCounter = 0;
        getAdvertisement();

        sharedPreferences = this.getSharedPreferences("com.serkanemek.garrybarry.Activities" , Context.MODE_PRIVATE);
        languageInteger = sharedPreferences.getInt("storedLanguage",0);

        startMathGame();

        keyboards();

        myMediaPlayerForBackground = new MyMediaPlayerForBackground();

        myMediaPlayerForCorrectAnswer = new MyMediaPlayerForCorrectAnswer();

        imageView_changequestion = findViewById(R.id.imageView_ForMathGamePractice_changequestion);
        imageView_ForMathGamePractice_HomeButton = findViewById(R.id.imageView_ForMathGamePractice_HomeButton);

        getAnimationEffect();




    }

    private void getAdvertisement() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adViewMathGameForPractice);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void checkBackground() {
        sharedPForBackgrounds = new SharedPForBackgrounds();
        constraintLayout = findViewById(R.id.background_MathGamePractice);
        Integer numberOfBackground = -1;
        numberOfBackground =  sharedPForBackgrounds.getMathGamePracticeBackground(getApplicationContext());
        if(numberOfBackground == 1){
            constraintLayout.setBackgroundResource(R.drawable.bg_math_practice_01);
        }else if(numberOfBackground == 2){
            constraintLayout.setBackgroundResource(R.drawable.bg_math_practice_02);
        }else if(numberOfBackground == 3){
            constraintLayout.setBackgroundResource(R.drawable.bg_math_practice_03);
        }else if(numberOfBackground == 4){
            constraintLayout.setBackgroundResource(R.drawable.bg_darkmood);
        }

    }

    private void startMathGame(){
        mathOperationsCheckIntent();
        initializing();
        checkOperations(randomCheckOperation);
        setfonts();
        imageView_keyboardok.setEnabled(true);

    }

    private void getAnimationEffect() {
        animationForKeyboard = new AnimationForKeyboard();
        animationForKeyboard.animationForKeyboardInitializingforRotationForHomeButton(getApplicationContext(),800,"rotation",imageView_ForMathGamePractice_HomeButton);
        animationForKeyboard.animationForKeyboardInitializingforRotationForChangeButton(getApplicationContext(),800,"rotation",imageView_changequestion);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard2);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard7);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard4);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard9);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard1);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard6);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard5);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard0);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboardback);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboardok);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard3);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_keyboard8);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"push_down_in",textView_result);
    }

    private void mathOperationsCheckIntent(){
        maths = new boolean[4];
        Intent intent = getIntent();
        level = intent.getIntExtra("level",0);
        maths = intent.getBooleanArrayExtra("info");
    }

    private void checkOperations(int randomCheckOperation) {
        if(maths[0] == true && randomCheckOperation == 0){
            textView_Number1.setText(sum.getNumber1() + "");
            textView_Number2.setText(sum.getNumber2() + "");
            textView_operation.setText("+");
            result = sum.calculater(level);

            if(level == 3 || level == 4 || level == 5 || level == 6){
                textView_Number3.setAlpha(1.0f);
                textView_Number3.setText(sum.getNumber3() + "");
                textView_operation2.setAlpha(1.0f);
                textView_operation2.setText("+");
            }

        } else if(maths[1] == true && randomCheckOperation == 1){
            textView_Number1.setText(subs.getNumber1() + "");
            textView_Number2.setText(subs.getNumber2() + "");
            textView_operation.setText("-");

            result = subs.calculater(level);

        } else if(maths[2] == true && randomCheckOperation == 2){
            textView_Number1.setText(multi.getNumber1() + "");
            textView_Number2.setText(multi.getNumber2() + "");
            textView_operation.setText("x");

            result = multi.calculater(level);

            if(level == 3 || level == 5 || level == 6){
                textView_Number3.setAlpha(1.0f);
                textView_Number3.setText(multi.getNumber3() + "");
                textView_operation2.setAlpha(1.0f);
                textView_operation2.setText("x");
            }

        }else if(maths[3] == true && randomCheckOperation == 3){
            textView_Number1.setText(divide.getNumber1() + "");
            textView_Number2.setText(divide.getNumber2() + "");
            textView_operation.setText("÷");

            result = divide.calculater(level);

        } else{
            randomCheckOperation = randomforOps.nextInt(4);
            checkOperations(randomCheckOperation);
        }
    }

    private void finalstate(){
        try {
            sharedPForAdvAndTotalQuestionCounter.updateMathGameForPracticeTotalQuestionData(getApplicationContext(),1);

            AlertDialog.Builder builder =new AlertDialog.Builder(this);
             if(result == Integer.parseInt(textView_result.getText().toString())){
                 imageView_keyboardok.setEnabled(false);
                 myMediaPlayerForCorrectAnswer.playCorrectAnswerSound(getApplicationContext());
                 sharedPForEnergy.updateCoinsData(getApplicationContext(),2);
                 sharedPForAdvAndTotalQuestionCounter.updateMathGameForPracticeCorrectAnswersData(getApplicationContext(),1);

                 if(rewardedAdCounter == 0){
                     rewardedAdCounter++;
                     Log.d("TAG" , "rewardedAdCounter = 0");
                 }else if(rewardedAdCounter == 1){
                     sharedPForAdvAndTotalQuestionCounter.updateAdvertisementCounterData(getApplicationContext(),1);
                     rewardedAdCounter = 0;
                     Log.d("TAG" , "rewardedAdCounter = 1");
                 }

                 if(languageInteger == 1){
                     builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>RESULT</b></font>")).setCancelable(false);
                     builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Correct answer <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Congratulations</b></font>"));
                     builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                             toastForNewQuestions.show();
                             textView_result.setText("");
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
                             textView_result.setText("");
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
                             textView_result.setText("");
                             startMathGame();
                         }
                     });

                 }
                 AlertDialog dialog = builder.create();
                 dialog.show();

                 if(randomNumberForAlertDialog == 0){
                     dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_next_2coins_1);
                 }else if(randomNumberForAlertDialog == 1){
                     dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_next_2coins_2);
                 }else if(randomNumberForAlertDialog == 2){
                     dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_next_2coins_3);
                 }

                 vibration.getVibrator(75, this);
            } else {

                 vibration.getVibrator(150, this);

                 toastForWrongAnswers.show();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initializing(){
        sum = new Sum(level);
        subs = new Subs(level);
        multi = new Multi(level);
        divide = new Divide(level);

        textView_Number1 = findViewById(R.id.textView_number1);
        textView_Number2 = findViewById(R.id.textView_number2);
        textView_Number3 = findViewById(R.id.textView_number3);
        textView_Number3.setAlpha(0.0f);
        textView_operation = findViewById(R.id.textView_operation);
        textView_operation2 = findViewById(R.id.textView_operation2);
        textView_operation2.setAlpha(0.0f);
        textView_result = findViewById(R.id.textView_ForMath_result);

        randomforOps = new Random();
        randomCheckOperation = randomforOps.nextInt(4);

        imageView_keyboard1 = findViewById(R.id.imageView_keyboard1);
        imageView_keyboard2 = findViewById(R.id.imageView_keyboard2);
        imageView_keyboard3 = findViewById(R.id.imageView_keyboard3);
        imageView_keyboard4 = findViewById(R.id.imageView_keyboard4);
        imageView_keyboard5 = findViewById(R.id.imageView_keyboard5);
        imageView_keyboard6 = findViewById(R.id.imageView_keyboard6);
        imageView_keyboard7 = findViewById(R.id.imageView_keyboard7);
        imageView_keyboard8 = findViewById(R.id.imageView_keyboard8);
        imageView_keyboard9 = findViewById(R.id.imageView_keyboard9);
        imageView_keyboard0 = findViewById(R.id.imageView_keyboard0);
        imageView_keyboardback = findViewById(R.id.imageView_keyboardback);
        imageView_keyboardok = findViewById(R.id.imageView_keyboardok);
        numberForResult = -1;

        rnd = new Random();
        randomNumberForAlertDialog = rnd.nextInt(3);

        textView_MathGame_For_Coins = findViewById(R.id.textView_MathGame_For_Coins);
        sharedPForEnergy = new SharedPForCoins();
        textView_MathGame_For_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
        sharedPForAdvAndTotalQuestionCounter = new SharedPForAdvAndTotalQuestionCounter();

        vibration = new Vibration();

        toastMessageForWrongAnswers();
        toastMessageForNewQuestions();

        checkMusicOnOff = sharedPForAdvAndTotalQuestionCounter.getCheckMusicForOnOff(this);

    }

    private void toastMessageForWrongAnswers(){
        toastForWrongAnswers = Toast.makeText(this,"Wrong Answer, Try Again",Toast.LENGTH_SHORT);
        if(languageInteger == 1){
            toastForWrongAnswers.setText("Wrong Answer, Try Again!");
        }else if(languageInteger == 2){
            toastForWrongAnswers.setText("Yanlış Cevap, Tekrar Deneyiniz!");
        }else if(languageInteger == 3){
            toastForWrongAnswers.setText("Неверный ответ, Попробуй еще раз!");
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

    private void setfonts() {
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/FredokaOne-Regular.ttf");
        textView_Number1.setTypeface(typeface);
        textView_Number2.setTypeface(typeface);
        textView_Number3.setTypeface(typeface);
        textView_operation.setTypeface(typeface);
        textView_operation2.setTypeface(typeface);
        textView_result.setTypeface(typeface);

        textView_MathGame_For_Coins.setTypeface(typeface);
    }

    public void changequestion(View view){
        sharedPForAdvAndTotalQuestionCounter.updateMathGameForPracticeTotalQuestionData(getApplicationContext(),1);
        vibration.getVibrator(75, this);
        toastForNewQuestions.cancel();
        toastForWrongAnswers.cancel();
        textView_result.setText("");
        startMathGame();
        toastForNewQuestions.show();//We have to use after startgame, if we wont it's doesn't work.

    }

    public void homeicon(View view){
        animationForKeyboard.cancelAnimation();
        myMediaPlayerForBackground.stopBackgroundMusic();
        myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
        vibration.getVibrator(75, this);
        toastForNewQuestions.cancel();
        toastForWrongAnswers.cancel();
        Intent intenttoHome = new Intent(getApplicationContext(), MainMenuActivity2.class);
        intenttoHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intenttoHome);
        finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            animationForKeyboard.cancelAnimation();
            myMediaPlayerForBackground.stopBackgroundMusic();
            myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
            toastForNewQuestions.cancel();
            toastForWrongAnswers.cancel();
            Intent intent = new Intent(MathGameForPractice_Activity.this, MainMenuActivity2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void keyboards(){
        imageView_keyboard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75, getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 1;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 1;
                }

                textView_result.setText( numberForResult + "");
            }
        });
        imageView_keyboard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75, getApplicationContext());
               if(numberForResult == -1){
                   numberForResult = 2;
               }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 2;
               }

                textView_result.setText( numberForResult + "");
            }
        });
        imageView_keyboard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75, getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 3;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 3;
                }

                textView_result.setText( numberForResult + "");
            }
        });
        imageView_keyboard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75, getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 4;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 4;
                }

                textView_result.setText( numberForResult + "");
            }
        });
        imageView_keyboard5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75, getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 5;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 5;
                }

                textView_result.setText( numberForResult + "");
            }
        });
        imageView_keyboard6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75, getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 6;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 6;
                }

                textView_result.setText( numberForResult + "");
            }
        });
        imageView_keyboard7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75, getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 7;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 7;
                }

                textView_result.setText( numberForResult + "");
            }
        });
        imageView_keyboard8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75, getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 8;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 8;
                }

                textView_result.setText( numberForResult + "");
            }
        });
        imageView_keyboard9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75, getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 9;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 9;
                }

                textView_result.setText( numberForResult + "");
            }
        });
        imageView_keyboard0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75, getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 0;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 0;
                }

                textView_result.setText( numberForResult + "");
            }
        });
        imageView_keyboardback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75, getApplicationContext());
               if(numberForResult > 9){
                   numberForResult = numberForResult / 10;
                   textView_result.setText( numberForResult + "");
               }else if(numberForResult >= 0 && numberForResult <= 9){
                   numberForResult = 0;
                   textView_result.setText("");
               }

            }
        });
        imageView_keyboardok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75, getApplicationContext());
                finalstate();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        toastForWrongAnswers.cancel();
        toastForNewQuestions.cancel();
        myMediaPlayerForBackground.pauseBackgroundMusic();
        myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
        animationForKeyboard.pauseAnimation();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(checkMusicOnOff == 1){
            myMediaPlayerForBackground.playBackgroundMusic(getApplicationContext(),5);
        }
        animationForKeyboard.resumeAnimation();
    }
}