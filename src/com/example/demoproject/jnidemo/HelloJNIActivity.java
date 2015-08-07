package com.example.demoproject.jnidemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by abner on 8/6/15.
 */
public class HelloJNIActivity extends Activity {
    public native String hiJNI();

    static {
        System.loadLibrary("hellojni");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        setContentView(tv);

        tv.setText(hiJNI());
    }
}
