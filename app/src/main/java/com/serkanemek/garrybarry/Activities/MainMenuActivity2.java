package com.serkanemek.garrybarry.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.serkanemek.garrybarry.R;
import com.serkanemek.garrybarry.util.AnimationForKeyboard;
import com.serkanemek.garrybarry.mediaplayer.MyMediaPlayerForMainMenu;
import com.serkanemek.garrybarry.data.SharedPForAdvAndTotalQuestionCounter;
import com.serkanemek.garrybarry.data.SharedPForCoins;
import com.serkanemek.garrybarry.data.SharedPForDiamondCoins;
import com.serkanemek.garrybarry.util.Vibration;
import com.google.android.gms.ads.rewarded.RewardedAd;

import java.util.Random;

public class MainMenuActivity2 extends AppCompatActivity {

    private static final int FILTERED_GREY = Color.argb(155, 185, 185, 185);
    private static final int TRANSPARENT_GREY = Color.argb(0, 185, 185, 185);
    ImageView imageView_For_NewEpisode,imageView_For_MemoryGame,imageView_For_IQGame,
            imageView_For_MathGame, imageView_For_MathGamePractice;

    ImageView imageView_ForRewardedAdvertisement;

    SharedPreferences sharedPreferences;
    Integer languageInteger;

    SharedPForCoins sharedPForEnergy;
    TextView textView_For_Coins;
    TextView textView_diamond;

    SharedPForAdvAndTotalQuestionCounter sharedPForAdvAndTotalQuestionCounter;
    Integer checkCoins;

    Integer rewardedAdCounterData;

    SharedPForDiamondCoins sharedPForDiamondCoins;

    MyMediaPlayerForMainMenu myMediaPlayerForMainMenu;
    Integer checkMusicOnOff;

    AnimationForKeyboard animationForKeyboard;

    Integer checkOnOffPause;//For statistics mediaplayer will not pause.

    Vibration vibration;

    static Toast toastForAd;

    private AdView mAdView; // For Banner ad

    private RewardedAd mRewardedAd;
    private AdRequest adRequestForRewarded;

