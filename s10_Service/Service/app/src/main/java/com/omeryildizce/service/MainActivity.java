package com.omeryildizce.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button startService, stopService;
    EditText editText;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initalize();
        intent = new Intent(getApplicationContext(), ServiceClass.class);
        service();
    }

    private void service() {
        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });

        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });
    }

    private void initalize() {
        startService = findViewById(R.id.buttonStart);
        stopService = findViewById(R.id.buttonStop);
        editText = findViewById(R.id.editTextText);
    }
}