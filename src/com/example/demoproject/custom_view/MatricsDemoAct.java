package com.example.demoproject.custom_view;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.example.demoproject.R;

/**
 * Created by abner on 5/19/15.
 */
public class MatricsDemoAct extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout ll = new LinearLayout(this);
        ll.setBackgroundColor(Color.parseColor("#00ff00"));
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(new ViewGroup.LayoutParams(-2,-2));

        ll.addView(new MatricsDemoView(this),new ViewGroup.LayoutParams(500,300));

        ll.addView(new MatricsDemoView2(this),new ViewGroup.LayoutParams(-2,-2));

        setContentView(ll);
    }
}
