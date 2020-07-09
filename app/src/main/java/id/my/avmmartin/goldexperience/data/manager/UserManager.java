package id.my.avmmartin.goldexperience.data.manager;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import id.my.avmmartin.goldexperience.data.model.User;
import id.my.avmmartin.goldexperience.exception.DuplicateUserException;
import id.my.avmmartin.goldexperience.exception.UserNotFoundException;

public class UserManager {
    static final String TABLE_NAME = "users";
    public static final int VERSION = 1;

    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String FULLNAME = "fullname";
    public static final String BIRTHDAY = "birthday";
    public static final String PHONE = "phone";
    public static final String USERTYPE = "usertype";
    public static final String SEX = "sex";

    // create read update

    public void insertNewUser(User user) throws DuplicateUserException {
        try {
            db.insertOrThrow(TABLE_NAME, null, user.toContentValues());
        } catch (SQLiteConstraintException e) {
            throw new DuplicateUserException();
        }
    }

    public User getUserById(int id) {
        String selection = (
            ID + " = ?"
        );
        String[] selectionArgs = {
            Integer.toString(id)
        };

        try (Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
            cursor.moveToFirst();
            return new User(cursor);
        }
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        String selection = (
            EMAIL + " = ?"
        );
        String[] selectionArgs = {
            email
        };

        try (Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
            cursor.moveToFirst();
            return new User(cursor);

        } catch (CursorIndexOutOfBoundsException e) {
            throw new UserNotFoundException();
        }

    }

    public void updateUser(int userId, User user) {
        String whereClause = (
            ID + " = ?"
        );
        String[] whereArgs = {
            Integer.toString(userId)
        };

        db.update(TABLE_NAME, user.toContentValues(), whereClause, whereArgs);
    }

    // overridden method

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EMAIL + " TEXT UNIQUE, "
                + PASSWORD + " TEXT, "
                + FULLNAME + " TEXT, "
                + BIRTHDAY + " INTEGER, "
                + PHONE + " TEXT, "
                + USERTYPE + " INTEGER, "
                + SEX + " INTEGER"
                + ");"
        );
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL(
                "DROP TABLE IF EXISTS " + TABLE_NAME + ";"
            );
            onCreate(db);
        }
    }

    // constructor

    private SQLiteDatabase db;

    public UserManager(SQLiteDatabase db) {
        this.db = db;
    }
}
