package com.wangjingke;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;



public class Main {
    public static String path = "C:/Users/wangjink/Downloads/madresGpsTesting/MadresGpsTracking_MAD0808.csv";
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {
        IO.process(path);
    }

}
