package com.lrstry.referromizer.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "provider")
public class Provider {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private String name;
    private String url;
    private String refIdRegex;
    private String refUrlRegex;

    Provider() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getRefUrlRegex() {
        return refUrlRegex;
    }

    public Matcher getRefUrlMatcher(String refUrl) {
        Pattern pattern = Pattern.compile(refUrlRegex);
        return pattern.matcher(refUrl);
    }

}
