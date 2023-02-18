package com.serkanemek.garrybarry.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.serkanemek.garrybarry.R;
import com.serkanemek.garrybarry.util.Vibration;

public class MathLevelCheckActivity extends AppCompatActivity {

    boolean[] maths;
    CheckBox sum,subs,mult,divi;
    ImageView imageView_level1,imageView_level2,imageView_level3,imageView_level4,imageView_level5,imageView_level6;

    private static final int FILTERED_GREY = Color.argb(155, 185, 185, 185);
    private static final int TRANSPARENT_GREY = Color.argb(0, 185, 185, 185);

    SharedPreferences sharedPreferences;
    Integer languageInteger;

    static Toast toast;

    Vibration vibration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mathlevelcheck);

        vibration = new Vibration();

        imageView_level1 = findViewById(R.id.imageView_level1);
        imageView_level2 = findViewById(R.id.imageView_level2);
        imageView_level3 = findViewById(R.id.imageView_level3);
        imageView_level4 = findViewById(R.id.imageView_level4);
        imageView_level5 = findViewById(R.id.imageView_level5);
        imageView_level6 = findViewById(R.id.imageView_level6);

        maths = new boolean[4];
       // maths = new ArrayList<>();
        for (int i = 0; i < 4 ; i++) {
            maths[i] = false;
        }

        sum = (CheckBox) findViewById(R.id.checkBox_sum);
        subs =(CheckBox) findViewById(R.id.checkBox_subs);
        mult =(CheckBox) findViewById(R.id.checkBox_mult);
        divi =(CheckBox) findViewById(R.id.checkBox_divi);

        sharedPreferences = this.getSharedPreferences("com.serkanemek.garrybarry.Activities" , Context.MODE_PRIVATE);
        languageInteger = sharedPreferences.getInt("storedLanguage",0);

        toastMessage();

    }


    private void addListenerOnButtonClick(){

        try {

            if(sum.isChecked()){
                maths[0] = true;
            }
            if(subs.isChecked()){
                maths[1] = true;
            }
            if(mult.isChecked()){
                maths[2] = true;
            }
            if(divi.isChecked()){
                maths[3] = true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void level_1(View view){
        if(!sum.isChecked() && !subs.isChecked() && !mult.isChecked() && !divi.isChecked()){
            toast.show();
        }else{
            imageView_level1.setColorFilter(FILTERED_GREY);
            addListenerOnButtonClick();
            Intent intent = new Intent(getApplicationContext(), MathGameForPractice_Activity.class);
            intent.putExtra("info",maths);
            intent.putExtra("level",1);
            startActivity(intent);
            finish();
            vibration.getVibrator(75,getApplicationContext());
        }
    }

    public void level_2(View view){
        if(!sum.isChecked() && !subs.isChecked() && !mult.isChecked() && !divi.isChecked()){
            toast.show();

        }else {
            imageView_level2.setColorFilter(FILTERED_GREY);
            addListenerOnButtonClick();
            Intent intent = new Intent(getApplicationContext(), MathGameForPractice_Activity.class);
            intent.putExtra("info", maths);
            intent.putExtra("level", 2);
            startActivity(intent);
            finish();
            vibration.getVibrator(75,getApplicationContext());
        }
    }

    public void level_3(View view){
        if(!sum.isChecked() && !subs.isChecked() && !mult.isChecked() && !divi.isChecked()){
            toast.show();

        }else {
            imageView_level3.setColorFilter(FILTERED_GREY);
            addListenerOnButtonClick();
            Intent intent = new Intent(getApplicationContext(), MathGameForPractice_Activity.class);
            intent.putExtra("info", maths);
            intent.putExtra("level", 3);
            startActivity(intent);
            finish();
            vibration.getVibrator(75,getApplicationContext());
        }
    }

    public void level_4(View view){
        if(!sum.isChecked() && !subs.isChecked() && !mult.isChecked() && !divi.isChecked()){
            toast.show();

        }else {
            imageView_level4.setColorFilter(FILTERED_GREY);
            addListenerOnButtonClick();
            Intent intent = new Intent(getApplicationContext(), MathGameForPractice_Activity.class);
            intent.putExtra("info", maths);
            intent.putExtra("level", 4);
            startActivity(intent);
            finish();
            vibration.getVibrator(75,getApplicationContext());
        }
    }

    public void level_5(View view){
        if(!sum.isChecked() && !subs.isChecked() && !mult.isChecked() && !divi.isChecked()){
            toast.show();

        }else {
            imageView_level5.setColorFilter(FILTERED_GREY);
            addListenerOnButtonClick();
            Intent intent = new Intent(getApplicationContext(), MathGameForPractice_Activity.class);
            intent.putExtra("info", maths);
            intent.putExtra("level", 5);
            startActivity(intent);
            finish();
            vibration.getVibrator(75,getApplicationContext());
        }
    }

    public void level_6(View view){
        if(!sum.isChecked() && !subs.isChecked() && !mult.isChecked() && !divi.isChecked()){
            toast.show();

        }else {
            imageView_level6.setColorFilter(FILTERED_GREY);
            addListenerOnButtonClick();
            Intent intent = new Intent(getApplicationContext(), MathGameForPractice_Activity.class);
            intent.putExtra("info", maths);
            intent.putExtra("level", 6);
            startActivity(intent);
            finish();
            vibration.getVibrator(75,getApplicationContext());
        }
    }

    private void toastMessage(){
        toast = Toast.makeText(this,"Please select one of the math operations",Toast.LENGTH_SHORT);
        if(languageInteger == 1){
            toast.setText("Please select one of the math operations");
        }else if(languageInteger == 2){
            toast.setText("Matematik işlemlerinden birisini seçiniz!");
        }else if(languageInteger == 3){
            toast.setText("Выберите один из математических функций!");
        }


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            toast.cancel();
            Intent intent = new Intent(MathLevelCheckActivity.this, MainMenuActivity2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        toast.cancel();
    }
}