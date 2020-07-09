package id.my.avmmartin.goldexperience.data.model;

import org.json.JSONException;
import org.json.JSONObject;

import id.my.avmmartin.goldexperience.data.PlaceManager;

public class Place {
    private static final String ID = PlaceManager.ID;
    private static final String NAME = PlaceManager.NAME;
    private static final String DESCRIPTION = PlaceManager.DESCRIPTION;
    private static final String RATING = PlaceManager.RATING;
    private static final String LATITUDE = PlaceManager.LATITUDE;
    private static final String LONGITUDE = PlaceManager.LONGITUDE;

    private int id;
    private String name;
    private double rating;
    private String desc;
    private double longitude;
    private double latitude;

    @Override
    public String toString() {
        return getName();
    }

    // constructor

    public Place(String name, int rating, String desc, double longitude, double latitude) {
        setName(name);
        setRating(rating);
        setDesc(desc);
        setLongitude(longitude);
        setLatitude(latitude);
    }

    public Place(JSONObject jsonObject) throws JSONException {
        setId(jsonObject.getInt(ID));
        setName(jsonObject.getString(NAME));
        setRating(jsonObject.getDouble(RATING));
        setDesc(jsonObject.getString(DESCRIPTION));
        setLongitude(jsonObject.getDouble(LONGITUDE));
        setLatitude(jsonObject.getDouble(LATITUDE));
    }

    // getter

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public String getDesc() {
        return desc;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    // setter

    private void setId(int id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setRating(double rating) {
        this.rating = rating;
    }

    private void setDesc(String desc) {
        this.desc = desc;
    }

    private void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
