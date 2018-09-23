package com.lifesoft.chc.database.engine;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lifesoft.chc.view.sms.model.SmsModel;
import com.lifesoft.chc.view.sms.model.Transaction;

import java.util.List;

import static com.lifesoft.chc.database.engine.DBConstants.DB_NAME;
import static com.lifesoft.chc.database.engine.DBConstants.DB_TABLE;
import static com.lifesoft.chc.database.engine.DBConstants.ID;
import static com.lifesoft.chc.database.engine.DBConstants.TRANSACTION_DATE;
import static com.lifesoft.chc.database.engine.DBConstants.TRANSACTION_ID;
import static com.lifesoft.chc.database.engine.DBConstants.TRANSACTION_TEXT;
import static com.lifesoft.chc.database.engine.DBConstants.TRANSACTION_TYPE;
import static com.lifesoft.chc.database.engine.DBConstants.VERSION;

public class DBService extends SQLiteOpenHelper {
    private static final String TAG = DBService.class.getName();
    public DBService(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DB_TABLE + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TRANSACTION_TYPE + " TEXT NOT NULL, " +
                TRANSACTION_TEXT + " TEXT NOT NULL, " +
                TRANSACTION_DATE + " TEXT NOT NULL, " +
                TRANSACTION_ID + " TEXT NOT NULL); "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        this.onCreate(db);
    }

    //save data in database
    public void save(SmsModel model) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        List<Transaction> transactions = model.getTransactions();
        for (int index = 0; index < transactions.size(); index++) {
            cv.put(TRANSACTION_TYPE, model.getTransactions().get(index).getType());
            cv.put(TRANSACTION_TEXT, model.getTransactions().get(index).getText());
            cv.put(TRANSACTION_DATE, model.getTransactions().get(index).getDate());
            cv.put(TRANSACTION_ID, model.getTransactions().get(index).getId());
        }
        db.insert(DB_TABLE, null, cv);
        Log.i(TAG, "save: "+cv.size());
        db.close();
    }
}
