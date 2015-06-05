package com.example.demoproject.animation;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
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
import android.view.animation.BounceInterpolator;
import android.widget.LinearLayout;
import com.example.demoproject.R;

/**
 * Created by abner on 11/3/14.
 */
public class Custome_Evaluator extends Activity{
    MyAnimationView anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.custome_evaluator);

        findViewById(R.id.evaluator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim.startAnimation();
            }
        });

        LinearLayout container = (LinearLayout) findViewById(R.id.container);
        anim = new MyAnimationView(this);
        container.addView(anim);

    }


    private class MyAnimationView extends View implements ValueAnimator.AnimatorUpdateListener {
        private ObjectAnimator anim;
        private float mDensity;
        XYBall ball;

        public MyAnimationView(Context context) {
            super(context);
            mDensity = context.getResources().getDisplayMetrics().density;
            ball = addBall(50f,25f);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            ShapeHolder shapeHolder = ball;
            canvas.save();
            canvas.translate(shapeHolder.getX(),shapeHolder.getY());
            shapeHolder.getShape().draw(canvas);
            canvas.restore();
        }

        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            invalidate();
        }

        private class XYBall extends ShapeHolder{

            public XYBall(ShapeDrawable s) {
                super(s);
            }

            public XYHolder getXY(){
                return new XYHolder(getX(),getY());
            }

            public void setXY(XYHolder xYHolder){
                setX(xYHolder.getX());
                setY(xYHolder.getY());
            }
        }

        private XYBall addBall(float x, float y) {
            OvalShape circle = new OvalShape();
            circle.resize(50f * mDensity, 50f * mDensity);
            ShapeDrawable drawable = new ShapeDrawable(circle);
            XYBall shapeHolder = new XYBall(drawable);
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
            return shapeHolder;
        }

        private void startAnimation() {
            createAnimation();
            anim.start();
        }

        private class XYHolder{
            private  float y;
            private  float x;

            public XYHolder(float x,float y){
                this.x = x;
                this.y = y;
            }

            public float getX(){
                return x;
            }

            public void  setX(float x){
                this.x = x;
            }

            public float getY(){
                return y;
            }

            public void  setY(float y){
                this.y = y;
            }
        }

        private void createAnimation() {
            if(anim == null){
                anim = ObjectAnimator.ofObject(ball,"xY",new MyEvaluator(),new XYHolder(300f,600f));
                anim.setDuration(1500);
                anim.addUpdateListener(this);
                anim.setInterpolator(new BounceInterpolator());
            }
        }

        private class MyEvaluator implements TypeEvaluator<XYHolder>{

            @Override
            public XYHolder evaluate(float v, XYHolder xyHolder, XYHolder xyHolder2) {
                float x = xyHolder.getX()+v*(xyHolder2.getX()-xyHolder.getX());
                float y = xyHolder.getY()+v*(xyHolder2.getY()-xyHolder.getY());
                return new XYHolder(x,y);
            }
        }
    }
}
