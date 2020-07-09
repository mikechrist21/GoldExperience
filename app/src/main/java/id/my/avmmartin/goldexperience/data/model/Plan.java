package id.my.avmmartin.goldexperience.data.model;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.Calendar;

import id.my.avmmartin.goldexperience.data.manager.PlanManager;

public class Plan {
    private static final String ID = PlanManager.ID;
    private static final String FK_PLACE_ID = PlanManager.FK_PLACE_ID;
    private static final String FK_USER_ID = PlanManager.FK_USER_ID;
    private static final String NAME = PlanManager.NAME;
    private static final String DATE = PlanManager.DATE;
    private static final String TIME = PlanManager.TIME;
    private static final String NOTE = PlanManager.NOTE;

    private int id;
    private int fkPlaceId;
    private int fkUserId;
    private String name;
    private Calendar date;
    private Calendar time;
    private String note;

    // database-related method

    public Plan(Cursor cursor) {
        setId(cursor.getInt(cursor.getColumnIndex(ID)));
        setFkPlaceId(cursor.getInt(cursor.getColumnIndex(FK_PLACE_ID)));
        setFkUserId(cursor.getInt(cursor.getColumnIndex(FK_USER_ID)));

        setName(cursor.getString(cursor.getColumnIndex(NAME)));

        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(cursor.getLong(cursor.getColumnIndex(DATE)));
        setDate(date);

        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(cursor.getLong(cursor.getColumnIndex(TIME)));
        setTime(time);

        setNote(cursor.getString(cursor.getColumnIndex(NOTE)));
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(FK_PLACE_ID, getFkPlaceId());
        contentValues.put(FK_USER_ID, getFkUserId());
        contentValues.put(NAME, getName());
        contentValues.put(DATE, getDate().getTimeInMillis());
        contentValues.put(TIME, getTime().getTimeInMillis());
        contentValues.put(NOTE, getNote());

        return contentValues;
    }

    // constructor

    public Plan(int fkPlaceId, int fkUserId, String name, Calendar date, Calendar time, String note) {
        setFkPlaceId(fkPlaceId);
        setFkUserId(fkUserId);

        setName(name);
        setDate(date);
        setTime(time);
        setNote(note);
    }

    // getter

    public int getId() {
        return id;
    }

    public int getFkPlaceId() {
        return fkPlaceId;
    }

    public int getFkUserId() {
        return fkUserId;
    }

    public String getName() {
        return name;
    }

    public Calendar getDate() {
        return date;
    }

    public Calendar getTime() {
        return time;
    }

    public String getNote() {
        return note;
    }

    // setter

    private void setId(int id) {
        this.id = id;
    }

    private void setFkPlaceId(int fkPlaceId) {
        this.fkPlaceId = fkPlaceId;
    }

    private void setFkUserId(int fkUserId) {
        this.fkUserId = fkUserId;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setDate(Calendar date) {
        this.date = date;
    }

    private void setTime(Calendar time) {
        this.time = time;
    }

    private void setNote(String note) {
        this.note = note;
    }
}
