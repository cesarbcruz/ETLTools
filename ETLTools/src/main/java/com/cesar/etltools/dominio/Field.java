/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dominio;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;

@javax.persistence.Entity
public class Field {

    @Id
    @GeneratedValue
    private int id;
    private String nameFieldSource;
    private String nameFieldDestination;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entityid", insertable = true, updatable = true)
    @Fetch(org.hibernate.annotations.FetchMode.JOIN)
    @Cascade(CascadeType.ALL)
    private Entity entity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameFieldSource() {
        return nameFieldSource;
    }

    public void setNameFieldSource(String nameFieldSource) {
        this.nameFieldSource = nameFieldSource;
    }

    public String getNameFieldDestination() {
        return nameFieldDestination;
    }

    public void setNameFieldDestination(String nameFieldDestination) {
        this.nameFieldDestination = nameFieldDestination;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
