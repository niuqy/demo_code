package com.example.demoproject.custom_view.blur;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.demoproject.R;

/**
 * Created by abner on 5/22/15.
 */
public class BlurTestAct extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final RelativeLayout rl = new RelativeLayout(this);
//        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(500,500);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(rl, lp);

        rl.post(new Runnable() {
            @Override
            public void run() {
                blur(((BitmapDrawable) getResources().getDrawable(R.drawable.haibao1)).getBitmap(), rl);
//                Drawable d = boxBlurFilter(((BitmapDrawable) getResources().getDrawable(R.drawable.haibao1)).getBitmap());
//                rl.setBackgroundDrawable(d);
            }
        });
    }

    /**
     * horizontal blur degree
     */
    private static float hRadius = 5;
    /**
     * vertical blur degree
     */
    private static float vRadius = 5;
    /**
     * blur iteration
     */
    private static int iterations = 7;
    /**
     * width and height size factor
     */
    private static int sizeFactor = 8;

    public static Drawable boxBlurFilter(Bitmap bmp) {
        int width = bmp.getWidth()/sizeFactor;
        int height = bmp.getHeight()/sizeFactor;
        int[] inPixels = new int[width * height];
        int[] outPixels = new int[width * height];
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bmp.getPixels(inPixels,0,width, 0, 0,width, height);
        for (int i = 0;i < iterations; i++) {
            blur(inPixels,outPixels, width, height, hRadius);
            blur(outPixels,inPixels, height, width, vRadius);
        }
        blurFractional(inPixels, outPixels, width, height, hRadius);
        blurFractional(outPixels,inPixels, height, width, vRadius);
        bitmap.setPixels(inPixels,0, width, 0,0,width, height);
        Drawable drawable = new BitmapDrawable(bitmap);

        return drawable;
    }

    public static void blur(int[]in, int[]out, int width,int height,float radius) {
        int widthMinus1 = width - 1;
        int r = (int)radius;
        int tableSize= 2 *r + 1;
        int divide[]= new int[256 *tableSize];

        for (int i = 0;i < 256 * tableSize; i++)
            divide[i] = i / tableSize;

        int inIndex = 0;

        for (int y = 0; y < height; y++) {
            int outIndex = y;
            int ta = 0,tr = 0,tg = 0,tb = 0;
            for (int i = -r; i <= r; i++) {
                int rgb = in[inIndex + clamp(i, 0,width - 1)];
                ta+= (rgb >> 24)
                        & 0xff;
                tr+= (rgb >> 16)
                        & 0xff;
                tg+= (rgb >> 8)
                        & 0xff;
                tb+= rgb & 0xff;
            }

            for (int x= 0;x < width; x++) {
                out[outIndex] = (divide[ta] << 24)| (divide[tr] << 16)|(divide[tg] << 8)| divide[tb];

                int i1 = x + r + 1;
                if (i1> widthMinus1)
                    i1= widthMinus1;
                int i2 = x - r;
                if (i2< 0)
                    i2= 0;
                int rgb1 = in[inIndex + i1];
                int rgb2 = in[inIndex + i2];

                ta += ((rgb1 >> 24) & 0xff)- ((rgb2 >> 24)& 0xff);
                tr += ((rgb1 & 0xff0000) - (rgb2 & 0xff0000))>> 16;
                tg += ((rgb1 & 0xff00) - (rgb2 & 0xff00))>> 8;
                tb += (rgb1 & 0xff) - (rgb2 & 0xff);
                outIndex += height;
            }
            inIndex += width;
        }
    }

    public static void blurFractional(int[]in, int[]out, int width,int height,float radius) {
        radius -= (int) radius;
        float f = 1.0f/ (1 + 2 * radius);
        int inIndex = 0;

        for (int y = 0; y < height; y++) {
            int outIndex = y;

            out[outIndex] = in[0];
            outIndex += height;
            for (int x = 1; x < width - 1;x++) {
                int i = inIndex + x;
                int rgb1 = in[i - 1];
                int rgb2 = in[i];
                int rgb3 = in[i + 1];

                int a1 = (rgb1 >> 24) & 0xff;
                int r1= (rgb1 >> 16) & 0xff;
                int g1= (rgb1 >> 8) & 0xff;
                int b1= rgb1 & 0xff;
                int a2 = (rgb2 >> 24) & 0xff;
                int r2 = (rgb2 >> 16)& 0xff;
                int g2 = (rgb2 >> 8) & 0xff;
                int b2 = rgb2 & 0xff;
                int a3 = (rgb3 >> 24) & 0xff;
                int r3 = (rgb3 >> 16) & 0xff;
                int g3 = (rgb3 >> 8)& 0xff;
                int b3 = rgb3 & 0xff;
                a1 = a2 + (int)((a1 + a3) * radius);
                r1 = r2 + (int)((r1 + r3) * radius);
                g1 = g2 + (int) ((g1 + g3) * radius);
                b1 = b2 + (int)((b1 + b3) * radius);
                a1 *= f;
                r1 *= f;
                g1 *= f;
                b1 *= f;
                out[outIndex]= (a1 << 24)| (r1 << 16)| (g1 << 8) | b1;
                outIndex+= height;
            }
            out[outIndex] = in[width - 1];
            inIndex+= width;
        }
    }

    public static int clamp(int x, int a,int b) {
        return (x< a) ? a : (x > b) ? b : x;
    }


    private void blur(Bitmap bkg, View view) {
        long startMs = System.currentTimeMillis();
        float radius = 2;
        float scaleFactor = 8;

        Bitmap overlay = Bitmap.createBitmap((int) (view.getMeasuredWidth()/scaleFactor),
                (int) (view.getMeasuredHeight()/scaleFactor), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.translate(-view.getLeft()/scaleFactor, -view.getTop()/scaleFactor);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(bkg, 0, 0, paint);

        overlay = FastBlur.doBlur(overlay, (int)radius, true);
        view.setBackgroundDrawable(new BitmapDrawable(getResources(), overlay));
       Log.w("nqy",System.currentTimeMillis() - startMs + "ms");
    }
}
