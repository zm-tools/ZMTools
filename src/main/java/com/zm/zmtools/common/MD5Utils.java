package com.zm.zmtools.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * <p><b>类说明摘要</b></p>
 * @ClassName MD5Utils
 * @Author MengMeng 
 * @Date 2018/9/1 </p>
 * @Version: 0.1
 * @Since JDK 1.80_171
 */
public class MD5Utils { public static String MD5(String inStr) {
    return MD532(inStr);
}

    public static String MD532(String pwd){
        return encryption(pwd);
    }
    /**
     *
     * @param plainText
     *            明文
     * @return 32位密文
     */
    public static String encryption(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }
}
