package com.example.demoproject.custom_view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.graphics.Matrix;
import com.example.demoproject.R;

/**
 * Created by abner on 6/9/15.
 */
public class MatricsAct2 extends Activity implements View.OnClickListener {
    ImageView content;
    Bitmap baseBp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrix_demo2);

        findViewById(R.id.scale).setOnClickListener(this);
        findViewById(R.id.translate).setOnClickListener(this);
        findViewById(R.id.rotate).setOnClickListener(this);
        findViewById(R.id.skew).setOnClickListener(this);

        content = (ImageView) findViewById(R.id.content);

        baseBp = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.scale:
                myScale(3,3);
            break;
            case R.id.translate:
                myTranslate(100,100);
                break;
            case R.id.rotate:
                myRotate(30);
                break;
            case R.id.skew:
                mySkew(0.5f,0.5f);
                break;
        }
    }

    private void mySkew(float x, float y) {
        Bitmap bm = Bitmap.createBitmap(baseBp);

        Canvas canvas = new Canvas();
        Matrix matrix = new Matrix();
        matrix.setSkew(x,y);
        canvas.drawBitmap(bm,matrix,null);

        content.setImageBitmap(bm);
    }

    private void myRotate(int i) {
        Bitmap bm = Bitmap.createBitmap(baseBp.getWidth(),baseBp.getHeight(),baseBp.getConfig());

        Canvas canvas = new Canvas(bm);
        Matrix matrix = new Matrix();
        matrix.setRotate(i);
        canvas.drawBitmap(baseBp,matrix,null);

        content.setImageBitmap(bm);
    }

    private void myTranslate(int x, int y) {
        Bitmap bm = Bitmap.createBitmap(baseBp);

        Canvas canvas = new Canvas();
        Matrix matrix = new Matrix();
        matrix.setTranslate(x,y);
        canvas.drawBitmap(bm,matrix,null);

        content.setImageBitmap(bm);
    }

    private void myScale(float x,float y) {
        Log.d("nqy","myScale "+x+","+y);
        Bitmap bm = Bitmap.createBitmap((int)(baseBp.getWidth()*x),(int)(baseBp.getHeight()*y),baseBp.getConfig());

        Canvas canvas = new Canvas(bm);
        Matrix matrix = new Matrix();
        matrix.setScale(x,y);
        canvas.drawBitmap(baseBp,matrix,null);

        content.setImageBitmap(bm);
    }
}
