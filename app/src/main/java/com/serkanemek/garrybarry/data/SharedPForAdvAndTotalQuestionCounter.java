package com.serkanemek.garrybarry.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPForAdvAndTotalQuestionCounter {


    SharedPreferences sharedPreferencesForAdvertisement;
    SharedPreferences sharedPreferencesForMemoryGameTotalQuestion;
    SharedPreferences sharedPreferencesForMemoryGameCorrectAnswers;
    SharedPreferences sharedPreferencesForIqGameTotalQuestion;
    SharedPreferences sharedPreferencesForIqGameCorrectAnswers;
    SharedPreferences sharedPreferencesForMathGameTotalQuestion;
    SharedPreferences sharedPreferencesForMathGameCorrectAnswers;
    SharedPreferences sharedPreferencesForMathGameForPracticeTotalQuestion;
    SharedPreferences sharedPreferencesForMathGameForPracticeCorrectAnswers;

    SharedPreferences sharedPreferencesForCheckMusicForOnOff;
    SharedPreferences sharedPreferencesForCheckSoundForOnOff;
    SharedPreferences sharedPreferencesForCheckVibrationOnOff;

    public void updateAdvertisementCounterData(Context context, Integer counterNumber){
        try {
            Integer storedAdvertisementCounter = -1;
            sharedPreferencesForAdvertisement = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedAdvertisementCounter = sharedPreferencesForAdvertisement.getInt("storedAdvertisementCounter",0);
            storedAdvertisementCounter = storedAdvertisementCounter + counterNumber;
            sharedPreferencesForAdvertisement.edit().putInt("storedAdvertisementCounter" , storedAdvertisementCounter).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getAdvertisementCounterData(Context context){
        Integer storedAdvertisementCounter = -1;
        try {
            sharedPreferencesForAdvertisement = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedAdvertisementCounter = sharedPreferencesForAdvertisement.getInt("storedAdvertisementCounter",0);
            return storedAdvertisementCounter;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedAdvertisementCounter;

    }

    public void makeItZeroAdvertisementCounterData(Context context){
        try {
            sharedPreferencesForAdvertisement = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            sharedPreferencesForAdvertisement.edit().putInt("storedAdvertisementCounter" , 0).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void updateMemoryGameTotalQuestionData(Context context, Integer counterNumber){
        try {
            Integer storedMemoryGameTotalQuestion = -1;
            sharedPreferencesForMemoryGameTotalQuestion = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedMemoryGameTotalQuestion = sharedPreferencesForMemoryGameTotalQuestion.getInt("storedMemoryGameTotalQuestionData",0);
            storedMemoryGameTotalQuestion = storedMemoryGameTotalQuestion + counterNumber;
            sharedPreferencesForMemoryGameTotalQuestion.edit().putInt("storedMemoryGameTotalQuestionData" , storedMemoryGameTotalQuestion).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getMemoryGameTotalQuestionData(Context context){
        Integer storedMemoryGameTotalQuestion = -1;
        try {
            sharedPreferencesForMemoryGameTotalQuestion = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedMemoryGameTotalQuestion = sharedPreferencesForMemoryGameTotalQuestion.getInt("storedMemoryGameTotalQuestionData",0);
            return storedMemoryGameTotalQuestion;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedMemoryGameTotalQuestion;

    }

    public void updateMemoryGameCorrectAnswersData(Context context, Integer counterNumber){
        try {
            Integer storedMemoryGameCorrectAnswers = -1;
            sharedPreferencesForMemoryGameCorrectAnswers = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedMemoryGameCorrectAnswers = sharedPreferencesForMemoryGameCorrectAnswers.getInt("storedMemoryGameCorrectAnswersData",0);
            storedMemoryGameCorrectAnswers = storedMemoryGameCorrectAnswers + counterNumber;
            sharedPreferencesForMemoryGameCorrectAnswers.edit().putInt("storedMemoryGameCorrectAnswersData" , storedMemoryGameCorrectAnswers).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getMemoryGameCorrectAnswersData(Context context){
        Integer storedMemoryGameCorrectAnswers = -1;
        try {
            sharedPreferencesForMemoryGameCorrectAnswers = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedMemoryGameCorrectAnswers = sharedPreferencesForMemoryGameCorrectAnswers.getInt("storedMemoryGameCorrectAnswersData",0);
            return storedMemoryGameCorrectAnswers;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedMemoryGameCorrectAnswers;

    }

    public void updateIqGameTotalQuestionData(Context context, Integer counterNumber){
        try {
            Integer storedIqGameTotalQuestion = -1;
            sharedPreferencesForIqGameTotalQuestion = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedIqGameTotalQuestion = sharedPreferencesForIqGameTotalQuestion.getInt("storedIqGameTotalQuestionData",0);
            storedIqGameTotalQuestion = storedIqGameTotalQuestion + counterNumber;
            sharedPreferencesForIqGameTotalQuestion.edit().putInt("storedIqGameTotalQuestionData" , storedIqGameTotalQuestion).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getIqGameTotalQuestionData(Context context){
        Integer storedIqGameTotalQuestion = -1;
        try {
            sharedPreferencesForIqGameTotalQuestion = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedIqGameTotalQuestion = sharedPreferencesForIqGameTotalQuestion.getInt("storedIqGameTotalQuestionData",0);
            return storedIqGameTotalQuestion;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedIqGameTotalQuestion;

    }

    public void updateIqGameCorrectAnswersData(Context context, Integer counterNumber){
        try {
            Integer storedIqGameCorrectAnswers = -1;
            sharedPreferencesForIqGameCorrectAnswers = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedIqGameCorrectAnswers = sharedPreferencesForIqGameCorrectAnswers.getInt("storedIqGameCorrectAnswersData",0);
            storedIqGameCorrectAnswers = storedIqGameCorrectAnswers + counterNumber;
            sharedPreferencesForIqGameCorrectAnswers.edit().putInt("storedIqGameCorrectAnswersData" , storedIqGameCorrectAnswers).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getIqGameCorrectAnswersData(Context context){
        Integer storedIqGameCorrectAnswers = -1;
        try {
            sharedPreferencesForIqGameCorrectAnswers = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedIqGameCorrectAnswers = sharedPreferencesForIqGameCorrectAnswers.getInt("storedIqGameCorrectAnswersData",0);
            return storedIqGameCorrectAnswers;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedIqGameCorrectAnswers;

    }

    public void updateMathGameTotalQuestionData(Context context, Integer counterNumber){
        try {
            Integer storedMathGameTotalQuestion = -1;
            sharedPreferencesForMathGameTotalQuestion = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedMathGameTotalQuestion = sharedPreferencesForMathGameTotalQuestion.getInt("storedMathGameTotalQuestionData",0);
            storedMathGameTotalQuestion = storedMathGameTotalQuestion + counterNumber;
            sharedPreferencesForMathGameTotalQuestion.edit().putInt("storedMathGameTotalQuestionData" , storedMathGameTotalQuestion).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getMathGameTotalQuestionData(Context context){
        Integer storedMathGameTotalQuestion = -1;
        try {
            sharedPreferencesForMathGameTotalQuestion = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedMathGameTotalQuestion = sharedPreferencesForMathGameTotalQuestion.getInt("storedMathGameTotalQuestionData",0);
            return storedMathGameTotalQuestion;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedMathGameTotalQuestion;

    }

    public void updateMathGameCorrectAnswersData(Context context, Integer counterNumber){
        try {
            Integer storedMathGameCorrectAnswers = -1;
            sharedPreferencesForMathGameCorrectAnswers = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedMathGameCorrectAnswers = sharedPreferencesForMathGameCorrectAnswers.getInt("storedMathGameCorrectAnswersData",0);
            storedMathGameCorrectAnswers = storedMathGameCorrectAnswers + counterNumber;
            sharedPreferencesForMathGameCorrectAnswers.edit().putInt("storedMathGameCorrectAnswersData" , storedMathGameCorrectAnswers).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getMathGameCorrectAnswersData(Context context){
        Integer storedMathGameCorrectAnswers = -1;
        try {
            sharedPreferencesForMathGameCorrectAnswers = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedMathGameCorrectAnswers = sharedPreferencesForMathGameCorrectAnswers.getInt("storedMathGameCorrectAnswersData",0);
            return storedMathGameCorrectAnswers;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedMathGameCorrectAnswers;

    }

    public void updateMathGameForPracticeTotalQuestionData(Context context, Integer counterNumber){
        try {
            Integer storedMathGameForPracticeTotalQuestion = -1;
            sharedPreferencesForMathGameForPracticeTotalQuestion = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedMathGameForPracticeTotalQuestion = sharedPreferencesForMathGameForPracticeTotalQuestion.getInt("storedMathGameForPracticeTotalQuestionData",0);
            storedMathGameForPracticeTotalQuestion = storedMathGameForPracticeTotalQuestion + counterNumber;
            sharedPreferencesForMathGameForPracticeTotalQuestion.edit().putInt("storedMathGameForPracticeTotalQuestionData" , storedMathGameForPracticeTotalQuestion).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getMathGameForPracticeTatolQuestionData(Context context){
        Integer storedMathGameForPracticeTotalQuestion = -1;
        try {
            sharedPreferencesForMathGameForPracticeTotalQuestion = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedMathGameForPracticeTotalQuestion = sharedPreferencesForMathGameForPracticeTotalQuestion.getInt("storedMathGameForPracticeTotalQuestionData",0);
            return storedMathGameForPracticeTotalQuestion;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedMathGameForPracticeTotalQuestion;

    }

    public void updateMathGameForPracticeCorrectAnswersData(Context context, Integer counterNumber){
        try {
            Integer storedMathGameForPracticeCorrectAnswers = -1;
            sharedPreferencesForMathGameForPracticeCorrectAnswers = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedMathGameForPracticeCorrectAnswers = sharedPreferencesForMathGameForPracticeCorrectAnswers.getInt("storedMathGameForPracticeCorrectAnswersData",0);
            storedMathGameForPracticeCorrectAnswers = storedMathGameForPracticeCorrectAnswers + counterNumber;
            sharedPreferencesForMathGameForPracticeCorrectAnswers.edit().putInt("storedMathGameForPracticeCorrectAnswersData" , storedMathGameForPracticeCorrectAnswers).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getMathGameForPracticeCorrectAnswersData(Context context){
        Integer storedMathGameForPracticeCorrectAnswers = -1;
        try {
            sharedPreferencesForMathGameForPracticeCorrectAnswers = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            storedMathGameForPracticeCorrectAnswers = sharedPreferencesForMathGameForPracticeCorrectAnswers.getInt("storedMathGameForPracticeCorrectAnswersData",0);
            return storedMathGameForPracticeCorrectAnswers;
        }catch (Exception e){
            e.printStackTrace();
        }
        return storedMathGameForPracticeCorrectAnswers;

    }

    public void makeThemAllReset(){
        try {
            sharedPreferencesForMemoryGameTotalQuestion.edit().putInt("storedMemoryGameTotalQuestionData",0).apply();
            sharedPreferencesForMemoryGameCorrectAnswers.edit().putInt("storedMemoryGameCorrectAnswersData" , 0).apply();
            sharedPreferencesForIqGameTotalQuestion.edit().putInt("storedIqGameTotalQuestionData" , 0).apply();
            sharedPreferencesForIqGameCorrectAnswers.edit().putInt("storedIqGameCorrectAnswersData" , 0).apply();
            sharedPreferencesForMathGameTotalQuestion.edit().putInt("storedMathGameTotalQuestionData" , 0).apply();
            sharedPreferencesForMathGameCorrectAnswers.edit().putInt("storedMathGameCorrectAnswersData" , 0).apply();
            sharedPreferencesForMathGameForPracticeTotalQuestion.edit().putInt("storedMathGameForPracticeTotalQuestionData" , 0).apply();
            sharedPreferencesForMathGameForPracticeCorrectAnswers.edit().putInt("storedMathGameForPracticeCorrectAnswersData" , 0).apply();

        }catch (Exception e){
            e.printStackTrace();
        }



    }


    public void updateCheckMusicForOnOff(Context context,Integer integer){  // false is off, true is on.
        try {
            sharedPreferencesForCheckMusicForOnOff = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            sharedPreferencesForCheckMusicForOnOff.edit().putInt("storedCheckMusicOnOffData",integer).apply();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getCheckMusicForOnOff(Context context){
        Integer checkMusic = -1;
        try {
            sharedPreferencesForCheckMusicForOnOff = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            checkMusic = sharedPreferencesForCheckMusicForOnOff.getInt("storedCheckMusicOnOffData", 1);
            return checkMusic;
        }catch (Exception e){
            e.printStackTrace();
        }
        return checkMusic;
    }


    public void updateCheckSoundForOnOff(Context context,Integer integer){  // 0 is off, 1 is on.
        try {
            sharedPreferencesForCheckSoundForOnOff = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            sharedPreferencesForCheckSoundForOnOff.edit().putInt("storedCheckSoundOnOffData",integer).apply();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getCheckSoundForOnOff(Context context){
        Integer checkSound = -1;
        try {
            sharedPreferencesForCheckSoundForOnOff = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            checkSound = sharedPreferencesForCheckSoundForOnOff.getInt("storedCheckSoundOnOffData", 1);
            return checkSound;
        }catch (Exception e){
            e.printStackTrace();
        }
        return checkSound;
    }

    public void updateCheckVibrationForOnOff(Context context,Integer integer){  // 0 is off, 1 is on.
        try {
            sharedPreferencesForCheckVibrationOnOff = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            sharedPreferencesForCheckVibrationOnOff.edit().putInt("storedCheckVibrationOnOffData",integer).apply();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getCheckVibrationForOnOff(Context context){
        Integer checkVibrator = -1;
        try {
            sharedPreferencesForCheckVibrationOnOff = context.getSharedPreferences("com.serkanemek.garrybarry.Activities", Context.MODE_PRIVATE);
            checkVibrator = sharedPreferencesForCheckVibrationOnOff.getInt("storedCheckVibrationOnOffData", 1);
            return checkVibrator;
        }catch (Exception e){
            e.printStackTrace();
        }
        return checkVibrator;
    }


}
