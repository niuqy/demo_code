package com.example.demoproject.service_started_bound;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by abner on 6/2/15.
 */
public class ServiceDemo extends ListActivity {
    int screenWidth;
    static String[] activities = {
            "MessengerAct"
            ,"AIDLAct"
    };
    static List<Map<String,String>> data = new ArrayList<Map<String, String>>();
    static {
        for(int i=0;i<activities.length;i++){
            Map<String,String> map = new HashMap<String, String>();
            map.put("activity",activities[i]);
            data.add(map);
        }
    }

    private LayoutTransition layoutTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleAdapter adapter = new SimpleAdapter(this,data,android.R.layout.simple_list_item_1,new String[]{"activity"},new int[]{android.R.id.text1});
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String activity = null;
                if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    activity = (String) textView.getText();
                    Log.d("nqy", "activity:" + activity);
                }
                if (activity != null) {
                    try {
                        Intent it = new Intent(ServiceDemo.this, Class.forName("com.example.demoproject.service_started_bound." + activity));
                        adapterView.getContext().startActivity(it);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        screenWidth = getResources().getDisplayMetrics().widthPixels;

        //LayoutTransition is not for the animation of children of ViewGroup,it is for other using
        getListView().setLayoutTransition(getLayoutTransition());
    }

    public LayoutTransition getLayoutTransition() {
        LayoutTransition layoutTransition = new LayoutTransition();
        Animator appearingAnim = ObjectAnimator.ofFloat(null, "translationX", screenWidth * 1.0f, 0.0f);
        layoutTransition.setAnimator(LayoutTransition.APPEARING,appearingAnim);
        return layoutTransition;
    }
}
