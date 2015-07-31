package com.example.demoproject.animation.property_anim;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.example.demoproject.R;

/**
 * Created by abner on 7/31/15.
 */
public class Five_Eight_LoadingView_Act extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.five_eight_loading);
        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShapeLoadingDialog dialog = new ShapeLoadingDialog(Five_Eight_LoadingView_Act.this);
                dialog.setLoadingText("加载中...");
                dialog.show();
            }
        });
    }
}
