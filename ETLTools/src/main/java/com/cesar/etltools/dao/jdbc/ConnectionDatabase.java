/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dao.jdbc;

import com.cesar.etltools.model.SGDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author cesar
 */
public class ConnectionDatabase {

    public Connection conecta(SGDB sgdb, String ipHost, String port, String user, String password) throws ClassNotFoundException, SQLException {

        String url = sgdb.getUrl() + ipHost + ":" + port;

        Class.forName(sgdb.getDriver());

        return (Connection) DriverManager.getConnection(url, user, password);

    }
}
