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
import com.serkanemek.garrybarry.operations.Divide;
import com.serkanemek.garrybarry.operations.Multi;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForCorrectAnswer;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForLast5Min;
import com.serkanemek.garrybarry.operations.Subs;
import com.serkanemek.garrybarry.operations.Sum;
import com.serkanemek.garrybarry.util.Vibration;

import java.util.Random;

public class MathGameFragment extends Fragment {

    TextView textView_NewMath1, textView_NewMath2,textView_NewMath3, textView_operation_forMathgame1, textView_operation_forMathgame2;
    TextView textView_timer4,textView_level4, textView_Score_ForMathGame;
    TextView editText_result_forMathgame;
    Integer level,result, point;
    CountDownTimer countDownTimer;

    Sum sum;
    Subs subs;
    Multi multi;
    Divide divide;


    ImageView imageView_MathGame_Fragment_Keyboard1,imageView_MathGame_Fragment_Keyboard2,
            imageView_MathGame_Fragment_Keyboard3,imageView_MathGame_Fragment_Keyboard4,
            imageView_MathGame_Fragment_Keyboard5,imageView_MathGame_Fragment_Keyboard6,
            imageView_MathGame_Fragment_Keyboard7,imageView_MathGame_Fragment_Keyboard8,
            imageView_MathGame_Fragment_Keyboard9,imageView_MathGame_Fragment_Keyboard0,
            imageView_MathGame_Fragment_Keyboardback,imageView_MathGame_Fragment_Keyboardok;

    Integer numberForResult;

    SharedPreferences sharedPreferences;
    Integer languageInteger;

    Random rnd;
    Integer randomNumberForAlertDialog;

    MyMediaPlayerForCorrectAnswer myMediaPlayerForCorrectAnswer;
    MyMediaPlayerForLast5Min myMediaPlayerForLast5Min;

    Boolean checkOnPause;

    Vibration vibration;

    public MathGameFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onKeyDownForFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_math_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializing(view);
        myMediaPlayerForCorrectAnswer = new MyMediaPlayerForCorrectAnswer();
        myMediaPlayerForLast5Min = new MyMediaPlayerForLast5Min();


        if(getArguments() != null){
            level = MathGameFragmentArgs.fromBundle(getArguments()).getLevel();
            textView_level4.setText("Question:" + level);
            point = MathGameFragmentArgs.fromBundle(getArguments()).getPoint();
            textView_Score_ForMathGame.setText("Score: " + point);
        }

        checkoperations();
        setFonts();
        timer(view);
        keyboards();

        sharedPreferences = getActivity().getSharedPreferences("com.serkanemek.garrybarry.Activities" , Context.MODE_PRIVATE);
        languageInteger = sharedPreferences.getInt("storedLanguage",0);

