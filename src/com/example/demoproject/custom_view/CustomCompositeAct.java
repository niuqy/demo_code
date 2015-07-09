package com.example.demoproject.custom_view;

import android.app.Activity;
import android.os.Bundle;
import com.example.demoproject.R;

/**
 * Created by abner on 7/9/15.
 */
public class CustomCompositeAct extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CustomCompositeView view = new CustomCompositeView(this);
        setContentView(R.layout.custom_composite_act);
//        setContentView(view);
    }
}
