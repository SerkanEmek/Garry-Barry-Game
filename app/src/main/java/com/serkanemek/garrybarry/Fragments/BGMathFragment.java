package com.serkanemek.garrybarry.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.serkanemek.garrybarry.Activities.MainMenuActivity1;
import com.serkanemek.garrybarry.R;
import com.serkanemek.garrybarry.data.SharedPForBackgrounds;
import com.serkanemek.garrybarry.data.SharedPForDiamondCoins;
import com.serkanemek.garrybarry.util.Vibration;

public class BGMathFragment extends Fragment {

    SharedPForBackgrounds sharedPForBackgrounds;
    SharedPForDiamondCoins sharedPForDiamondCoins;
    Integer diamond,numberBGMath,numberCheckSell2,numberCheckSell3,numberCheckSell4;

    TextView textView_Bg_Math_Diamond;
    ImageView imageView_bg_Math1,imageView_bg_Math2,imageView_bg_Math3,imageView_bg_Math4,
            imageView_bg_math_title;

    NavDirections navDirections;

    SharedPreferences sharedPreferences;
    Integer languageInteger;

    AlertDialog.Builder builder;
    static Toast toast;

    Vibration vibration;

    public BGMathFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onKeyDownForFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b_g_math, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializing(view);
        checkLanguage();
        toastForDiamondMessage();


