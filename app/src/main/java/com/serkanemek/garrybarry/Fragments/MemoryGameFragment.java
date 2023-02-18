package com.serkanemek.garrybarry.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.os.PowerManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.serkanemek.garrybarry.Activities.CheckStatisticForNewChallengeActivity;
import com.serkanemek.garrybarry.R;
import com.serkanemek.garrybarry.operations.MemoryNumbers;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForCorrectAnswer;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForLast5Min;
import com.serkanemek.garrybarry.util.Vibration;

import java.util.Random;


public class MemoryGameFragment extends Fragment {

    TextView textView_NewRemember1,textView_NewRemember2,textView_NewRemember3,textView_NewRemember4,textView_NewRemember5;
    TextView editTextNumber_NewResult1,editTextNumber_NewResult2,editTextNumber_NewResult3,editTextNumber_NewResult4,editTextNumber_NewResult5;
    TextView textView_timer2,textView_level2, textView_Score_ForMemoryGame,textView_For_NewEp_Memory_Comment;
    Integer level_for_background, level, point;
    Random randomforbackground;
    MemoryNumbers memorygame;
    boolean result;
    CountDownTimer countDownTimer,secondCountDownTimer;
    Integer secondCountDownTimerCounter;

    ImageView imageView_MemoryGame_Fragment_Keyboard1,imageView_MemoryGame_Fragment_Keyboard2,
            imageView_MemoryGame_Fragment_Keyboard3,imageView_MemoryGame_Fragment_Keyboard4,
            imageView_MemoryGame_Fragment_Keyboard5,imageView_MemoryGame_Fragment_Keyboard6,
            imageView_MemoryGame_Fragment_Keyboard7,imageView_MemoryGame_Fragment_Keyboard8,
            imageView_MemoryGame_Fragment_Keyboard9,imageView_MemoryGame_Fragment_Keyboard0,
            imageView_MemoryGame_Fragment_Keyboardback,imageView_MemoryGame_Fragment_Keyboardok;

    Integer numberForResult;
    Integer checkKeyboardForResult;
    TextView editText;


    SharedPreferences sharedPreferences;
    Integer languageInteger;

    boolean[] numberforresultview = new boolean[6];

    Random rnd;
    Integer randomNumberForAlertDialog;

    MyMediaPlayerForCorrectAnswer myMediaPlayerForCorrectAnswer;
    MyMediaPlayerForLast5Min myMediaPlayerForLast5Min;


    Boolean checkOnPause;

    Vibration vibration;

    public MemoryGameFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onKeyDownForFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_memory_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializing(view);
        myMediaPlayerForCorrectAnswer = new MyMediaPlayerForCorrectAnswer();
        myMediaPlayerForLast5Min = new MyMediaPlayerForLast5Min();



        if(getArguments() != null){
            level = MemoryGameFragmentArgs.fromBundle(getArguments()).getLevel();
            textView_level2.setText("Question: " + level);
            point = MemoryGameFragmentArgs.fromBundle(getArguments()).getPoint();
            textView_Score_ForMemoryGame.setText("Score: " + point);
        }



        sharedPreferences = getActivity().getSharedPreferences("com.serkanemek.garrybarry.Activities" , Context.MODE_PRIVATE);
        languageInteger = sharedPreferences.getInt("storedLanguage",0);

        if(languageInteger == 1){
            textView_level2.setText("Question: " + level);
            textView_Score_ForMemoryGame.setText("Score: " + point);
        }else if(languageInteger == 2){
            textView_level2.setText("Soru: " + level);
            textView_Score_ForMemoryGame.setText("Puan: " + point);
        }else if(languageInteger == 3){
            textView_level2.setText("Вопрос: " + level);
            textView_Score_ForMemoryGame.setText("Очко: " + point);
        }


        getRandomforGame();

