package com.example.demoproject.animation.property_anim;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.demoproject.R;

/**
 * Created by abner on 7/30/15.
 */
public class QQ5_SlideMenu_Act extends Activity {
    private XCSlideMenu xcSlideMenu;
    private TextView btnSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qq5_main);
        xcSlideMenu = (XCSlideMenu) findViewById(R.id.slideMenu);
        btnSwitch = (TextView)findViewById(R.id.btnSwitch);
        btnSwitch.setClickable(true);
        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xcSlideMenu.switchMenu();
            }
        });
    }
}
