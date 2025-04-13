package com.videosimplemaster.hdplaydownload.dummy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.videosimplemaster.hdplaydownload.MainActivity;
import com.videosimplemaster.hdplaydownload.R;
import com.videosimplemaster.hdplaydownload.util.AdsManager;
import com.videosimplemaster.hdplaydownload.util.PrefManagerVideo;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_two);
        if (new PrefManagerVideo(this).getString(SplashActivity.dummy_one_screen).contains("ad")) {
            AdsManager.showAndLoadNativeAd(this, findViewById(R.id.nativeAd),1);
        }
        AdsManager.showAndLoadNativeAd(this, findViewById(R.id.nativeAdTwo), 2);

        onClicks();

    }

    private void onClicks() {

        findViewById(R.id.tvNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity();
            }
        });

    }

    private void startActivity() {
        Intent intent;
        if (new PrefManagerVideo(SecondActivity.this).getString(SplashActivity.status_dummy_three_enabled).contains("true")) {
            intent = new Intent(SecondActivity.this, ThirdActivity.class);
        } else if (new PrefManagerVideo(SecondActivity.this).getString(SplashActivity.status_dummy_four_enabled).contains("true")) {
            intent = new Intent(SecondActivity.this, FourthActivity.class);
        } else if (new PrefManagerVideo(this).getString(SplashActivity.status_dummy_five_enabled).contains("true") && !new PrefManagerVideo(SecondActivity.this).getString(SplashActivity.TAG_NATIVEID).contains("sandeep")) {
            intent = new Intent(this, FifthActivity.class);
        } else if (new PrefManagerVideo(this).getString(SplashActivity.status_dummy_six_enabled).contains("true")) {
            intent = new Intent(this, SixthActivity.class);
        } else {
            intent = new Intent(SecondActivity.this, MainActivity.class);
        }

        AdsManager.showInterstitialAd(this, new AdsManager.AdFinished() {
            @Override
            public void onAdFinished() {
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent;
        if (new PrefManagerVideo(this).getString(SplashActivity.status_dummy_one_back_enabled).contains("true")) {
            intent = new Intent(this, FirstActivity.class);
            AdsManager.showInterstitialAd(this, new AdsManager.AdFinished() {
                @Override
                public void onAdFinished() {
                    startActivity(intent);
                }
            });
        } else {
            finishAffinity();
        }

    }

    public void LanguageClick(View view) {
        startActivity();
    }
}