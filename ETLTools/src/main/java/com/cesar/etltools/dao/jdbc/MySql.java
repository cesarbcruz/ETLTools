/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dao.jdbc;

import com.cesar.etltools.dao.jdbc.factory.Database;
import com.cesar.etltools.model.SGDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cesar
 */
public class MySql extends Database {

    @Override
    public Connection connectDatabase(String ipHost, String port, String user, String password) throws ClassNotFoundException, SQLException {
            return conecta(SGDB.MYSQL, ipHost, port, user, password);
    }
    
}
