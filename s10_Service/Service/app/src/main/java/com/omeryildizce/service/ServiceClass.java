package com.omeryildizce.service;


import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class ServiceClass extends Service {
    private static final String TAG = "ServiceClass";
    String currentThread;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        currentThread = Thread.currentThread().getName();
        Log.d(TAG, "onCreate: " + currentThread);
        super.onCreate();

    }

    @Override
    public void onDestroy() {
        currentThread = Thread.currentThread().getName();
        Log.d(TAG, "onDestroy: " + currentThread);
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        currentThread = Thread.currentThread().getName();
        Log.d(TAG, "onStartCommand: " + currentThread);

        AsyncTaskinClass asyncTaskinClass = new AsyncTaskinClass();
        asyncTaskinClass.execute(10_000);
        return super.onStartCommand(intent, flags, startId);
    }

    class AsyncTaskinClass extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            currentThread = Thread.currentThread().getName();
            Log.d(TAG, "doInBackground: " + currentThread);

            int sleepTime = integers[0];
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            currentThread = Thread.currentThread().getName();
            Log.d(TAG, "onPostExecute: " + currentThread);
            super.onPostExecute(unused);
        }

        @Override
        protected void onPreExecute() {
            currentThread = Thread.currentThread().getName();
            Log.d(TAG, "onPreExecute: " + currentThread);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            currentThread = Thread.currentThread().getName();

            Log.d(TAG, "onProgressUpdate: " + currentThread);
            super.onProgressUpdate(values);
        }
    }
}
