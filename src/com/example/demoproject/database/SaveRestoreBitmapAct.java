package com.example.demoproject.database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.demoproject.R;

import java.io.ByteArrayOutputStream;

/**
 * Created by abner on 6/30/15.
 */
public class SaveRestoreBitmapAct extends Activity implements View.OnClickListener {
    ImageView iv;
    DBHelper dbHelper;
    String dbName = "bm_stuff.db";
    String table = "bm_save_restore";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        dbHelper = new DBHelper(this,dbName,null,1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_retore_bitmap);

        iv = (ImageView) findViewById(R.id.myiv);
        findViewById(R.id.save).setOnClickListener(this);
        findViewById(R.id.restore).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.save){
            saveBitmap2Db();
        }else if(view.getId() == R.id.restore){
            restore();
        }
    }

    private void restore() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(table, null, null, null, null, null, null);
        if(cursor != null && cursor.getCount() > 0 && cursor.moveToNext()){
            byte[] thumb = cursor.getBlob(cursor.getColumnIndex("thumb"));
            iv.setImageBitmap(BitmapFactory.decodeByteArray(thumb,0,thumb.length));
        }else{
            Toast.makeText(mContext,"cursor is null or size is 0",Toast.LENGTH_SHORT).show();
        }
    }

    private void saveBitmap2Db() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.car);
        byte[] thumb = bitmapToByte(bm);
        contentValues.put("thumb",thumb);

        long ic = db.insert(table,null,contentValues);

        Toast.makeText(mContext,"insert, size is "+ic,Toast.LENGTH_SHORT).show();
    }

    byte[] bitmapToByte(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,100,baos);
        return baos.toByteArray();
    }

    class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table "+table+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,thumb BLOB)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        }
    }
}
