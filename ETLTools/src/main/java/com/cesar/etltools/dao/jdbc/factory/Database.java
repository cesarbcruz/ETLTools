/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dao.jdbc.factory;

import com.cesar.etltools.dao.jdbc.ConnectionDatabase;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author cesar
 */
public abstract class Database extends ConnectionDatabase{
    
    public abstract Connection connectDatabase(String ipHost, String port, String user, String password) throws ClassNotFoundException, SQLException;
    
}
