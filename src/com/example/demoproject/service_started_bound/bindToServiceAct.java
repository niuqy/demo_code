package com.example.demoproject.service_started_bound;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by abner on 8/11/15.
 */
public class bindToServiceAct extends Activity {
    private final String TAG = "bindToServiceAct";
    BoundServiceImpl.BoundBinder mBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this,BoundServiceImpl.class);
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d(TAG,"ComponentName:"+componentName.toString());
                mBinder = (BoundServiceImpl.BoundBinder) iBinder;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        bindService(intent,serviceConnection,0);
        unbindService(serviceConnection);
    }
}
