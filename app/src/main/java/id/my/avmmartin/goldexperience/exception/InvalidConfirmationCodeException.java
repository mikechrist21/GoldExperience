package id.my.avmmartin.goldexperience.exception;

import id.my.avmmartin.goldexperience.R;

public class InvalidConfirmationCodeException extends GeneralException {
    public InvalidConfirmationCodeException() {
        super("Invalid confirmation code", R.string.warning_invalid_confirmation);
    }
}
