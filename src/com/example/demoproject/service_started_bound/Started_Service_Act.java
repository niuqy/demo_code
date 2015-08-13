package com.example.demoproject.service_started_bound;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import com.example.demoproject.MyReceiver;

/**
 * Created by abner on 8/10/15.
 */
public class Started_Service_Act extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //start a service normally,just start a service,the client that start the service won't get any result
        Intent it = new Intent(this,SubService.class);
        startService(it);

        //
        Intent itWithPendingIt = new Intent(this,SubService.class);
        Intent broadCastIt = new Intent(this, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,broadCastIt,PendingIntent.FLAG_UPDATE_CURRENT);
        itWithPendingIt.putExtra("pending_intent",pendingIntent);
        startService(itWithPendingIt);
    }
}
