package com.example.demoproject.custom_view;

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
 * Created by abner on 6/5/15.
 */
public class ViewDemoAct extends ListActivity {
    static String[] activities = {
            "blur.BlurTestAct"
            ,"scroll_view.ScrollTestAct"
            ,"Custom_Atrt_Act"
            ,"MatricsDemoAct"
            ,"MatricsAct2"
            ,"ViewPagerMorePageOnScreenAct"
    };
    static List<Map<String,String>> data = new ArrayList<Map<String, String>>();
    static {
        for(int i=0;i<activities.length;i++){
            Map<String,String> map = new HashMap<String, String>();
            map.put("activity",activities[i]);
            data.add(map);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleAdapter adapter = new SimpleAdapter(this,data,android.R.layout.simple_list_item_1,new String[]{"activity"},new int[]{android.R.id.text1});
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String activity = null;
                if(view instanceof TextView){
                    TextView textView = (TextView) view;
                    activity= (String) textView.getText();
                    Log.d("nqy", "activity:" + activity);
                }
                if(activity != null){
                    try {
                        Intent it = new Intent(ViewDemoAct.this,Class.forName("com.example.demoproject.custom_view." + activity));
                        adapterView.getContext().startActivity(it);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
}
