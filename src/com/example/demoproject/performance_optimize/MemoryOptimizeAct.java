package com.example.demoproject.performance_optimize;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import com.example.demoproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abner on 8/17/15.
 */
public class MemoryOptimizeAct extends Activity implements View.OnClickListener {
    static int[] bitmapResource = {R.drawable.bg_letv,R.drawable.car,R.drawable.city,R.drawable.doggg,R.drawable.girl
    ,R.drawable.haibao1,R.drawable.ic_browser_press,R.drawable.ic_launcher,R.drawable.land};

    List<Drawable> drawableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_optimize);

        findViewById(R.id.bt1).setOnClickListener(this);
        findViewById(R.id.bt2).setOnClickListener(this);
        findViewById(R.id.bt3).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt1:
                createBitmapsWithList();
                break;
            case R.id.bt2:
                createBitmaps();
                break;
            case R.id.bt3:
                findViewById(R.id.iv1).setBackgroundDrawable(drawableList.get(0));
                break;
        }
    }


    private void createBitmapsWithList() {
        drawableList = new ArrayList<Drawable>(10);
        for(Integer i:bitmapResource){
//            Drawable drawable = getResources().getDrawable(i);
            drawableList.add(getResources().getDrawable(i));
        }
    }

    private void createBitmaps() {
        for(Integer i:bitmapResource){
            Drawable drawable = getResources().getDrawable(i);
        }
    }
}
