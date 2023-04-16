package org.example;

import java.util.Random;

public class RandomGenerator {
    public static String randomString(int length) {
        Random random = new Random();
        int leftLimit = 'a';
        int rightLimit = 'z';
        StringBuilder buffer = new StringBuilder(length);

        for (int i = 0; i < length; ++i) {
            int letterCode = leftLimit + (int)(random.nextFloat() * (float)(rightLimit - leftLimit + 1));
            buffer.append(Character.toChars(letterCode));
        }

        return buffer.toString();
    }
}
