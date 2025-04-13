package com.videosimplemaster.hdplaydownload.dummy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.videosimplemaster.hdplaydownload.MainActivity;
import com.videosimplemaster.hdplaydownload.R;
import com.videosimplemaster.hdplaydownload.util.AdsManager;
import com.videosimplemaster.hdplaydownload.util.PrefManagerVideo;


public class SixthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);

        if (new PrefManagerVideo(this).getString(SplashActivity.dummy_one_screen).contains("ad")) {
            AdsManager.showAndLoadNativeAd(this, findViewById(R.id.nativeAd), 1);
        }
        AdsManager.loadBannerAdForNative(this, findViewById(R.id.nativeAdTwo));


        findViewById(R.id.tvPP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getResources().getString(R.string.privacy_policy_url)));
                startActivity(intent);
            }
        });

        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                intent = new Intent(SixthActivity.this, MainActivity.class);

                AdsManager.showInterstitialAd(SixthActivity.this, new AdsManager.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        startActivity(intent);
                    }
                });
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent;
         if (new PrefManagerVideo(this).getString(SplashActivity.status_dummy_five_back_enabled).contains("true") && !new PrefManagerVideo(SixthActivity.this).getString(SplashActivity.TAG_NATIVEID).contains("sandeep")) {
            intent = new Intent(this, FifthActivity.class);
            AdsManager.showInterstitialAd(this, new AdsManager.AdFinished() {
                @Override
                public void onAdFinished() {
                    startActivity(intent);
                }
            });
        } else if (new PrefManagerVideo(this).getString(SplashActivity.status_dummy_four_back_enabled).contains("true")) {
            intent = new Intent(this, FourthActivity.class);
            AdsManager.showInterstitialAd(this, new AdsManager.AdFinished() {
                @Override
                public void onAdFinished() {
                    startActivity(intent);
                }
            });
        } else if (new PrefManagerVideo(this).getString(SplashActivity.status_dummy_three_back_enabled).contains("true")) {
            intent = new Intent(this, ThirdActivity.class);
            AdsManager.showInterstitialAd(this, new AdsManager.AdFinished() {
                @Override
                public void onAdFinished() {
                    startActivity(intent);
                }
            });
        } else if (new PrefManagerVideo(this).getString(SplashActivity.status_dummy_two_back_enabled).contains("true")) {
            intent = new Intent(this, SecondActivity.class);
            AdsManager.showInterstitialAd(this, new AdsManager.AdFinished() {
                @Override
                public void onAdFinished() {
                    startActivity(intent);
                }
            });
        } else if (new PrefManagerVideo(this).getString(SplashActivity.status_dummy_one_back_enabled).contains("true")) {
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
}