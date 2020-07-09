package id.my.avmmartin.goldexperience.exception;

public class EmptyEntryException extends GeneralException {
    public EmptyEntryException(int resId) {
        super("Empty edit text", resId);
    }
}
