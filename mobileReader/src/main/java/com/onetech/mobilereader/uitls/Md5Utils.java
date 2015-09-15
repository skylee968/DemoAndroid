package com.onetech.mobilereader.uitls;

import java.security.MessageDigest;

/**
 * Created by thienlm on 8/23/2015.
 */
public class Md5Utils {
    public static final String md5(final String data) {
        String md5 = null;

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(data.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            md5 = hexString.toString();

        } catch (Exception e) {
            // Logger
            // e.printStackTrace();
        }
        return md5;
    }
}
