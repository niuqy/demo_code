package com.example.demoproject.service_started_bound;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by abner on 8/11/15.
 */
public class BoundServiceImpl extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return new BoundBinder();
    }

    public class BoundBinder extends Binder {

    }
}
