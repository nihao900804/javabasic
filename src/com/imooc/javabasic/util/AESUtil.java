package com.imooc.javabasic.util;

import org.springframework.util.Base64Utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


/**
 * @author Ace
 * @create 2020-08-07
 */
public class AESUtil {

    private static final String KEY = "hmj1428ygw";
    private static final String KEY_ALGORITHM = "AES";
    /**
     * 默认的加密方式
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @return
     */
    public static String encrypt(String content) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            byte[] byteContent = content.getBytes("utf-8");

            // 初始化
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(KEY));

            byte[] result = cipher.doFinal(byteContent);
            // 加密
            return new String(Base64Utils.encode(result));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**解密
     * @param content  待解密内容
     * @return
     */
    public static String decrypt(String content) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(KEY));
            byte[] result = cipher.doFinal(Base64Utils.decode(content.getBytes()));
            return new String(result, "utf-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static Key getSecretKey(String key) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);

            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes());
            keyGenerator.init(128, secureRandom);

            SecretKey secretKey = keyGenerator.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            return new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        String content = "{\"publicuser\":\"admin-sd-jn-zrzy\",\"tk\":\"915915b4c37f14b49eca4431e3b987b9df7915b4c37f14b49eca4431e3b987b9df7915b4c37f14b49eca4431e3b987b9df7915b4c37f14b49eca4431e3b987b9df7915b4c37f14b49eca4431e3b987b9df7915b4c37f14b49eca4431e3b987b9df7b4c37f14b49eca4431e3b987b9df7\"}";
        //加密
        System.out.println("加密前：" + content);
        long timeStart = System.currentTimeMillis();
        String encryptResult = encrypt(content);
        System.out.println("加密后：" + encryptResult);
        //解密
        String decryptResult = decrypt(encryptResult);
        System.out.println("解密后：" + decryptResult);
        long timeEnd = System.currentTimeMillis();
        System.out.println("耗时(ms)："+(timeEnd - timeStart));
    }


}
