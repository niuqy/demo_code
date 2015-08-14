package com.example.demoproject.service_started_bound;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.demoproject.R;
import com.example.demoproject.aidl.IRemoteService;

import java.util.List;

/**
 * Created by abner on 8/13/15.
 */
public class AIDLAct extends Activity {
    private IRemoteService mService;
    ServiceConnection connection;
    TextView mCallBackText;
    boolean isBound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.aidl_act);

        mCallBackText = (TextView) findViewById(R.id.callback);
        findViewById(R.id.bind).setOnClickListener(bindClick);
        findViewById(R.id.unbind).setOnClickListener(unBindClick);
        findViewById(R.id.getPid).setOnClickListener(getClick);
        findViewById(R.id.addPerson).setOnClickListener(addPerson);
        findViewById(R.id.getPersons).setOnClickListener(getPersons);

        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mService = IRemoteService.Stub.asInterface(iBinder);
                isBound = true;
                mCallBackText.setText("binding");
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Toast.makeText(AIDLAct.this, "onServiceDisconnected " + componentName, Toast.LENGTH_SHORT).show();
                mService = null;
            }
        };

    }

    View.OnClickListener bindClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            bindService(new Intent(AIDLAct.this,AIDLService.class),connection, Service.BIND_AUTO_CREATE);
        }
    };

    View.OnClickListener unBindClick = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            if(isBound){
                unbindService(connection);
                isBound = false;
                mCallBackText.setText("unbinding");
            }
        }
    };

    View.OnClickListener addPerson = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            if(isBound){
                try {
                    Person p = new Person();
                    p.age = 5;
                    p.name = "Jim";
                    mService.addPerson(p);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    View.OnClickListener getPersons = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if(isBound){
                try {
                    List<Person> list = mService.getPerson();
                    if(list != null && list.size() > 0){
                        Person person = list.get(0);
                        mCallBackText.setText(person.age+" "+person.name);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    View.OnClickListener getClick = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            getPid();
        }
    };

    private void getPid(){
        try {
            Toast.makeText(this, "current Pid:" + android.os.Process.myPid()+",remote Pid:"+mService.getPid(),Toast.LENGTH_LONG).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
