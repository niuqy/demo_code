package com.example.demoproject.focus_control_for_tv_key;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by abner on 4/30/15.
 */
public class FocusControl_Act extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new FocusControl_View(this));
    }
}
