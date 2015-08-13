package com.example.demoproject.service_started_bound;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;
import com.example.demoproject.IRemoteService;
import android.os.Process;

/**
 * Created by abner on 8/13/15.
 */
public class AIDLService extends Service {
    private final IRemoteService.Stub mServcie = new IRemoteService.Stub(){

        @Override
        public int getPid() throws RemoteException {
            return Process.myPid();
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mServcie;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this,"onUnbind",Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }
}
