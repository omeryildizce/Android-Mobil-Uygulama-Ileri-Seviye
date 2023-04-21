package com.omeryildizce.downloadservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void download(View view) {
        Intent intent = new Intent(getApplicationContext(), DownloadService.class);
        startService(intent);
    }

    public void stop(View view) {
        Intent intent = new Intent(getApplicationContext(), DownloadService.class);
        stopService(intent);
    }
}