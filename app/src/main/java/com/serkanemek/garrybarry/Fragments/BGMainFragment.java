package com.serkanemek.garrybarry.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.serkanemek.garrybarry.Activities.MainMenuActivity1;
import com.serkanemek.garrybarry.R;
import com.serkanemek.garrybarry.util.Vibration;

public class BGMainFragment extends Fragment {


    private static final int FILTERED_GREY = Color.argb(155, 185, 185, 185);
    private static final int TRANSPARENT_GREY = Color.argb(0, 185, 185, 185);
    TextView textView_BG_Title;
    ImageView imageView_Memory_bg,imageView_Iq_bg,imageView_Math_bg,imageView_MathPractice_bg;
    NavDirections navDirections;

    SharedPreferences sharedPreferences;
    Integer languageInteger;

    Vibration vibration;

    public BGMainFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onKeyDownForFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b_g_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializing(view);
        setFonts();
        checkLanguage();


        imageView_Memory_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memoryButtonBG(view);
            }
        });
        imageView_Iq_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iQButtonBG(view);
            }
        });
        imageView_Math_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MathButtonBG(view);
            }
        });
        imageView_MathPractice_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MathPracticeButtonBG(view);
            }
        });


    }

    private void checkLanguage(){
        sharedPreferences = getActivity().getSharedPreferences("com.serkanemek.garrybarry.Activities" , Context.MODE_PRIVATE);
        languageInteger = sharedPreferences.getInt("storedLanguage",0);
        if(languageInteger == 1){
            imageView_Memory_bg.setImageResource(R.drawable.memorygame_image);
            imageView_Iq_bg.setImageResource(R.drawable.iqgame_image);
            imageView_Math_bg.setImageResource(R.drawable.mathgame_image);
            imageView_MathPractice_bg.setImageResource(R.drawable.mathgame_forpractice_image);
        }else if(languageInteger == 2){
            imageView_Memory_bg.setImageResource(R.drawable.hafizaoyunuimage);
            imageView_Iq_bg.setImageResource(R.drawable.iqoyunu);
            imageView_Math_bg.setImageResource(R.drawable.mathoyunuimage);
            imageView_MathPractice_bg.setImageResource(R.drawable.mathoyunu_pratik_image);
        }else if(languageInteger == 3){
            imageView_Memory_bg.setImageResource(R.drawable.memorygame_image_russian);
            imageView_Iq_bg.setImageResource(R.drawable.iqgame_image_russian);
            imageView_Math_bg.setImageResource(R.drawable.math_image_russian);
            imageView_MathPractice_bg.setImageResource(R.drawable.math_forpractice_image_russian);
        }
    }

    private void initializing(View view){
        textView_BG_Title = view.findViewById(R.id.textView_BG_Title);
        imageView_Memory_bg = view.findViewById(R.id.imageView_Memory_bg);
        imageView_Memory_bg.setColorFilter(TRANSPARENT_GREY);
        imageView_Iq_bg = view.findViewById(R.id.imageView_Iq_bg);
        imageView_Iq_bg.setColorFilter(TRANSPARENT_GREY);
        imageView_Math_bg = view.findViewById(R.id.imageView_Math_bg);
        imageView_Math_bg.setColorFilter(TRANSPARENT_GREY);
        imageView_MathPractice_bg = view.findViewById(R.id.imageView_MathPractice_bg);
        imageView_MathPractice_bg.setColorFilter(TRANSPARENT_GREY);

        vibration = new Vibration();
    }

    private void setButtonClickEnable(){
        imageView_Memory_bg.setEnabled(false);
        imageView_Iq_bg.setEnabled(false);
        imageView_Math_bg.setEnabled(false);
        imageView_MathPractice_bg.setEnabled(false);

    }

    public void memoryButtonBG(View view){
        setButtonClickEnable();
        vibration.getVibrator(75,getActivity().getApplicationContext());
        imageView_Memory_bg.setColorFilter(FILTERED_GREY);
        navDirections = BGMainFragmentDirections.actionBackgroundMainFragmentToBackgroundMemoryGameFragment();
        Navigation.findNavController(view).navigate(navDirections);
    }

    public void iQButtonBG(View view){
        setButtonClickEnable();
        vibration.getVibrator(75,getActivity().getApplicationContext());
        imageView_Iq_bg.setColorFilter(FILTERED_GREY);
        navDirections = BGMainFragmentDirections.actionBackgroundMainFragmentToBackgroundIqGameFragment();
        Navigation.findNavController(view).navigate(navDirections);
    }

    public void MathButtonBG(View view){
        setButtonClickEnable();
        vibration.getVibrator(75,getActivity().getApplicationContext());
        imageView_Math_bg.setColorFilter(FILTERED_GREY);
        navDirections = BGMainFragmentDirections.actionBackgroundMainFragmentToBGMathFragment();
        Navigation.findNavController(view).navigate(navDirections);
    }

    public void MathPracticeButtonBG(View view){
        setButtonClickEnable();
        vibration.getVibrator(75,getActivity().getApplicationContext());
        imageView_MathPractice_bg.setColorFilter(FILTERED_GREY);
        navDirections = BGMainFragmentDirections.actionBackgroundMainFragmentToBGMathPracticeFragment();
        Navigation.findNavController(view).navigate(navDirections);
    }

    private void setFonts(){
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");
        textView_BG_Title.setTypeface(typeface);
    }

    public void onKeyDownForFragment(){
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {

                Intent intent = new Intent(getActivity(), MainMenuActivity1.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish();

            }

        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }



}