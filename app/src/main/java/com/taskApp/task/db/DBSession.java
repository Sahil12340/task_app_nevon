package com.taskApp.task.db;

import android.content.Context;
import android.util.Log;

import java.io.File;

public class DBSession {
    public DBSession() {
    }

    public DaoSession getSession(Context context) {
        File path = new File("/data/data/" + context.getPackageName() + "/databases/task-db");

        path.getParentFile().mkdirs();
        Log.d("Debug", "onCreate: " + path.getAbsolutePath());
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, path.getAbsolutePath(), null);

        android.database.sqlite.SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession session = daoMaster.newSession();
        return session;
    }
}
