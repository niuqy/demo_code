package com.example.demoproject.animation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import com.example.demoproject.R;

/**
 * Created by abner on 4/28/15.
 */
public class ViewAnimAct extends Activity {
    private final String TAG = "ViewAnimAct";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_anim);

        final View mImageView = findViewById(R.id.imageView);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation anim = AnimationUtils.loadAnimation(ViewAnimAct.this,R.anim.hyperspace_jump);
//                mImageView.startAnimation(anim);

                //a,alternative way to startAnimation(),but it doesn't work for now
//                mImageView.setAnimation(anim);
//                anim.setStartTime(AnimationUtils.currentAnimationTimeMillis()+1*1000);//start the animation 1s later

                //b,why it only work
                mImageView.setAnimation(anim);
                anim.start();
                mImageView.invalidate();
            }
        });

    }
}
