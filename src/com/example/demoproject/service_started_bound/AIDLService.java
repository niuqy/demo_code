package com.example.demoproject.service_started_bound;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import com.example.demoproject.aidl.IRemoteService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abner on 8/13/15.
 */
public class AIDLService extends Service {
    private List<Person> persons = new ArrayList<Person>();

    private final IRemoteService.Stub mServcie = new IRemoteService.Stub(){

        @Override
        public int getPid() throws RemoteException {
            return Process.myPid();
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public List<Person> getPerson() throws RemoteException {
            return persons;
        }

        @Override
        public void addPerson(Person person) throws RemoteException {
            persons.add(person);
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String packageName = null;
            String[] packages = AIDLService.this.getPackageManager().getPackagesForUid(getCallingUid());
            if(packages != null && packages.length > 0){
                packageName = packages[0];
                Log.d("AIDLService", "onTransact packageName " + packageName);
                if(!"com.example.demoproject".equals(packageName)){
                    return false;
                }
            }
            return super.onTransact(code, data, reply, flags);
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
