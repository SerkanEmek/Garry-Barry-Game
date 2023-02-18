package com.serkanemek.garrybarry.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.serkanemek.garrybarry.R;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForMainMenu;
import com.serkanemek.garrybarry.data.SharedPForAdvAndTotalQuestionCounter;
import com.serkanemek.garrybarry.util.Vibration;

public class CheckStatisticForMainGames extends AppCompatActivity {

    TextView textView_ForStatistic_Memory_TotalQuestion,textView_ForStatistic_Memory_CorrectAnswer,
            textView_ForStatistic_Memory_WrongAnswer,textView_ForStatistic_Memory_Percent,
            textView_ForStatistic_IQGame_TotalQuestion,textView_ForStatistic_IQGame_CorrectAnswer,
            textView_ForStatistic_IQGame_WrongAnswer,textView_ForStatistic_IQGame_Percent,
            textView_ForStatistic_MathGame_TotalQuestion,textView_ForStatistic_MathGame_CorrectAnswer,
            textView_ForStatistic_MathGame_WrongAnswer,textView_ForStatistic_MathGame_Percent,
            textView_ForStatistic_MathPractice_TotalQuestion,textView_ForStatistic_MathPractice_CorrectAnswer,
            textView_ForStatistic_MathPractice_WrongAnswer,textView_ForStatistic_MathPractice_Percent,
            textView_Statistic_ForMakeReset;

    ImageView imageView_Statistic_MemoryGame,imageView_Statistic_IQGame,imageView_Statistic_MathGame,
            imageView_Statistic_MathPracticeGame;

    ImageView imageView_Statistic_TotalQuestion,imageView_Statistic_CorrectAnswer,
            imageView_Statistic_WrongAnswer,imageView_Statistic_Percent,
            imageView_Statistic_MathPractice_TotalQuestion,imageView_Statistic_MathPractice_CorrectAnswer,
            imageView_Statistic_MathPractice_WrongAnswer,imageView_Statistic_MathPractice_Percent;

    SharedPForAdvAndTotalQuestionCounter sharedPForAdvAndTotalQuestionCounter;
    SharedPreferences sharedPreferences;
    Integer languageInteger;

    MyMediaPlayerForMainMenu myMediaPlayerForMainMenu;
    Integer checkOnOffPause;
    Integer checkMusicOnOff;

    Vibration vibration;

    private AdView mAdView;

