package com.dbpiper.todo;

import android.app.Application;

import org.greenrobot.greendao.database.Database;


/**
 * Created by suerg on 4/11/2017.
 */

public class App extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "todo-db");
        Database db = helper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);

        daoMaster.dropAllTables(db, true);
        daoMaster.createAllTables(db, true);

        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