    //Yeni Ödüllü ad TEST ca-app-pub-3940256099942544/5354046379

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu2);



        checkOnOffPause = 0;
        checkCoins = 0;
        textView_For_Coins = findViewById(R.id.textView_For_Coins);
        sharedPForEnergy = new SharedPForCoins();
        textView_For_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
        checkCoins = sharedPForEnergy.getCoinsData(this);

        sharedPForAdvAndTotalQuestionCounter = new SharedPForAdvAndTotalQuestionCounter();

        checkMusicOnOff = sharedPForAdvAndTotalQuestionCounter.getCheckMusicForOnOff(this);
        rewardedAdCounterData = sharedPForAdvAndTotalQuestionCounter.getAdvertisementCounterData(getApplicationContext());


        myMediaPlayerForMainMenu = new MyMediaPlayerForMainMenu();

        initializing();

        checkLanguage();

        toastMessageForAd();

        setFonts();

        getAnimationEffect();

        getBannerAdvertisement();
        getRewardedAdvertisement();
        updateRewardedAd_imageview();
    }

    private void updateRewardedAd_imageview() {
        rewardedAdCounterData = sharedPForAdvAndTotalQuestionCounter.getAdvertisementCounterData(getApplicationContext());

        if(rewardedAdCounterData == 0){
            imageView_ForRewardedAdvertisement.setImageResource(R.drawable.rewarded_ad_0);
        }else if(rewardedAdCounterData == 1){
            imageView_ForRewardedAdvertisement.setImageResource(R.drawable.rewarded_ad_1);
        }else if(rewardedAdCounterData == 2){
            imageView_ForRewardedAdvertisement.setImageResource(R.drawable.rewarded_ad_2);
        }else if(rewardedAdCounterData == 3){
            imageView_ForRewardedAdvertisement.setImageResource(R.drawable.rewarded_ad_3);
        }else if(rewardedAdCounterData == 4){
            imageView_ForRewardedAdvertisement.setImageResource(R.drawable.rewarded_ad_4);
        }else {
            imageView_ForRewardedAdvertisement.setImageResource(R.drawable.rewarded_ad);
        }
    }

    private void getRewardedAdvertisement() {

        adRequestForRewarded = new AdRequest.Builder().build();

        loadAdForRewardedAd();



            imageView_ForRewardedAdvertisement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vibration.getVibrator(75, getApplicationContext());

                    if(rewardedAdCounterData >= 5){

                        if (mRewardedAd != null) {

                            mRewardedAd.show(MainMenuActivity2.this, new OnUserEarnedRewardListener() {

                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    Log.d("TAG", "The user earned the reward. YOU WON COINSSS");
                                    loadAdForRewardedAd();//with this we making reload ad.

                                    adAlertDialog();
                                }
                            });
                        } else {
                            Log.d("TAG", "The rewarded ad wasn't ready yet.");
                        }
                    }else{
                        toastForAd.show();
                    }
                }
            });


    }

    private void loadAdForRewardedAd(){
        RewardedAd.load(this, "ca-app-pub-3940256099942544/5354046379",
                adRequestForRewarded, new RewardedAdLoadCallback(){
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d("TAG", loadAdError.getMessage());
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        Log.d("TAG", "Ad was loaded.");


                        mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when ad is shown.
                                Log.d("TAG", "Ad was shown.");
                                mRewardedAd = null;



                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when ad fails to show.
                                Log.d("TAG", "Ad failed to show.");
                                updateRewardedAd_imageview();
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when ad is dismissed.
                                // Don't forget to set the ad reference to null so you
                                // don't show the ad a second time.
                                Log.d("TAG", "Ad was dismissed.");
                                myMediaPlayerForMainMenu.playBackgroundMusic(MainMenuActivity2.this);
                                sharedPForAdvAndTotalQuestionCounter.makeItZeroAdvertisementCounterData(getApplicationContext());
                                updateRewardedAd_imageview();
                                imageView_ForRewardedAdvertisement.setEnabled(false);
                            }
                        });

                    }
                });
    }

    private void adAlertDialog(){

        Random random = new Random();
        Integer random1 = random.nextInt(5) + 1;
        Log.d("TAG1", random1 + "");

        if(random1 == 5){
            random1 = random.nextInt(5) + 1;
            Log.d("TAG2", random1 + "");
        }

        AlertDialog.Builder builderForAd =new AlertDialog.Builder(this);

        if(languageInteger == 1){
            builderForAd.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Congratulations</b></font>")).setCancelable(false);

            if(random1 == 1){
                builderForAd.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>You Won 25 Coins <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>"));
                builderForAd.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPForEnergy.updateCoinsData(getApplicationContext(),25);
                        textView_For_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
                    }
                });
            }else if(random1 == 2){
                builderForAd.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>You Won 50 Coins <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>"));
                builderForAd.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPForEnergy.updateCoinsData(getApplicationContext(),50);
                        textView_For_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
                    }
                });
            }else if(random1 == 3){
                builderForAd.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>You Won 75 Coins <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>"));
                builderForAd.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPForEnergy.updateCoinsData(getApplicationContext(),75);
                        textView_For_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
                    }
                });
            }else if(random1 == 4){
                builderForAd.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>You Won 100 Coins <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>"));
                builderForAd.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPForEnergy.updateCoinsData(getApplicationContext(),100);
                        textView_For_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
                    }
                });
            }else if(random1 == 5){
                builderForAd.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>You Won 1 Diamond <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>"));
                builderForAd.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPForDiamondCoins.updateDiamondCoinsData(getApplicationContext(),1);
                        Integer diamond = sharedPForDiamondCoins.getDiamondCoinsData(getApplicationContext());
                        textView_diamond.setText(diamond + "");
                    }
                });
            }

        }else if(languageInteger == 2){
            builderForAd.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Tebrikler</b></font>")).setCancelable(false);

            if(random1 == 1){
                builderForAd.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>25 Coins Kazandınız <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>"));
                builderForAd.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPForEnergy.updateCoinsData(getApplicationContext(),25);
                        textView_For_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
                    }
                });
            }else if(random1 == 2){
                builderForAd.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>50 Coins Kazandınız<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>"));
                builderForAd.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPForEnergy.updateCoinsData(getApplicationContext(),50);
                        textView_For_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
                    }
                });
            }else if(random1 == 3){
                builderForAd.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>75 Coins Kazandınız<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>"));
                builderForAd.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPForEnergy.updateCoinsData(getApplicationContext(),75);
                        textView_For_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
                    }
                });
            }else if(random1 == 4){
                builderForAd.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>100 Coins Kazandınız<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>"));
                builderForAd.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPForEnergy.updateCoinsData(getApplicationContext(),100);
                        textView_For_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
                    }
                });
            }else if(random1 == 5){
                builderForAd.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>1 Diamond Kazandınız<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>"));
                builderForAd.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPForDiamondCoins.updateDiamondCoinsData(getApplicationContext(),1);
                        Integer diamond = sharedPForDiamondCoins.getDiamondCoinsData(getApplicationContext());
                        textView_diamond.setText(diamond + "");
                    }
                });
            }

        }else if(languageInteger == 3){
            builderForAd.setTitle(Html.fromHtml("<font color='#FF8B00'><b>Поздравления</b></font>")).setCancelable(false);

            if(random1 == 1){
                builderForAd.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>Вы выиграли 25 Coins <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>"));
                builderForAd.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPForEnergy.updateCoinsData(getApplicationContext(),25);
                        textView_For_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
                    }
                });
            }else if(random1 == 2){
                builderForAd.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>Вы выиграли 50 Coins <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>"));
                builderForAd.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPForEnergy.updateCoinsData(getApplicationContext(),50);
                        textView_For_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
                    }
                });
            }else if(random1 == 3){
                builderForAd.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>Вы выиграли 75 Coins <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>"));
                builderForAd.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPForEnergy.updateCoinsData(getApplicationContext(),75);
                        textView_For_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
                    }
                });
            }else if(random1 == 4){
                builderForAd.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>Вы выиграли 100 Coins <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>"));
                builderForAd.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPForEnergy.updateCoinsData(getApplicationContext(),100);
                        textView_For_Coins.setText("Coins: " + sharedPForEnergy.getCoinsData(getApplicationContext()));
                    }
                });
            }else if(random1 == 5){
                builderForAd.setMessage(Html.fromHtml("<font size='5' color='#FFFFFF'><b>Вы выиграли 1 Diamond <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>"));
                builderForAd.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPForDiamondCoins.updateDiamondCoinsData(getApplicationContext(),1);
                        Integer diamond = sharedPForDiamondCoins.getDiamondCoinsData(getApplicationContext());
                        textView_diamond.setText(diamond + "");
                    }
                });
            }

        }



        AlertDialog dialog = builderForAd.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.alertdialog_for_ad);

    }

    private void toastMessageForAd(){
        toastForAd = Toast.makeText(this, "For free Coins&Diamond, You should solve a few questions", Toast.LENGTH_SHORT);
        if(languageInteger == 1){
            toastForAd.setText("You should solve a few questions for free Coins&Diamond");
        }else if(languageInteger == 2){
            toastForAd.setText("Ücretsiz Coins&Diamond için birkaç soru çözmelisin");
        }else if(languageInteger == 3){
            toastForAd.setText("Вы должны решить несколько вопросов бесплатно Coins&Diamond");
        }
    }

    private void getBannerAdvertisement() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adViewMainMenu);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);




    }

    private void getAnimationEffect() {
        animationForKeyboard = new AnimationForKeyboard();
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),300,"left",imageView_For_MemoryGame);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),300,"right",imageView_For_IQGame);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),500,"left",imageView_For_MathGame);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),500,"right",imageView_For_MathGamePractice);
        animationForKeyboard.animationForKeyboardInitializing(getApplicationContext(),800,"fadein",imageView_For_NewEpisode);
    }

    private void checkLanguage() {

        sharedPreferences = this.getSharedPreferences("com.serkanemek.garrybarry.Activities" , Context.MODE_PRIVATE);
        languageInteger = sharedPreferences.getInt("storedLanguage",0);
        if(languageInteger == 1){
            if(checkCoins >= 50){
                imageView_For_NewEpisode.setImageResource(R.drawable.newepisode_image);
            }else {
                imageView_For_NewEpisode.setImageResource(R.drawable.newepisode_image_without200coins);
            }

            imageView_For_IQGame.setImageResource(R.drawable.iqgame_image);
            imageView_For_MathGame.setImageResource(R.drawable.mathgame_image);
            imageView_For_MemoryGame.setImageResource(R.drawable.memorygame_image);
            imageView_For_MathGamePractice.setImageResource(R.drawable.mathgame_forpractice_image);

        }else if( languageInteger == 2){

            if(checkCoins >= 50){
                imageView_For_NewEpisode.setImageResource(R.drawable.yenioyunimage);
            }else {
                imageView_For_NewEpisode.setImageResource(R.drawable.yenioyunimage_without200coins);
            }

            imageView_For_IQGame.setImageResource(R.drawable.iqoyunu);
            imageView_For_MathGame.setImageResource(R.drawable.mathoyunuimage);
            imageView_For_MemoryGame.setImageResource(R.drawable.hafizaoyunuimage);
            imageView_For_MathGamePractice.setImageResource(R.drawable.mathoyunu_pratik_image);

        }else if(languageInteger == 3){
            if(checkCoins >= 50){
                imageView_For_NewEpisode.setImageResource(R.drawable.newepisode_image_russian);
            }else{
                imageView_For_NewEpisode.setImageResource(R.drawable.newepisode_image_without200coins_russian);
            }

            imageView_For_IQGame.setImageResource(R.drawable.iqgame_image_russian);
            imageView_For_MathGame.setImageResource(R.drawable.math_image_russian);
            imageView_For_MemoryGame.setImageResource(R.drawable.memorygame_image_russian);
            imageView_For_MathGamePractice.setImageResource(R.drawable.math_forpractice_image_russian);

        }

    }

    private void initializing() {

        imageView_For_NewEpisode = findViewById(R.id.imageView_For_NewEpisode);
        imageView_For_NewEpisode.setColorFilter(TRANSPARENT_GREY);
        imageView_For_MemoryGame = findViewById(R.id.imageView_For_MemoryGame);
        imageView_For_MemoryGame.setColorFilter(TRANSPARENT_GREY);
        imageView_For_IQGame = findViewById(R.id.imageView_For_IQGame);
        imageView_For_IQGame.setColorFilter(TRANSPARENT_GREY);
        imageView_For_MathGame = findViewById(R.id.imageView_For_MathGame);
        imageView_For_MathGame.setColorFilter(TRANSPARENT_GREY);
        imageView_For_MathGamePractice = findViewById(R.id.imageView_For_MathGamePractice);
        imageView_For_MathGamePractice.setColorFilter(TRANSPARENT_GREY);

        imageView_ForRewardedAdvertisement = findViewById(R.id.imageView_ForRewardedAdvertisement);
        imageView_ForRewardedAdvertisement.setEnabled(true);
        textView_diamond = findViewById(R.id.textView_diamond);
        sharedPForDiamondCoins = new SharedPForDiamondCoins();
        Integer diamond = sharedPForDiamondCoins.getDiamondCoinsData(getApplicationContext());
        textView_diamond.setText(diamond + "");

        vibration = new Vibration();


    }

    public void memorygame(View view){

            setButtonClickEnable();
            myMediaPlayerForMainMenu.stopBackgroundMusic();
            toastForAd.cancel();
            imageView_For_MemoryGame.setColorFilter(FILTERED_GREY);
            Intent intentToMemoryGame = new Intent(getApplicationContext(), MemoryGame_Activity.class);
            intentToMemoryGame.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intentToMemoryGame);
            finish();
            vibration.getVibrator(75,getApplicationContext());

    }

    public void iqgame(View view){
        vibration.getVibrator(75,getApplicationContext());
        toastForAd.cancel();

        setButtonClickEnable();
        myMediaPlayerForMainMenu.stopBackgroundMusic();

        imageView_For_IQGame.setColorFilter(FILTERED_GREY);
        Intent intentToIqExercise = new Intent(getApplicationContext(), IqGame_Activity.class);
        intentToIqExercise.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentToIqExercise);
        finish();

    }

    public void newepisode(View view){
        Integer coins = 0;
        coins = sharedPForEnergy.getCoinsData(getApplicationContext());
        vibration.getVibrator(75,getApplicationContext());
        toastForAd.cancel();
        if(coins >= 50){
            setButtonClickEnable();
            myMediaPlayerForMainMenu.stopBackgroundMusic();
            sharedPForEnergy.updateCoinsData(this, -50);
            imageView_For_NewEpisode.setColorFilter(FILTERED_GREY);

            try {
                if(languageInteger == 1){
                    AlertDialog.Builder builderForBegin =new AlertDialog.Builder(this);
                    builderForBegin.setTitle(Html.fromHtml("<font color='#FF8B00'><b></b></font>")).setCancelable(false);
                    builderForBegin.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent intentToNewEpisode = new Intent(getApplicationContext(),NewEpisodeActivity.class);
                            intentToNewEpisode.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intentToNewEpisode);
                            finish();

                        }
                    });
                    AlertDialog dialogForBegin = builderForBegin.create();
                    dialogForBegin.show();
                    dialogForBegin.getWindow().setBackgroundDrawableResource(R.drawable.challenge_startinfo_english);

                }else if(languageInteger == 2){
                    AlertDialog.Builder builderForBegin =new AlertDialog.Builder(this);
                    builderForBegin.setTitle(Html.fromHtml("<font color='#FF8B00'><b></b></font>")).setCancelable(false);
                    builderForBegin.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent intentToNewEpisode = new Intent(getApplicationContext(),NewEpisodeActivity.class);
                            intentToNewEpisode.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intentToNewEpisode);
                            finish();

                        }
                    });
                    AlertDialog dialogForBegin = builderForBegin.create();
                    dialogForBegin.show();
                    dialogForBegin.getWindow().setBackgroundDrawableResource(R.drawable.challenge_startinfo_turkish);
                }else if(languageInteger == 3){
                    AlertDialog.Builder builderForBegin =new AlertDialog.Builder(this);
                    builderForBegin.setTitle(Html.fromHtml("<font color='#FF8B00'><b></b></font>")).setCancelable(false);
                    builderForBegin.setPositiveButton(Html.fromHtml("<font color='#FFFFFF'><b>               </b> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent intentToNewEpisode = new Intent(getApplicationContext(),NewEpisodeActivity.class);
                            intentToNewEpisode.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intentToNewEpisode);
                            finish();

                        }
                    });
                    AlertDialog dialogForBegin = builderForBegin.create();
                    dialogForBegin.show();
                    dialogForBegin.getWindow().setBackgroundDrawableResource(R.drawable.challenge_startinfo_russian);
                }

            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }

    public void mathgame(View view){
        vibration.getVibrator(75,getApplicationContext());
        toastForAd.cancel();

        setButtonClickEnable();
        myMediaPlayerForMainMenu.stopBackgroundMusic();

        imageView_For_MathGame.setColorFilter(FILTERED_GREY);
        Intent intentToExercise = new Intent(getApplicationContext(), MathGameForMainActivity.class);
        intentToExercise.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentToExercise);
        finish();

    }

    public void mathgameforpractice(View view){
        setButtonClickEnable();
        myMediaPlayerForMainMenu.stopBackgroundMusic();
        toastForAd.cancel();
        imageView_For_MathGamePractice.setColorFilter(FILTERED_GREY);
        Intent intentToExercise = new Intent(getApplicationContext(), MathLevelCheckActivity.class);
        intentToExercise.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentToExercise);
        finish();
        vibration.getVibrator(75,getApplicationContext());
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

    private void setFonts(){
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/FredokaOne-Regular.ttf");
       textView_For_Coins.setTypeface(typeface);
        textView_diamond.setTypeface(typeface);
    }

    private void setButtonClickEnable(){
        imageView_For_NewEpisode.setEnabled(false);
        imageView_For_IQGame.setEnabled(false);
        imageView_For_MemoryGame.setEnabled(false);
        imageView_For_MathGame.setEnabled(false);
        imageView_For_MathGamePractice.setEnabled(false);

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
        toastForAd.cancel();
        if(checkOnOffPause == 0){
            myMediaPlayerForMainMenu.pauseBackgroundMusic();
        }

    }


}
