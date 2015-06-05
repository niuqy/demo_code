package com.example.demoproject.animation;

import android.animation.*;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import com.example.demoproject.R;

/**
 * Created by abner on 10/30/14.
 */
public class Anim_Test_Icon_Test_Act extends Activity implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
    private static final String TAG = "Anim_Test_Icon_Test_Act";
    private int DURATION = 1000;
    int[] icons = new int[]{R.drawable.ic_launcher,R.drawable.doggg};
    String[] texts = new String[]{"ic_launcher","doggg"};
    AnimatorSet animator = null;
    Anim_ImageView icon;
    TextView text;
    GridLayout containerBtn;
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_icon_test);
        icon = (Anim_ImageView) findViewById(R.id.icon);
        text = (TextView) findViewById(R.id.text);

        icon.setImageResource(icons[0]);
        text.setText(texts[0]);

        findViewById(R.id.run).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick");
                startAnimation();
            }
        });

        containerBtn = (GridLayout) findViewById(R.id.c2);
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btn = new Button(Anim_Test_Icon_Test_Act.this);
                btn.setText(String.valueOf(index++));
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        containerBtn.removeView(view);
                    }
                });

                containerBtn.addView(btn,Math.min(1,containerBtn.getChildCount()));
            }
        });
    }


    private Button getBtn(int index){
        Button btn =  new Button(Anim_Test_Icon_Test_Act.this);
        btn.setText(String.valueOf(index));
        return btn;
    }

    private void startAnimation() {
        createAnimation();
        animator.start();
    }

    private void createAnimation() {
        if(animator == null){
            Log.d(TAG,"width:"+icon.getWidth()+",height:"+icon.getHeight());
            PropertyValuesHolder pvhWidth = PropertyValuesHolder.ofInt("width", icon.getWidth(), 0);
            PropertyValuesHolder pvhHeight = PropertyValuesHolder.ofInt("height", icon.getHeight(), 0);

            Keyframe kf1 = Keyframe.ofFloat(0f,1f);
            Keyframe kf2 = Keyframe.ofFloat(.9999f,.8f);
            Keyframe kf3 = Keyframe.ofFloat(1f,0f);

            PropertyValuesHolder pvhScaleX =
                    PropertyValuesHolder.ofKeyframe("scaleX",kf1,kf2,kf3);
            PropertyValuesHolder pvhScaleY =
                    PropertyValuesHolder.ofKeyframe("scaleY",kf1,kf2,kf3);

            PropertyValuesHolder pvhx = PropertyValuesHolder.ofFloat("x",icon.getX(),icon.getX()+300f);
            PropertyValuesHolder pvhy = PropertyValuesHolder.ofFloat("y",icon.getY(),icon.getY()+300f);
            ObjectAnimator animIcon = ObjectAnimator.ofPropertyValuesHolder(icon,pvhScaleX,pvhScaleY).setDuration(DURATION);
            animIcon.setRepeatCount(1);
            animIcon.setRepeatMode(ValueAnimator.REVERSE);
            animIcon.addListener(this);
            animIcon.addUpdateListener(this);
            animIcon.setInterpolator(new AccelerateDecelerateInterpolator());



            ObjectAnimator animText = ObjectAnimator.ofFloat(text,"alpha",1.0f,0f).setDuration(DURATION);
            animText.setRepeatCount(1);
            animText.setRepeatMode(ValueAnimator.REVERSE);
            animText.addListener(this);
            animText.setInterpolator(new AccelerateDecelerateInterpolator());

            animator = new AnimatorSet();
            animator.playTogether(animIcon,animText);
        }
    }

    @Override
    public void onAnimationStart(Animator animator) {
        Log.d(TAG,"onAnimationStart");
    }

    @Override
    public void onAnimationEnd(Animator animator) {
        Log.d(TAG,"onAnimationEnd");
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {
        Log.d(TAG,"onAnimationRepeat");
        icon.setImageResource(icons[1]);
        text.setText(texts[1]);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        icon.requestLayout();
    }
}
