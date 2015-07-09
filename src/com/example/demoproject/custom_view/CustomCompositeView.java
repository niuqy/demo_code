package com.example.demoproject.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.demoproject.R;

/**
 * Created by abner on 7/9/15.
 */
public class CustomCompositeView extends ViewGroup {
    ImageView mAuthorImage;
    TextView mAuthorName,mPostMsg;

    public CustomCompositeView(Context context) {
        this(context, null);
    }

    public CustomCompositeView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomCompositeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        Log.d("nqy","CustomCompositeView()");

        LayoutInflater.from(getContext()).inflate(R.layout.custom_composite_view,this,true);

        mAuthorImage = (ImageView) findViewById(R.id.author_image);
        mAuthorName = (TextView) findViewById(R.id.author_name);
        mPostMsg = (TextView) findViewById(R.id.post_message);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int widthUsed = 0;
        int heightUsed = 0;

        measureChildWithMargins(mAuthorImage,widthMeasureSpec,widthUsed,heightMeasureSpec,heightUsed);
        widthUsed += getChildMeasureWidthWithMargin(mAuthorImage);

        measureChildWithMargins(mAuthorName,widthMeasureSpec,widthUsed,heightMeasureSpec,heightUsed);
        heightUsed += getChildMeasureHeightWithMargin(mAuthorName);

        measureChildWithMargins(mPostMsg,widthMeasureSpec,widthUsed,heightMeasureSpec,heightUsed);
        heightUsed += getChildMeasureHeightWithMargin(mPostMsg);

        int contentLeftHeight = getChildMeasureHeightWithMargin(mAuthorImage);
        if(contentLeftHeight > heightUsed){
            heightUsed = contentLeftHeight;
        }

        int heightSize = heightUsed + getPaddingTop() + getPaddingBottom();

        Log.d("nqy","onMeasure():widthSize "+widthSize+",heightSize "+heightSize);
        setMeasuredDimension(widthSize,heightSize);
    }

    private int getChildMeasureHeightWithMargin(View view) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        return view.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    private int getChildMeasureWidthWithMargin(View view) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        return view.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("nqy","onLayout()");
        int currentLeft = getPaddingLeft();
        int currentTop = getPaddingTop();

        layoutView(mAuthorImage,currentLeft,currentTop,mAuthorImage.getMeasuredWidth(),mAuthorImage.getMeasuredHeight());

        currentLeft += getChildWidthWithMargin(mAuthorImage);
        int contentWidth = r - l - currentLeft - getPaddingRight();
        layoutView(mAuthorName,currentLeft,currentTop,contentWidth,mAuthorName.getMeasuredHeight());

        currentTop += getChildHeightWithMargin(mAuthorName);
        layoutView(mPostMsg,currentLeft,currentTop,contentWidth,mPostMsg.getMeasuredHeight());
    }

    private int getChildWidthWithMargin(View view){
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        return view.getWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
    }


    private int getChildHeightWithMargin(View view){
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        return view.getHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    private void layoutView(View view,int l,int t,int width,int height){
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        int left = l + marginLayoutParams.leftMargin;
        int top = t + marginLayoutParams.topMargin;
        view.layout(left,top,left + width,top + height);
        Log.d("nqy","layoutView() "+view.getClass().getName()+" l "+left+",t "+top+",width "+width+",height "+height);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
}
