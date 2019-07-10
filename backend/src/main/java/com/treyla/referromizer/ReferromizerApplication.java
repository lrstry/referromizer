package com.lrstry.referromizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReferromizerApplication {

    public static void main(String[] args) {

        String port = System.getenv().get("PORT");
        String dyno = System.getenv().get("DYNO");

        if (port != null && dyno != null) {
            System.getProperties().put("server.port", port);
        }

        SpringApplication.run(ReferromizerApplication.class, args);

    }
}
