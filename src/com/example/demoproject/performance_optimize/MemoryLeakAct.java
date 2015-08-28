package com.example.demoproject.performance_optimize;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.demoproject.R;

import java.util.Random;

/**
 * Created by abner on 8/18/15.
 */
public class MemoryLeakAct extends Activity implements View.OnClickListener {
    TextView greetingTv;
    Handler mHandler;
    int count = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_leak);

        greetingTv = (TextView) findViewById(R.id.greeting);
        findViewById(R.id.bt1).setOnClickListener(this);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
//                greetingTv.setText("Greeting "+count);
                Log.d("nqy", "Greeting... " + msg.arg1);
                sendGreeting();
            }
        };
    }

    @Override
    public void onClick(View view) {
        sendGreeting();
    }

    private void sendGreeting() {
        Message msg = new Message();
        Random random = new Random();
        msg.arg1 = random.nextInt(100);
        mHandler.sendMessageDelayed(msg,1000);
    }
}
