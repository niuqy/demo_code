package com.example.demoproject.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.example.demoproject.R;

/**
 * Created by abner on 11/4/14.
 */
public class ViewPropertyAnimatorTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.viewpropertyanimatortest);

        final LinearLayout container = (LinearLayout) findViewById(R.id.container);

        final View animBtn = findViewById(R.id.animBtn);
        animBtn.animate().setDuration(1000);

        findViewById(R.id.fadeOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animBtn.animate().alpha(0f);
            }
        });
        findViewById(R.id.fadeIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animBtn.animate().alpha(1f);
            }
        });
        findViewById(R.id.moveOver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float x = container.getWidth()-animBtn.getWidth();
                float y = container.getHeight()-animBtn.getHeight();
                animBtn.animate().x(x).y(y);
//                animBtn.animate().translationX(x).translationY(y);
            }
        });
        findViewById(R.id.moveBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animBtn.animate().x(0).y(0);
            }
        });
        findViewById(R.id.rotate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animBtn.animate().alpha(animBtn.getAlpha()==0f?1f:0f).rotationY(360f).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animBtn.setRotationY(0f);
                    }
                });
            }
        });

    }
}
