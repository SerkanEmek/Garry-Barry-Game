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
import com.serkanemek.garrybarry.operations.IqGame;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForCorrectAnswer;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForLast5Min;
import com.serkanemek.garrybarry.util.Vibration;

import java.util.Random;


public class IqGameFragment extends Fragment {

    TextView textView_NewNumber1,textView_NewNumber2,textView_NewNumber3,textView_NewNumber4,textView_NewNumber5,textView_NewNumber6
            ,textView_NewNumber7,textView_NewNumber8,textView_NewNumber9,textView_NewNumber10,textView_NewNumber11,textView_NewNumber12;
    TextView editTextNewResult;
    Random randomNumber;
    IqGame iqGame;
    Integer combination, levelforCombination, result, level, point;
    TextView textView_timer3,textView_level3, textView_Score_ForIqGame;
    CountDownTimer countDownTimer;

    ImageView imageView_IqGame_Fragment_Keyboard1,imageView_IqGame_Fragment_Keyboard2,
            imageView_IqGame_Fragment_Keyboard3,imageView_IqGame_Fragment_Keyboard4,
            imageView_IqGame_Fragment_Keyboard5,imageView_IqGame_Fragment_Keyboard6,
            imageView_IqGame_Fragment_Keyboard7,imageView_IqGame_Fragment_Keyboard8,
            imageView_IqGame_Fragment_Keyboard9,imageView_IqGame_Fragment_Keyboard0,
            imageView_IqGame_Fragment_Keyboardback,imageView_IqGame_Fragment_Keyboardok;
    Integer numberForResult;

    SharedPreferences sharedPreferences;
    Integer languageInteger;

    Random rnd;
    Integer randomNumberForAlertDialog;

    MyMediaPlayerForCorrectAnswer myMediaPlayerForCorrectAnswer;
    MyMediaPlayerForLast5Min myMediaPlayerForLast5Min;


    Boolean checkOnPause;

    Vibration vibration;

    public IqGameFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onKeyDownForFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_iq_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializing(view);


        myMediaPlayerForCorrectAnswer = new MyMediaPlayerForCorrectAnswer();
        myMediaPlayerForLast5Min = new MyMediaPlayerForLast5Min();



        iqGame = new IqGame(combination, levelforCombination);
        checkCombinations();
        setFonts();
        timer(view);
        keyboards();

        if(getArguments() != null){
            level = IqGameFragmentArgs.fromBundle(getArguments()).getLevel();
            textView_level3.setText("Question:" + level);
            point = IqGameFragmentArgs.fromBundle(getArguments()).getPoint();
            textView_Score_ForIqGame.setText("Score: " + point);
        } else{
            textView_level3.setText("Question:" + level);
        }

