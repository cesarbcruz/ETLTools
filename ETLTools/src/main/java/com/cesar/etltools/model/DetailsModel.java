/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.model;

import com.cesar.etltools.dominio.Entity;
import com.cesar.etltools.dominio.Field;
import java.util.ArrayList;

/**
 *
 * @author cesar
 */
public class DetailsModel {

    private Entity entity;
    private ArrayList<Field> fields;
    private String nameKeySource;
    private String conditionSource;
    private String nameKeyDestination;
    private String valueKeyDestination;

    public DetailsModel() {
        fields = new ArrayList<>();
    }

    public String getNameKeySource() {
        return nameKeySource;
    }

    public void setNameKeySource(String nameKeySource) {
        this.nameKeySource = nameKeySource;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public String getConditionSource() {
        return conditionSource;
    }

    public void setConditionSource(String conditionSource) {
        this.conditionSource = conditionSource;
    }

    @Override
    public String toString() {
        return fields != null ? String.valueOf(fields.size()) : "";
    }

    public String getNameKeyDestination() {
        return nameKeyDestination;
    }

    public void setNameKeyDestination(String nameKeyDestination) {
        this.nameKeyDestination = nameKeyDestination;
    }

    public String getValueKeyDestination() {
        return valueKeyDestination;
    }

    public void setValueKeyDestination(String valueKeyDestination) {
        this.valueKeyDestination = valueKeyDestination;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    
}
