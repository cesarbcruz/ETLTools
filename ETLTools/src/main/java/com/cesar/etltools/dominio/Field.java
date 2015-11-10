/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dominio;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@javax.persistence.Entity
public class Field {

    @Id
    @GeneratedValue
    private int id;
    private String nameFieldSource;
    private String nameFieldDestination;
    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Entity entity;

    public Field(int id, String nameFieldSource, String nameFieldDestination, Entity entity) {
        this.id = id;
        this.nameFieldSource = nameFieldSource;
        this.nameFieldDestination = nameFieldDestination;
        this.entity = entity;
    }

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
