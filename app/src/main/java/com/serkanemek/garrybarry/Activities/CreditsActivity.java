package com.serkanemek.garrybarry.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.serkanemek.garrybarry.R;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForMainMenu;
import com.serkanemek.garrybarry.data.SharedPForAdvAndTotalQuestionCounter;
import com.serkanemek.garrybarry.util.Vibration;

public class CreditsActivity extends AppCompatActivity {

    TextView textView_Credits_DeveloperTitle,textView_Credits_DeveloperName,textView_Credits_MusicsTitle,
            textView_Credits_Musics,textView_Credits_BackgroundsTitle,textView_Credits_Backgrounds1,
            textView_Credits_Backgrounds2,textView_Credits_Backgrounds3,textView_Credits_Backgrounds4,
            textView_Credits_Backgrounds5,textView_Credits_Backgrounds6,textView_Credits_Backgrounds7,
            textView_Credits_Backgrounds8,textView_Credits_Backgrounds9,textView_Credits_Backgrounds10,
            textView_Credits_Backgrounds11,textView_Credits_Backgrounds12,textView_Credits_Backgrounds13,
            textView_Credits_Backgrounds14,textView_Credits_Backgrounds15,textView_Credits_Backgrounds16,
            textView_Credits_Backgrounds17;


    MyMediaPlayerForMainMenu myMediaPlayerForMainMenu;
    Integer checkOnOffPause;

    SharedPForAdvAndTotalQuestionCounter sharedPForAdvAndTotalQuestionCounter;
    Integer checkMusicOnOff;

    Vibration vibration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_credits);
        checkOnOffPause = 0;
        initializing();

        setFonts();

        myMediaPlayerForMainMenu = new MyMediaPlayerForMainMenu();
        checkMusicOnOff = sharedPForAdvAndTotalQuestionCounter.getCheckMusicForOnOff(this);
    }

    public void home_Credits_icon(View view){
        checkOnOffPause = 1;
        vibration.getVibrator(75,getApplicationContext());
        Intent intentToMainMenu = new Intent(getApplicationContext(), MainMenuActivity1.class);
        intentToMainMenu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentToMainMenu);
        finish();
    }

    private void initializing() {
        textView_Credits_DeveloperTitle = findViewById(R.id.textView_Credits_DeveloperTitle);
        textView_Credits_DeveloperName = findViewById(R.id.textView_Credits_DeveloperName);
        textView_Credits_MusicsTitle = findViewById(R.id.textView_Credits_MusicsTitle);
        textView_Credits_Musics = findViewById(R.id.textView_Credits_Musics);
        textView_Credits_BackgroundsTitle = findViewById(R.id.textView_Credits_BackgroundsTitle);
        textView_Credits_Backgrounds1 = findViewById(R.id.textView_Credits_Backgrounds1);
        textView_Credits_Backgrounds2 = findViewById(R.id.textView_Credits_Backgrounds2);
        textView_Credits_Backgrounds3 = findViewById(R.id.textView_Credits_Backgrounds3);
        textView_Credits_Backgrounds4 = findViewById(R.id.textView_Credits_Backgrounds4);
        textView_Credits_Backgrounds5 = findViewById(R.id.textView_Credits_Backgrounds5);
        textView_Credits_Backgrounds6 = findViewById(R.id.textView_Credits_Backgrounds6);
        textView_Credits_Backgrounds7 = findViewById(R.id.textView_Credits_Backgrounds7);
        textView_Credits_Backgrounds8 = findViewById(R.id.textView_Credits_Backgrounds8);
        textView_Credits_Backgrounds9 = findViewById(R.id.textView_Credits_Backgrounds9);
        textView_Credits_Backgrounds10 = findViewById(R.id.textView_Credits_Backgrounds10);
        textView_Credits_Backgrounds11 = findViewById(R.id.textView_Credits_Backgrounds11);
        textView_Credits_Backgrounds12 = findViewById(R.id.textView_Credits_Backgrounds12);
        textView_Credits_Backgrounds13 = findViewById(R.id.textView_Credits_Backgrounds13);
        textView_Credits_Backgrounds14 = findViewById(R.id.textView_Credits_Backgrounds14);
        textView_Credits_Backgrounds15 = findViewById(R.id.textView_Credits_Backgrounds15);
        textView_Credits_Backgrounds16 = findViewById(R.id.textView_Credits_Backgrounds16);
        textView_Credits_Backgrounds17 = findViewById(R.id.textView_Credits_Backgrounds17);




        sharedPForAdvAndTotalQuestionCounter = new SharedPForAdvAndTotalQuestionCounter();
        vibration = new Vibration();
    }

    private void setFonts(){
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/FredokaOne-Regular.ttf");
        textView_Credits_DeveloperTitle.setTypeface(typeface);
        textView_Credits_DeveloperName.setTypeface(typeface);
        textView_Credits_MusicsTitle.setTypeface(typeface);
        textView_Credits_BackgroundsTitle.setTypeface(typeface);

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) { //Geri tuşu basıldığında ne olacağı burada yazıyoruz.
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            checkOnOffPause = 1;
            Intent intent = new Intent(getApplicationContext(), MainMenuActivity1.class);
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