package com.erp.util;

/**
 * 密码加密
 *
 * @author hekui
 * @since 2015年12月14日 上午10:28:42
 */
public class PasswordUtil {
    private static final int HASH_ITERATIONS = 1;

    public static String generatePassword(String username, String password) {
        return MD5Util.encrypt(password, username, HASH_ITERATIONS);
    }

}