        if(languageInteger == 1){
            textView_level4.setText("Question:" + level);
            textView_Score_ForMathGame.setText("Score: " + point);
        }else if(languageInteger == 2){
            textView_level4.setText("Soru:" + level);
            textView_Score_ForMathGame.setText("Puan: " + point);
        }else if(languageInteger == 3){
            textView_level4.setText("Вопрос:" + level);
            textView_Score_ForMathGame.setText("Очко: " + point);
        }

    }

    private void timer(final View view){

        countDownTimer =   new CountDownTimer(15_000, 1_000) {
            @Override
            public void onTick(long l) {
                imageView_MathGame_Fragment_Keyboardok.setEnabled(true);
                if(languageInteger == 1) {
                    textView_timer4.setText("" + (l / 1000));
                }else if(languageInteger == 2){
                    textView_timer4.setText("" + (l / 1000));
                }else if(languageInteger == 3){
                    textView_timer4.setText("" + (l / 1000));
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
                            editText_result_forMathgame.setText("");
                            gotoNextFragment(view);
                        }
                    });

                }else if(languageInteger == 2){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>SONUÇ</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Geç Kaldın! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sıradaki soru ile devam ediniz</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            editText_result_forMathgame.setText("");
                            gotoNextFragment(view);
                        }
                    });

                }else if(languageInteger == 3){
                    builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Результат</b></font>")).setCancelable(false);
                    builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Ты опоздал! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Переходите к следующему вопросу</b></font>"));
                    builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            editText_result_forMathgame.setText("");
                            gotoNextFragment(view);
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
        if(l/1_000 == 14){
            textView_level4.setTextColor(Color.WHITE);
            textView_Score_ForMathGame.setTextColor(Color.WHITE);
        }
        if(l/1_000 == 13){
            textView_level4.setTextColor(Color.BLACK);
            textView_Score_ForMathGame.setTextColor(Color.BLACK);
        }
    }

    private void finalstate(final View view){
            try {
                myMediaPlayerForLast5Min.stopLast5Min();
                imageView_MathGame_Fragment_Keyboardok.setEnabled(false);
                AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
                if(result == Integer.parseInt(editText_result_forMathgame.getText().toString())){
                    vibration.getVibrator(75,getActivity().getApplicationContext());
                    myMediaPlayerForCorrectAnswer.playCorrectAnswerSound(getActivity());
                    countDownTimer.cancel();
                    point += 5;

                    if(languageInteger == 1){
                        textView_Score_ForMathGame.setText("Score: " + point);
                    }else if(languageInteger == 2){
                        textView_Score_ForMathGame.setText("Puan: " + point);
                    }else if(languageInteger == 3){
                        textView_Score_ForMathGame.setText("Очко: " + point);
                    }


                    if(languageInteger == 1){
                        builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>RESULT</b></font>")).setCancelable(false);
                        builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Correct answer <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Congratulations</b></font>"));
                        builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                editText_result_forMathgame.setText("");
                                gotoNextFragment(view);
                                myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
                            }
                        });

                    }else if(languageInteger == 2){
                        builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>SONUÇ</b></font>")).setCancelable(false);
                        builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Doğru Cevap <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tebrikler</b></font>"));
                        builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                editText_result_forMathgame.setText("");
                                gotoNextFragment(view);
                                myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
                            }
                        });

                    }else if(languageInteger == 3){
                        builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Результат</b></font>")).setCancelable(false);
                        builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Правильный ответ <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Поздравления</b></font>"));
                        builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                editText_result_forMathgame.setText("");
                                gotoNextFragment(view);
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
                                editText_result_forMathgame.setText("");
                                gotoNextFragment(view);
                            }
                        });

                    }else if(languageInteger == 2){
                        builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>SONUÇ</b></font>")).setCancelable(false);
                        builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Yanlış Cevap! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sıradaki soru ile devam ediniz</b></font>"));
                        builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                editText_result_forMathgame.setText("");
                                gotoNextFragment(view);
                            }
                        });

                    }else if(languageInteger == 3){
                        builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Результат</b></font>")).setCancelable(false);
                        builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><br><b>Неверный ответ! <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Переходите к следующему вопросу</b></font>"));
                        builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><br><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                editText_result_forMathgame.setText("");
                                gotoNextFragment(view);
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

    private void checkoperations(){
        if(level == 3){
                textView_NewMath1.setText(sum.getNumber1() + "");
                textView_NewMath2.setText(sum.getNumber2() + "");
                textView_operation_forMathgame1.setText("+");
                textView_NewMath3.setAlpha(1.0f);
                textView_NewMath3.setText(sum.getNumber3() + "");
                textView_operation_forMathgame2.setAlpha(1.0f);
                textView_operation_forMathgame2.setText("+");
                result = sum.calculater(5);

        } else if(level == 7){
            textView_NewMath1.setText(subs.getNumber1() + "");
            textView_NewMath2.setText(subs.getNumber2() + "");
            textView_operation_forMathgame1.setText("-");
            result = subs.calculater(3);
        } else if(level == 11){
            textView_NewMath1.setText(multi.getNumber1() + "");
            textView_NewMath2.setText(multi.getNumber2() + "");
            textView_operation_forMathgame1.setText("x");
            textView_NewMath3.setAlpha(1.0f);
            textView_NewMath3.setText(multi.getNumber3() + "");
            textView_operation_forMathgame2.setAlpha(1.0f);
            textView_operation_forMathgame2.setText("x");
            result = multi.calculater(3);
        } else if(level == 15){
            textView_NewMath1.setText(divide.getNumber1() + "");
            textView_NewMath2.setText(divide.getNumber2() + "");
            textView_operation_forMathgame1.setText("÷");
            result = divide.calculater(3);
        } else if(level == 19){
            textView_NewMath1.setText(sum.getNumber1() + "");
            textView_NewMath2.setText(sum.getNumber2() + "");
            textView_operation_forMathgame1.setText("+");
            textView_NewMath3.setAlpha(1.0f);
            textView_NewMath3.setText(sum.getNumber3() + "");
            textView_operation_forMathgame2.setAlpha(1.0f);
            textView_operation_forMathgame2.setText("+");
            result = sum.calculater(5);
        }
    }

    private void initializing(View view) {
        textView_NewMath1 = view.findViewById(R.id.textView_NewMath1);
        textView_NewMath2 = view.findViewById(R.id.textView_NewMath2);
        textView_NewMath3 = view.findViewById(R.id.textView_NewMath3);
        textView_NewMath3.setAlpha(0.0f);
        textView_operation_forMathgame1 = view.findViewById(R.id.textView_operation_forMathgame1);
        textView_operation_forMathgame2 = view.findViewById(R.id.textView_operation_forMathgame2);
        textView_operation_forMathgame2.setAlpha(0.0f);
        textView_timer4 = view.findViewById(R.id.textView_timer4);
        textView_level4 = view.findViewById(R.id.textView_level4);
        textView_Score_ForMathGame = view.findViewById(R.id.textView_Score_ForMathGame);
        editText_result_forMathgame = view.findViewById(R.id.editText_result_forMathgame);
        sum = new Sum(5);
        subs = new Subs(3);
        multi = new Multi(3);
        divide = new Divide(3);
        level = 1;
        result = -1;
        point = 0;
        checkOnPause = false;

        rnd = new Random();
        randomNumberForAlertDialog = rnd.nextInt(9);

        imageView_MathGame_Fragment_Keyboard1 = view.findViewById(R.id.imageView_MathGame_Fragment_Keyboard1);
        imageView_MathGame_Fragment_Keyboard2 = view.findViewById(R.id.imageView_MathGame_Fragment_Keyboard2);
        imageView_MathGame_Fragment_Keyboard3 = view.findViewById(R.id.imageView_MathGame_Fragment_Keyboard3);
        imageView_MathGame_Fragment_Keyboard4 = view.findViewById(R.id.imageView_MathGame_Fragment_Keyboard4);
        imageView_MathGame_Fragment_Keyboard5 = view.findViewById(R.id.imageView_MathGame_Fragment_Keyboard5);
        imageView_MathGame_Fragment_Keyboard6 = view.findViewById(R.id.imageView_MathGame_Fragment_Keyboard6);
        imageView_MathGame_Fragment_Keyboard7 = view.findViewById(R.id.imageView_MathGame_Fragment_Keyboard7);
        imageView_MathGame_Fragment_Keyboard8 = view.findViewById(R.id.imageView_MathGame_Fragment_Keyboard8);
        imageView_MathGame_Fragment_Keyboard9 = view.findViewById(R.id.imageView_MathGame_Fragment_Keyboard9);
        imageView_MathGame_Fragment_Keyboard0 = view.findViewById(R.id.imageView_MathGame_Fragment_Keyboard0);
        imageView_MathGame_Fragment_Keyboardback = view.findViewById(R.id.imageView_MathGame_Fragment_Keyboardback);
        imageView_MathGame_Fragment_Keyboardok = view.findViewById(R.id.imageView_MathGame_Fragment_Keyboardok);
        numberForResult = -1;

        vibration = new Vibration();
    }

    private void gotoNextFragment(View view){
        if(level == 3){
            MathGameFragmentDirections.ActionMathGameFragmentToTriangleFragment action = MathGameFragmentDirections.actionMathGameFragmentToTriangleFragment();
            action.setLevel(4);
            action.setPoint(point);
            Navigation.findNavController(view).navigate(action);
        }else if(level == 7){
            MathGameFragmentDirections.ActionMathGameFragmentToRectangleFragment action = MathGameFragmentDirections.actionMathGameFragmentToRectangleFragment();
            action.setLevel(8);
            action.setPoint(point);
            Navigation.findNavController(view).navigate(action);
        }else if(level == 11){
            MathGameFragmentDirections.ActionMathGameFragmentToXFigureFragment action = MathGameFragmentDirections.actionMathGameFragmentToXFigureFragment();
            action.setLevel(12);
            action.setPoint(point);
            Navigation.findNavController(view).navigate(action);
        }else if(level == 15){
            MathGameFragmentDirections.ActionMathGameFragmentToTriangleFragment action = MathGameFragmentDirections.actionMathGameFragmentToTriangleFragment();
            action.setLevel(16);
            action.setPoint(point);
            Navigation.findNavController(view).navigate(action);
        }else if(level == 19){
            MathGameFragmentDirections.ActionMathGameFragmentToXFigureFragment action = MathGameFragmentDirections.actionMathGameFragmentToXFigureFragment();
            action.setLevel(20);
            action.setPoint(point);
            Navigation.findNavController(view).navigate(action);
        }
    }

    private void setFonts(){
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");
        textView_NewMath1.setTypeface(typeface);
        textView_NewMath2.setTypeface(typeface);
        textView_NewMath3.setTypeface(typeface);
        textView_operation_forMathgame1.setTypeface(typeface);
        textView_operation_forMathgame2.setTypeface(typeface);
        editText_result_forMathgame.setTypeface(typeface);
        textView_timer4.setTypeface(typeface);
        textView_level4.setTypeface(typeface);
        textView_Score_ForMathGame.setTypeface(typeface);
    }

    public void onKeyDownForFragment(){
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                myMediaPlayerForLast5Min.stopLast5Min();
                countDownTimer.cancel();
                myMediaPlayerForCorrectAnswer.stopCorrectAnswerSound();
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
                            editText_result_forMathgame.setText("");
                            gotoNextFragment(getView());

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
                            editText_result_forMathgame.setText("");
                            gotoNextFragment(getView());

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
                            editText_result_forMathgame.setText("");
                            gotoNextFragment(getView());

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
        imageView_MathGame_Fragment_Keyboard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 1;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 1;
                }

                editText_result_forMathgame.setText( numberForResult + "");
            }
        });
        imageView_MathGame_Fragment_Keyboard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 2;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 2;
                }

                editText_result_forMathgame.setText( numberForResult + "");
            }
        });
        imageView_MathGame_Fragment_Keyboard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 3;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 3;
                }

                editText_result_forMathgame.setText( numberForResult + "");
            }
        });
        imageView_MathGame_Fragment_Keyboard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 4;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 4;
                }

                editText_result_forMathgame.setText( numberForResult + "");
            }
        });
        imageView_MathGame_Fragment_Keyboard5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 5;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 5;
                }

                editText_result_forMathgame.setText( numberForResult + "");
            }
        });
        imageView_MathGame_Fragment_Keyboard6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 6;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 6;
                }

                editText_result_forMathgame.setText( numberForResult + "");
            }
        });
        imageView_MathGame_Fragment_Keyboard7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 7;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 7;
                }

                editText_result_forMathgame.setText( numberForResult + "");
            }
        });
        imageView_MathGame_Fragment_Keyboard8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 8;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 8;
                }

                editText_result_forMathgame.setText( numberForResult + "");
            }
        });
        imageView_MathGame_Fragment_Keyboard9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 9;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 9;
                }

                editText_result_forMathgame.setText( numberForResult + "");
            }
        });
        imageView_MathGame_Fragment_Keyboard0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult == -1){
                    numberForResult = 0;
                }else if(numberForResult <= 9999  && numberForResult >= 0){
                    numberForResult = (numberForResult * 10) + 0;
                }

                editText_result_forMathgame.setText( numberForResult + "");
            }
        });
        imageView_MathGame_Fragment_Keyboardback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibration.getVibrator(75,getActivity().getApplicationContext());
                if(numberForResult > 9){
                    numberForResult = numberForResult / 10;
                    editText_result_forMathgame.setText( numberForResult + "");
                }else if(numberForResult >= 0 && numberForResult <= 9){
                    numberForResult = 0;
                    editText_result_forMathgame.setText("");
                }

            }
        });
        imageView_MathGame_Fragment_Keyboardok.setOnClickListener(new View.OnClickListener() {
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