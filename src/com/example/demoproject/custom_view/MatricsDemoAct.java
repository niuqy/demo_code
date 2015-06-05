package com.example.demoproject.custom_view;

import android.app.Activity;
import android.os.Bundle;
import com.example.demoproject.R;

/**
 * Created by abner on 5/19/15.
 */
public class MatricsDemoAct extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MatricsDemoView(this));
    }
}
