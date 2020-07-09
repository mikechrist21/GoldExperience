package id.my.avmmartin.goldexperience;

import android.app.Application;

import id.my.avmmartin.goldexperience.data.DataManager;

public class GoldExperience extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        dataManager = new DataManager(this);
    }

    // getter

    private DataManager dataManager;

    public DataManager getDataManager() {
        return dataManager;
    }
}