        if(level == 1 || level == 5 || level == 9 || level == 13 || level == 17){
            startMemoryGame();
        }

    }



    private void startMemoryGame(){

        try {
            memorygame = new MemoryNumbers(level_for_background);
            getRememberNumbers();
            setFonts();
            timer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void timer(){
        secondCountDownTimerCounter = 0;
        selectResultViewForKeyboardMakeFalseAllNumbers();
        try {
            countDownTimer =  new CountDownTimer(5_000,500){

                @Override
                public void onTick(long l) {

                    if(languageInteger == 1){
                        textView_timer2.setText("" + (l/1_000));
                        textView_For_NewEp_Memory_Comment.setText("Keep the Numbers in mind");
                    }else if(languageInteger == 2){
                        textView_timer2.setText("" + (l/1_000));
                        textView_For_NewEp_Memory_Comment.setText("Sayıları aklında tut!");
                    }else if(languageInteger == 3){
                        textView_timer2.setText("" + (l/1_000));
                        textView_For_NewEp_Memory_Comment.setText("Запомните эти числа!");
                    }
                    setTimerForOnTick(l);

                    editTextNumber_NewResult1.setBackgroundResource(R.drawable.circleforremember);
                    editTextNumber_NewResult2.setBackgroundResource(R.drawable.circleforremember);
                    editTextNumber_NewResult3.setBackgroundResource(R.drawable.circleforremember);
                    editTextNumber_NewResult4.setBackgroundResource(R.drawable.circleforremember);
                    editTextNumber_NewResult5.setBackgroundResource(R.drawable.circleforremember);

                    checkForClickListener(0);
                }
                @Override
                public void onFinish() {
                    selectResultViewForKeyboard2();
                    imageView_MemoryGame_Fragment_Keyboardok.setEnabled(true);
                    if(languageInteger == 1){
                        textView_timer2.setText("0");
                        textView_For_NewEp_Memory_Comment.setText("Please Fill Same Numbers");
                    }else if(languageInteger == 2){
                        textView_timer2.setText("0");
                        textView_For_NewEp_Memory_Comment.setText("Aynı Sayıları Yazalım");
                    }else if(languageInteger == 3){
                        textView_timer2.setText("0");
                        textView_For_NewEp_Memory_Comment.setText("Напишем те же числа");
                    }

                    getEditTextResultNumbers();
                   checkForClickListener(1);

                   try {
                       secondCountDownTimer = new CountDownTimer(15_000, 1_000) {
                           @Override
                           public void onTick(long l) {
                               secondCountDownTimerCounter = 1;
                               if(languageInteger == 1){
                                   textView_timer2.setText("" + (l/1_000));
                               }else if(languageInteger == 2){
                                   textView_timer2.setText("" + (l/1_000));
                               }else if(languageInteger == 3){
                                   textView_timer2.setText("" + (l/1_000));
                               }
                               setTimerForOnTickForLast5MinsSound(l);
                           }
                           @Override
                           public void onFinish() {
                               secondCountDownTimerForFinish(getView());
                               myMediaPlayerForLast5Min.stopLast5Min();
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

    private void secondCountDownTimerForFinish(final View view){

        vibration.getVibrator(150,getActivity().getApplicationContext());
            AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());

            if(languageInteger == 1){
                builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>RESULT</b></font>")).setCancelable(false);
                builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Too Late! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Continue to Next Question</b></font>"));
                builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MemoryGameFragmentDirections.ActionMemoryGameFragmentToİqGameFragment action = MemoryGameFragmentDirections.actionMemoryGameFragmentToİqGameFragment();
                        action.setPoint(point);
                        if(level == 1){
                            action.setLevel(2);
                        }else if(level == 5){
                            action.setLevel(6);
                        }else if(level == 9){
                            action.setLevel(10);
                        }else if(level == 13){
                            action.setLevel(14);
                        }else if(level == 17){
                            action.setLevel(18);
                        }
                        Navigation.findNavController(view).navigate(action);
                    }
                });

            }else if(languageInteger == 2){
                builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>SONUÇ</b></font>")).setCancelable(false);
                builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Geç Kaldın! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sıradaki soru ile devam ediniz</b></font>"));
                builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MemoryGameFragmentDirections.ActionMemoryGameFragmentToİqGameFragment action = MemoryGameFragmentDirections.actionMemoryGameFragmentToİqGameFragment();
                        action.setPoint(point);
                        if(level == 1){
                            action.setLevel(2);
                        }else if(level == 5){
                            action.setLevel(6);
                        }else if(level == 9){
                            action.setLevel(10);
                        }else if(level == 13){
                            action.setLevel(14);
                        }else if(level == 17){
                            action.setLevel(18);
                        }
                        Navigation.findNavController(view).navigate(action);
                    }
                });

            }else if(languageInteger == 3){
                builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Результат</b></font>")).setCancelable(false);
                builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Ты опоздал! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Переходите к следующему вопросу</b></font>"));
                builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MemoryGameFragmentDirections.ActionMemoryGameFragmentToİqGameFragment action = MemoryGameFragmentDirections.actionMemoryGameFragmentToİqGameFragment();
                        action.setPoint(point);
                        if(level == 1){
                            action.setLevel(2);
                        }else if(level == 5){
                            action.setLevel(6);
                        }else if(level == 9){
                            action.setLevel(10);
                        }else if(level == 13){
                            action.setLevel(14);
                        }else if(level == 17){
                            action.setLevel(18);
                        }
                        Navigation.findNavController(view).navigate(action);
                    }
                });

            }
        AlertDialog dialog = builder.create();
        dialog.show();
        if(randomNumberForAlertDialog == 0){
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_nextforchallenge_1);
        }else if(randomNumberForAlertDialog == 1){
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_nextforchallenge_2);
        }else if(randomNumberForAlertDialog == 2){
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_nextforchallenge_3);
        }else if(randomNumberForAlertDialog == 3){
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_nextforchallenge_4);
        }else if(randomNumberForAlertDialog == 4){
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_nextforchallenge_5);
        }else if(randomNumberForAlertDialog == 5){
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_nextforchallenge_6);
        }else if(randomNumberForAlertDialog == 6){
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_nextforchallenge_7);
        }else if(randomNumberForAlertDialog == 7){
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_nextforchallenge_8);
        }else {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_nextforchallenge_9);
        }


        }

    private void setTimerForOnTickForLast5MinsSound(Long l){

        if (l / 1_000 == 5) {
            PowerManager pm = (PowerManager) getActivity().getSystemService(Context.POWER_SERVICE);//PowerManager and this method we just check screen on/off
            boolean screenOn;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
                screenOn = pm.isInteractive();
            } else {
                screenOn = pm.isScreenOn();
            }

            if (screenOn) {
                if(!checkOnPause){
                    myMediaPlayerForLast5Min.playLast5Min(getActivity());
                }
            }

        }
    }

    private void setTimerForOnTick(Long l){


        if(l/1000 == 4){
            textView_level2.setTextColor(Color.WHITE);
            textView_Score_ForMemoryGame.setTextColor(Color.WHITE);
        }
        if(l/1000 == 3){
            textView_level2.setTextColor(Color.BLACK);
            textView_Score_ForMemoryGame.setTextColor(Color.BLACK);
        }
    }

    private void finalstate(final View view){

        try {
            myMediaPlayerForLast5Min.stopLast5Min();
            imageView_MemoryGame_Fragment_Keyboardok.setEnabled(false);
            checkResult();
            makeEmptyToEditResultTexts();
            if(result == false){
                vibration.getVibrator(150,getActivity().getApplicationContext());
                countDownTimer.cancel();
                secondCountDownTimer.cancel();
                AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());

                if(languageInteger == 1){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>RESULT</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Wrong Answer! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Continue to Next Question</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MemoryGameFragmentDirections.ActionMemoryGameFragmentToİqGameFragment action = MemoryGameFragmentDirections.actionMemoryGameFragmentToİqGameFragment();
                            action.setPoint(point);
                            if(level == 1){
                                action.setLevel(2);
                            }else if(level == 5){
                                action.setLevel(6);
                            }else if(level == 9){
                                action.setLevel(10);
                            }else if(level == 13){
                                action.setLevel(14);
                            }else if(level == 17){
                                action.setLevel(18);
                            }
                            Navigation.findNavController(view).navigate(action);
                        }
                    });

                }else if(languageInteger == 2){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>SONUÇ</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Yanlış Cevap! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sıradaki soru ile devam ediniz</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MemoryGameFragmentDirections.ActionMemoryGameFragmentToİqGameFragment action = MemoryGameFragmentDirections.actionMemoryGameFragmentToİqGameFragment();
                            action.setPoint(point);
                            if(level == 1){
                                action.setLevel(2);
                            }else if(level == 5){
                                action.setLevel(6);
                            }else if(level == 9){
                                action.setLevel(10);
                            }else if(level == 13){
                                action.setLevel(14);
                            }else if(level == 17){
                                action.setLevel(18);
                            }
                            Navigation.findNavController(view).navigate(action);
                        }
                    });

                }else if(languageInteger == 3){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Результат</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Неверный ответ! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Переходите к следующему вопросу</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MemoryGameFragmentDirections.ActionMemoryGameFragmentToİqGameFragment action = MemoryGameFragmentDirections.actionMemoryGameFragmentToİqGameFragment();
                            action.setPoint(point);
                            if(level == 1){
                                action.setLevel(2);
                            }else if(level == 5){
                                action.setLevel(6);
                            }else if(level == 9){
                                action.setLevel(10);
                            }else if(level == 13){
                                action.setLevel(14);
                            }else if(level == 17){
                                action.setLevel(18);
                            }
                            Navigation.findNavController(view).navigate(action);
                        }
                    });

                }
                AlertDialog dialog = builder.create();
                dialog.show();
                if(randomNumberForAlertDialog == 0){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_nextforchallenge_1);
                }else if(randomNumberForAlertDialog == 1){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_nextforchallenge_2);
                }else if(randomNumberForAlertDialog == 2){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_nextforchallenge_3);
                }else if(randomNumberForAlertDialog == 3){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_nextforchallenge_4);
                }else if(randomNumberForAlertDialog == 4){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_nextforchallenge_5);
                }else if(randomNumberForAlertDialog == 5){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_nextforchallenge_6);
                }else if(randomNumberForAlertDialog == 6){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_nextforchallenge_7);
                }else if(randomNumberForAlertDialog == 7){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_nextforchallenge_8);
                }else {
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogwrong_nextforchallenge_9);
                }


            }

            if(result == true){
                myMediaPlayerForCorrectAnswer.playCorrectAnswerSound(getActivity());
                vibration.getVibrator(75,getActivity().getApplicationContext());
                countDownTimer.cancel();
                secondCountDownTimer.cancel();
                point += 5;

                if(languageInteger == 1){
                    textView_Score_ForMemoryGame.setText("Score: " + point);
                }else if(languageInteger == 2){
                    textView_Score_ForMemoryGame.setText("Puan: " + point);
                }else if(languageInteger == 3){
                    textView_Score_ForMemoryGame.setText("Очко: " + point);
                }


                AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());

                if(languageInteger == 1){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>RESULT</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Correct answer <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Congratulations</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MemoryGameFragmentDirections.ActionMemoryGameFragmentToİqGameFragment action = MemoryGameFragmentDirections.actionMemoryGameFragmentToİqGameFragment();
                            action.setPoint(point);
                            if(level == 1){
                                action.setLevel(2);
                            }else if(level == 5){
                                action.setLevel(6);
                            }else if(level == 9){
                                action.setLevel(10);
                            }else if(level == 13){
                                action.setLevel(14);
                            }else if(level == 17){
                                action.setLevel(18);
                            }
                            Navigation.findNavController(view).navigate(action);
                            myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
                        }
                    });

                }else if(languageInteger == 2){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>SONUÇ</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Doğru Cevap <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tebrikler</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MemoryGameFragmentDirections.ActionMemoryGameFragmentToİqGameFragment action = MemoryGameFragmentDirections.actionMemoryGameFragmentToİqGameFragment();
                            action.setPoint(point);
                            if(level == 1){
                                action.setLevel(2);
                            }else if(level == 5){
                                action.setLevel(6);
                            }else if(level == 9){
                                action.setLevel(10);
                            }else if(level == 13){
                                action.setLevel(14);
                            }else if(level == 17){
                                action.setLevel(18);
                            }
                            Navigation.findNavController(view).navigate(action);
                            myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
                        }
                    });

                }else if(languageInteger == 3){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Результат</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Правильный ответ <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Поздравления</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MemoryGameFragmentDirections.ActionMemoryGameFragmentToİqGameFragment action = MemoryGameFragmentDirections.actionMemoryGameFragmentToİqGameFragment();
                            action.setPoint(point);
                            if(level == 1){
                                action.setLevel(2);
                            }else if(level == 5){
                                action.setLevel(6);
                            }else if(level == 9){
                                action.setLevel(10);
                            }else if(level == 13){
                                action.setLevel(14);
                            }else if(level == 17){
                                action.setLevel(18);
                            }
                            Navigation.findNavController(view).navigate(action);
                            myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
                        }
                    });
                }
                AlertDialog dialog = builder.create();
                dialog.show();

                if(randomNumberForAlertDialog == 0){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_fivepoints_1);
                }else if(randomNumberForAlertDialog == 1){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_fivepoints_2);
                }else if(randomNumberForAlertDialog == 2){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_fivepoints_3);
                }else if(randomNumberForAlertDialog == 3){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_fivepoints_4);
                }else if(randomNumberForAlertDialog == 4){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_fivepoints_5);
                }else if(randomNumberForAlertDialog == 5){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_fivepoints_6);
                }else if(randomNumberForAlertDialog == 6){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_fivepoints_7);
                }else if(randomNumberForAlertDialog == 7){
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_fivepoints_8);
                }else {
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogright_fivepoints_9);
                }


            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initializing(View view) {
        textView_NewRemember1 = view.findViewById(R.id.textView_NewRemember1);
        textView_NewRemember2 = view.findViewById(R.id.textView_NewRemember2);
        textView_NewRemember3 = view.findViewById(R.id.textView_NewRemember3);
        textView_NewRemember4 = view.findViewById(R.id.textView_NewRemember4);
        textView_NewRemember5 = view.findViewById(R.id.textView_NewRemember5);
        editTextNumber_NewResult1 = view.findViewById(R.id.editTextNumber_NewResult1);
        editTextNumber_NewResult2 = view.findViewById(R.id.editTextNumber_NewResult2);
        editTextNumber_NewResult3 = view.findViewById(R.id.editTextNumber_NewResult3);
        editTextNumber_NewResult4 = view.findViewById(R.id.editTextNumber_NewResult4);
        editTextNumber_NewResult5 = view.findViewById(R.id.editTextNumber_NewResult5);
        textView_timer2 = view.findViewById(R.id.textView_timer2);
        textView_level2 = view.findViewById(R.id.textView_level2);
        textView_Score_ForMemoryGame = view.findViewById(R.id.textView_Score_ForMemoryGame);
        textView_For_NewEp_Memory_Comment = view.findViewById(R.id.textView_For_NewEp_Memory_Comment);
        result = false;
        textView_NewRemember1.setAlpha(1.0f);
        textView_NewRemember2.setAlpha(1.0f);
        textView_NewRemember3.setAlpha(1.0f);
        textView_NewRemember4.setAlpha(1.0f);
        textView_NewRemember5.setAlpha(1.0f);
        editTextNumber_NewResult1.setAlpha(1.0f);
        editTextNumber_NewResult2.setAlpha(1.0f);
        editTextNumber_NewResult3.setAlpha(1.0f);
        editTextNumber_NewResult4.setAlpha(1.0f);
        editTextNumber_NewResult5.setAlpha(1.0f);
        level_for_background =1;
        level = 0;
        point = 0;
        randomforbackground = new Random();

        rnd = new Random();
        randomNumberForAlertDialog = rnd.nextInt(9);

        imageView_MemoryGame_Fragment_Keyboard1 = view.findViewById(R.id.imageView_MemoryGame_Fragment_Keyboard1);
        imageView_MemoryGame_Fragment_Keyboard2 = view.findViewById(R.id.imageView_MemoryGame_Fragment_Keyboard2);
        imageView_MemoryGame_Fragment_Keyboard3 = view.findViewById(R.id.imageView_MemoryGame_Fragment_Keyboard3);
        imageView_MemoryGame_Fragment_Keyboard4 = view.findViewById(R.id.imageView_MemoryGame_Fragment_Keyboard4);
        imageView_MemoryGame_Fragment_Keyboard5 = view.findViewById(R.id.imageView_MemoryGame_Fragment_Keyboard5);
        imageView_MemoryGame_Fragment_Keyboard6 = view.findViewById(R.id.imageView_MemoryGame_Fragment_Keyboard6);
        imageView_MemoryGame_Fragment_Keyboard7 = view.findViewById(R.id.imageView_MemoryGame_Fragment_Keyboard7);
        imageView_MemoryGame_Fragment_Keyboard8 = view.findViewById(R.id.imageView_MemoryGame_Fragment_Keyboard8);
        imageView_MemoryGame_Fragment_Keyboard9 = view.findViewById(R.id.imageView_MemoryGame_Fragment_Keyboard9);
        imageView_MemoryGame_Fragment_Keyboard0 = view.findViewById(R.id.imageView_MemoryGame_Fragment_Keyboard0);
        imageView_MemoryGame_Fragment_Keyboardback = view.findViewById(R.id.imageView_MemoryGame_Fragment_Keyboardback);
        imageView_MemoryGame_Fragment_Keyboardok = view.findViewById(R.id.imageView_MemoryGame_Fragment_Keyboardok);
        numberForResult = -1;
        checkKeyboardForResult = 1;
        checkOnPause = false;

        vibration = new Vibration();

    }

    private void getRememberNumbers() {
        hideEditTexts();
        if(level_for_background == 1 || level_for_background == 5){
            textView_NewRemember1.setAlpha(0.0f);
            textView_NewRemember2.setText(memorygame.getNumb2() + "");
            textView_NewRemember3.setText(memorygame.getNumb3() + "");
            textView_NewRemember4.setAlpha(0.0f);
            textView_NewRemember5.setAlpha(0.0f);
        }
        if(level_for_background == 2 || level_for_background == 3 || level_for_background == 4 || level_for_background == 6 || level_for_background == 7 || level_for_background == 15){
            textView_NewRemember1.setAlpha(0.0f);
            textView_NewRemember2.setText(memorygame.getNumb2() + "");
            textView_NewRemember3.setText(memorygame.getNumb3() + "");
            textView_NewRemember4.setText(memorygame.getNumb4() + "");
            textView_NewRemember5.setAlpha(0.0f);
        }
        if(level_for_background == 8 || level_for_background == 9 || level_for_background == 10 || level_for_background == 11 || level_for_background == 12 || level_for_background == 13 || level_for_background == 14 || level_for_background == 16 || level_for_background == 17 || level_for_background == 24){
            textView_NewRemember1.setText(memorygame.getNumb1() + "");
            textView_NewRemember2.setText(memorygame.getNumb2() + "");
            textView_NewRemember3.setText(memorygame.getNumb3() + "");
            textView_NewRemember4.setText(memorygame.getNumb4() + "");
            textView_NewRemember5.setAlpha(0.0f);
        }
        if(level_for_background == 18 || level_for_background == 19 || level_for_background == 20 || level_for_background == 21 || level_for_background == 22 || level_for_background == 23 || level_for_background == 25 || level_for_background == 26 || level_for_background == 27 || level_for_background == 28 || level_for_background == 29 || level_for_background == 30){
            textView_NewRemember1.setText(memorygame.getNumb1() + "");
            textView_NewRemember2.setText(memorygame.getNumb2() + "");
            textView_NewRemember3.setText(memorygame.getNumb3() + "");
            textView_NewRemember4.setText(memorygame.getNumb4() + "");
            textView_NewRemember5.setText(memorygame.getNumb5() + "");
        }

    }

    private void getEditTextResultNumbers(){
        hideTextViews();
        if(level_for_background == 1 || level_for_background == 5){
            editTextNumber_NewResult2.setAlpha(1.0f);
            editTextNumber_NewResult3.setAlpha(1.0f);
            editTextNumber_NewResult2.setEnabled(true);
            editTextNumber_NewResult3.setEnabled(true);
        }
        if(level_for_background == 2 || level_for_background == 3 || level_for_background == 4 || level_for_background == 6 || level_for_background == 7 || level_for_background == 15){
            editTextNumber_NewResult2.setAlpha(1.0f);
            editTextNumber_NewResult3.setAlpha(1.0f);
            editTextNumber_NewResult4.setAlpha(1.0f);
            editTextNumber_NewResult2.setEnabled(true);
            editTextNumber_NewResult3.setEnabled(true);
            editTextNumber_NewResult4.setEnabled(true);
        }
        if(level_for_background == 8 || level_for_background == 9 || level_for_background == 10 || level_for_background == 11 || level_for_background == 12 || level_for_background == 13 || level_for_background == 14 || level_for_background == 16 || level_for_background == 17 || level_for_background == 24){
            editTextNumber_NewResult1.setAlpha(1.0f);
            editTextNumber_NewResult2.setAlpha(1.0f);
            editTextNumber_NewResult3.setAlpha(1.0f);
            editTextNumber_NewResult4.setAlpha(1.0f);
            editTextNumber_NewResult1.setEnabled(true);
            editTextNumber_NewResult2.setEnabled(true);
            editTextNumber_NewResult3.setEnabled(true);
            editTextNumber_NewResult4.setEnabled(true);
        }
        if(level_for_background == 18 || level_for_background == 19 || level_for_background == 20 || level_for_background == 21 || level_for_background == 22 || level_for_background == 23 || level_for_background == 25 || level_for_background == 26 || level_for_background == 27 || level_for_background == 28 || level_for_background == 29 || level_for_background == 30){
            editTextNumber_NewResult1.setAlpha(1.0f);
            editTextNumber_NewResult2.setAlpha(1.0f);
            editTextNumber_NewResult3.setAlpha(1.0f);
            editTextNumber_NewResult4.setAlpha(1.0f);
            editTextNumber_NewResult5.setAlpha(1.0f);
            editTextNumber_NewResult1.setEnabled(true);
            editTextNumber_NewResult2.setEnabled(true);
            editTextNumber_NewResult3.setEnabled(true);
            editTextNumber_NewResult4.setEnabled(true);
            editTextNumber_NewResult5.setEnabled(true);
        }

    }

    private void hideTextViews(){
        textView_NewRemember1.setAlpha(0.0f);
        textView_NewRemember2.setAlpha(0.0f);
        textView_NewRemember3.setAlpha(0.0f);
        textView_NewRemember4.setAlpha(0.0f);
        textView_NewRemember5.setAlpha(0.0f);
    }

    private void hideEditTexts(){
        editTextNumber_NewResult1.setAlpha(0.0f);
        editTextNumber_NewResult2.setAlpha(0.0f);
        editTextNumber_NewResult3.setAlpha(0.0f);
        editTextNumber_NewResult4.setAlpha(0.0f);
        editTextNumber_NewResult5.setAlpha(0.0f);
        editTextNumber_NewResult1.setEnabled(false);
        editTextNumber_NewResult2.setEnabled(false);
        editTextNumber_NewResult3.setEnabled(false);
        editTextNumber_NewResult4.setEnabled(false);
        editTextNumber_NewResult5.setEnabled(false);
    }

    private void checkResult(){

        try {
            if(level_for_background == 1 || level_for_background == 5){
                if(memorygame.getNumb2() == Integer.parseInt(editTextNumber_NewResult2.getText().toString())
                        && memorygame.getNumb3() == Integer.parseInt(editTextNumber_NewResult3.getText().toString())){
                    result = true;
                }
            }else if(level_for_background == 2 || level_for_background == 3 || level_for_background == 4 || level_for_background == 6 || level_for_background == 7 || level_for_background == 15){
                if(memorygame.getNumb2() == Integer.parseInt(editTextNumber_NewResult2.getText().toString())
                        && memorygame.getNumb3() == Integer.parseInt(editTextNumber_NewResult3.getText().toString())
                        && memorygame.getNumb4() == Integer.parseInt(editTextNumber_NewResult4.getText().toString())){
                    result = true;
                }
            }else if(level_for_background == 8 || level_for_background == 9 || level_for_background == 10 || level_for_background == 11 || level_for_background == 12 || level_for_background == 13 || level_for_background == 14 || level_for_background == 16 || level_for_background == 17 || level_for_background == 24){
                if( memorygame.getNumb1() == Integer.parseInt(editTextNumber_NewResult1.getText().toString())
                        && memorygame.getNumb2() == Integer.parseInt(editTextNumber_NewResult2.getText().toString())
                        && memorygame.getNumb3() == Integer.parseInt(editTextNumber_NewResult3.getText().toString())
                        && memorygame.getNumb4() == Integer.parseInt(editTextNumber_NewResult4.getText().toString())){
                    result = true;
                }
            }else if(level_for_background == 18 || level_for_background == 19 || level_for_background == 20 || level_for_background == 21 || level_for_background == 22 || level_for_background == 23 || level_for_background == 25 || level_for_background == 26 || level_for_background == 27 || level_for_background == 28 || level_for_background == 29 || level_for_background == 30) {
                if( memorygame.getNumb1() == Integer.parseInt(editTextNumber_NewResult1.getText().toString())
                        && memorygame.getNumb2() == Integer.parseInt(editTextNumber_NewResult2.getText().toString())
                        && memorygame.getNumb3() == Integer.parseInt(editTextNumber_NewResult3.getText().toString())
                        && memorygame.getNumb4() == Integer.parseInt(editTextNumber_NewResult4.getText().toString())
                        && memorygame.getNumb5() == Integer.parseInt(editTextNumber_NewResult5.getText().toString()) ){
                    result = true;
                }
            } else {
                result = false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void makeEmptyToEditResultTexts(){
        editTextNumber_NewResult1.setText("");
        editTextNumber_NewResult2.setText("");
        editTextNumber_NewResult3.setText("");
        editTextNumber_NewResult4.setText("");
        editTextNumber_NewResult5.setText("");
    }

    private void getRandomforGame(){
        if(level == 1){
            level_for_background = 1;
        }
        if(level == 5){
            level_for_background = randomforbackground.nextInt(6) + 2;
        }
        if(level == 9 || level == 13 ){
            level_for_background = randomforbackground.nextInt(10) + 8;
        }
        if(level == 17){
            level_for_background = randomforbackground.nextInt(13) + 18;
        }
    }

    private void setFonts(){
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");
        textView_NewRemember1.setTypeface(typeface);
        textView_NewRemember2.setTypeface(typeface);
        textView_NewRemember3.setTypeface(typeface);
        textView_NewRemember4.setTypeface(typeface);
        textView_NewRemember5.setTypeface(typeface);
        editTextNumber_NewResult1.setTypeface(typeface);
        editTextNumber_NewResult2.setTypeface(typeface);
        editTextNumber_NewResult3.setTypeface(typeface);
        editTextNumber_NewResult4.setTypeface(typeface);
        editTextNumber_NewResult5.setTypeface(typeface);
        textView_For_NewEp_Memory_Comment.setTypeface(typeface);
        textView_timer2.setTypeface(typeface);
        textView_level2.setTypeface(typeface);
        textView_Score_ForMemoryGame.setTypeface(typeface);
    }

    public void onKeyDownForFragment(){
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                myMediaPlayerForLast5Min.stopLast5Min();
                myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
                countDownTimer.cancel();
                if(secondCountDownTimerCounter == 1){//if this working we can cancel.
                    secondCountDownTimer.cancel();
                }
                AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());

                if(languageInteger == 1){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>&nbsp;&nbsp;&nbsp;      </b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>      <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>"));
                    builder.setPositiveButton("     ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getActivity(), CheckStatisticForNewChallengeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            getActivity().finish();

                        }
                    });

                    builder.setNegativeButton("         ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MemoryGameFragmentDirections.ActionMemoryGameFragmentToİqGameFragment action = MemoryGameFragmentDirections.actionMemoryGameFragmentToİqGameFragment();
                            action.setPoint(point);
                            if(level == 1){
                                action.setLevel(2);
                            }else if(level == 5){
                                action.setLevel(6);
                            }else if(level == 9){
                                action.setLevel(10);
                            }else if(level == 13){
                                action.setLevel(14);
                            }else if(level == 17){
                                action.setLevel(18);
                            }
                            Navigation.findNavController(getView()).navigate(action);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.areyousurequestion_english);
                }else if(languageInteger == 2){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>&nbsp;&nbsp;&nbsp;      </b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>      <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>"));
                    builder.setPositiveButton("     ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getActivity(), CheckStatisticForNewChallengeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            getActivity().finish();


                        }
                    });

                    builder.setNegativeButton("         ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MemoryGameFragmentDirections.ActionMemoryGameFragmentToİqGameFragment action = MemoryGameFragmentDirections.actionMemoryGameFragmentToİqGameFragment();
                            action.setPoint(point);
                            if(level == 1){
                                action.setLevel(2);
                            }else if(level == 5){
                                action.setLevel(6);
                            }else if(level == 9){
                                action.setLevel(10);
                            }else if(level == 13){
                                action.setLevel(14);
                            }else if(level == 17){
                                action.setLevel(18);
                            }
                            Navigation.findNavController(getView()).navigate(action);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.areyousurequestion_turkish);
                } else if(languageInteger == 3){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>&nbsp;&nbsp;&nbsp;      </b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>      <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>"));
                    builder.setPositiveButton("     ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getActivity(), CheckStatisticForNewChallengeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            getActivity().finish();


                        }
                    });

                    builder.setNegativeButton("         ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MemoryGameFragmentDirections.ActionMemoryGameFragmentToİqGameFragment action = MemoryGameFragmentDirections.actionMemoryGameFragmentToİqGameFragment();
                            action.setPoint(point);
                            if(level == 1){
                                action.setLevel(2);
                            }else if(level == 5){
                                action.setLevel(6);
                            }else if(level == 9){
                                action.setLevel(10);
                            }else if(level == 13){
                                action.setLevel(14);
                            }else if(level == 17){
                                action.setLevel(18);
                            }
                            Navigation.findNavController(getView()).navigate(action);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.areyousurequestion_russian);
                }
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private void selectResultViewForKeyboardMakeFalseAllNumbers(){
        numberforresultview[1] = false;
        numberforresultview[2] = true;
        numberforresultview[3] = false;
        numberforresultview[4] = false;
        numberforresultview[5] = false;
    }

    private void selectResultViewForKeyboard2(){  //2=Res2   3=Res3    1=Res1   4=Res4   5=Res5
        editTextNumber_NewResult1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberforresultview[1] = true;
                numberforresultview[2] = false;
                numberforresultview[3] = false;
                numberforresultview[4] = false;
                numberforresultview[5] = false;
                editTextNumber_NewResult1.setBackgroundResource(R.drawable.circleforremember2);
                editTextNumber_NewResult2.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_NewResult3.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_NewResult4.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_NewResult5.setBackgroundResource(R.drawable.circleforremember);
                numberForResult = -1;
            }
        });
        editTextNumber_NewResult2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberforresultview[1] = false;
                numberforresultview[2] = true;
                numberforresultview[3] = false;
                numberforresultview[4] = false;
                numberforresultview[5] = false;
                editTextNumber_NewResult1.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_NewResult2.setBackgroundResource(R.drawable.circleforremember2);
                editTextNumber_NewResult3.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_NewResult4.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_NewResult5.setBackgroundResource(R.drawable.circleforremember);
                numberForResult = -1;
            }
        });
        editTextNumber_NewResult3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberforresultview[1] = false;
                numberforresultview[2] = false;
                numberforresultview[3] = true;
                numberforresultview[4] = false;
                numberforresultview[5] = false;
                editTextNumber_NewResult1.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_NewResult2.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_NewResult3.setBackgroundResource(R.drawable.circleforremember2);
                editTextNumber_NewResult4.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_NewResult5.setBackgroundResource(R.drawable.circleforremember);
                numberForResult = -1;
            }
        });
        editTextNumber_NewResult4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberforresultview[1] = false;
                numberforresultview[2] = false;
                numberforresultview[3] = false;
                numberforresultview[4] = true;
                numberforresultview[5] = false;
                editTextNumber_NewResult1.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_NewResult2.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_NewResult3.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_NewResult4.setBackgroundResource(R.drawable.circleforremember2);
                editTextNumber_NewResult5.setBackgroundResource(R.drawable.circleforremember);
                numberForResult = -1;
            }
        });
        editTextNumber_NewResult5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberforresultview[1] = false;
                numberforresultview[2] = false;
                numberforresultview[3] = false;
                numberforresultview[4] = false;
                numberforresultview[5] = true;
                editTextNumber_NewResult1.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_NewResult2.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_NewResult3.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_NewResult4.setBackgroundResource(R.drawable.circleforremember);
                editTextNumber_NewResult5.setBackgroundResource(R.drawable.circleforremember2);
                numberForResult = -1;

            }
        });
    }

    private TextView selectResultViewForKeyboard(){

        if(numberforresultview[1]){
            return editTextNumber_NewResult1;
        }else if(numberforresultview[2]){
            return editTextNumber_NewResult2;
        }else if(numberforresultview[3]){
            return editTextNumber_NewResult3;
        }else if(numberforresultview[4]){
            return editTextNumber_NewResult4;
        }else if(numberforresultview[5]){
            return editTextNumber_NewResult5;
        }else {
            return editTextNumber_NewResult2;
        }
    }

    private void keyboards(){

        imageView_MemoryGame_Fragment_Keyboard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = selectResultViewForKeyboard();
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 1;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 1;
                }
                editText.setText( numberForResult + "");
            }
        });
        imageView_MemoryGame_Fragment_Keyboard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = selectResultViewForKeyboard();
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 2;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 2;
                }

                editText.setText( numberForResult + "");
            }
        });
        imageView_MemoryGame_Fragment_Keyboard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = selectResultViewForKeyboard();
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 3;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 3;
                }

                editText.setText( numberForResult + "");
            }
        });
        imageView_MemoryGame_Fragment_Keyboard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = selectResultViewForKeyboard();
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 4;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 4;
                }

                editText.setText( numberForResult + "");
            }
        });
        imageView_MemoryGame_Fragment_Keyboard5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = selectResultViewForKeyboard();
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 5;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 5;
                }

                editText.setText( numberForResult + "");
            }
        });
        imageView_MemoryGame_Fragment_Keyboard6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = selectResultViewForKeyboard();
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 6;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 6;
                }

                editText.setText( numberForResult + "");
            }
        });
        imageView_MemoryGame_Fragment_Keyboard7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = selectResultViewForKeyboard();
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 7;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 7;
                }

                editText.setText( numberForResult + "");
            }
        });
        imageView_MemoryGame_Fragment_Keyboard8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = selectResultViewForKeyboard();
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 8;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 8;
                }

                editText.setText( numberForResult + "");
            }
        });
        imageView_MemoryGame_Fragment_Keyboard9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = selectResultViewForKeyboard();
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 9;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 9;
                }

                editText.setText( numberForResult + "");
            }
        });
        imageView_MemoryGame_Fragment_Keyboard0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = selectResultViewForKeyboard();
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 0;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 0;
                }

                editText.setText( numberForResult + "");
            }
        });
        imageView_MemoryGame_Fragment_Keyboardback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = selectResultViewForKeyboard();
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult > 9){
                    numberForResult = numberForResult / 10;
                    editText.setText( numberForResult + "");
                }else if(numberForResult >= 0 && numberForResult <= 9){
                    numberForResult = 0;
                    editText.setText("");
                }

            }
        });
        imageView_MemoryGame_Fragment_Keyboardok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                finalstate(view);
            }
        });
    }

    private void checkForClickListener(int i){
        if(i == 0){
            imageView_MemoryGame_Fragment_Keyboard1.setOnClickListener(null);
            imageView_MemoryGame_Fragment_Keyboard2.setOnClickListener(null);
            imageView_MemoryGame_Fragment_Keyboard3.setOnClickListener(null);
            imageView_MemoryGame_Fragment_Keyboard4.setOnClickListener(null);
            imageView_MemoryGame_Fragment_Keyboard5.setOnClickListener(null);
            imageView_MemoryGame_Fragment_Keyboard6.setOnClickListener(null);
            imageView_MemoryGame_Fragment_Keyboard7.setOnClickListener(null);
            imageView_MemoryGame_Fragment_Keyboard8.setOnClickListener(null);
            imageView_MemoryGame_Fragment_Keyboard9.setOnClickListener(null);
            imageView_MemoryGame_Fragment_Keyboard0.setOnClickListener(null);
            imageView_MemoryGame_Fragment_Keyboardback.setOnClickListener(null);
            imageView_MemoryGame_Fragment_Keyboardok.setOnClickListener(null);
        }
        if(i == 1){
            keyboards();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        checkOnPause = false;
    }

    @Override
    public void onPause() {
        super.onPause();

        myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
        myMediaPlayerForLast5Min.stopLast5Min();
        checkOnPause = true;
    }
}



