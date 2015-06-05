package com.example.demoproject.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.example.demoproject.R;

/**
 * Created by abner on 11/5/14.
 */
public class ListFliper extends Activity {
    ListView enList,cnList;
    private static final String[] ENLIST = new String[]{"one","two","three"};
    private static final String[] CNLIST = new String[]{"1","2","3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_fliper);

        enList = (ListView) findViewById(R.id.en_list);
        cnList = (ListView) findViewById(R.id.cn_list);

        ListAdapter adapterEn = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ENLIST);
        ListAdapter adapterCn = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,CNLIST);

        enList.setAdapter(adapterEn);
        cnList.setAdapter(adapterCn);
        cnList.setRotationY(-90f);

        findViewById(R.id.flip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flip();
            }
        });
    }

    private void flip() {
        final View visibleView;
        final View invisibleView;
        if(enList.getVisibility() == View.GONE){
            visibleView = cnList;
            invisibleView = enList;
        }else {
            visibleView = enList;
            invisibleView = cnList;
        }

        ObjectAnimator viToinvis = ObjectAnimator.ofFloat(visibleView,"rotationY",0f,90f).setDuration(500);
        final ObjectAnimator invisTovi = ObjectAnimator.ofFloat(invisibleView,"rotationY",-90f,0f).setDuration(500);

        viToinvis.setInterpolator(new AccelerateInterpolator());
        invisTovi.setInterpolator(new DecelerateInterpolator());

        viToinvis.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                visibleView.setVisibility(View.GONE);
                invisTovi.start();
                invisibleView.setVisibility(View.VISIBLE);
            }
        });

        viToinvis.start();

    }
}
