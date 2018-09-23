package com.lifesoft.chc.view.activity;

import android.content.Intent;
import android.graphics.Color;

import com.daimajia.androidanimations.library.Techniques;
import com.lifesoft.chc.chargingcontrol.R;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class SplashScreenActivity extends AwesomeSplash {

    @Override
    public void initSplash(ConfigSplash configSplash) {
        getSupportActionBar().hide();
        //Customize Circular
        configSplash.setBackgroundColor(R.color.splash_background); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.ic_launcher); //or any other drawable
        configSplash.setAnimLogoSplashDuration(1000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Path
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(1000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setAnimPathFillingDuration(1000);


        //Customize Title
        configSplash.setTitleSplash("Charging Control");
        configSplash.setTitleTextSize(30f); //float value
        configSplash.setAnimTitleDuration(1000);
        configSplash.setTitleTextColor(R.color.black);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
    }

    @Override
    public void animationsFinished() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
