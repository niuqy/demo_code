package com.example.demoproject.custom_view.scroll_view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.HorizontalScrollView;

/**
 * Created by abner on 5/22/15.
 */
public class GetKnownScrollView extends HorizontalScrollView {
    private int count;
    private int lastSelection;

    public GetKnownScrollView(Context context) {
        super(context);
    }

    public GetKnownScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GetKnownScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    public void setItemCount(int count){
        this.count = count;
    }

    public void setSelection(int selection){
        this.lastSelection = selection;
    }

    /**
     * Compute the amount to scroll in the X direction in order to get
     * a rectangle completely on the screen (or, if taller than the screen,
     * at least the first screen size chunk of it).
     *
     * @param rect The rect.
     * @return The scroll delta.
     */
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        if (getChildCount() == 0) return 0;

        int width = getWidth();
        int screenLeft = getScrollX();
        int screenRight = screenLeft + width;

        int fadingEdge = getHorizontalFadingEdgeLength();

        // leave room for left fading edge as long as rect isn't at very left
        if (rect.left > 0) {
            screenLeft += fadingEdge;
        }

        // leave room for right fading edge as long as rect isn't at very right
        if (rect.right < getChildAt(0).getWidth()) {
            screenRight -= fadingEdge;
        }

        int scrollXDelta = 0;

        int offset = 0;
        if (rect.right > screenRight && rect.left > screenLeft) {
            // need to move right to get it in view: move right just enough so
            // that the entire rectangle is in view (or at least the first
            // screen size chunk).

            if (rect.width() > width) {
                // just enough to get screen size chunk on
                scrollXDelta += (rect.left - screenLeft);
            } else {
                // get entire rect at right of screen
                scrollXDelta += (rect.right - screenRight);
            }

            // make sure we aren't scrolling beyond the end of our content
            int right = getChildAt(0).getRight();
            int distanceToRight = right - screenRight;

            scrollXDelta = Math.min(scrollXDelta, distanceToRight);

            //make it to scroll to the most right when current selection is the third-last  one
            if(lastSelection == (count-4)){
                scrollXDelta = distanceToRight;
            }

        } else if (rect.left < screenLeft && rect.right < screenRight) {
            // need to move right to get it in view: move right just enough so that
            // entire rectangle is in view (or at least the first screen
            // size chunk of it).

            if (rect.width() > width) {
                // screen size chunk
                scrollXDelta -= (screenRight - rect.right);
            } else {
                // entire rect at left
                scrollXDelta -= (screenLeft - rect.left);
            }

            // make sure we aren't scrolling any further than the left our content
            scrollXDelta = Math.max(scrollXDelta, -getScrollX());

            //make it to scroll to the most left when current selection is third one
            if(lastSelection == 3){
                scrollXDelta = -getScrollX();
            }
        }

        Log.d("nqy","width:"+width+",getChildAt(0).getWidth():"+getChildAt(0).getWidth()+",screenLeft:"+screenLeft+",screenRight:"+screenRight);
        Log.d("nqy", "rect.left :" + rect.left + ",rect.right:" + rect.right + ",fadingEdge:" + fadingEdge + "");
        Log.d("nqy","offset:"+offset+",rect.width:"+rect.width()+",scrollXDelta:"+scrollXDelta+",itemCount:"+count+",lastSelection:"+lastSelection);
        return scrollXDelta;
    }
}
