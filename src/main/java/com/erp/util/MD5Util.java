package com.erp.util;

import java.security.MessageDigest;

/**
 * md5
 *
 * @author hekui
 * @since 2015年12月14日 上午10:29:08
 */
public class MD5Util {
    private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    private static final int DEFAULT_ITERATIONS = 1;

    public static String encrypt(String string) {
        try {
            byte[] bytes = string.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            byte[] updateBytes = messageDigest.digest();
            return toString(updateBytes);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param source
     * @param saltStr
     * @param hashIterations
     *            md5次数
     * @return
     */
    public static String encrypt(String source, String saltStr, int hashIterations) {
        byte[] bytes = source.getBytes();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            if (saltStr != null) {
                byte[] salt = saltStr.getBytes();
                digest.reset();
                digest.update(salt);
            }
            byte[] hashed = digest.digest(bytes);

            int iterations = hashIterations - DEFAULT_ITERATIONS;
            for (int i = 0; i < iterations; i++) {
                digest.reset();
                hashed = digest.digest(hashed);
            }
            return toString(hashed);
        } catch (Exception e) {
            return null;
        }
    }

    private static String toString(byte[] bytes) {
        int len = bytes.length;
        char myChar[] = new char[len * 2];
        int k = 0;
        for (byte b : bytes) {
            myChar[k++] = hexDigits[b >>> 4 & 0x0f];
            myChar[k++] = hexDigits[b & 0x0f];
        }
        return new String(myChar);
    }
}
