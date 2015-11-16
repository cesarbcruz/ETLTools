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
public class Entity {

    @Id
    @GeneratedValue
    private int id;
    private String entitySource;
    private String nameKeySource;
    private String conditionSource;
    private String entityDestination;
    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Source source;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntitySource() {
        return entitySource;
    }

    public void setEntitySource(String entitySource) {
        this.entitySource = entitySource;
    }

    public String getNameKeySource() {
        return nameKeySource;
    }

    public void setNameKeySource(String nameKeySource) {
        this.nameKeySource = nameKeySource;
    }

    public String getConditionSource() {
        return conditionSource;
    }

    public void setConditionSource(String conditionSource) {
        this.conditionSource = conditionSource;
    }

    public String getEntityDestination() {
        return entityDestination;
    }

    public void setEntityDestination(String entityDestination) {
        this.entityDestination = entityDestination;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

}
