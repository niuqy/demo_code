package com.example.demoproject.animation.property_anim;

import android.animation.*;
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
    View contentView,leftView,cover;
    float xySacleRate = 0.5f;
    boolean isOpen;
    ObjectAnimator contentAnimator,leftAnimator,coverAnimator;
    AnimatorSet set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qq5_imitate);
        contentView = findViewById(R.id.content);
        leftView = findViewById(R.id.leftView);
        cover = findViewById(R.id.cover);
//        cover.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
//                    coverAnimator.start();
//                    leftAnimator.start();
//                }
//                return true;
//            }
//        });
        Button btn = (Button) findViewById(R.id.left);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set.start();
            }
        });
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               set.start();
            }
        });

        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isOpen && motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    leftAnimator.reverse();
                    coverAnimator.reverse();
                    contentAnimator.reverse();
                    isOpen = false;
                }
                return true;
            }
        });

        initContentViewAnim();
        initLeftViewAnim();

        set = new AnimatorSet();
        set.playTogether(contentAnimator,coverAnimator,leftAnimator);
    }

    private void initLeftViewAnim() {
        PropertyValuesHolder pvhWidth = PropertyValuesHolder.ofFloat("scaleX",0.5f, 1f);
        PropertyValuesHolder pvhHeight = PropertyValuesHolder.ofFloat("scaleY", 0.5f, 1f);
        PropertyValuesHolder pvhTranslateX = PropertyValuesHolder.ofFloat("translationX",-200f,0f);
        leftAnimator = ObjectAnimator.ofPropertyValuesHolder(leftView,pvhWidth,pvhHeight,pvhTranslateX).setDuration(1000);

        PropertyValuesHolder pvhAlpha = PropertyValuesHolder.ofFloat("alpha",1f,0.0f);
        coverAnimator = ObjectAnimator.ofPropertyValuesHolder(
                cover,pvhAlpha)
                .setDuration(1000);
    }

    private void initContentViewAnim() {
        PropertyValuesHolder pvhWidth = PropertyValuesHolder.ofFloat("scaleX", 1f, xySacleRate);
        PropertyValuesHolder pvhHeight = PropertyValuesHolder.ofFloat("scaleY", 1f, xySacleRate);
        PropertyValuesHolder pvhTranslateX = PropertyValuesHolder.ofFloat("translationX", 0f, 400f);
        contentAnimator = ObjectAnimator.ofPropertyValuesHolder(
                contentView,pvhWidth,pvhHeight,pvhTranslateX)
                .setDuration(300);
        contentAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if(contentView.getScaleX() < 1){
                    isOpen = true;
                }
            }
        });
    }
}
