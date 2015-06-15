package com.example.demoproject.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by abner on 6/12/15.
 */
public class PlayHistoryDatabase {
    public static final class PLAYHISTORY_DB implements BaseColumns {
        public static final Uri CONTENT_URI = Uri
                .parse("content://com.example.demoproject.content_provider/play_history_db");
        public static final String _ID = "_id";
        public static final String URL = "url";
        public static final String TITLE = "title";
        public static final String CURRENT_TIME = "current_play_time";
        public static final String SAVE_TIME = "save_time";
        public static final String VIDEO_CAPTURE = "video_capture";
        public static final String TOTAL_TIME = "total_time";
        public static final String CURRENT_PART_TIME = "current_part_time";
        public static final String CURRENT_PART = "current_part";
        public static final String PREV_TOTAL_TIME = "prev_total_time";
        public static final String CURRENT_FORMAT = "current_format";
        public static final String CREATE_TABLE = "CREATE TABLE play_history_db(_id INTEGER PRIMARY KEY AUTOINCREMENT"
                + ",url TEXT,title TEXT,current_play_time INTEGER,current_part_time INTEGER,current_part INTEGER,prev_total_time INTEGER,current_format TEXT,total_time TEXT,save_time LONG,video_capture BLOB);";
    }
}

