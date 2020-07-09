package id.my.avmmartin.goldexperience.data.model;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.Calendar;

import id.my.avmmartin.goldexperience.data.manager.UserManager;

public class User {
    private static final String ID = UserManager.ID;
    private static final String EMAIL = UserManager.EMAIL;
    private static final String PASSWORD = UserManager.PASSWORD;
    private static final String FULLNAME = UserManager.FULLNAME;
    private static final String BIRTHDAY = UserManager.BIRTHDAY;
    private static final String PHONE = UserManager.PHONE;
    private static final String USERTYPE = UserManager.USERTYPE;
    private static final String SEX = UserManager.SEX;

    private int id;
    private String email;
    private String password;
    private String fullName;
    private Calendar birthday;
    private String phone;
    private boolean userTypeVIP;
    private boolean sexMale;

    public boolean isValidPassword(String password) {
        return getPassword().equals(password);
    }

    // database-related method

    public User(Cursor cursor) {
        setId(cursor.getInt(cursor.getColumnIndex(ID)));
        setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
        setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
        setFullName(cursor.getString(cursor.getColumnIndex(FULLNAME)));

        Calendar birthday = Calendar.getInstance();
        birthday.setTimeInMillis(cursor.getLong(cursor.getColumnIndex(BIRTHDAY)));
        setBirthday(birthday);

        setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
        setUserTypeVIP(cursor.getInt(cursor.getColumnIndex(USERTYPE)) == 1);
        setSexMale(cursor.getInt(cursor.getColumnIndex(SEX)) == 1);
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(EMAIL, getEmail());
        contentValues.put(PASSWORD, getPassword());
        contentValues.put(FULLNAME, getFullName());
        contentValues.put(BIRTHDAY, getBirthday().getTimeInMillis());
        contentValues.put(PHONE, getPhone());
        contentValues.put(USERTYPE, isUserTypeVIP() ? 1 : 0);
        contentValues.put(SEX, isSexMale() ? 1 : 0);

        return contentValues;
    }

    // constructor

    public User(
        String email, String password, String fullName,
        Calendar birthday, String phone, boolean userTypeVIP, boolean sexMale
    ) {
        setEmail(email);
        setPassword(password);
        setFullName(fullName);
        setBirthday(birthday);
        setPhone(phone);
        setUserTypeVIP(userTypeVIP);
        setSexMale(sexMale);
    }

    // getter

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    private String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isUserTypeVIP() {
        return userTypeVIP;
    }

    public boolean isSexMale() {
        return sexMale;
    }

    // setter

    private void setId(int id) {
        this.id = id;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    private void setPhone(String phone) {
        this.phone = phone;
    }

    private void setUserTypeVIP(boolean userTypeVIP) {
        this.userTypeVIP = userTypeVIP;
    }

    private void setSexMale(boolean sexMale) {
        this.sexMale = sexMale;
    }
}
