package com.omeryildizce.broadcastreciverpermission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Broadcasting  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Signal From App 2", Toast.LENGTH_LONG).show();
        Toast.makeText(context, "Signal From App 2", Toast.LENGTH_LONG).show();
    }
}
