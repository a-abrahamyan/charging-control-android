package com.lifesoft.chc.database.engine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.lifesoft.chc.database.engine.DBConstants.DB_NAME;
import static com.lifesoft.chc.database.engine.DBConstants.VERSION;

public class DBService extends SQLiteOpenHelper {
    public DBService(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
