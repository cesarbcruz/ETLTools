/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dao.jdbc.factory;

import com.cesar.etltools.dao.jdbc.ConnectionDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cesar
 */
public abstract class Database extends ConnectionDatabase{
    
    public abstract List<String> listTables() throws SQLException, ClassNotFoundException;
    public abstract Map<String, Integer> listFieldsTable(String tableName) throws ClassNotFoundException, SQLException;
    public abstract ResultSet executeQuery(String query) throws ClassNotFoundException, SQLException;
    public abstract PreparedStatement executeSql(String sql) throws ClassNotFoundException, SQLException;
    
}
