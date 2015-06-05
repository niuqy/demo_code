package com.example.demoproject.animation;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.demoproject.R;

/**
 * Created by abner on 6/3/15.
 */
public class BasicTestAct extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.basic_anim_test);

        Button pvh = (Button) findViewById(R.id.anim_pvh);
        Button set = (Button) findViewById(R.id.animSet);
        pvh.setOnClickListener(this);
        set.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.animSet:
                ObjectAnimator objectAnimator = new ObjectAnimator();
                break;
            case R.id.anim_pvh:
                break;
        }
    }
}
