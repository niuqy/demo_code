package com.example.demoproject.animation;

import android.animation.*;
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
public class AnimDemo extends ListActivity {
    int screenWidth;
    static String[] activities = {
            "property_anim.QQ5ImitateAct"
            ,"property_anim.QQ5_SlideMenu_Act"
            ,"Anim_Custom_LayoutTransiztion_ListViewAct"
    ,"Anim_LayoutTransition_hideshow"
    ,"Anim_Test_Icon_Test_Act"
    ,"Animation_CloningAct"
    ,"AnimationLoading"
    ,"AnimMultiPropertiesAct"
    ,"BouncingBalls"
    ,"Custome_Evaluator"
    ,"ListFliper"
    ,"TranslationTest"
    ,"ViewPropertyAnimatorTest"
    ,"BasicTestAct"
    ,"DrawableAnimAct"
    ,"ViewAnimAct"
    ,"view_anim.SetAnimForActivityAct"
    ,"view_anim.ViewAnimationUseAct"
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
                        Intent it = new Intent(AnimDemo.this, Class.forName("com.example.demoproject.animation." + activity));
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
