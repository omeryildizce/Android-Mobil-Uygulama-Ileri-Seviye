package com.omeryildizce.senddata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendBroadcast(View view) {
        Intent intent = new Intent(this, BroadcastInner.class);
        Bundle bundle = new Bundle();

        bundle.putString("name","james");
        bundle.putInt("age",40);
        intent.putExtras(bundle);
        sendBroadcast(intent);
    }


    public static class BroadcastInner extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            String name = bundle.getString("name");
            int age = bundle.getInt("age");

            Toast.makeText(context, String.format("%s %d", name, age), Toast.LENGTH_SHORT).show();


        }
    }
}