    //Setting Statistic Background Banner TEST id: ca-app-pub-3940256099942544/6300978111

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_check_statistic_for_main_games);

        getAdvertisement();

        initializing();

        getMemoryStatisticTable();
        getIqStatisticTable();
        getMathStatisticTable();
        getMathPracticeStatisticTable();
        setFonts();
        checkLanguage();

        myMediaPlayerForMainMenu = new MyMediaPlayerForMainMenu();
        checkOnOffPause = 0;
        checkMusicOnOff = sharedPForAdvAndTotalQuestionCounter.getCheckMusicForOnOff(this);

    }


    private void getAdvertisement() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adViewCheckStatisticForAll);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    private void checkLanguage() {

        sharedPreferences = this.getSharedPreferences("com.serkanemek.garrybarry.Activities" , Context.MODE_PRIVATE);
        languageInteger = sharedPreferences.getInt("storedLanguage",0);
        if(languageInteger == 1){

            imageView_Statistic_MemoryGame.setImageResource(R.drawable.statistic_memorygame_english);
            imageView_Statistic_IQGame.setImageResource(R.drawable.statistic_iqgame_english);
            imageView_Statistic_MathGame.setImageResource(R.drawable.statistic_mathgame_english);
            imageView_Statistic_MathPracticeGame.setImageResource(R.drawable.statistic_mathpractice_english);

            imageView_Statistic_TotalQuestion.setImageResource(R.drawable.statistic_totalquestion_english);
            imageView_Statistic_CorrectAnswer.setImageResource(R.drawable.statistic_correctanswer_english);
            imageView_Statistic_WrongAnswer.setImageResource(R.drawable.statistic_wronganswer_english);
            imageView_Statistic_Percent.setImageResource(R.drawable.statisticpercent);
            imageView_Statistic_MathPractice_TotalQuestion.setImageResource(R.drawable.statistic_totalquestion_english);
            imageView_Statistic_MathPractice_CorrectAnswer.setImageResource(R.drawable.statistic_correctanswer_english);
            imageView_Statistic_MathPractice_WrongAnswer.setImageResource(R.drawable.statistic_wronganswer_english);
            imageView_Statistic_MathPractice_Percent.setImageResource(R.drawable.statisticpercent);

            textView_Statistic_ForMakeReset.setText("RESET");


        }else if(languageInteger == 2){

            imageView_Statistic_MemoryGame.setImageResource(R.drawable.statistic_memorygame_turkish);
            imageView_Statistic_IQGame.setImageResource(R.drawable.statistic_iqgame_turkish);
            imageView_Statistic_MathGame.setImageResource(R.drawable.statistic_mathgame_turkish);
            imageView_Statistic_MathPracticeGame.setImageResource(R.drawable.statistic_mathpractice_turkish);

            imageView_Statistic_TotalQuestion.setImageResource(R.drawable.statistic_totalquestion_turkish);
            imageView_Statistic_CorrectAnswer.setImageResource(R.drawable.statistic_correctanswer_turkish);
            imageView_Statistic_WrongAnswer.setImageResource(R.drawable.statistic_wronganswer_turkish);
            imageView_Statistic_Percent.setImageResource(R.drawable.statisticpercent);
            imageView_Statistic_MathPractice_TotalQuestion.setImageResource(R.drawable.statistic_totalquestion_turkish);
            imageView_Statistic_MathPractice_CorrectAnswer.setImageResource(R.drawable.statistic_correctanswer_turkish);
            imageView_Statistic_MathPractice_WrongAnswer.setImageResource(R.drawable.statistic_wronganswer_turkish);
            imageView_Statistic_MathPractice_Percent.setImageResource(R.drawable.statisticpercent);

            textView_Statistic_ForMakeReset.setText("SIFIRLA");

        }else if(languageInteger == 3){

            imageView_Statistic_MemoryGame.setImageResource(R.drawable.statistic_memorygame_russian);
            imageView_Statistic_IQGame.setImageResource(R.drawable.statistic_iqgame_russian);
            imageView_Statistic_MathGame.setImageResource(R.drawable.statistic_mathgame_russian);
            imageView_Statistic_MathPracticeGame.setImageResource(R.drawable.statistic_mathpractice_russian);

            imageView_Statistic_TotalQuestion.setImageResource(R.drawable.statistic_totalquestion_russian);
            imageView_Statistic_CorrectAnswer.setImageResource(R.drawable.statistic_correctanswer_russian);
            imageView_Statistic_WrongAnswer.setImageResource(R.drawable.statistic_wronganswer_russian);
            imageView_Statistic_Percent.setImageResource(R.drawable.statisticpercent);
            imageView_Statistic_MathPractice_TotalQuestion.setImageResource(R.drawable.statistic_totalquestion_russian);
            imageView_Statistic_MathPractice_CorrectAnswer.setImageResource(R.drawable.statistic_correctanswer_russian);
            imageView_Statistic_MathPractice_WrongAnswer.setImageResource(R.drawable.statistic_wronganswer_russian);
            imageView_Statistic_MathPractice_Percent.setImageResource(R.drawable.statisticpercent);

            textView_Statistic_ForMakeReset.setText("СБРОС");
        }
    }

    private void getMathPracticeStatisticTable() {
        Integer mathPracticeTotalQuestion = sharedPForAdvAndTotalQuestionCounter.getMathGameForPracticeTatolQuestionData(getApplicationContext());
        textView_ForStatistic_MathPractice_TotalQuestion.setText(mathPracticeTotalQuestion + "");
        Integer mathPracticeCorrectAnswer = sharedPForAdvAndTotalQuestionCounter.getMathGameForPracticeCorrectAnswersData(getApplicationContext());
        textView_ForStatistic_MathPractice_CorrectAnswer.setText(mathPracticeCorrectAnswer + "");
        Integer mathPracticeWrongAnswer = mathPracticeTotalQuestion - mathPracticeCorrectAnswer;
        textView_ForStatistic_MathPractice_WrongAnswer.setText(mathPracticeWrongAnswer +"");
        if(mathPracticeTotalQuestion != 0 && mathPracticeCorrectAnswer != 0){
            double mathPracticePercent = (int) (((100 * mathPracticeCorrectAnswer) / mathPracticeTotalQuestion) * 100) / 100.0;
            textView_ForStatistic_MathPractice_Percent.setText(mathPracticePercent + "");
            textView_ForStatistic_MathPractice_TotalQuestion.setTextColor(Color.WHITE);
            textView_ForStatistic_MathPractice_CorrectAnswer.setTextColor(Color.WHITE);
            textView_ForStatistic_MathPractice_WrongAnswer.setTextColor(Color.WHITE);
            textView_ForStatistic_MathPractice_Percent.setTextColor(Color.WHITE);
        }
    }

    private void getMathStatisticTable() {
        Integer mathTotalQuestion = sharedPForAdvAndTotalQuestionCounter.getMathGameTotalQuestionData(getApplicationContext());
        textView_ForStatistic_MathGame_TotalQuestion.setText(mathTotalQuestion + "");
        Integer mathCorrectAnswer = sharedPForAdvAndTotalQuestionCounter.getMathGameCorrectAnswersData(getApplicationContext());
        textView_ForStatistic_MathGame_CorrectAnswer.setText(mathCorrectAnswer + "");
        Integer mathWrongAnswer = mathTotalQuestion - mathCorrectAnswer;
        textView_ForStatistic_MathGame_WrongAnswer.setText(mathWrongAnswer + "");
        if(mathTotalQuestion != 0 && mathCorrectAnswer != 0){
            double mathPercent = (int) (((100 * mathCorrectAnswer) / mathTotalQuestion) * 100) / 100.0;
            textView_ForStatistic_MathGame_Percent.setText(mathPercent + "");
            textView_ForStatistic_MathGame_TotalQuestion.setTextColor(Color.WHITE);
            textView_ForStatistic_MathGame_CorrectAnswer.setTextColor(Color.WHITE);
            textView_ForStatistic_MathGame_WrongAnswer.setTextColor(Color.WHITE);
            textView_ForStatistic_MathGame_Percent.setTextColor(Color.WHITE);
        }

    }

    private void getIqStatisticTable() {
        Integer iqTotalQuestion = sharedPForAdvAndTotalQuestionCounter.getIqGameTotalQuestionData(getApplicationContext());
        textView_ForStatistic_IQGame_TotalQuestion.setText(iqTotalQuestion + "");
        Integer iqCorrectAnswer = sharedPForAdvAndTotalQuestionCounter.getIqGameCorrectAnswersData(getApplicationContext());
        textView_ForStatistic_IQGame_CorrectAnswer.setText(iqCorrectAnswer + "");
        Integer iqWrongAnswer = iqTotalQuestion - iqCorrectAnswer;
        textView_ForStatistic_IQGame_WrongAnswer.setText(iqWrongAnswer + "");
        if(iqTotalQuestion != 0 && iqCorrectAnswer != 0 ){
            double iqPercent = (int) (((100 * iqCorrectAnswer) / iqTotalQuestion) * 100) / 100.0;
            textView_ForStatistic_IQGame_Percent.setText(iqPercent + "");
            textView_ForStatistic_IQGame_TotalQuestion.setTextColor(Color.WHITE);
            textView_ForStatistic_IQGame_CorrectAnswer.setTextColor(Color.WHITE);
            textView_ForStatistic_IQGame_WrongAnswer.setTextColor(Color.WHITE);
            textView_ForStatistic_IQGame_Percent.setTextColor(Color.WHITE);
        }
    }

    private void getMemoryStatisticTable() {
        Integer memoryTotalQuestion = sharedPForAdvAndTotalQuestionCounter.getMemoryGameTotalQuestionData(getApplicationContext());
        textView_ForStatistic_Memory_TotalQuestion.setText(memoryTotalQuestion + "");
        Integer memoryCorrectAnswer = sharedPForAdvAndTotalQuestionCounter.getMemoryGameCorrectAnswersData(getApplicationContext());
        textView_ForStatistic_Memory_CorrectAnswer.setText(memoryCorrectAnswer + "");
        Integer memoryWrongAnswer = memoryTotalQuestion - memoryCorrectAnswer;
        textView_ForStatistic_Memory_WrongAnswer.setText(memoryWrongAnswer + "");
        if(memoryTotalQuestion != 0 && memoryCorrectAnswer !=0 ){
            double memoryPercent = (int) (((100 * memoryCorrectAnswer) / memoryTotalQuestion) * 100) / 100.0;
            textView_ForStatistic_Memory_Percent.setText(memoryPercent + "");
            textView_ForStatistic_Memory_TotalQuestion.setTextColor(Color.WHITE);
            textView_ForStatistic_Memory_CorrectAnswer.setTextColor(Color.WHITE);
            textView_ForStatistic_Memory_WrongAnswer.setTextColor(Color.WHITE);
            textView_ForStatistic_Memory_Percent.setTextColor(Color.WHITE);
        }

    }

    public void make_it_reset(View view){
        try {
            vibration.getVibrator(150,getApplicationContext());
            sharedPForAdvAndTotalQuestionCounter.makeThemAllReset();

            Intent intent = new Intent(this,CheckStatisticForMainGames.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initializing() {
        textView_ForStatistic_Memory_TotalQuestion = findViewById(R.id.textView_ForStatistic_Memory_TotalQuestion);
        textView_ForStatistic_Memory_CorrectAnswer = findViewById(R.id.textView_ForStatistic_Memory_CorrectAnswer);
        textView_ForStatistic_Memory_WrongAnswer = findViewById(R.id.textView_ForStatistic_Memory_WrongAnswer);
        textView_ForStatistic_Memory_Percent = findViewById(R.id.textView_ForStatistic_Memory_Percent);
        textView_ForStatistic_IQGame_TotalQuestion = findViewById(R.id.textView_ForStatistic_IQGame_TotalQuestion);
        textView_ForStatistic_IQGame_CorrectAnswer = findViewById(R.id.textView_ForStatistic_IQGame_CorrectAnswer);
        textView_ForStatistic_IQGame_WrongAnswer = findViewById(R.id.textView_ForStatistic_IQGame_WrongAnswer);
        textView_ForStatistic_IQGame_Percent = findViewById(R.id.textView_ForStatistic_IQGame_Percent);
        textView_ForStatistic_MathGame_TotalQuestion = findViewById(R.id.textView_ForStatistic_MathGame_TotalQuestion);
        textView_ForStatistic_MathGame_CorrectAnswer = findViewById(R.id.textView_ForStatistic_MathGame_CorrectAnswer);
        textView_ForStatistic_MathGame_WrongAnswer = findViewById(R.id.textView_ForStatistic_MathGame_WrongAnswer);
        textView_ForStatistic_MathGame_Percent = findViewById(R.id.textView_ForStatistic_MathGame_Percent);
        textView_ForStatistic_MathPractice_TotalQuestion = findViewById(R.id.textView_ForStatistic_MathPractice_TotalQuestion);
        textView_ForStatistic_MathPractice_CorrectAnswer = findViewById(R.id.textView_ForStatistic_MathPractice_CorrectAnswer);
        textView_ForStatistic_MathPractice_WrongAnswer = findViewById(R.id.textView_ForStatistic_MathPractice_WrongAnswer);
        textView_ForStatistic_MathPractice_Percent = findViewById(R.id.textView_ForStatistic_MathPractice_Percent);
        textView_Statistic_ForMakeReset = findViewById(R.id.textView_Statistic_ForMakeReset);

        imageView_Statistic_MemoryGame = findViewById(R.id.imageView_Statistic_MemoryGame);
        imageView_Statistic_IQGame = findViewById(R.id.imageView_Statistic_IQGame);
        imageView_Statistic_MathGame = findViewById(R.id.imageView_Statistic_MathGame);
        imageView_Statistic_MathPracticeGame = findViewById(R.id.imageView_Statistic_MathPracticeGame);
        imageView_Statistic_TotalQuestion = findViewById(R.id.imageView_Statistic_TotalQuestion);
        imageView_Statistic_CorrectAnswer = findViewById(R.id.imageView_Statistic_CorrectAnswer);
        imageView_Statistic_WrongAnswer = findViewById(R.id.imageView_Statistic_WrongAnswer);
        imageView_Statistic_Percent = findViewById(R.id.imageView_Statistic_Percent);
        imageView_Statistic_MathPractice_TotalQuestion = findViewById(R.id.imageView_Statistic_MathPractice_TotalQuestion);
        imageView_Statistic_MathPractice_CorrectAnswer = findViewById(R.id.imageView_Statistic_MathPractice_CorrectAnswer);
        imageView_Statistic_MathPractice_WrongAnswer = findViewById(R.id.imageView_Statistic_MathPractice_WrongAnswer);
        imageView_Statistic_MathPractice_Percent = findViewById(R.id.imageView_Statistic_MathPractice_Percent);

        sharedPForAdvAndTotalQuestionCounter = new SharedPForAdvAndTotalQuestionCounter();

        vibration = new Vibration();

    }

    private void setFonts(){
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/FredokaOne-Regular.ttf");
        textView_ForStatistic_Memory_TotalQuestion.setTypeface(typeface);
        textView_ForStatistic_Memory_CorrectAnswer.setTypeface(typeface);
        textView_ForStatistic_Memory_WrongAnswer.setTypeface(typeface);
        textView_ForStatistic_Memory_Percent.setTypeface(typeface);
        textView_ForStatistic_IQGame_TotalQuestion.setTypeface(typeface);
        textView_ForStatistic_IQGame_CorrectAnswer.setTypeface(typeface);
        textView_ForStatistic_IQGame_WrongAnswer.setTypeface(typeface);
        textView_ForStatistic_IQGame_Percent.setTypeface(typeface);
        textView_ForStatistic_MathGame_TotalQuestion.setTypeface(typeface);
        textView_ForStatistic_MathGame_CorrectAnswer.setTypeface(typeface);
        textView_ForStatistic_MathGame_WrongAnswer.setTypeface(typeface);
        textView_ForStatistic_MathGame_Percent.setTypeface(typeface);
        textView_ForStatistic_MathPractice_TotalQuestion.setTypeface(typeface);
        textView_ForStatistic_MathPractice_CorrectAnswer.setTypeface(typeface);
        textView_ForStatistic_MathPractice_WrongAnswer.setTypeface(typeface);
        textView_ForStatistic_MathPractice_Percent.setTypeface(typeface);
        textView_Statistic_ForMakeReset.setTypeface(typeface);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) { //Geri tuşu basıldığında,
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            checkOnOffPause = 1;
            vibration.getVibrator(75,getApplicationContext());
            Intent intent = new Intent(this, MainMenuActivity1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
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