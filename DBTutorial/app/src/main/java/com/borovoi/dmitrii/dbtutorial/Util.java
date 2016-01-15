package com.borovoi.dmitrii.dbtutorial;

import android.util.Log;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by dimas on 1/13/2016.
 */
public class Util {

    private static final String TAG = "<Util>";

    public static String encryptPassword(String plainPassword) {
        String encryptedPassword = "";
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(plainPassword.getBytes());
            BigInteger bigInt = new BigInteger(1, m.digest());
            encryptedPassword = bigInt.toString(16);
            while (encryptedPassword.length() < 32) {
                encryptedPassword = "0" + encryptedPassword;
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, e.getMessage());
        }
        Log.d(TAG, encryptedPassword);
        return encryptedPassword;
    }

    public static int bool2int(boolean bool) {
        return !bool ? 1 : 0;
    }

    public static boolean int2bool(int val) {
        return val != 0;
    }
}
