package com.example.demoproject.content_provider;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.demoproject.R;

/**
 * Created by abner on 6/12/15.
 */
public class ProviderTestAct extends Activity implements View.OnClickListener {
    TextView text;
    EditText title,url;
    private Context mContxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provider_test);
        mContxt = this;

        findViewById(R.id.query).setOnClickListener(this);
        findViewById(R.id.insert).setOnClickListener(this);
        findViewById(R.id.update).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        text = (TextView) findViewById(R.id.text);
        title = (EditText) findViewById(R.id.title);
        url = (EditText) findViewById(R.id.url);
    }

    @Override
    public void onClick(View view) {
        Uri uri = Uri.parse("content://com.example.demoproject.provider/play_history_db");
        switch (view.getId()){
            case R.id.query:
                text.setText("");
                Cursor cursor = mContxt.getContentResolver().query(uri,null,null,null,null);
                if(cursor != null){
                    while(cursor.moveToNext()){
                        String title = cursor.getString(cursor.getColumnIndex("title"));
                        String url = cursor.getString(cursor.getColumnIndex("url"));
                        text.append(title + "," + url+"\r\n");
                    }
                }else{
                    text.setText("query fail");
                }
                break;
            case R.id.insert:
                ContentValues values = new ContentValues();
                values.put("title",title.getText().toString());
                values.put("url",url.getText().toString());
                Uri retUri = mContxt.getContentResolver().insert(uri,values);
                if(retUri != null){
                    Toast.makeText(mContxt,"OK! insert "+ ContentUris.parseId(retUri),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContxt,"Fail!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.update:
                ContentValues values2 = new ContentValues();
                values2.put("url",url.getText().toString());
                String where = "title = '"+title.getText().toString()+"'";
                int c = mContxt.getContentResolver().update(uri,values2,where,null);
                Toast.makeText(mContxt,"update "+c,Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                String where2 = "title = ?";
                String[] whereArgs = {title.getText().toString()};
                int cs = mContxt.getContentResolver().delete(uri,where2,whereArgs);
                Toast.makeText(mContxt,"delete "+cs,Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
