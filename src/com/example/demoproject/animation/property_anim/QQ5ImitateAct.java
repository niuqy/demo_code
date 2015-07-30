package com.example.demoproject.animation.property_anim;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import com.example.demoproject.R;

/**
 * Created by abner on 7/29/15.
 */
public class QQ5ImitateAct extends Activity {
    View contentView,leftView;
    float xySacleRate = 0.5f;
    ObjectAnimator contentAnimator,leftAnimator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qq5_imitate);
        contentView = findViewById(R.id.content);
        leftView = findViewById(R.id.leftView);
        Button btn = (Button) findViewById(R.id.left);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentAnimator.start();
                leftAnimator.start();
            }
        });

        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    contentAnimator.reverse();
                leftAnimator.reverse();
                }
                return true;
            }
        });

        initContentViewAnim();
        initLeftViewAnim();
    }

    private void initLeftViewAnim() {
        PropertyValuesHolder pvhWidth = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f);
        PropertyValuesHolder pvhHeight = PropertyValuesHolder.ofFloat("scaleY", 0f, 1f);
        PropertyValuesHolder pvhAlpha = PropertyValuesHolder.ofFloat("alpha",0f,1f);
        leftAnimator = ObjectAnimator.ofPropertyValuesHolder(
                leftView,pvhWidth,pvhHeight,pvhAlpha)
                .setDuration(300);
    }

    private void initContentViewAnim() {
        PropertyValuesHolder pvhWidth = PropertyValuesHolder.ofFloat("scaleX", 1f, xySacleRate);
        PropertyValuesHolder pvhHeight = PropertyValuesHolder.ofFloat("scaleY", 1f, xySacleRate);
        PropertyValuesHolder pvhTranslateX = PropertyValuesHolder.ofFloat("translationX", 0f, 400f);
        contentAnimator = ObjectAnimator.ofPropertyValuesHolder(
                contentView,pvhWidth,pvhHeight,pvhTranslateX)
                .setDuration(300);
    }
}
