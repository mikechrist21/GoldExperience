package id.my.avmmartin.goldexperience.exception;

import id.my.avmmartin.goldexperience.R;

public class DuplicateUserException extends GeneralException {
    public DuplicateUserException() {
        super("Duplicate user", R.string.warning_email_duplicate);
    }
}
