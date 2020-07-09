package id.my.avmmartin.goldexperience.exception;

public class UserNotFoundException extends GeneralException {
    public UserNotFoundException() {
        super("User not found");
    }
}
