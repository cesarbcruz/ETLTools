/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.model;

import com.cesar.etltools.dominio.Field;
import java.util.ArrayList;

/**
 *
 * @author cesar
 */
public class DetailsModel {
    private ArrayList<Field> fields;

    public DetailsModel() {
        fields = new ArrayList<>();
    }
    
    

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }
    
    public ArrayList<Field> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return fields!=null?String.valueOf(fields.size()):"";
    }
    
    
    
}
