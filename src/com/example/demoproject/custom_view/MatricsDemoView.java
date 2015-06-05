package com.example.demoproject.custom_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import com.example.demoproject.R;

/**
 * Created by abner on 6/1/15.
 */
public class MatricsDemoView extends View {
    private Bitmap mBitmap;
    private Matrix mMatrix = new Matrix();

    public MatricsDemoView(Context context) {
        super(context);
        initialize();
    }

    public MatricsDemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MatricsDemoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap,mMatrix,null);
    }

    private void initialize() {

        mBitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.car)).getBitmap();

        float cosValue = (float) Math.cos(-Math.PI/6);

        float sinValue = (float) Math.sin(-Math.PI/6);

        mMatrix.setValues(

                new float[]{

                        cosValue, -sinValue, 100,

                        sinValue, cosValue, 100,

                        0, 0, 1});

    }

}
