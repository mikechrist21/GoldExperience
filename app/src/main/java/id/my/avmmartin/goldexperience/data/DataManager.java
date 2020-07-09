package id.my.avmmartin.goldexperience.data;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.List;

import id.my.avmmartin.goldexperience.data.model.Place;
import id.my.avmmartin.goldexperience.data.model.Plan;
import id.my.avmmartin.goldexperience.data.model.User;
import id.my.avmmartin.goldexperience.exception.DuplicateUserException;
import id.my.avmmartin.goldexperience.exception.InvalidCredentialsException;
import id.my.avmmartin.goldexperience.exception.UserNotFoundException;
import id.my.avmmartin.goldexperience.utils.Constants;

public class DataManager {
    public void register(User user) throws DuplicateUserException {
        databaseManager.getUserManager().insertNewUser(user);
    }

    public void login(String email, String password) throws InvalidCredentialsException {
        try {
            User user = databaseManager.getUserManager().getUserByEmail(email);

            if (!user.isValidPassword(password)) {
                throw new InvalidCredentialsException();
            }

            preferencesManager.setUserId(user.getId());

        } catch (UserNotFoundException e) {
            throw new InvalidCredentialsException();
        }
    }

    public void logout() {
        preferencesManager.setUserId(Constants.GUEST);
    }

    public boolean isLoggedIn() {
        return getAppUserId() != Constants.GUEST;
    }

    public int getAppUserId() {
        return preferencesManager.getUserId();
    }

    public User getAppUser() {
        return databaseManager.getUserManager().getUserById(getAppUserId());
    }

    // place

    public void reloadOnlinePlacesData(ProgressDialog progressDialog, Runnable runnable) {
        placeManager.reloadOnlinePlacesData(progressDialog, runnable);
    }

    public List<Place> getPlaces() {
        return placeManager.getPlaces();
    }

    public Place getPlace(int placeId) {
        return placeManager.getPlaceById(placeId);
    }

    // plan

    public void insertNewPlan(Plan plan) {
        databaseManager.getPlanManager().insertNewPlan(plan);
    }

    public Plan getPlanByPosition(int position) {
        return databaseManager.getPlanManager().getPlanByUserByPosition(getAppUserId(), position);
    }

    public int planSize() {
        return databaseManager.getPlanManager().sizeByUser(getAppUserId());
    }

    public void deletePlanById(int planId) {
        databaseManager.getPlanManager().deletePlanById(getAppUserId(), planId);
    }

    // user

    public void updateUser(int userId, User user) {
        databaseManager.getUserManager().updateUser(userId, user);
    }

    // constructor

    private DatabaseManager databaseManager;
    private PreferencesManager preferencesManager;

    private PlaceManager placeManager;

    public DataManager(Context context) {
        databaseManager = new DatabaseManager(context);
        preferencesManager = new PreferencesManager(context);

        placeManager = new PlaceManager(context);
    }
}
