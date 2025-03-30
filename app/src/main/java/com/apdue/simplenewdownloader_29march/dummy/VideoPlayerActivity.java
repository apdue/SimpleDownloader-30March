package com.apdue.simplenewdownloader_29march.dummy;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.apdue.simplenewdownloader_29march.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class VideoPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        findViewById(R.id.ivBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        boolean isOnline = getIntent().getBooleanExtra("isOnline", false);
        if (isOnline){

            String video = getIntent().getStringExtra("video");
            findViewById(R.id.downloadBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String fileName = "Video_" + System.currentTimeMillis() + ".mp4";

                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(video));
                    request.setTitle(fileName);
                    request.setDescription(fileName);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                    DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    downloadManager.enqueue(request);

                    Toast.makeText(VideoPlayerActivity.this, "Downloading...", Toast.LENGTH_SHORT).show();
                }
            });

            videoView.setVideoURI(Uri.parse(video));

            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);

            videoView.start();
        } else {
            Integer video = getIntent().getIntExtra("video", 0);
            findViewById(R.id.downloadBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    copyRawVideoToDownloads(VideoPlayerActivity.this, video, "Video_" + System.currentTimeMillis() + ".mp4");
                }
            });

            String path = "android.resource://" + getPackageName() + "/" + video;

            videoView.setVideoURI(Uri.parse(path));

            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);

            videoView.start();
        }

    }

    public void copyRawVideoToDownloads(Context context, int rawId, String fileName) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                return;
            }
        }

        try {
            Log.d("TAGSS", "copyRawVideoToDownloads: trying");
            InputStream inputStream = context.getResources().openRawResource(rawId);
            String downloadsPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            String destinationPath = downloadsPath + File.separator + fileName;
            File file = new File(destinationPath);
            file.getParentFile().mkdirs(); // Create parent directories if needed

            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }

            inputStream.close();
            outputStream.flush();
            outputStream.close();

            Toast.makeText(context, "Downloaded!", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("TAGSS", "copyRawVideoToDownloads: eeee : "+e.getMessage());
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted! Try again.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied! Cannot save video.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}