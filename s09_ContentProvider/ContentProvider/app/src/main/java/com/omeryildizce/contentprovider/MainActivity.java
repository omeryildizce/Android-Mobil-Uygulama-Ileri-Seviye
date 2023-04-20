package com.omeryildizce.contentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
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
                ContentResolver contentResolver = getContentResolver();
                String[] projection = {ContactsContract.Contacts.DISPLAY_NAME};
                Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, projection, null, null, ContactsContract.Contacts.DISPLAY_NAME);
            } else {
                Snackbar.make(view, "Replace with your action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }


}