package com.example.demoproject.gesture;

import android.app.Activity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.demoproject.R;

/**
 * Created by abner on 4/20/15.
 */
public class VelocityTrackerAct extends Activity{
    private TextView mTextView;
    private GestureDetector mGestureDetector;
    private VelocityTrackingTouchListener mTouchListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.velocity_tracker);

        // Instantiate a gesture listener to consume scroll and fling events
        FlingDetector flingDetector = new FlingDetector();
        // Pass the FlingDetector to mGestureDetector to receive the appropriate callbacks
        mGestureDetector = new GestureDetector(this, flingDetector);
        // Instantiate the touch listener
        mTouchListener = new VelocityTrackingTouchListener();
        // Initialize the TextView which will be used to display the logged events
        mTextView = (TextView) findViewById(R.id.mytextview);
        mTextView.setOnTouchListener(mTouchListener);
        // Initialize the "clear text" button
        Button btnClear = (Button) findViewById(R.id.clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.setText("");
            }
        });
    }

    /**
     * Helper method to prepend new text to the TextView log and highlight the most recent line.
     * @param newText The new logging statement, which will be displayed at the top of the TextView.
     */
    public void updateText(String newText) {
        CharSequence oldText = mTextView.getText();
//        mTextView.setText(newText + "\n" + oldText);
        mTextView.setText(newText + "\n" + oldText, TextView.BufferType.SPANNABLE);
        Spannable s = (Spannable) mTextView.getText();
        s.setSpan(new ForegroundColorSpan(0xFFFFFFFF), 0, newText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    private class VelocityTrackingTouchListener implements View.OnTouchListener {
        VelocityTracker mVelocityTracker = null;
        float xVelocity, yVelocity;

        /**
         * @inheritDoc
         * This method largely taken from "Tracking Movement":
         * http://developer.android.com/training/gestures/movement.html
         */
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            updateText("in onTouch");

            int index = motionEvent.getActionIndex();
            int action = motionEvent.getActionMasked();
            int pointerId = motionEvent.getPointerId(index);

            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    updateText("ACTION_DOWN");
                    if (mVelocityTracker == null) {
                        // Retrieve a new VelocityTracker object to watch the velocity of a motion.
                        mVelocityTracker = VelocityTracker.obtain();
                    } else {
                        // Reset the velocity tracker back to its initial state.
                        mVelocityTracker.clear();
                    }
                    // Add a user's movement to the tracker.
                    mVelocityTracker.addMovement(motionEvent);
                    break;
                case MotionEvent.ACTION_MOVE:
                    updateText("ACTION_MOVE");
                    mVelocityTracker.addMovement(motionEvent);
                    // When you want to determine the velocity, call
                    // computeCurrentVelocity(). Then call getXVelocity()
                    // and getYVelocity() to retrieve the velocity for each pointer ID.
                    mVelocityTracker.computeCurrentVelocity(1000);
                    // Log velocity of pixels per second
                    xVelocity = mVelocityTracker.getXVelocity(pointerId);
                    yVelocity = mVelocityTracker.getYVelocity(pointerId);

                    break;
                case MotionEvent.ACTION_UP:
                    updateText("ACTION_UP");
                    break;
                case MotionEvent.ACTION_CANCEL:
                    updateText("ACTION_CANCEL");
                    // Return a VelocityTracker object back to be re-used by others.
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                    break;
            }
            // Call onTouchEvent which will invoke the callbacks in the listener that we passed into mGestureDetector's constructor.
//            mGestureDetector.onTouchEvent(motionEvent);
            return true;
        }
    }

    /**
     * Class to log scroll and fling events
     */
    private class FlingDetector extends GestureDetector.SimpleOnGestureListener {
        public FlingDetector() {
            super();
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            updateText("in onFling");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            updateText(String.format("onScroll velocity = (%f, %f)", mTouchListener.xVelocity, mTouchListener.yVelocity));
            return false;
        }
    }
}
