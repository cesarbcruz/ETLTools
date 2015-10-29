/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dao.jdbc.factory;

import com.cesar.etltools.dao.jdbc.MySql;
import com.cesar.etltools.dao.jdbc.PostgreSQL;
import com.cesar.etltools.model.SGDB;

/**
 *
 * @author cesar
 */
public class DatabaseFactory {

    public static Database getDatabase(SGDB sgdb) {
        switch (sgdb) {
            case MYSQL:
                return new MySql();
            case POSTGRES:
                return new PostgreSQL();
            default:
                return null;
        }
    }

}
