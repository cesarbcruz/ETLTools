package com.cesar.etltools.dominio;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@javax.persistence.Entity
public class Task {

    @Id
    @GeneratedValue
    private int id;
    private String description;
    private long initialDelay;
    private long period;
    private String unit;
    private boolean active;

    protected Task() {
    }

    public Task(String description, long initialDelay, long period, String unit, boolean active) {
        this.id = id;
        this.description = description;
        this.initialDelay = initialDelay;
        this.period = period;
        this.unit = unit;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return this.description;
    }

}
