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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;

/**
 *
 * @author cesar
 */
@javax.persistence.Entity
public class MigrationDataTable {

    @Id
    @GeneratedValue
    private int id;
    private int lastKeyField;
    private Timestamp dateTimeUpdate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Entity entity;    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "addressSourceId", insertable = true, updatable = true)
    @Fetch(org.hibernate.annotations.FetchMode.JOIN)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private AddressSource addressSource;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLastKeyField() {
        return lastKeyField;
    }

    public void setLastKeyField(int lastKeyField) {
        this.lastKeyField = lastKeyField;
    }

    public Timestamp getDateTimeUpdate() {
        return dateTimeUpdate;
    }

    public void setDateTimeUpdate(Timestamp dateTimeUpdate) {
        this.dateTimeUpdate = dateTimeUpdate;
    }

    public AddressSource getAddressSource() {
        return addressSource;
    }

    public void setAddressSource(AddressSource addressSource) {
        this.addressSource = addressSource;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

}
