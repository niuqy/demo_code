package com.example.demoproject.gesture;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by abner on 4/20/15.
 */
public class CusLinearLayout extends LinearLayout {
    public CusLinearLayout(Context context) {
        super(context);
    }

    public CusLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CusLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
//            case MotionEvent.ACTION_DOWN:
//                break;
            case MotionEvent.ACTION_MOVE:
                //here return true,the child view will receive ACTION_CANCEL and won't receive ACTION_MOVE and ACTION_UP
                return true;
//            case MotionEvent.ACTION_UP:
//                return true;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
