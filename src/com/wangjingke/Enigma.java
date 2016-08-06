package com.wangjingke;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class Enigma {

    public static Key askForKey() {
        Scanner typeIn = new Scanner(System.in);
        System.out.println("Enter the key: ");
        String key = typeIn.next();
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        return aesKey;
    }

    public static String encode (String input, Key keyX) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        Key aesKey = keyX;
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);

        byte[] encrypted = cipher.doFinal(input.getBytes());
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedString = encoder.encodeToString(encrypted);
        return encryptedString;
    }

    public static String decode(String input, Key keyX) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key aesKey = keyX;
        Cipher cipher = Cipher.getInstance("AES");

        Base64.Decoder decoder = Base64.getDecoder();
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        String decrypted = new String(cipher.doFinal(decoder.decode(input)));
        return decrypted;
    }
}
