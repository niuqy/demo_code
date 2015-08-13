package com.example.demoproject.service_started_bound;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by abner on 8/10/15.
 */
public class IntentServiceUsing extends IntentService {

    public IntentServiceUsing(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
