package com.example.demoproject.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by abner on 5/19/15.
 */
public class DemoMeasureView extends View {
    public DemoMeasureView(Context context) {
        super(context);
    }

    public DemoMeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DemoMeasureView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int smh = getSuggestedMinimumHeight();
        int smw = getSuggestedMinimumWidth();

        int width = getDefaultSize2(smw,widthMeasureSpec);
        int height = getDefaultSize2(smh,heightMeasureSpec);

        Log.d("nqy","width:"+width+",height:"+height);

        setMeasuredDimension(smw, smh);
    }

    public int getDefaultSize2(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        Log.d("nqy","getDefaultSize2 sms size:"+size+",specMode:"+specMode+",specSize:"+specSize);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                Log.d("nqy","UNSPECIFIED");
                break;
            case MeasureSpec.AT_MOST:
                Log.d("nqy","AT_MOST");
                result = specSize;
                break;
            case MeasureSpec.EXACTLY:
                Log.d("nqy","EXACTLY");
                result = specSize;
                break;
        }
        return result;
    }
}
