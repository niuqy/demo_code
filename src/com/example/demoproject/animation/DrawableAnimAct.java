package com.example.demoproject.animation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import com.example.demoproject.R;

/**
 * Created by abner on 4/28/15.
 */
public class DrawableAnimAct extends Activity {
    AnimationDrawable rocketAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawable_anim);

        ImageView rocketImage = (ImageView) findViewById(R.id.rocket_image);
        rocketImage.setBackgroundResource(R.drawable.rocket_thrust);
        rocketAnimation = (AnimationDrawable) rocketImage.getBackground();

        //It's important to note that the start() method called on the AnimationDrawable cannot be called during the onCreate() method of your Activity,
        // because the AnimationDrawable is not yet fully attached to the window.
        // If you want to play the animation immediately, without requiring interaction,
        // then you might want to call it from the onWindowFocusChanged() method in your Activity,
        // which will get called when Android brings your window into focus.
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){ //why it only work when the first action_down occurs ?
            rocketAnimation.start();
            return true;
        }
        return super.onTouchEvent(event);
    }
}
