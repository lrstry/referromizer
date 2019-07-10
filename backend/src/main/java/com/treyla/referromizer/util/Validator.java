package com.lrstry.referromizer.util;

import org.apache.commons.validator.routines.UrlValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean isValidUrl(String url) {

        String[] validSchemes = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(validSchemes);

        if (!urlValidator.isValid(url)) {
            return false;
        }
        return true;

    }

    public static boolean isValidUrl(String url, String regex) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);

        if (!matcher.matches()) {
            return false;
        }
        return true;

    }

    public static boolean isValidProviderName(String providerName) {

        if (!providerName.matches("^[a-zA-Z0-9.]{1,255}$")) {
            return false;
        }
        return true;

    }

}
