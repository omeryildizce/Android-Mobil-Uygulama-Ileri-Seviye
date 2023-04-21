package com.omeryildizce.stickyserviceaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void play(View view) {
        intent = new Intent(this, StickService.class);
        startService(intent);
    }

    public void stop(View view) {
        intent = new Intent(this, StickService.class);

        stopService(intent);
    }
}