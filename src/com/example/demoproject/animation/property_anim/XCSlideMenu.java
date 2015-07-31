package com.example.demoproject.animation.property_anim;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * 自定义侧滑菜单View
 * @author caizhiming
 *
 */
public class XCSlideMenu extends HorizontalScrollView {

    private LinearLayout mWapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;
    private ViewGroup rightMenu;
    private int mScreenWidth;
    private int mMenuRightPadding;
    private int mMenuWidth = 0,rightMenuWidth = 0;

    private boolean once = false;
    //Menu是否处于显示状态
    private boolean isSlideOut;

    public static final int RIGHT_PADDING = 100;

    public XCSlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        //将dp转化为px
        mMenuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, XCSlideMenu.RIGHT_PADDING, context.getResources().getDisplayMetrics());
    }

    /**
     * 设置子View的宽和高
     * 设置自己的宽和高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        if(!once){
            mWapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) mWapper.getChildAt(0);
            mContent = (ViewGroup) mWapper.getChildAt(1);
            rightMenu = (ViewGroup) mWapper.getChildAt(2);

            mMenuWidth  = mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
            rightMenuWidth = rightMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
            mContent.getLayoutParams().width = mScreenWidth;
            once = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    /**
     * 通过设置偏移量将Menu隐藏
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub
        super.onLayout(changed, l, t, r, b);
        if(changed){
            this.scrollTo(mMenuWidth, 0);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                //隐藏在左边宽度
                int scrollX = getScrollX();
                if(scrollX >= mMenuWidth /2){
                    if((scrollX - mMenuWidth) <= rightMenuWidth/2){
                        //hide left and right menu,show content
                        this.smoothScrollTo(mMenuWidth, 0);
                        isSlideOut = false;
                    }else{
                        //show right menu
                        this.smoothScrollTo(mWapper.getWidth(),0);
                    }
                }else{
                    //Menu 右滑 显示出来
                    this.smoothScrollTo(0, 0);
                    isSlideOut = true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }
    /**
     * 向右滑出菜单显示出来
     */
    public void slideOutMenu(){
        if(!isSlideOut){
            this.smoothScrollTo(0, 0);
            isSlideOut = true;
        }
    }
    /**
     * 向左滑出菜单隐藏起来
     */
    public void slideInMenu(){
        if(isSlideOut){
            this.smoothScrollTo(mMenuWidth, 0);
            isSlideOut = false;
        }
    }
    /**
     * 切换菜单向右滑出显示或向左滑出隐藏的状态
     */
    public void switchMenu(){
        if(isSlideOut){
            slideInMenu();
        }else{
            slideOutMenu();
        }
    }
    /**
     * 滚动发生时
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        Log.d("nqy","l:"+l+",t:"+t+",oldl:"+oldl+",oldt:"+oldt);

        if(l <= mMenuWidth){
            //实现抽屉式滑动
            float scale = l * 1.0f /mMenuWidth ;//1 ~ 0
            float menuScale = 1.0f - scale * 0.3f;
            float menuAlpha = 0.0f + 1.0f * (1 - scale);
            float contentScale = 0.8f + 0.2f * scale;
            //调用属性动画，设置TranslationX
//        ViewHelper.setTranslationX(mMenu, mMenuWidth*scale*0.8f);
            mMenu.setTranslationX(mMenuWidth * scale * 0.8f);

            //左侧菜单的缩放
//        ViewHelper.setScaleX(mMenu, menuScale);
//        ViewHelper.setScaleY(mMenu, menuScale);
            mMenu.setScaleX(menuScale);
            mMenu.setScaleY(menuScale);
            //左侧菜单的透明度缩放
//        ViewHelper.setAlpha(mMenu, menuAlpha);
            mMenu.setAlpha(menuAlpha);

            //右侧内容的缩放
//        ViewHelper.setPivotX(mContent, 0);
//        ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
//        ViewHelper.setScaleY(mContent, contentScale);
//        ViewHelper.setScaleX(mContent, contentScale);
            mContent.setPivotX(0);
            mContent.setPivotY(mContent.getHeight() / 2);
            mContent.setScaleX(contentScale);
            mContent.setScaleY(contentScale);
        }else{
            float scale = (l - mMenuWidth) * 1.0f /rightMenuWidth ;//0 ~ 1
            float rightMenuScale = 0.7f + scale * 0.3f;
            float rightmMenuAlpha = 1.0f * scale;
            float contentScale = 1.0f - 0.2f * scale;

            rightMenu.setAlpha(rightmMenuAlpha);
            rightMenu.setPivotX(0);
            rightMenu.setPivotY(rightMenu.getHeight()/2);
            rightMenu.setScaleX(rightMenuScale);
            rightMenu.setScaleY(rightMenuScale);
            rightMenu.setTranslationX(-rightMenuWidth * 0.2f * (1 - scale));

            mContent.setPivotX(mContent.getWidth());
            mContent.setPivotY(mContent.getHeight() / 2);
            mContent.setScaleX(contentScale);
            mContent.setScaleY(contentScale);
        }

    }

}
