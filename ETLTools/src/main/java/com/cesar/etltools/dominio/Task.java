package com.cesar.etltools.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private int id;
    private String description;
    private long initialDelay;
    private long period;
    private String unit;

    protected Task() {
    }

    public Task(int id, String description, long initialDelay, long period, String unit) {
        this.id = id;
        this.description = description;
        this.initialDelay = initialDelay;
        this.period = period;
        this.unit = unit;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getInitialDelay() {
        return initialDelay;
    }

    public void setInitialDelay(long initialDelay) {
        this.initialDelay = initialDelay;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
