/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.model;

import java.text.SimpleDateFormat;

/**
 *
 * @author cesar
 */
public class DataConverter {

    public static Object execute(int typeCollumnSource, int typeCollumnDestination, Object obj) {
        if (typeCollumnSource == typeCollumnDestination) {
            return obj;
        } else if (typeCollumnSource == java.sql.Types.DATE && typeCollumnDestination == java.sql.Types.VARCHAR) {
            return new SimpleDateFormat("yyyy-MM-dd").format((java.sql.Date) obj);
        } else {
            return obj;
        }

    }

}
