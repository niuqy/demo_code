package com.example.demoproject.animation.view_anim;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.demoproject.R;

/**
 * Created by abner on 7/3/15.
 */
public class SetAnimForActivityAct extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Button btn = new Button(this);
        btn.setText("show popup window");
        btn.setTextSize(30);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        btn.setLayoutParams(lp);
        btn.setFocusable(true);
        btn.setBackgroundColor(getResources().getColor(android.R.color.white));
        setContentView(btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPop(view);
            }
        });

    }

    private void showPop(View anchor) {
        PopupWindow mVisitedMostPop = new PopupWindow(this);
        mVisitedMostPop.setBackgroundDrawable(new BitmapDrawable());
        mVisitedMostPop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        mVisitedMostPop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mVisitedMostPop.setFocusable(true);
        mVisitedMostPop.setTouchable(true);

        mVisitedMostPop.setAnimationStyle(R.style.popup_anim_style_right_in_left_out);


        TextView tv = new TextView(this);
        tv.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        tv.setText("I'm TextView");
        tv.setTextColor(Color.RED);
        tv.setTextSize(50);
        mVisitedMostPop.setContentView(tv);
        mVisitedMostPop.showAtLocation(anchor, Gravity.CENTER, 0, 0);
    }
}
