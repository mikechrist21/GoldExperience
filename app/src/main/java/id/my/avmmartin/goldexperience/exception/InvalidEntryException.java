package id.my.avmmartin.goldexperience.exception;

public class InvalidEntryException extends GeneralException {
    public InvalidEntryException(int resId) {
        super("Invalid entry", resId);
    }
}
