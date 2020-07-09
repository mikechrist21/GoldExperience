package id.my.avmmartin.goldexperience.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class MessageUtils {
    private SmsManager smsManager;
    private String confirmationCode;

    // singleton style

    private static MessageUtils instance = new MessageUtils();

    public static MessageUtils getInstance() {
        return instance;
    }

    private MessageUtils() {
        smsManager = SmsManager.getDefault();
    }

    // private method

    private void generateConfirmationCode() {
        confirmationCode = (
            Integer.toString(new Random().nextInt(10))
                + Integer.toString(new Random().nextInt(10))
                + Integer.toString(new Random().nextInt(10))
                + Integer.toString(new Random().nextInt(10))
        );
    }

    private void sendMessage(String phoneNumber, String message) throws SecurityException {
        smsManager.sendTextMessage(
            phoneNumber,
            null,
            message,
            null,
            null
        );
    }

    // public method

    public void requestPermission(Activity activity) {
        int sendSMSPermission = ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.SEND_SMS
        );

        if(sendSMSPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                activity,
                new String[]{
                    Manifest.permission.SEND_SMS
                },
                1
            );
        }
    }

    public void sendConfirmationCode(String phoneNumber) throws SecurityException {
        generateConfirmationCode();

        sendMessage(
            phoneNumber,
            "Your Gold Experience confirmation code is " + confirmationCode + "."
        );
    }

    public boolean confirmCode(String code) {
        return confirmationCode.equals(code);
    }

    public void welcomeMessage(String fullname, String phoneNumber) throws SecurityException {
        sendMessage(
            phoneNumber,
            "Hi " + fullname + ". Your Gold Experience account is registered!"
        );
    }
}
