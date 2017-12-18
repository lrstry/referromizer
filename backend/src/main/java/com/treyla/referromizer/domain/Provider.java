package com.treyla.referromizer.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "provider")
public class Provider {

    @Id
    private UUID id;
    private String name;
    private String url;

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

}
