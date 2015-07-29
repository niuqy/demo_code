package com.example.demoproject.animation.view_anim;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.example.demoproject.R;

/**
 * Created by abner on 7/28/15.
 */
public class ViewAnimationUseAct extends Activity {
    Animation alphaIn,alphaOut;
    View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_anim_using);
        root = findViewById(R.id.root);

        alphaIn = AnimationUtils.loadAnimation(this, R.anim.alpha_in);
        alphaIn.setFillAfter(true);
        root.startAnimation(alphaIn);
    }

    @Override
    protected void onChildTitleChanged(Activity childActivity, CharSequence title) {
        super.onChildTitleChanged(childActivity, title);

    }

}
