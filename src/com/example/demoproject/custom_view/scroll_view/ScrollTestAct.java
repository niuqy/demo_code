package com.example.demoproject.custom_view.scroll_view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.example.demoproject.R;

/**
 * Created by abner on 5/22/15.
 */
public class ScrollTestAct extends Activity implements View.OnFocusChangeListener {
    TextView textView;
    GetKnownScrollView scrollView;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_test);

        scrollView = (GetKnownScrollView) findViewById(R.id.myscroll);
        linearLayout = (LinearLayout) findViewById(R.id.myll);
        textView = (TextView) findViewById(R.id.mytextview);

        int size = 10;
        scrollView.setSmoothScrollingEnabled(true);
        scrollView.setItemCount(size);
        for(int i=0;i<size;i++){
            ImageView iv = new ImageView(this);
            iv.setFocusable(true);
            iv.setOnFocusChangeListener(this);
            iv.setTag(i+"");
            iv.setImageDrawable(getResources().getDrawable(R.drawable.bg_letv));
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(320,180);
            linearLayout.addView(iv,lp);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus){
            scrollView.setSelection(Integer.parseInt((String) v.getTag()));
            textView.setText((String) v.getTag());
            v.setBackgroundColor(Color.rgb(50,50,50));
        }else{
            v.setBackgroundColor(Color.rgb(255,2255,255));
        }
    }
}
