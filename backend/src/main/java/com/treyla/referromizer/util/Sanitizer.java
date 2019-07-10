package com.lrstry.referromizer.util;

public class Sanitizer {

    public static String sanitizeUrl(String url) {

        url = url.trim();

        if (!url.matches("^(http|https)://.*$")) {
            return "http://" + url;
        } else {
            return url;
        }

    }

    public static String sanitizeRequestedProviderName(String name) {
        return name.trim();
    }

}
