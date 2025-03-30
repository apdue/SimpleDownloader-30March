package com.apdue.simplenewdownloader_29march;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.apdue.simplenewdownloader_29march.dummy.FifthActivity;
import com.apdue.simplenewdownloader_29march.dummy.FirstActivity;
import com.apdue.simplenewdownloader_29march.dummy.FourthActivity;
import com.apdue.simplenewdownloader_29march.dummy.SecondActivity;
import com.apdue.simplenewdownloader_29march.dummy.SplashActivity;
import com.apdue.simplenewdownloader_29march.dummy.ThirdActivity;
import com.google.firebase.analytics.FirebaseAnalytics;

import com.apdue.simplenewdownloader_29march.util.AdsManager;
import com.apdue.simplenewdownloader_29march.util.PrefManagerVideo;
import com.apdue.simplenewdownloader_29march.util.VideosAdapterNayaDownloader;
import com.apdue.simplenewdownloader_29march.util.VideosAdapterOnline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class MainActivityFixed extends AppCompatActivity {

    VideosAdapterNayaDownloader videosAdapter;
    RecyclerView recyclerView;
    ArrayList<Integer> arrayList;
    private FirebaseAnalytics mFirebaseAnalytics;

    VideosAdapterOnline videosAdapterOnline;
    ArrayList<String> arrayListOnline;
    RecyclerView recyclerViewOnline;
    PrefManagerVideo pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fixed_vids);
        pref = new PrefManagerVideo(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

//        if (!Permissions.isAllStoragePermissionsGranted(this)) {
//            Permissions.requestAllStoragePermissions(this, 101);
//        }

        arrayListOnline = new ArrayList<>();
        recyclerViewOnline = findViewById(R.id.videosRecyclerViewOnline);

        arrayListOnline.add(pref.getString(SplashActivity.video_1));
        arrayListOnline.add(pref.getString(SplashActivity.video_2));
        arrayListOnline.add(pref.getString(SplashActivity.video_3));
        arrayListOnline.add(pref.getString(SplashActivity.video_4));
        arrayListOnline.add(pref.getString(SplashActivity.video_5));
        arrayListOnline.add(pref.getString(SplashActivity.video_6));
        arrayListOnline.add(pref.getString(SplashActivity.video_8));
        arrayListOnline.add(pref.getString(SplashActivity.video_7));
        arrayListOnline.add(pref.getString(SplashActivity.video_9));
        arrayListOnline.add(pref.getString(SplashActivity.video_10));
        arrayListOnline.add(pref.getString(SplashActivity.video_11));
        arrayListOnline.add(pref.getString(SplashActivity.video_12));
        arrayListOnline.add(pref.getString(SplashActivity.video_13));
        arrayListOnline.add(pref.getString(SplashActivity.video_14));
        arrayListOnline.add(pref.getString(SplashActivity.video_15));
        arrayListOnline.add(pref.getString(SplashActivity.video_16));
        arrayListOnline.add(pref.getString(SplashActivity.video_17));
        arrayListOnline.add(pref.getString(SplashActivity.video_18));
        arrayListOnline.add(pref.getString(SplashActivity.video_19));
        arrayListOnline.add(pref.getString(SplashActivity.video_20));
        arrayListOnline.add(pref.getString(SplashActivity.video_21));
        arrayListOnline.add(pref.getString(SplashActivity.video_22));
        arrayListOnline.add(pref.getString(SplashActivity.video_23));
        arrayListOnline.add(pref.getString(SplashActivity.video_24));
        arrayListOnline.add(pref.getString(SplashActivity.video_25));
        arrayListOnline.add(pref.getString(SplashActivity.video_26));
        arrayListOnline.add(pref.getString(SplashActivity.video_27));
        arrayListOnline.add(pref.getString(SplashActivity.video_28));
        arrayListOnline.add(pref.getString(SplashActivity.video_29));
        arrayListOnline.add(pref.getString(SplashActivity.video_30));
        recyclerView = findViewById(R.id.videosRecyclerView);

        arrayList = new ArrayList<>();

        arrayList.add(R.raw.video_1);
        arrayList.add(R.raw.video_2);

        Collections.shuffle(arrayList);
        Collections.shuffle(arrayListOnline);

        videosAdapter = new VideosAdapterNayaDownloader(this, arrayList);
        recyclerView.setAdapter(videosAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivityFixed.this, 2));

        recyclerViewOnline.setLayoutManager(new GridLayoutManager(MainActivityFixed.this, 2));
        videosAdapterOnline = new VideosAdapterOnline(this, arrayListOnline);
        recyclerViewOnline.setAdapter(videosAdapterOnline);

    }


    @Override
    public void onBackPressed() {
        Intent intent;
        if (new PrefManagerVideo(this).getString(SplashActivity.status_dummy_five_back_enabled).contains("true")) {
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