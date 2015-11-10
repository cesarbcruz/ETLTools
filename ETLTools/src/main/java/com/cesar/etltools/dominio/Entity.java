/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dominio;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@javax.persistence.Entity
public class Entity {

    @Id
    @GeneratedValue
    private int id;
    private String entitySource;
    private String nameKeySource;
    private String conditionSource;
    private String entityDestination;

    public Entity(int id, String entitySource, String nameKeySource, String conditionSource, String entityDestination) {
        this.id = id;
        this.entitySource = entitySource;
        this.nameKeySource = nameKeySource;
        this.conditionSource = conditionSource;
        this.entityDestination = entityDestination;
    }

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

}
