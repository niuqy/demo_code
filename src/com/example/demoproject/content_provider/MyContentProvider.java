package com.example.demoproject.content_provider;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.example.demoproject.database.PlayHistoryDatabase;

import java.io.Serializable;

/**
 * Created by abner on 6/12/15.
 */
public class MyContentProvider extends ContentProvider {
    private PlayHistoryDBHelper dbHelper;
    private int currentVersion = 1;
    private String dbName = "play_history_db";
    private static UriMatcher matcher;
    private static String URI_AUTHORITY = "com.example.demoproject.provider";
    private final static int PLAYHISTORY = 1;
    private final static int PLAYHISTORY_ID = 2;

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(URI_AUTHORITY,"play_history_db",PLAYHISTORY);
        matcher.addURI(URI_AUTHORITY,"play_history_db/#", PLAYHISTORY_ID);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new PlayHistoryDBHelper(getContext(),dbName,currentVersion);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (matcher.match(uri)){
            case PLAYHISTORY:
                cursor = db.query(dbName,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case PLAYHISTORY_ID:
                break;
            default:
                throw new IllegalArgumentException("unknown uri "+uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        if(uri != null && contentValues != null){
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            long count ;
            switch (matcher.match(uri)){
                case PLAYHISTORY:
                    count = db.insert(dbName,null,contentValues);
                    break;
                default:
                    throw new IllegalArgumentException("unknown uri "+uri);
            }
            if(count > 0){
                Uri retUri = ContentUris.withAppendedId(PlayHistoryDatabase.PLAYHISTORY_DB.CONTENT_URI,count);
                retUri.buildUpon().build();
                getContext().getContentResolver().notifyChange(retUri,null);
                return retUri;
            }
            throw new SQLiteException("Failed to insert row into " + uri);
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String whereClause, String[] whereArgs) {
        int count = -1;
        if(uri != null) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            switch (matcher.match(uri)){
                case PLAYHISTORY:
                    count = db.delete(dbName,whereClause,whereArgs);
                    break;
                case PLAYHISTORY_ID:
                    break;
                default:
                    throw new IllegalArgumentException("unknown uri "+uri);
            }

        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String whereClause, String[] whereArgs) {
        int count = -1;
        if(uri != null) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            switch (matcher.match(uri)){
                case PLAYHISTORY:
                    count = db.update(dbName,contentValues,whereClause,whereArgs);
                    break;
                case PLAYHISTORY_ID:
                    count = db.update(dbName,contentValues
                    ,PlayHistoryDatabase.PLAYHISTORY_DB._ID + "=" + uri.getPathSegments().get(1) + (!TextUtils.isEmpty(whereClause) ? " AND (" + whereClause + ')' :"")
                    ,whereArgs);
                    break;
                default:
                    throw new IllegalArgumentException("unknown uri "+uri);
            }
        }
        return count;
    }

    public class PlayHistoryDBHelper extends SQLiteOpenHelper{

        public PlayHistoryDBHelper(Context context,String dbName,int version) {
            super(context, dbName, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(PlayHistoryDatabase.PLAYHISTORY_DB.CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            switch (oldVersion){
                /**
                 * notice here:there is no "break" after each "case",because being compatible for different version
                 */
                case 1:
                case 2:
                    default:
            }
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            super.onDowngrade(db, oldVersion, newVersion);
        }
    }
}
