package com.example.demoproject.service_started_bound;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by abner on 8/10/15.
 */
public class MyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
