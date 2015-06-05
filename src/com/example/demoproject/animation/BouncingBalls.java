package com.example.demoproject.animation;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import com.example.demoproject.R;

import java.util.ArrayList;

/**
 * Created by abner on 11/6/14.
 */
public class BouncingBalls extends Activity {
    private static final String TAG = "BouncingBalls";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bouncing_balls);
        MyAnimationView animView = new MyAnimationView(this);
        LinearLayout container = (LinearLayout) findViewById(R.id.container);
        container.addView(animView);
    }

    public class MyAnimationView extends View  {
        private static final int RED = 0xffFF8080;
        private static final int BLUE = 0xff8080FF;
        private ArrayList<ShapeHolder> balls = new ArrayList<ShapeHolder>();
        private AnimatorSet animator = null;
        private float mDensity;

        public MyAnimationView(Context context) {
            super(context);

            mDensity = getContext().getResources().getDisplayMetrics().density;

            ObjectAnimator colorAnim = ObjectAnimator.ofInt(this,"backgroundColor",RED,BLUE);
            colorAnim.setDuration(3000);
            colorAnim.setEvaluator(new ArgbEvaluator());
            colorAnim.setRepeatCount(ValueAnimator.INFINITE);
            colorAnim.setRepeatMode(ValueAnimator.REVERSE);
            colorAnim.start();
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

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() != MotionEvent.ACTION_DOWN &&  event.getAction() != MotionEvent.ACTION_MOVE){
                return false;
            }


            float x = event.getX();
            float y = event.getY();
            float startY = y;
            float endY = getHeight() - 50f;
            float h = getHeight();

            ShapeHolder ball = addBall(x,y);
            int duration = (int)(500*((h-y)/h));

            ObjectAnimator bounceOver = ObjectAnimator.ofFloat(ball,"y",startY,endY);
            bounceOver.setDuration(duration);
            bounceOver.setInterpolator(new DecelerateInterpolator());

            float endWidth = ball.getWidth() + 50f;
            ObjectAnimator squashWidthAnim = ObjectAnimator.ofFloat(ball, "width", ball.getWidth(), endWidth);
            squashWidthAnim.setDuration(bounceOver.getDuration()/4);
            squashWidthAnim.setInterpolator(new DecelerateInterpolator());
            squashWidthAnim.setRepeatCount(1);
            squashWidthAnim.setRepeatMode(ValueAnimator.REVERSE);

            float endHeight = ball.getHeight() - 50f;
            ObjectAnimator squashHeightAnim  = ObjectAnimator.ofFloat(ball,"height",ball.getHeight(),endHeight);
            squashHeightAnim.setDuration(bounceOver.getDuration()/4);
            squashHeightAnim.setInterpolator(new DecelerateInterpolator());
            squashHeightAnim.setRepeatCount(1);
            squashHeightAnim.setRepeatMode(ValueAnimator.REVERSE);

            ObjectAnimator squashHeightAnim2 = ObjectAnimator.ofFloat(ball,"y",ball.getY(),ball.getY()+25f);
            squashHeightAnim2.setDuration(bounceOver.getDuration()/4);

            ObjectAnimator bounceBack = ObjectAnimator.ofFloat(ball,"y",endY,startY);
            bounceOver.setDuration(1000);
            bounceOver.setInterpolator(new AccelerateInterpolator());

            ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(ball,"alpha",1f,0f).setDuration(100);


            AnimatorSet as1 = new AnimatorSet();
            as1.play(bounceOver).before(squashWidthAnim);
            as1.play(squashWidthAnim).with(squashHeightAnim);
            as1.play(squashHeightAnim).with(squashHeightAnim2);
            as1.play(squashHeightAnim2).before(bounceBack);

            AnimatorSet as = new AnimatorSet();
            as.play(as1).before(fadeAnim);

            as.start();

            bounceOver.start();

            return true;
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

    }

}
