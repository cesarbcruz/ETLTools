/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dao.jdbc.factory;

import com.cesar.etltools.dao.jdbc.ConnectionDatabase;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author cesar
 */
public abstract class Database extends ConnectionDatabase{
    
    public abstract List<String> listDatabase() throws SQLException, ClassNotFoundException;
    
}
