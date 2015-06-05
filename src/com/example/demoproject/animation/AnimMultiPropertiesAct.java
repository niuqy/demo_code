package com.example.demoproject.animation;

import android.animation.*;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.LinearLayout;
import com.example.demoproject.R;

import java.util.ArrayList;

/**
 * Created by abner on 10/30/14.
 */
public class AnimMultiPropertiesAct extends Activity {
    LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.anim_multi_properties);
        container = (LinearLayout) findViewById(R.id.container);
        final MyAnimationView animView = new MyAnimationView(this);
        container.addView(animView);

        findViewById(R.id.run).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animView.startAnimation();
            }
        });

        startContainerBackgroundAnimation();
    }

    private void startContainerBackgroundAnimation() {
        //TODO
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(container,"alpha",1.0f,0.1f).setDuration(5000);
        alphaAnim.setRepeatMode(ValueAnimator.REVERSE);
        alphaAnim.setRepeatCount(ValueAnimator.INFINITE);
        alphaAnim.start();
    }

    public class MyAnimationView extends View implements ValueAnimator.AnimatorUpdateListener {
        private ArrayList<ShapeHolder> balls = new ArrayList<ShapeHolder>();
        private AnimatorSet animator = null;
        private float mDensity;
        private int DURATION = 1000;
        private float BALLSIZE = 100f;

        public MyAnimationView(Context context) {
            super(context);

            mDensity = getContext().getResources().getDisplayMetrics().density;

            ShapeHolder ball0 = addBall(50f,25f);
            ShapeHolder ball1 = addBall(150f,25f);
            ShapeHolder ball2 = addBall(250f,25f);
            ShapeHolder ball3 = addBall(350f,25f);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            for(int i=0;i<balls.size();++i){
                ShapeHolder shapeHolder = balls.get(i);
                canvas.save();
                canvas.translate(shapeHolder.getX(),shapeHolder.getY());
                shapeHolder.getShape().draw(canvas);
                canvas.restore();
            }
        }

        private ShapeHolder addBall(float x, float y) {
            OvalShape circle = new OvalShape();
            circle.resize(50f * mDensity, 50f * mDensity);
            ShapeDrawable drawable = new ShapeDrawable(circle);
            ShapeHolder shapeHolder = new ShapeHolder(drawable);
            shapeHolder.setX(x - 25f);
            shapeHolder.setY(y - 25f);
            int red = (int)(100 + Math.random() * 155);
            int green = (int)(100 + Math.random() * 155);
            int blue = (int)(100 + Math.random() * 155);
            int color = 0xff000000 | red << 16 | green << 8 | blue;
            Paint paint = drawable.getPaint(); //new Paint(Paint.ANTI_ALIAS_FLAG);
            int darkColor = 0xff000000 | red/4 << 16 | green/4 << 8 | blue/4;
            RadialGradient gradient = new RadialGradient(37.5f, 12.5f,
                    50f, color, darkColor, Shader.TileMode.CLAMP);
            paint.setShader(gradient);
            shapeHolder.setPaint(paint);
            balls.add(shapeHolder);
            return shapeHolder;
        }


        public void startAnimation() {
            createAnimation();
            animator.start();
        }

        private void createAnimation() {
            if(animator == null){
                ShapeHolder ball;

                ball = balls.get(0);
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(ball,"y",0f,getHeight()-ball.getHeight()).setDuration(DURATION);
                anim1.setInterpolator(new BounceInterpolator());
                anim1.addUpdateListener(this);

                ball = balls.get(1);
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y",0f,getHeight()-ball.getHeight());
                PropertyValuesHolder pvhAlpha = PropertyValuesHolder.ofFloat("alpha",1f,0f);
                ObjectAnimator anim2 = ObjectAnimator.ofPropertyValuesHolder(ball,pvhAlpha,pvhY).setDuration(DURATION/2);
                anim2.setInterpolator(new AccelerateInterpolator());
                anim2.setRepeatCount(1);
                anim2.setRepeatMode(ValueAnimator.REVERSE);
                anim2.addUpdateListener(this);

                ball = balls.get(2);
                PropertyValuesHolder pvhWidth = PropertyValuesHolder.ofFloat("width",ball.getWidth(),ball.getWidth()*2);
                PropertyValuesHolder pvhHeight = PropertyValuesHolder.ofFloat("height",ball.getHeight(),ball.getHeight()*2);
                PropertyValuesHolder pvhTX = PropertyValuesHolder.ofFloat("x",ball.getX(),ball.getX()-BALLSIZE/2f);
                PropertyValuesHolder pvhTY = PropertyValuesHolder.ofFloat("y",ball.getY(),ball.getY()-BALLSIZE/2f);
                ObjectAnimator anim3 = ObjectAnimator.ofPropertyValuesHolder(ball,pvhWidth,pvhHeight,pvhTX,pvhTY).setDuration(DURATION/2);
                anim3.setRepeatCount(1);
                anim3.setRepeatMode(ValueAnimator.REVERSE);
//                anim3.addUpdateListener(this);

                ball = balls.get(3);
                Keyframe kf1 = Keyframe.ofFloat(0f,ball.getX());
                Keyframe kf2 = Keyframe.ofFloat(.5f,ball.getX()+100f);
                Keyframe kf3 = Keyframe.ofFloat(1f,ball.getX());
                PropertyValuesHolder pvhx = PropertyValuesHolder.ofKeyframe("x",kf1,kf2,kf3);
                PropertyValuesHolder pvhy = PropertyValuesHolder.ofFloat("y",ball.getY(),getHeight()-ball.getHeight());
                ObjectAnimator anim4 = ObjectAnimator.ofPropertyValuesHolder(ball,pvhx,pvhy).setDuration(DURATION/2);
                anim4.setRepeatCount(1);
                anim4.setRepeatMode(ValueAnimator.REVERSE);

                animator = new AnimatorSet();
                animator.playTogether(anim1,anim2,anim3,anim4);
            }
        }

        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            invalidate();
        }
    }
}
