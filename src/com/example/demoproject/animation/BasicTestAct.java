package com.example.demoproject.animation;

import android.animation.*;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.demoproject.R;

/**
 * Created by abner on 6/3/15.
 */
public class BasicTestAct extends Activity implements View.OnClickListener {
    Anim_ImageView demoView;
    boolean alphaSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.basic_anim_test);

        Button pvh = (Button) findViewById(R.id.anim_pvh);
        Button ofFloat = (Button) findViewById(R.id.ofFloat);
        Button set = (Button) findViewById(R.id.animSet);
        demoView = (Anim_ImageView) findViewById(R.id.demo);
        pvh.setOnClickListener(this);
        set.setOnClickListener(this);
        ofFloat.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.animSet:
                ObjectAnimator objectAnimator = new ObjectAnimator();
                break;
            case R.id.anim_pvh:
                pvhAnim(demoView);
                break;
            case R.id.ofFloat:
                ofXXXAnim(demoView);
                break;
        }
    }

    private void ofXXXAnim(View target) {
        ObjectAnimator anim = null;
        if(alphaSwitch){
            anim  = ObjectAnimator.ofFloat(target, "alpha", 0f, 1f);
        }else {
            anim  = ObjectAnimator.ofFloat(target, "alpha", 1f, 0f);
        }
        alphaSwitch = !alphaSwitch;
        anim.setDuration(1000);
        anim.start();
    }

    public void pvhAnim(final Anim_ImageView target){
        PropertyValuesHolder pvhWidth = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.25f);
        PropertyValuesHolder pvhHeight = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.25f);
        ObjectAnimator largeAnimator = ObjectAnimator.ofPropertyValuesHolder(target,pvhWidth,pvhHeight).setDuration(500);
        largeAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                Log.d("nqy", "largeAnimator onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        largeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
            }
        });
        largeAnimator.start();
    }
}
