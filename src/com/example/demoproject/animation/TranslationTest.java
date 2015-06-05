package com.example.demoproject.animation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.demoproject.R;

/**
 * Created by abner on 11/3/14.
 */
public class TranslationTest extends Activity {
    private static final String TAG = "TranslationTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translation_test);

        LinearLayout container = (LinearLayout) findViewById(R.id.container);
        CusImg img = new CusImg(this);
        img.setImageResource(R.drawable.ic_launcher);
        container.addView(img);
    }

    public class CusImg extends ImageView{


        public CusImg(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            int action = event.getAction();
            switch (action){
                case MotionEvent.ACTION_DOWN:
//                    donwX = event.getX();
//                    downY = event.getY();
                    Log.d(TAG, "ACTION_DOWN");
                    break;
                case MotionEvent.ACTION_MOVE:
                    return handleMove(event);
            }
            return super.onTouchEvent(event);
        }

        private boolean handleMove(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            Log.d(TAG, "x=" + x +",y="+y);

//            setTranslationX(x);
//            setTranslationY(y);
            return  true;
        }
    }
}
