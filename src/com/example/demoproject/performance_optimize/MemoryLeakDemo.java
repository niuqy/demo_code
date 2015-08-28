package com.example.demoproject.performance_optimize;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.lang.ref.WeakReference;


/**
 * Created by abner on 8/19/15.
 */
public class MemoryLeakDemo extends Activity{
    private static Drawable sBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bitmap bm = null;
        WeakReference<Bitmap> bitmapWeakReference = new WeakReference<Bitmap>(bm);
        bm = bitmapWeakReference.get();

        Hi hi = new AnonymousClass(3) {
            @Override
            public void sayHi() {
                System.out.println("this is hi from id "+id);
            }
        };
    }

    abstract class AnonymousClass implements Hi{
        int id;
        AnonymousClass(int id){
            this.id = id;
        }
    }

    interface Hi{
        void sayHi();
    }
}