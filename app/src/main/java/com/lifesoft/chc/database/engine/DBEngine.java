package com.lifesoft.chc.database.engine;

import android.content.Context;

public class DBEngine {
    private static DBEngine INSTANCE = null;
    private DBService service=null;

    private DBEngine() {
    }

    public static DBEngine INSTANCE() {
        return INSTANCE == null ? INSTANCE = new DBEngine() : INSTANCE;
    }
    public DBService getServices(Context context){
        return (service==null)?service=new DBService(context):service;
    }
}
