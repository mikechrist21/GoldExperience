package id.my.avmmartin.goldexperience.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import id.my.avmmartin.goldexperience.data.manager.PlanManager;
import id.my.avmmartin.goldexperience.data.manager.UserManager;
import id.my.avmmartin.goldexperience.utils.Constants;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DB_NAME = Constants.DB_NAME;
    private static final int DB_VERSION = PlanManager.VERSION + UserManager.VERSION;

    private PlanManager planManager;
    private UserManager userManager;

    @Override
    public void onCreate(SQLiteDatabase db) {
        UserManager.onCreate(db);
        PlanManager.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        UserManager.onUpgrade(db, oldVersion, newVersion);
        PlanManager.onUpgrade(db, oldVersion, newVersion);
    }

    // constructor

    private SQLiteDatabase db;

    DatabaseManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        SQLiteDatabase db = getWritableDatabase();

        planManager = new PlanManager(db);
        userManager = new UserManager(db);
    }

    // getter

    PlanManager getPlanManager() {
        return planManager;
    }

    UserManager getUserManager() {
        return userManager;
    }
}
