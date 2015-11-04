/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dao.jdbc;

import com.cesar.etltools.dao.jdbc.factory.Database;
import com.cesar.etltools.model.ParamDatabase;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cesar
 */
public class MySql extends Database {

    public MySql(ParamDatabase paramDataBase) {
        super.contruct(paramDataBase);
    }

    @Override
    public List<String> listDatabase() throws SQLException, ClassNotFoundException {
        List<String> databases = new ArrayList<>();
        try {
            DatabaseMetaData meta = connect().getMetaData();
            ResultSet res = meta.getCatalogs();
            while (res.next()) {
                databases.add(res.getString("TABLE_CAT"));
            }
        } finally {
            closeConnection();
        }
        return databases;
    }

    @Override
    public List<String> listTables(String databaseName) throws SQLException, ClassNotFoundException {
        List<String> tables = new ArrayList<>();
        try {
            String[] types = {"TABLE"};
            ResultSet resultSet = connect().getMetaData().getTables(databaseName, null, "%", types);
            while (resultSet.next()) {
                tables.add(resultSet.getString(3));
            }
        } finally {
            closeConnection();
        }
        return tables;
    }

}
