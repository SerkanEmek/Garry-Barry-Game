package com.serkanemek.garrybarry.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.serkanemek.garrybarry.R;
import com.serkanemek.garrybarry.adapter.DataClassForAdapter;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForMainMenu;
import com.serkanemek.garrybarry.data.SharedPForAdvAndTotalQuestionCounter;
import com.serkanemek.garrybarry.data.SharedPForDiamondCoins;

import java.util.ArrayList;

public class CheckStatisticForNewChallengeActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter arrayAdapter;
    ArrayList<Integer> idArray;
    ArrayList<String> questionArray;
    ArrayList<String> pointArray;
    Integer question, point;
    SQLiteDatabase database;
    DataClassForAdapter dataClassForAdapter;
    TextView textView_for_average;

    ArrayList<Integer> idArrayForShow;
    ArrayList<String> pointArrayForShow;

    TextView textView_StatisticAverage_Title,textView_Statistic_id,textView_Statistic_Point;
    ImageView imageView_For_Statistic;
    SharedPreferences sharedPreferences;
    Integer languageInteger;

    SharedPForDiamondCoins sharedPForDiamondCoins;

    MyMediaPlayerForMainMenu myMediaPlayerForMainMenu;
    Integer checkOnOffPause;

    SharedPForAdvAndTotalQuestionCounter sharedPForAdvAndTotalQuestionCounter;
    Integer checkMusicOnOff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_check_statistic_for_newchallenge);
        textView_for_average = findViewById(R.id.textView_for_average);

        Intent intent = getIntent();
        question = intent.getIntExtra("question" , 0);
        point = intent.getIntExtra("point", 0);

        textView_StatisticAverage_Title = findViewById(R.id.textView_StatisticAverage_Title);
        textView_Statistic_id = findViewById(R.id.textView_Statistic_id);
        textView_Statistic_Point = findViewById(R.id.textView_Statistic_Point);
        imageView_For_Statistic = findViewById(R.id.imageView_For_Statistic);

        listView = findViewById(R.id.listview);
        pointArray = new ArrayList<String>();
        idArray = new ArrayList<Integer>();
        questionArray = new ArrayList<String>();

        idArrayForShow = new ArrayList<>();
        pointArrayForShow = new ArrayList<>();

        dataClassForAdapter = new DataClassForAdapter(pointArrayForShow,idArrayForShow,this);
        listView.setAdapter(dataClassForAdapter);

        if(question != 0){
            addData();
        }

        sharedPForDiamondCoins = new SharedPForDiamondCoins();
        if(point > 60){
            sharedPForDiamondCoins.updateDiamondCoinsData(getApplicationContext(), 1);
        }

        getData();
        showToTable();
        calculateAverage();
        setFonts();

        sharedPreferences = this.getSharedPreferences("com.serkanemek.garrybarry.Activities" , Context.MODE_PRIVATE);
        languageInteger = sharedPreferences.getInt("storedLanguage",0);
        if(languageInteger == 1){
            textView_StatisticAverage_Title.setText("Average");
            textView_Statistic_id.setText("№");
            textView_Statistic_Point.setText("Point");
            imageView_For_Statistic.setImageResource(R.drawable.checkstatistic);
        }else if(languageInteger == 2){
            textView_StatisticAverage_Title.setText("Ortalama");
            textView_Statistic_id.setText("№");
            textView_Statistic_Point.setText("Puan");
            imageView_For_Statistic.setImageResource(R.drawable.checkstatistic_for_turkish);
        }else if(languageInteger == 3){
            textView_StatisticAverage_Title.setText("Среднее");
            textView_Statistic_id.setText("№");
            textView_Statistic_Point.setText("Очко");
            imageView_For_Statistic.setImageResource(R.drawable.checkstatistic_russian);
        }

        myMediaPlayerForMainMenu = new MyMediaPlayerForMainMenu();
        checkOnOffPause = 0;
        sharedPForAdvAndTotalQuestionCounter = new SharedPForAdvAndTotalQuestionCounter();
        checkMusicOnOff = sharedPForAdvAndTotalQuestionCounter.getCheckMusicForOnOff(this);
    }

    private void addData(){

        try {
            String stringQuestion = Integer.toString(question);
            String stringPoint = Integer.toString(point);

            database = this.openOrCreateDatabase("Data",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS data (id INTEGER PRIMARY KEY, question VARCHAR, point VARCHAR)");
            String sqlString = "INSERT INTO data (question,point) VALUES (?,?)";
            SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
            sqLiteStatement.bindString(1,stringQuestion);
            sqLiteStatement.bindString(2,stringPoint);
            sqLiteStatement.execute();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void getData() { //SQl'den verileri getiriyoruz.
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Data",MODE_PRIVATE,null);
            Cursor cursor = database.rawQuery("SELECT * FROM data", null);
            int idIndex = cursor.getColumnIndex("id");
            int questionIndex = cursor.getColumnIndex("question");
            int pointIndex = cursor.getColumnIndex("point");

            //SQL den getirilen verileri Arrayler içine yerleştiriyoruz.
            while (cursor.moveToNext()){
                idArray.add(cursor.getInt(idIndex));
                questionArray.add(cursor.getString(questionIndex));
                pointArray.add(cursor.getString(pointIndex));
            }
            arrayAdapter.notifyDataSetChanged();
            cursor.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void calculateAverage(){
        Integer sum = 0;
        Double result = 0.0;
        ArrayList<Integer> arrayListInteger = new ArrayList<>();
        for (int i = 0; i < pointArray.size() ; i++) {
            arrayListInteger.add(Integer.parseInt(pointArray.get(i)));
        }
        if(!arrayListInteger.isEmpty()){
            for (int i = 0; i <arrayListInteger.size() ; i++) {
                sum += arrayListInteger.get(i);
            }
           result = (int)(((double) sum  / arrayListInteger.size()) * 100) / 100.0;
        }

        textView_for_average.setText(result + "");
    }

    private void showToTable(){//Arrayi ters çeviriyoruz.

        for (int i = 1; i < pointArray.size()  + 1; i++) {
            pointArrayForShow.add(pointArray.get(pointArray.size() - i));
            idArrayForShow.add(idArray.get(idArray.size() - i));
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) { //Geri tuşu basıldığında ne olacağı burada yazıyoruz.
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            checkOnOffPause = 1;
            Intent intent = new Intent(CheckStatisticForNewChallengeActivity.this, MainMenuActivity1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setFonts(){
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/FredokaOne-Regular.ttf");
        textView_for_average.setTypeface(typeface);
        textView_StatisticAverage_Title.setTypeface(typeface);
        textView_Statistic_id.setTypeface(typeface);
        textView_Statistic_Point.setTypeface(typeface);
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