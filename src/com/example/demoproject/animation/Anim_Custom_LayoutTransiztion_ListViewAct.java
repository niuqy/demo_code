package com.example.demoproject.animation;

import android.animation.*;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by abner on 10/31/14.
 */
public class Anim_Custom_LayoutTransiztion_ListViewAct extends ListActivity{
    private static final int DURATION = 500;
    private List<Map<String,String>> datas;
    SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        datas = new ArrayList<Map<String, String>>();
        for(int i=0;i<10;i++){
            Map<String,String> d = new HashMap<String, String>();
            d.put("name","name "+i);
            datas.add(d);
        }


        adapter = new SimpleAdapter(this,datas, android.R.layout.simple_list_item_1,new String[]{"name"},new int[]{android.R.id.text1});
        setListAdapter(adapter);

        LayoutTransition lt = new LayoutTransition();
        getListView().setLayoutTransition(lt);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startAnimation(view,i);
            }
        });
    }

    private void startAnimation(final View view, final int position) {
        int height = view.getHeight();
        ObjectAnimator animX = ObjectAnimator.ofFloat(view,"x",view.getX(),view.getX()-view.getWidth()).setDuration(DURATION);

        final ViewGroup.LayoutParams lp = view.getLayoutParams();
        ValueAnimator animH = ValueAnimator.ofInt(view.getHeight(),0);
        animH.setDuration(DURATION);

        animH.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int h = (Integer)valueAnimator.getAnimatedValue();
                lp.height = h;
                view.setLayoutParams(lp);
            }
        });

        animH.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                datas.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        AnimatorSet anim = new AnimatorSet();
        anim.playSequentially(animX,animH);

        anim.start();
    }

}
