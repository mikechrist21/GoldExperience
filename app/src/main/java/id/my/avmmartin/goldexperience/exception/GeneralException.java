package id.my.avmmartin.goldexperience.exception;

import id.my.avmmartin.goldexperience.R;

public class GeneralException extends Exception {
    private int resId;

    public int getErrorId() {
        return resId;
    }

    // constructor

    public GeneralException(String message) {
        super(message);
        this.resId = R.string.general_error;
    }

    public GeneralException(String message, int resId) {
        super(message);
        this.resId = resId;
    }
}
