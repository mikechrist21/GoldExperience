package id.my.avmmartin.goldexperience.data;

import android.content.Context;
import android.content.SharedPreferences;

import id.my.avmmartin.goldexperience.utils.Constants;

public class PreferencesManager {
    private static final String FILENAME = "GoldExperienceSharedPrefs";
    private static final String USER_ID = "user_id";

    private static final int GUEST = Constants.GUEST;

    private SharedPreferences sharedPreferences;

    // username

    int getUserId() {
        return sharedPreferences.getInt(USER_ID, GUEST);
    }

    void setUserId(int id) {
        sharedPreferences.edit().putInt(USER_ID, id).apply();
    }

    // constructor

    PreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
    }
}
