/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dominio;

import java.util.Collection;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;

@javax.persistence.Entity
public class Entity {

    @Id
    @GeneratedValue
    private int id;
    private String entitySource;
    private String nameKeySource;
    private String conditionSource;
    private String entityDestination;
    private String nameKeyDestination;
    private String valueKeyDestination;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sourceid", insertable = true, updatable = true)
    @Fetch(org.hibernate.annotations.FetchMode.JOIN)
    private Source source;

    @OneToMany(mappedBy = "entity", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Collection<Field> field;


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

    public Collection<Field> getField() {
        return field;
    }

    public void setField(Collection<Field> fields) {
        this.field = fields;
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
    
    
    
}