        sharedPreferences = getActivity().getSharedPreferences("com.serkanemek.garrybarry.Activities" , Context.MODE_PRIVATE);
        languageInteger = sharedPreferences.getInt("storedLanguage",0);
        if(languageInteger == 1){
            textView_level3.setText("Question:" + level);
            textView_Score_ForIqGame.setText("Score: " + point);
        }else if(languageInteger == 2){
            textView_level3.setText("Soru:" + level);
            textView_Score_ForIqGame.setText("Puan: " + point);
        }else if(languageInteger == 3){
            textView_level3.setText("Вопрос:" + level);
            textView_Score_ForIqGame.setText("Очко: " + point);
        }
    }

    private void timer(final View view){


        countDownTimer =   new CountDownTimer(25_000, 1_000) {
            @Override
            public void onTick(long l) {
                imageView_IqGame_Fragment_Keyboardok.setEnabled(true);
                if(languageInteger == 1){
                    textView_timer3.setText("" + (l/1000));
                }else if(languageInteger == 2){
                    textView_timer3.setText("" + (l/1000));
                }else if(languageInteger == 3){
                    textView_timer3.setText("" + (l/1000));

                }

                setTimerForOnTick(l);
                setTimerForOnTickForLast5MinsSound(l);


            }
            @Override
            public void onFinish() {
                myMediaPlayerForLast5Min.stopLast5Min();
                vibration.getVibrator(150,getActivity().getApplicationContext());
                AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());

                if(languageInteger == 1){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>RESULT</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Too Late! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Continue to Next Question</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            editTextNewResult.setText("");
                            IqGameFragmentDirections.ActionİqGameFragmentToMathGameFragment action = IqGameFragmentDirections.actionİqGameFragmentToMathGameFragment();
                            action.setPoint(point);
                            if(level == 2){
                                action.setLevel(3);
                            }else if(level == 6){
                                action.setLevel(7);
                            }else if(level == 10){
                                action.setLevel(11);
                            }else if(level == 14){
                                action.setLevel(15);
                            }else if(level == 18){
                                action.setLevel(19);
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
                            editTextNewResult.setText("");
                            IqGameFragmentDirections.ActionİqGameFragmentToMathGameFragment action = IqGameFragmentDirections.actionİqGameFragmentToMathGameFragment();
                            action.setPoint(point);
                            if(level == 2){
                                action.setLevel(3);
                            }else if(level == 6){
                                action.setLevel(7);
                            }else if(level == 10){
                                action.setLevel(11);
                            }else if(level == 14){
                                action.setLevel(15);
                            }else if(level == 18){
                                action.setLevel(19);
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
                            editTextNewResult.setText("");
                            IqGameFragmentDirections.ActionİqGameFragmentToMathGameFragment action = IqGameFragmentDirections.actionİqGameFragmentToMathGameFragment();
                            action.setPoint(point);
                            if(level == 2){
                                action.setLevel(3);
                            }else if(level == 6){
                                action.setLevel(7);
                            }else if(level == 10){
                                action.setLevel(11);
                            }else if(level == 14){
                                action.setLevel(15);
                            }else if(level == 18){
                                action.setLevel(19);
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
        };
        countDownTimer.start();
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
        if(l/1_000 == 24){
            textView_level3.setTextColor(Color.WHITE);
            textView_Score_ForIqGame.setTextColor(Color.WHITE);

        }
        if(l/1_000 == 23){
            textView_level3.setTextColor(Color.BLACK);
            textView_Score_ForIqGame.setTextColor(Color.BLACK);

        }


    }

    private void finalstate(final View view){

        try {
            myMediaPlayerForLast5Min.stopLast5Min();
            imageView_IqGame_Fragment_Keyboardok.setEnabled(false);
            AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
            if(result == Integer.parseInt(editTextNewResult.getText().toString())){
                myMediaPlayerForCorrectAnswer.playCorrectAnswerSound(getActivity());
                vibration.getVibrator(75,getActivity().getApplicationContext());
                countDownTimer.cancel();
                point += 5;

                if(languageInteger == 1){
                    textView_Score_ForIqGame.setText("Score: " + point);
                }else if(languageInteger == 2){
                    textView_Score_ForIqGame.setText("Puan: " + point);
                }else if(languageInteger == 3){
                    textView_Score_ForIqGame.setText("Очко: " + point);
                }


                if(languageInteger == 1){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>RESULT</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Correct answer <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Congratulations</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            editTextNewResult.setText("");
                            IqGameFragmentDirections.ActionİqGameFragmentToMathGameFragment action = IqGameFragmentDirections.actionİqGameFragmentToMathGameFragment();
                            action.setPoint(point);
                            if(level == 2){
                                action.setLevel(3);
                            }else if(level == 6){
                                action.setLevel(7);
                            }else if(level == 10){
                                action.setLevel(11);
                            }else if(level == 14){
                                action.setLevel(15);
                            }else if(level == 18){
                                action.setLevel(19);
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
                            editTextNewResult.setText("");
                            IqGameFragmentDirections.ActionİqGameFragmentToMathGameFragment action = IqGameFragmentDirections.actionİqGameFragmentToMathGameFragment();
                            action.setPoint(point);
                            if(level == 2){
                                action.setLevel(3);
                            }else if(level == 6){
                                action.setLevel(7);
                            }else if(level == 10){
                                action.setLevel(11);
                            }else if(level == 14){
                                action.setLevel(15);
                            }else if(level == 18){
                                action.setLevel(19);
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
                            editTextNewResult.setText("");
                            IqGameFragmentDirections.ActionİqGameFragmentToMathGameFragment action = IqGameFragmentDirections.actionİqGameFragmentToMathGameFragment();
                            action.setPoint(point);
                            if(level == 2){
                                action.setLevel(3);
                            }else if(level == 6){
                                action.setLevel(7);
                            }else if(level == 10){
                                action.setLevel(11);
                            }else if(level == 14){
                                action.setLevel(15);
                            }else if(level == 18){
                                action.setLevel(19);
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



            } else {
                vibration.getVibrator(150,getActivity().getApplicationContext());
                countDownTimer.cancel();

                if(languageInteger == 1){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>RESULT</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Wrong Answer! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Continue to Next Question</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            editTextNewResult.setText("");
                            IqGameFragmentDirections.ActionİqGameFragmentToMathGameFragment action = IqGameFragmentDirections.actionİqGameFragmentToMathGameFragment();
                            action.setPoint(point);
                            if(level == 2){
                                action.setLevel(3);
                            }else if(level == 6){
                                action.setLevel(7);
                            }else if(level == 10){
                                action.setLevel(11);
                            }else if(level == 14){
                                action.setLevel(15);
                            }else if(level == 18){
                                action.setLevel(19);
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
                            editTextNewResult.setText("");
                            IqGameFragmentDirections.ActionİqGameFragmentToMathGameFragment action = IqGameFragmentDirections.actionİqGameFragmentToMathGameFragment();
                            action.setPoint(point);
                            if(level == 2){
                                action.setLevel(3);
                            }else if(level == 6){
                                action.setLevel(7);
                            }else if(level == 10){
                                action.setLevel(11);
                            }else if(level == 14){
                                action.setLevel(15);
                            }else if(level == 18){
                                action.setLevel(19);
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
                            editTextNewResult.setText("");
                            IqGameFragmentDirections.ActionİqGameFragmentToMathGameFragment action = IqGameFragmentDirections.actionİqGameFragmentToMathGameFragment();
                            action.setPoint(point);
                            if(level == 2){
                                action.setLevel(3);
                            }else if(level == 6){
                                action.setLevel(7);
                            }else if(level == 10){
                                action.setLevel(11);
                            }else if(level == 14){
                                action.setLevel(15);
                            }else if(level == 18){
                                action.setLevel(19);
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
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void initializing(View view) {
        textView_NewNumber1 = view.findViewById(R.id.textView_NewNumber1);
        textView_NewNumber2 = view.findViewById(R.id.textView_NewNumber2);
        textView_NewNumber3 = view.findViewById(R.id.textView_NewNumber3);
        textView_NewNumber4 = view.findViewById(R.id.textView_NewNumber4);
        textView_NewNumber5 = view.findViewById(R.id.textView_NewNumber5);
        textView_NewNumber6 = view.findViewById(R.id.textView_NewNumber6);
        textView_NewNumber7 = view.findViewById(R.id.textView_NewNumber7);
        textView_NewNumber8 = view.findViewById(R.id.textView_NewNumber8);
        textView_NewNumber9 = view.findViewById(R.id.textView_NewNumber9);
        textView_NewNumber10 = view.findViewById(R.id.textView_NewNumber10);
        textView_NewNumber11 = view.findViewById(R.id.textView_NewNumber11);
        textView_NewNumber12 = view.findViewById(R.id.textView_NewNumber12);

        textView_NewNumber1.setAlpha(1.0f);
        textView_NewNumber2.setAlpha(1.0f);
        textView_NewNumber3.setAlpha(1.0f);
        textView_NewNumber4.setAlpha(1.0f);
        textView_NewNumber5.setAlpha(1.0f);
        textView_NewNumber6.setAlpha(1.0f);
        textView_NewNumber7.setAlpha(1.0f);
        textView_NewNumber8.setAlpha(1.0f);
        textView_NewNumber9.setAlpha(1.0f);
        textView_NewNumber10.setAlpha(1.0f);
        textView_NewNumber11.setAlpha(1.0f);
        textView_NewNumber12.setAlpha(1.0f);

        editTextNewResult = view.findViewById(R.id.editTextNewResult);
        randomNumber = new Random();
        combination = randomNumber.nextInt(4) + 7;
        levelforCombination = randomNumber.nextInt(5) + 1;
        result = -1;
        textView_level3 = view.findViewById(R.id.textView_level3);
        textView_timer3 = view.findViewById(R.id.textView_timer3);
        textView_Score_ForIqGame = view.findViewById(R.id.textView_Score_ForIqGame);
        point = 0;
        checkOnPause = false;

        rnd = new Random();
        randomNumberForAlertDialog = rnd.nextInt(9);

        imageView_IqGame_Fragment_Keyboard1 = view.findViewById(R.id.imageView_IqGame_Fragment_Keyboard1);
        imageView_IqGame_Fragment_Keyboard2 = view.findViewById(R.id.imageView_IqGame_Fragment_Keyboard2);
        imageView_IqGame_Fragment_Keyboard3 = view.findViewById(R.id.imageView_IqGame_Fragment_Keyboard3);
        imageView_IqGame_Fragment_Keyboard4 = view.findViewById(R.id.imageView_IqGame_Fragment_Keyboard4);
        imageView_IqGame_Fragment_Keyboard5 = view.findViewById(R.id.imageView_IqGame_Fragment_Keyboard5);
        imageView_IqGame_Fragment_Keyboard6 = view.findViewById(R.id.imageView_IqGame_Fragment_Keyboard6);
        imageView_IqGame_Fragment_Keyboard7 = view.findViewById(R.id.imageView_IqGame_Fragment_Keyboard7);
        imageView_IqGame_Fragment_Keyboard8 = view.findViewById(R.id.imageView_IqGame_Fragment_Keyboard8);
        imageView_IqGame_Fragment_Keyboard9 = view.findViewById(R.id.imageView_IqGame_Fragment_Keyboard9);
        imageView_IqGame_Fragment_Keyboard0 = view.findViewById(R.id.imageView_IqGame_Fragment_Keyboard0);
        imageView_IqGame_Fragment_Keyboardback = view.findViewById(R.id.imageView_IqGame_Fragment_Keyboardback);
        imageView_IqGame_Fragment_Keyboardok = view.findViewById(R.id.imageView_IqGame_Fragment_Keyboardok);
        numberForResult = -1;

        vibration = new Vibration();
    }

    private void checkCombinations() {
        if(combination == 1){
            textView_NewNumber1.setText(iqGame.getNumber1() + "");
            textView_NewNumber2.setText(iqGame.getNumber2() + "");
            textView_NewNumber3.setText("?");
            textView_NewNumber4.setAlpha(0.0f);
            textView_NewNumber5.setText(iqGame.getNumber5() + "");
            textView_NewNumber6.setText(iqGame.getNumber6() + "");
            textView_NewNumber7.setText(iqGame.getNumber7() + "");
            textView_NewNumber8.setAlpha(0.0f);
            textView_NewNumber9.setAlpha(0.0f);
            textView_NewNumber10.setAlpha(0.0f);
            textView_NewNumber11.setAlpha(0.0f);
            textView_NewNumber12.setAlpha(0.0f);
            result = iqGame.getResult();
        }
        if(combination == 2){
            textView_NewNumber1.setText(iqGame.getNumber1() + "");
            textView_NewNumber2.setText(iqGame.getNumber2() + "");
            textView_NewNumber3.setText(iqGame.getNumber3() + "");
            textView_NewNumber4.setAlpha(0.0f);
            textView_NewNumber5.setText(iqGame.getNumber5() + "");
            textView_NewNumber6.setText(iqGame.getNumber6() + "");
            textView_NewNumber7.setText("?");
            textView_NewNumber8.setAlpha(0.0f);
            textView_NewNumber9.setAlpha(0.0f);
            textView_NewNumber10.setAlpha(0.0f);
            textView_NewNumber11.setAlpha(0.0f);
            textView_NewNumber12.setAlpha(0.0f);
            result = iqGame.getResult();
        }
        if(combination == 3){
            textView_NewNumber1.setText("?");
            textView_NewNumber2.setText(iqGame.getNumber2() + "");
            textView_NewNumber3.setText(iqGame.getNumber3() + "");
            textView_NewNumber4.setAlpha(0.0f);
            textView_NewNumber5.setText(iqGame.getNumber5() + "");
            textView_NewNumber6.setText(iqGame.getNumber6() + "");
            textView_NewNumber7.setText(iqGame.getNumber7() + "");
            textView_NewNumber8.setAlpha(0.0f);
            textView_NewNumber9.setAlpha(0.0f);
            textView_NewNumber10.setAlpha(0.0f);
            textView_NewNumber11.setAlpha(0.0f);
            textView_NewNumber12.setAlpha(0.0f);
            result = iqGame.getResult();
        }
        if(combination == 4){
            textView_NewNumber1.setText(iqGame.getNumber1() + "");
            textView_NewNumber2.setText(iqGame.getNumber2() + "");
            textView_NewNumber3.setText(iqGame.getNumber3() + "");
            textView_NewNumber4.setText("?");
            textView_NewNumber5.setText(iqGame.getNumber5() + "");
            textView_NewNumber6.setText(iqGame.getNumber6() + "");
            textView_NewNumber7.setText(iqGame.getNumber7() + "");
            textView_NewNumber8.setText(iqGame.getNumber8() + "");
            textView_NewNumber9.setAlpha(0.0f);
            textView_NewNumber10.setAlpha(0.0f);
            textView_NewNumber11.setAlpha(0.0f);
            textView_NewNumber12.setAlpha(0.0f);
            result = iqGame.getResult();

        }
        if(combination == 5){
            textView_NewNumber1.setText(iqGame.getNumber1() + "");
            textView_NewNumber2.setText(iqGame.getNumber2() + "");
            textView_NewNumber3.setText(iqGame.getNumber3() + "");
            textView_NewNumber4.setText(iqGame.getNumber4() + "");
            textView_NewNumber5.setText(iqGame.getNumber5() + "");
            textView_NewNumber6.setText(iqGame.getNumber6() + "");
            textView_NewNumber7.setText(iqGame.getNumber7() + "");
            textView_NewNumber8.setText("?");
            textView_NewNumber9.setAlpha(0.0f);
            textView_NewNumber10.setAlpha(0.0f);
            textView_NewNumber11.setAlpha(0.0f);
            textView_NewNumber12.setAlpha(0.0f);
            result = iqGame.getResult();
        }
        if(combination == 6){
            textView_NewNumber1.setText(iqGame.getNumber1() + "");
            textView_NewNumber2.setText(iqGame.getNumber2() + "");
            textView_NewNumber3.setText(iqGame.getNumber3() + "");
            textView_NewNumber4.setText(iqGame.getNumber4() + "");
            textView_NewNumber5.setText("?");
            textView_NewNumber6.setText(iqGame.getNumber6() + "");
            textView_NewNumber7.setText(iqGame.getNumber7() + "");
            textView_NewNumber8.setText(iqGame.getNumber8() + "");
            textView_NewNumber9.setAlpha(0.0f);
            textView_NewNumber10.setAlpha(0.0f);
            textView_NewNumber11.setAlpha(0.0f);
            textView_NewNumber12.setAlpha(0.0f);
            result = iqGame.getResult();
        }
        if(combination == 7){
            textView_NewNumber1.setText(iqGame.getNumber1() + "");
            textView_NewNumber2.setText(iqGame.getNumber2() + "");
            textView_NewNumber3.setText(iqGame.getNumber3() + "");
            textView_NewNumber4.setAlpha(0.0f);
            textView_NewNumber5.setText(iqGame.getNumber5() + "");
            textView_NewNumber6.setText(iqGame.getNumber6() + "");
            textView_NewNumber7.setText(iqGame.getNumber7() + "");
            textView_NewNumber8.setAlpha(0.0f);
            textView_NewNumber9.setText("?");
            textView_NewNumber10.setText(iqGame.getNumber10() + "");
            textView_NewNumber11.setText(iqGame.getNumber11() + "");
            textView_NewNumber12.setAlpha(0.0f);
            result = iqGame.getResult();
        }
        if(combination == 8){
            textView_NewNumber1.setText(iqGame.getNumber1() + "");
            textView_NewNumber2.setText(iqGame.getNumber2() + "");
            textView_NewNumber3.setText(iqGame.getNumber3() + "");
            textView_NewNumber4.setAlpha(0.0f);
            textView_NewNumber5.setText(iqGame.getNumber5() + "");
            textView_NewNumber6.setText("?");
            textView_NewNumber7.setText(iqGame.getNumber7() + "");
            textView_NewNumber8.setAlpha(0.0f);
            textView_NewNumber9.setText(iqGame.getNumber9() + "");
            textView_NewNumber10.setText(iqGame.getNumber10() + "");
            textView_NewNumber11.setText(iqGame.getNumber11() + "");
            textView_NewNumber12.setAlpha(0.0f);
            result = iqGame.getResult();
        }
        if(combination == 9){
            textView_NewNumber1.setText(iqGame.getNumber1() + "");
            textView_NewNumber2.setText(iqGame.getNumber2() + "");
            textView_NewNumber3.setText(iqGame.getNumber3() + "");
            textView_NewNumber4.setAlpha(0.0f);
            textView_NewNumber5.setText("?");
            textView_NewNumber6.setText(iqGame.getNumber6() + "");
            textView_NewNumber7.setText(iqGame.getNumber7() + "");
            textView_NewNumber8.setAlpha(0.0f);
            textView_NewNumber9.setText(iqGame.getNumber9() + "");
            textView_NewNumber10.setText(iqGame.getNumber10() + "");
            textView_NewNumber11.setText(iqGame.getNumber11() + "");
            textView_NewNumber12.setAlpha(0.0f);
            result = iqGame.getResult();
        }
        if(combination == 10){
            textView_NewNumber1.setText(iqGame.getNumber1() + "");
            textView_NewNumber2.setText(iqGame.getNumber2() + "");
            textView_NewNumber3.setText(iqGame.getNumber3() + "");
            textView_NewNumber4.setText(iqGame.getNumber4() + "");
            textView_NewNumber5.setText(iqGame.getNumber5() + "");
            textView_NewNumber6.setText(iqGame.getNumber6() + "");
            textView_NewNumber7.setText(iqGame.getNumber7() + "");
            textView_NewNumber8.setText("?");
            textView_NewNumber9.setText(iqGame.getNumber9() + "");
            textView_NewNumber10.setText(iqGame.getNumber10() + "");
            textView_NewNumber11.setText(iqGame.getNumber11() + "");
            textView_NewNumber12.setText(iqGame.getNumber12() + "");
            result = iqGame.getResult();
        }
    }

    private void setFonts(){
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");
        textView_NewNumber1.setTypeface(typeface);
        textView_NewNumber2.setTypeface(typeface);
        textView_NewNumber3.setTypeface(typeface);
        textView_NewNumber4.setTypeface(typeface);
        textView_NewNumber5.setTypeface(typeface);
        textView_NewNumber6.setTypeface(typeface);
        textView_NewNumber7.setTypeface(typeface);
        textView_NewNumber8.setTypeface(typeface);
        textView_NewNumber9.setTypeface(typeface);
        textView_NewNumber10.setTypeface(typeface);
        textView_NewNumber11.setTypeface(typeface);
        textView_NewNumber12.setTypeface(typeface);
        editTextNewResult.setTypeface(typeface);
        textView_timer3.setTypeface(typeface);
        textView_level3.setTypeface(typeface);
        textView_Score_ForIqGame.setTypeface(typeface);
    }

    public void onKeyDownForFragment(){
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                myMediaPlayerForLast5Min.stopLast5Min();
                myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
                countDownTimer.cancel();


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                if (languageInteger == 1) {
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
                            editTextNewResult.setText("");
                            IqGameFragmentDirections.ActionİqGameFragmentToMathGameFragment action = IqGameFragmentDirections.actionİqGameFragmentToMathGameFragment();
                            action.setPoint(point);
                            if(level == 2){
                                action.setLevel(3);
                            }else if(level == 6){
                                action.setLevel(7);
                            }else if(level == 10){
                                action.setLevel(11);
                            }else if(level == 14){
                                action.setLevel(15);
                            }else if(level == 18){
                                action.setLevel(19);
                            }
                            Navigation.findNavController(getView()).navigate(action);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.areyousurequestion_english);
                } else if (languageInteger == 2) {
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
                            editTextNewResult.setText("");
                            IqGameFragmentDirections.ActionİqGameFragmentToMathGameFragment action = IqGameFragmentDirections.actionİqGameFragmentToMathGameFragment();
                            action.setPoint(point);
                            if(level == 2){
                                action.setLevel(3);
                            }else if(level == 6){
                                action.setLevel(7);
                            }else if(level == 10){
                                action.setLevel(11);
                            }else if(level == 14){
                                action.setLevel(15);
                            }else if(level == 18){
                                action.setLevel(19);
                            }
                            Navigation.findNavController(getView()).navigate(action);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.areyousurequestion_turkish);
                } else if (languageInteger == 3) {
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
                            editTextNewResult.setText("");
                            IqGameFragmentDirections.ActionİqGameFragmentToMathGameFragment action = IqGameFragmentDirections.actionİqGameFragmentToMathGameFragment();
                            action.setPoint(point);
                            if(level == 2){
                                action.setLevel(3);
                            }else if(level == 6){
                                action.setLevel(7);
                            }else if(level == 10){
                                action.setLevel(11);
                            }else if(level == 14){
                                action.setLevel(15);
                            }else if(level == 18){
                                action.setLevel(19);
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

    private void keyboards(){
        imageView_IqGame_Fragment_Keyboard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 1;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 1;
                }

                editTextNewResult.setText( numberForResult + "");
            }
        });
        imageView_IqGame_Fragment_Keyboard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 2;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 2;
                }

                editTextNewResult.setText( numberForResult + "");
            }
        });
        imageView_IqGame_Fragment_Keyboard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 3;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 3;
                }

                editTextNewResult.setText( numberForResult + "");
            }
        });
        imageView_IqGame_Fragment_Keyboard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 4;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 4;
                }

                editTextNewResult.setText( numberForResult + "");
            }
        });
        imageView_IqGame_Fragment_Keyboard5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 5;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 5;
                }

                editTextNewResult.setText( numberForResult + "");
            }
        });
        imageView_IqGame_Fragment_Keyboard6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 6;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 6;
                }

                editTextNewResult.setText( numberForResult + "");
            }
        });
        imageView_IqGame_Fragment_Keyboard7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 7;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 7;
                }

                editTextNewResult.setText( numberForResult + "");
            }
        });
        imageView_IqGame_Fragment_Keyboard8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 8;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 8;
                }

                editTextNewResult.setText( numberForResult + "");
            }
        });
        imageView_IqGame_Fragment_Keyboard9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 9;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 9;
                }

                editTextNewResult.setText( numberForResult + "");
            }
        });
        imageView_IqGame_Fragment_Keyboard0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 0;
                }else if(numberForResult <= 999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 0;
                }

                editTextNewResult.setText( numberForResult + "");
            }
        });
        imageView_IqGame_Fragment_Keyboardback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult > 9){
                    numberForResult = numberForResult / 10;
                    editTextNewResult.setText( numberForResult + "");
                }else if(numberForResult >= 0 && numberForResult <= 9){
                    numberForResult = 0;
                    editTextNewResult.setText("");
                }

            }
        });
        imageView_IqGame_Fragment_Keyboardok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                finalstate(view);
            }
        });
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