package com.example.demoproject.custom_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import com.example.demoproject.R;

/**
 * Created by abner on 6/1/15.
 */
public class MatricsDemoView2 extends View {
    private Bitmap mBitmap;
    private Matrix mMatrix = new Matrix();

    public MatricsDemoView2(Context context) {
        super(context);
        initialize();
    }

    public MatricsDemoView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MatricsDemoView2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap,mMatrix,null);
    }

    private void initialize() {
        setBackgroundColor(Color.parseColor("#0000ff"));

        mBitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.car)).getBitmap();

        //scale 0~infinity,no scale when it's 1
        mMatrix.setScale(0.3f, 0.3f);

        //绕着中心点在平面上旋转
        mMatrix.postRotate(20);

        mMatrix.postTranslate(100, 500);

        //skew -1~1f,no skew when 0,won't watch it when 1 or -1,正值正向扭曲，负值负向扭曲
//        mMatrix.preSkew(-0.2f, 0.2f);
    }


}
