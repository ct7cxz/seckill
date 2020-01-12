package org.ct.seckill.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * md5加密工具类
 */
public class Md5Util {

    private final static String SALT = "1a2b3c4d";

    public static String md5(String password) {
        return DigestUtils.md5Hex(password);
    }

    /**
     * 将用户输入的密码进行加密
     *
     * @return
     */
    public static String inputPassToFormPass(String inputPass) {
        String str = SALT.charAt(2) + SALT.charAt(1) + inputPass + SALT.charAt(5) + SALT.charAt(0);
        return md5(str);
    }

    /**
     * 将客户端接收到的密码再一次进行加密
     * @param formPass
     * @param salt
     * @return
     */
    public static String formPassToDBPass(String formPass, String salt) {
        String str = salt.charAt(2) + salt.charAt(1) + formPass + salt.charAt(5) + salt.charAt(0);
        return md5(str);
    }

    public static String inputPassToDBPass(String password,String salt) {
        String formPass = inputPassToFormPass(password);
        return formPassToDBPass(formPass,salt);
    }

    public static void main(String[] args) {
        System.out.println(formPassToDBPass("1d5065000b5cf8123a271764a00f3523","1a2b3c4d"));
    }

}
