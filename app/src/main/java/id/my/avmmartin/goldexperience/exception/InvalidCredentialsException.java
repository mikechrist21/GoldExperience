package id.my.avmmartin.goldexperience.exception;

import id.my.avmmartin.goldexperience.R;

public class InvalidCredentialsException extends GeneralException {
    public InvalidCredentialsException() {
        super("Invalid credentials", R.string.warning_not_match_account);
    }
}
