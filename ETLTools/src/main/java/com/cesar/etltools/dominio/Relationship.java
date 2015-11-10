/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dominio;

import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author cesar
 */
@javax.persistence.Entity
public class Relationship {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Source source;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Destination destination;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Entity entity;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Task task;
    private String lastKeyField;
    private Timestamp dateTimeUpdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getLastKeyField() {
        return lastKeyField;
    }

    public void setLastKeyField(String lastKeyField) {
        this.lastKeyField = lastKeyField;
    }

    public Timestamp getDateTimeUpdate() {
        return dateTimeUpdate;
    }

    public void setDateTimeUpdate(Timestamp dateTimeUpdate) {
        this.dateTimeUpdate = dateTimeUpdate;
    }

}
