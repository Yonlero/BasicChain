package org.basicchain.util;

import java.security.MessageDigest;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.security.MessageDigest.getInstance;

public class StringUtil {
    public static String applySha256(final String input) {
        try {
            MessageDigest digest = getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}