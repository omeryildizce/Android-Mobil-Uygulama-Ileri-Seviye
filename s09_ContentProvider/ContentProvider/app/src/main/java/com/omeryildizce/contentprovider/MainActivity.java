package com.omeryildizce.contentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener((view) -> {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                // Content Provider
            }else{
                Snackbar.make(view, "Replace with your action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


}