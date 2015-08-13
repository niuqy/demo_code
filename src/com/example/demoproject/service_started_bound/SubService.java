package com.example.demoproject.service_started_bound;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by abner on 8/11/15.
 */
public class SubService extends Service {
    private final String TAG = "SubService";
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand ");
        Object pendingIt = intent.getParcelableExtra("pending_intent");
        if(pendingIt != null && pendingIt instanceof PendingIntent){
            PendingIntent pendingIntent = (PendingIntent) pendingIt;
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(this,"",Toast.LENGTH_LONG).show();

        return super.onStartCommand(intent, flags, startId);
    }
}
