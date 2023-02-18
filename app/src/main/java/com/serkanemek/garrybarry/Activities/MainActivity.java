package com.serkanemek.garrybarry.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.serkanemek.garrybarry.R;
import com.serkanemek.garrybarry.util.Vibration;

public class MainActivity extends AppCompatActivity {

    ImageView turkish,english,russia;
    private static final int FILTERED_GREY = Color.argb(155, 185, 185, 185);
    private static final int TRANSPARENT_GREY = Color.argb(0, 185, 185, 185);

    SharedPreferences sharedPreferences;
    Integer languageInteger;

    Vibration vibration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //res-values-styles.xml 'deki DarkActionBarı ı silip yerine NoActionBar yazıyoruz.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, //Saat ve Şarj vs en yukarıyı kapatıyor.
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        turkish = findViewById(R.id.imageView_turkish);
        english = findViewById(R.id.imageView_english);
        russia = findViewById(R.id.imageView_russia);

        sharedPreferences = this.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
        languageInteger = sharedPreferences.getInt("storedLanguage",0);

        if(languageInteger == 1){
            //TODO go to mainmenu with languageinteger code.
            Intent intent = new Intent(MainActivity.this, MainMenuActivity1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }else if(languageInteger == 2){
            Intent intent = new Intent(MainActivity.this, MainMenuActivity1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }else if(languageInteger == 3){
            Intent intent = new Intent(MainActivity.this, MainMenuActivity1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }


        vibration = new Vibration();


    }

    public void english(View view){

        try {
            languageInteger = 1;
            vibration.getVibrator(75,getApplicationContext());
            english.setColorFilter(FILTERED_GREY);
            sharedPreferences.edit().putInt("storedLanguage",languageInteger).apply();

            AlertDialog.Builder builderForBegin =new AlertDialog.Builder(this);
            builderForBegin.setTitle(Html.fromHtml("<font color='#FF8B00'><b></b></font>")).setCancelable(false);
            builderForBegin.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>                        </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(MainActivity.this, MainMenuActivity1.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            });
            AlertDialog dialogForBegin = builderForBegin.create();
            dialogForBegin.show();
            dialogForBegin.getWindow().setBackgroundDrawableResource(R.drawable.image_garrybarrymath);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void turkish(View view){
        try {
            languageInteger = 2;
            vibration.getVibrator(75,getApplicationContext());
            turkish.setColorFilter(FILTERED_GREY);
            sharedPreferences.edit().putInt("storedLanguage",languageInteger).apply();

            AlertDialog.Builder builderForBegin =new AlertDialog.Builder(this);
            builderForBegin.setTitle(Html.fromHtml("<font color='#FF8B00'><b></b></font>")).setCancelable(false);
            builderForBegin.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>                       </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Intent intent = new Intent(MainActivity.this, MainMenuActivity1.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                }
            });
            AlertDialog dialogForBegin = builderForBegin.create();
            dialogForBegin.show();
            dialogForBegin.getWindow().setBackgroundDrawableResource(R.drawable.image_garrybarrymath);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void russia(View view){
        try {
            languageInteger = 3;
            vibration.getVibrator(75,getApplicationContext());
            russia.setColorFilter(FILTERED_GREY);
            sharedPreferences.edit().putInt("storedLanguage",languageInteger).apply();

            AlertDialog.Builder builderForBegin =new AlertDialog.Builder(this);
            builderForBegin.setTitle(Html.fromHtml("<font color='#FF8B00'><b></b></font>")).setCancelable(false);
            builderForBegin.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>                        </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Intent intent = new Intent(MainActivity.this, MainMenuActivity1.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                }
            });
            AlertDialog dialogForBegin = builderForBegin.create();
            dialogForBegin.show();
            dialogForBegin.getWindow().setBackgroundDrawableResource(R.drawable.image_garrybarrymath);


        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
