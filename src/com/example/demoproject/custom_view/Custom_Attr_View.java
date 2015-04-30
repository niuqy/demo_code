package com.example.demoproject.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import com.example.demoproject.R;
import org.w3c.dom.Text;

/**
 * Created by abner on 4/30/15.
 */
public class Custom_Attr_View extends TextView {

    public Custom_Attr_View(Context context) {
        super(context);
    }

    public Custom_Attr_View(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Custom_Attr_View);
        String title = array.getString(R.styleable.Custom_Attr_View_astTitle);
        int textSize = (int) array.getDimension(R.styleable.Custom_Attr_View_astTextSize, 10);
        array.recycle();

        TypedArray array1 = context.obtainStyledAttributes(attrs,R.styleable.ArbitraryName);
        String myAttrName = array1.getString(R.styleable.ArbitraryName_myAttrName);
        array1.recycle();

        setText(title+"-"+myAttrName);
        setTextSize(textSize);
    }

    public Custom_Attr_View(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
