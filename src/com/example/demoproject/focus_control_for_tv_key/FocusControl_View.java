package com.example.demoproject.focus_control_for_tv_key;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.example.demoproject.R;

/**
 * Created by abner on 4/30/15.
 */
public class FocusControl_View extends RelativeLayout implements View.OnFocusChangeListener {
    private Context mContext;
    public FocusControl_View(Context context) {
        super(context);
        mContext = context;

//        addView(initUiSimple());
        addView(initUiGridView());
    }

    private View initUiGridView() {

        GridView grid = new GridView(mContext);

        grid.setNumColumns(5);
        grid.setHorizontalSpacing(20);
        grid.setVerticalSpacing(20);
        grid.setFocusable(false);
        BaseAdapter adapter = new MyGridAdapter();
        grid.setAdapter(adapter);

        return grid;
    }

    private View initUiSimple(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.focus_control_content, null);

        View iv0 = view.findViewById(R.id.iv0);
        View iv1 = view.findViewById(R.id.iv1);

        iv0.setFocusable(true);
        iv1.setFocusable(true);

        iv0.setOnFocusChangeListener(this);
        iv1.setOnFocusChangeListener(this);

        iv0.requestFocus();

        return view;
    }

    public FocusControl_View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FocusControl_View(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        Log.d("nqy","onFocusChange "+view.getTag()+" "+hasFocus);
        if(hasFocus){
            largerAnim(view);
        }else{
            smallerAnim(view);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.d("nqy","dispatchKeyEvent:"+event.getAction());
        return super.dispatchKeyEvent(event);
    }

    private void largerAnim(final View v) {
        Animation a = AnimationUtils.loadAnimation(mContext,
                R.anim.my_enlarge_anim);
        a.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        });
        a.setFillAfter(true);
        v.clearAnimation();
        v.setAnimation(a);
    }

    private void smallerAnim(final View v) {
        Animation a = AnimationUtils.loadAnimation(mContext,
                R.anim.my_shrink_anim);
        a.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        });
        a.setFillAfter(true);
        v.clearAnimation();
        v.startAnimation(a);
    }

    class MyGridAdapter extends BaseAdapter{
        int count = 14;
        @Override
        public int getCount() {
            return count;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view == null){
                ImageView iv = new ImageView(mContext);
                iv.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_letv));
                view = iv;
            }

            view.setFocusable(true);
            view.setOnFocusChangeListener(FocusControl_View.this);

            view.setTag("index "+i);
            if(i == 0){
                view.requestFocus();
            }

            return view;
        }
    }
}
