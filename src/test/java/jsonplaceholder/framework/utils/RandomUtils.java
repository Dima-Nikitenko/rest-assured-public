package jsonplaceholder.framework.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {

    public static String generateCustomRandomString(int length, boolean useLetters, boolean useNumbers) {
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    public static String generateRandomAlphabeticString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static String generateRandomAlphanumericString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

}
