/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dominio;

import java.sql.Timestamp;
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
public class AddressSource {

    @Id
    @GeneratedValue
    private int id;
    private String ip;
    private String lastKeyField;
    private Timestamp dateTimeUpdate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sourceid", insertable = true, updatable = true)
    @Fetch(org.hibernate.annotations.FetchMode.JOIN)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Source source;
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
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

    @Override
    public String toString() {
        return ip;
    }
}
