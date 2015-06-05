package com.example.demoproject.animation;

import android.animation.AnimatorSet;
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
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import com.example.demoproject.R;

import java.util.ArrayList;

/**
 * Created by abner on 10/29/14.
 */
public class Animation_CloningAct extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.anim_cloing);

        LinearLayout container = (LinearLayout) findViewById(R.id.container);
        final MyAnimationView animView = new MyAnimationView(this);
        container.addView(animView);

        findViewById(R.id.run).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animView.startAnimation();
            }
        });

    }

    public class MyAnimationView extends View implements ValueAnimator.AnimatorUpdateListener {
        private ArrayList<ShapeHolder> balls = new ArrayList<ShapeHolder>();
        private AnimatorSet animator = null;
        private float mDensity;

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

        public void startAnimation() {
            createAnimation();
            animator.start();
        }

        private void createAnimation() {
            if(animator == null){
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(balls.get(0),"y",0f,getHeight()-balls.get(0).getHeight()).setDuration(500);
                ObjectAnimator anim2 = anim1.clone();
                anim2.setTarget(balls.get(1));
                anim1.addUpdateListener(this);

                ShapeHolder ball3 = balls.get(2);
                ObjectAnimator animDown = ObjectAnimator.ofFloat(ball3,"y",0f,getHeight()-ball3.getHeight()).setDuration(500);
                animDown.setInterpolator(new AccelerateInterpolator());
                ObjectAnimator animUp = ObjectAnimator.ofFloat(ball3,"y",getHeight()-ball3.getHeight(),0f).setDuration(500);
                animUp.setInterpolator(new DecelerateInterpolator());
                animDown.addUpdateListener(this);
                animUp.addUpdateListener(this);
                AnimatorSet s1 = new AnimatorSet();
                s1.playSequentially(animDown,animUp);
                AnimatorSet s2 = s1.clone();
                s2.setTarget(balls.get(3));

                animator = new AnimatorSet();
                animator.playTogether(anim1,anim2,s1);
                animator.playSequentially(s1,s2);
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

        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            invalidate();
        }
    }

}
