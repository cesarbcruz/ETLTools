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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Source source;
    private String lastKeyField;
    private Timestamp dateTimeUpdate;

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

}
