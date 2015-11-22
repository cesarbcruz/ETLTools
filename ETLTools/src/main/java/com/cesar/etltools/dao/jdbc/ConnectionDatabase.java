/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dao.jdbc;

import com.cesar.etltools.model.ParamDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author cesar
 */
public class ConnectionDatabase {

    private ParamDatabase paramDatabase;
    private Connection con;

    public void contruct(ParamDatabase paramDatabase) {
        this.paramDatabase = paramDatabase;
    }

    public Connection connect() throws ClassNotFoundException, SQLException {
        String url = paramDatabase.getSgdb().getUrl() + paramDatabase.getIpHost() + ":" + paramDatabase.getPort() + "/" + paramDatabase.getDatabase();
        Class.forName(paramDatabase.getSgdb().getDriver());
        con = (Connection) DriverManager.getConnection(url, paramDatabase.getUser(), paramDatabase.getPassword());
        return con;
    }

    public void closeConnection() throws SQLException {
        if (con != null) {
            con.close();
        }
    }

}