        imageView_bg_Math1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogForImage1(view);
                vibration.getVibrator(75,getActivity().getApplicationContext());
                setButtonClickEnable(false);
            }
        });

        imageView_bg_Math2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogForImage2(view);
                vibration.getVibrator(75,getActivity().getApplicationContext());
                setButtonClickEnable(false);
            }
        });

        imageView_bg_Math3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogForImage3(view);
                vibration.getVibrator(75,getActivity().getApplicationContext());
                setButtonClickEnable(false);
            }
        });

        imageView_bg_Math4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogForImage4(view);
                vibration.getVibrator(75,getActivity().getApplicationContext());
                setButtonClickEnable(false);
            }
        });


    }


    private void checkLanguage() {
        sharedPreferences = getActivity().getSharedPreferences("com.serkanemek.garrybarry.Activities" , Context.MODE_PRIVATE);
        languageInteger = sharedPreferences.getInt("storedLanguage",0);
        if(languageInteger == 1){
            imageView_bg_math_title.setImageResource(R.drawable.mathgame_image);
        }else if( languageInteger == 2){
            imageView_bg_math_title.setImageResource(R.drawable.mathoyunuimage);
        }else if(languageInteger == 3){
            imageView_bg_math_title.setImageResource(R.drawable.math_image_russian);
        }

    }

    private void initializing(View view){

        sharedPForBackgrounds = new SharedPForBackgrounds();
        sharedPForDiamondCoins = new SharedPForDiamondCoins();

        textView_Bg_Math_Diamond = view.findViewById(R.id.textView_Bg_Math_Diamond);
        diamond = sharedPForDiamondCoins.getDiamondCoinsData(getActivity().getApplicationContext());
        textView_Bg_Math_Diamond.setText("" + diamond);
        setFonts();

        numberBGMath = sharedPForBackgrounds.getMathGameBackground(getActivity().getApplicationContext());
        numberCheckSell2 = sharedPForBackgrounds.getsPCheckSellMath2(getActivity().getApplicationContext());
        numberCheckSell3 = sharedPForBackgrounds.getsPCheckSellMath3(getActivity().getApplicationContext());
        numberCheckSell4 = sharedPForBackgrounds.getsPCheckSellMath4(getActivity().getApplicationContext());

        imageView_bg_Math1 = view.findViewById(R.id.imageView_bg_Math1);
        imageView_bg_Math2 = view.findViewById(R.id.imageView_bg_Math2);
        imageView_bg_Math3 = view.findViewById(R.id.imageView_bg_Math3);
        imageView_bg_Math4 = view.findViewById(R.id.imageView_bg_Math4);

        imageView_bg_math_title = view.findViewById(R.id.imageView_bg_math_title);

        firstSetBackgrounds();

        checkChosenImage();

        vibration = new Vibration();
    }

    private void firstSetBackgrounds() {
        imageView_bg_Math1.setImageResource(R.drawable.bg_math_main_icon_01);

        if(numberCheckSell2 == 1){ //if didnt buy with diamond.
            imageView_bg_Math2.setImageResource(R.drawable.bg_math_main_icon_02_lock);
        } else if(numberCheckSell2 == 2){//if you buy with diamond.
            imageView_bg_Math2.setImageResource(R.drawable.bg_math_main_icon_02);
        }

        if(numberCheckSell3 == 1){
            imageView_bg_Math3.setImageResource(R.drawable.bg_math_main_icon_03_lock);
        }else if(numberCheckSell3 == 2){
            imageView_bg_Math3.setImageResource(R.drawable.bg_math_main_icon_03);
        }

        if(numberCheckSell4 == 1){
            imageView_bg_Math4.setImageResource(R.drawable.bg_darkmood_icon_lock);
        }else if(numberCheckSell4 == 2){
            imageView_bg_Math4.setImageResource(R.drawable.bg_darkmood_icon);
        }
    }

    private void checkChosenImage() {
        if(numberBGMath == 1){
            imageView_bg_Math1.setImageResource(R.drawable.bg_math_main_icon_01_chosen);
        }else if(numberBGMath == 2){
            imageView_bg_Math2.setImageResource(R.drawable.bg_math_main_icon_02_chosen);
        }else if(numberBGMath == 3){
            imageView_bg_Math3.setImageResource(R.drawable.bg_math_main_icon_03_chosen);
        }else if(numberBGMath == 4){
            imageView_bg_Math3.setImageResource(R.drawable.bg_darkmood_icon_chosen);
        }
    }

    private void dialogForImage1(final View view) {
        builder =new AlertDialog.Builder(getActivity());

        if(languageInteger == 1){
            builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>BACKGROUND</b></font>")).setCancelable(false);
            builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>Do you want to change background?</b></font>"));
            builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b> YES </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),1);
                    navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                    Navigation.findNavController(view).navigate(navDirections);
                    toast.cancel();
                }
            });
            builder.setNegativeButton(Html.fromHtml("<font color='#FFFFFF'><b> NO </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    toast.cancel();
                    setButtonClickEnable(true);
                }
            });
        }else if(languageInteger == 2){
            builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>ARKA PLAN</b></font>")).setCancelable(false);
            builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>Arka planı değiştirmek istiyor musun?</b></font>"));
            builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b> EVET </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),1);
                    navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                    Navigation.findNavController(view).navigate(navDirections);
                    toast.cancel();
                }
            });
            builder.setNegativeButton(Html.fromHtml("<font color='#FFFFFF'><b> HAYIR </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    toast.cancel();
                    setButtonClickEnable(true);

                }
            });
        }else if(languageInteger == 3){
            builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>ФОН</b></font>")).setCancelable(false);
            builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>Вы хотите сменить фон?</b></font>"));
            builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b> ДА </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),1);
                    navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                    Navigation.findNavController(view).navigate(navDirections);
                    toast.cancel();
                }
            });
            builder.setNegativeButton(Html.fromHtml("<font color='#FFFFFF'><b> НЕТ </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    toast.cancel();
                    setButtonClickEnable(true);

                }
            });
        }

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.alertdialog3);

    }

    private void dialogForImage2(final View view) {
        builder =new AlertDialog.Builder(getActivity());

        if(languageInteger == 1){
            builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>BACKGROUND</b></font>")).setCancelable(false);
            builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>Do you want to change background?</b></font>"));
            builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b> YES </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Integer check = -1;
                    check = sharedPForBackgrounds.getsPCheckSellMath2(getActivity().getApplicationContext());
                    if(check == 1){
                        if(diamond >= 3){
                            sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),2);
                            sharedPForBackgrounds.updatesPCheckSellMath2(getActivity().getApplicationContext(),2);
                            sharedPForDiamondCoins.updateDiamondCoinsData(getActivity().getApplicationContext(),-3);
                            navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                            Navigation.findNavController(view).navigate(navDirections);
                            toast.cancel();
                        }else {
                            toast.show();
                            setButtonClickEnable(true);
                        }
                    }else if (check == 2){
                        sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),2);
                        navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                        Navigation.findNavController(view).navigate(navDirections);
                        toast.cancel();
                    }
                }
            });
            builder.setNegativeButton(Html.fromHtml("<font color='#FFFFFF'><b> NO </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    toast.cancel();
                    setButtonClickEnable(true);

                }
            });
        }else if(languageInteger == 2){
            builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>ARKA PLAN</b></font>")).setCancelable(false);
            builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>Arka planı değiştirmek istiyor musun?</b></font>"));
            builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b> EVET </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Integer check = -1;
                    check = sharedPForBackgrounds.getsPCheckSellMath2(getActivity().getApplicationContext());
                    if(check == 1){
                        if(diamond >= 3){
                            sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),2);
                            sharedPForBackgrounds.updatesPCheckSellMath2(getActivity().getApplicationContext(),2);
                            sharedPForDiamondCoins.updateDiamondCoinsData(getActivity().getApplicationContext(),-3);
                            navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                            Navigation.findNavController(view).navigate(navDirections);
                            toast.cancel();
                        }else {
                            toast.show();
                            setButtonClickEnable(true);
                        }
                    }else if (check == 2){
                        sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),2);
                        navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                        Navigation.findNavController(view).navigate(navDirections);
                        toast.cancel();
                    }
                }
            });
            builder.setNegativeButton(Html.fromHtml("<font color='#FFFFFF'><b> HAYIR </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    toast.cancel();
                    setButtonClickEnable(true);
                }
            });
        }else if(languageInteger == 3){
            builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>ФОН</b></font>")).setCancelable(false);
            builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>Вы хотите сменить фон?</b></font>"));
            builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b> ДА </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Integer check = -1;
                    check = sharedPForBackgrounds.getsPCheckSellMath2(getActivity().getApplicationContext());
                    if(check == 1){
                        if(diamond >= 3){
                            sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),2);
                            sharedPForBackgrounds.updatesPCheckSellMath2(getActivity().getApplicationContext(),2);
                            sharedPForDiamondCoins.updateDiamondCoinsData(getActivity().getApplicationContext(),-3);
                            navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                            Navigation.findNavController(view).navigate(navDirections);
                            toast.cancel();
                        }else {
                            toast.show();
                            setButtonClickEnable(true);
                        }
                    }else if (check == 2){
                        sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),2);
                        navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                        Navigation.findNavController(view).navigate(navDirections);
                        toast.cancel();
                    }
                }
            });
            builder.setNegativeButton(Html.fromHtml("<font color='#FFFFFF'><b> НЕТ </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    toast.cancel();
                    setButtonClickEnable(true);

                }
            });
        }

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.alertdialog3);

    }

    private void dialogForImage3(final View view) {
        builder =new AlertDialog.Builder(getActivity());

        if(languageInteger == 1){
            builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>BACKGROUND</b></font>")).setCancelable(false);
            builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>Do you want to change background?</b></font>"));
            builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b> YES </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Integer check = -1;
                    check = sharedPForBackgrounds.getsPCheckSellMath3(getActivity().getApplicationContext());
                    if(check == 1){
                        if(diamond >= 5){
                            sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),3);
                            sharedPForBackgrounds.updatesPCheckSellMath3(getActivity().getApplicationContext(),2);
                            sharedPForDiamondCoins.updateDiamondCoinsData(getActivity().getApplicationContext(),-5);
                            navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                            Navigation.findNavController(view).navigate(navDirections);
                            toast.cancel();
                        }else {
                            toast.show();
                            setButtonClickEnable(true);
                        }
                    }else if(check == 2){
                        sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),3);
                        navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                        Navigation.findNavController(view).navigate(navDirections);
                        toast.cancel();
                    }
                }
            });
            builder.setNegativeButton(Html.fromHtml("<font color='#FFFFFF'><b> NO </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    toast.cancel();
                    setButtonClickEnable(true);
                }
            });
        }else if(languageInteger == 2){
            builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>ARKA PLAN</b></font>")).setCancelable(false);
            builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>Arka planı değiştirmek istiyor musun?</b></font>"));
            builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b> EVET </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Integer check = -1;
                    check = sharedPForBackgrounds.getsPCheckSellMath3(getActivity().getApplicationContext());
                    if(check == 1){
                        if(diamond >= 5){
                            sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),3);
                            sharedPForBackgrounds.updatesPCheckSellMath3(getActivity().getApplicationContext(),2);
                            sharedPForDiamondCoins.updateDiamondCoinsData(getActivity().getApplicationContext(),-5);
                            navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                            Navigation.findNavController(view).navigate(navDirections);
                            toast.cancel();
                        }else {
                            toast.show();
                            setButtonClickEnable(true);
                        }
                    }else if(check == 2){
                        sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),3);
                        navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                        Navigation.findNavController(view).navigate(navDirections);
                        toast.cancel();
                    }
                }
            });
            builder.setNegativeButton(Html.fromHtml("<font color='#FFFFFF'><b> HAYIR </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    toast.cancel();
                    setButtonClickEnable(true);
                }
            });
        }else if(languageInteger == 3){
            builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>ФОН</b></font>")).setCancelable(false);
            builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>Вы хотите сменить фон?</b></font>"));
            builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b> ДА </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Integer check = -1;
                    check = sharedPForBackgrounds.getsPCheckSellMath3(getActivity().getApplicationContext());
                    if(check == 1){
                        if(diamond >= 5){
                            sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),3);
                            sharedPForBackgrounds.updatesPCheckSellMath3(getActivity().getApplicationContext(),2);
                            sharedPForDiamondCoins.updateDiamondCoinsData(getActivity().getApplicationContext(),-5);
                            navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                            Navigation.findNavController(view).navigate(navDirections);
                            toast.cancel();
                        }else {
                            toast.show();
                            setButtonClickEnable(true);
                        }
                    }else if(check == 2){
                        sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),3);
                        navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                        Navigation.findNavController(view).navigate(navDirections);
                        toast.cancel();
                    }
                }
            });
            builder.setNegativeButton(Html.fromHtml("<font color='#FFFFFF'><b> НЕТ </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    toast.cancel();
                    setButtonClickEnable(true);
                }
            });
        }

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.alertdialog3);

    }

    private void dialogForImage4(final View view) {
        builder =new AlertDialog.Builder(getActivity());

        if(languageInteger == 1){
            builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>BACKGROUND</b></font>")).setCancelable(false);
            builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>Do you want to change background?</b></font>"));
            builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b> YES </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Integer check = -1;
                    check = sharedPForBackgrounds.getsPCheckSellMath4(getActivity().getApplicationContext());

                    if(check == 1){
                        if(diamond >= 7){
                            sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),4);
                            sharedPForBackgrounds.updatesPCheckSellMath4(getActivity().getApplicationContext(),2);
                            sharedPForDiamondCoins.updateDiamondCoinsData(getActivity().getApplicationContext(),-7);
                            navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                            Navigation.findNavController(view).navigate(navDirections);
                            toast.cancel();
                        }else {
                            toast.show();
                            setButtonClickEnable(true);
                        }
                    }else if(check == 2){
                        sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),4);
                        navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                        Navigation.findNavController(view).navigate(navDirections);
                        toast.cancel();
                    }

                }
            });
            builder.setNegativeButton(Html.fromHtml("<font color='#FFFFFF'><b> NO </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    toast.cancel();
                    setButtonClickEnable(true);
                }
            });
        }else if(languageInteger == 2){
            builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>ARKA PLAN</b></font>")).setCancelable(false);
            builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>Arka planı değiştirmek istiyor musun?</b></font>"));
            builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b> EVET </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Integer check = -1;
                    check = sharedPForBackgrounds.getsPCheckSellMath4(getActivity().getApplicationContext());

                    if(check == 1){
                        if(diamond >= 7){
                            sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),4);
                            sharedPForBackgrounds.updatesPCheckSellMath4(getActivity().getApplicationContext(),2);
                            sharedPForDiamondCoins.updateDiamondCoinsData(getActivity().getApplicationContext(),-7);
                            navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                            Navigation.findNavController(view).navigate(navDirections);
                            toast.cancel();
                        }else {
                            toast.show();
                            setButtonClickEnable(true);
                        }
                    }else if(check == 2){
                        sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),4);
                        navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                        Navigation.findNavController(view).navigate(navDirections);
                        toast.cancel();
                    }

                }
            });
            builder.setNegativeButton(Html.fromHtml("<font color='#FFFFFF'><b> HAYIR </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    toast.cancel();
                    setButtonClickEnable(true);
                }
            });
        }else if(languageInteger == 3){
            builder.setTitle(Html.fromHtml("<font color='#FF8B00'><b>ФОН</b></font>")).setCancelable(false);
            builder.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>Вы хотите сменить фон?</b></font>"));
            builder.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b> ДА </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Integer check = -1;
                    check = sharedPForBackgrounds.getsPCheckSellMath4(getActivity().getApplicationContext());

                    if(check == 1){
                        if(diamond >= 7){
                            sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),4);
                            sharedPForBackgrounds.updatesPCheckSellMath4(getActivity().getApplicationContext(),2);
                            sharedPForDiamondCoins.updateDiamondCoinsData(getActivity().getApplicationContext(),-7);
                            navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                            Navigation.findNavController(view).navigate(navDirections);
                            toast.cancel();
                        }else {
                            toast.show();
                            setButtonClickEnable(true);
                        }
                    }else if(check == 2){
                        sharedPForBackgrounds.updateMathGameBackground(getActivity().getApplicationContext(),4);
                        navDirections = BGMathFragmentDirections.actionBGMathFragmentToBackgroundMainFragment();
                        Navigation.findNavController(view).navigate(navDirections);
                        toast.cancel();
                    }

                }
            });
            builder.setNegativeButton(Html.fromHtml("<font color='#FFFFFF'><b> НЕТ </b> </font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    toast.cancel();
                    setButtonClickEnable(true);
                }
            });
        }

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.alertdialog3);

    }

    private void toastForDiamondMessage(){
        toast = Toast.makeText(getActivity().getApplicationContext(), "not enough coins",Toast.LENGTH_SHORT);

        if(languageInteger == 1){
            toast.setText("You don't have enough diamonds");
        }else if(languageInteger == 2){
            toast.setText("Yeterli Elmas'ın yok");
        }else if(languageInteger == 3){
            toast.setText("У тебя недостаточно бриллиантов");
        }
    }

    public void setFonts(){
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");
        textView_Bg_Math_Diamond.setTypeface(typeface);
    }

    public void setButtonClickEnable(boolean b){

        if(b){
            imageView_bg_Math1.setEnabled(true);
            imageView_bg_Math2.setEnabled(true);
            imageView_bg_Math3.setEnabled(true);
            imageView_bg_Math4.setEnabled(true);

        }else{
            imageView_bg_Math1.setEnabled(false);
            imageView_bg_Math2.setEnabled(false);
            imageView_bg_Math3.setEnabled(false);
            imageView_bg_Math4.setEnabled(false);
        }

    }

    public void onKeyDownForFragment(){
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                toast.cancel();
                Intent intent = new Intent(getActivity(), MainMenuActivity1.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

}