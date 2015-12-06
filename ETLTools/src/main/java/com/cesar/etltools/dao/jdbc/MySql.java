/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dao.jdbc;

import com.cesar.etltools.dao.jdbc.factory.Database;
import com.cesar.etltools.model.ParamDatabase;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cesar
 */
public class MySql extends Database {

    public MySql(ParamDatabase paramDataBase) {
        super.contruct(paramDataBase);
    }

    @Override
    public List<String> listTables() throws SQLException, ClassNotFoundException {
        List<String> tables = new ArrayList<>();
        try {
            DatabaseMetaData meta = connect().getMetaData();

            ResultSet res = meta.getTables(null, null, null,
                    new String[]{"TABLE"});
            while (res.next()) {
                String schem = res.getString("TABLE_SCHEM");
                tables.add(schem != null ? schem + "." + res.getString("TABLE_NAME") : res.getString("TABLE_NAME"));
            }
            return tables;
        } finally {
            closeConnection();
        }
    }

    public Map<String, Integer> listFieldsTable(String tableName) throws ClassNotFoundException, SQLException {
        try {
            Map<String, Integer> list = new LinkedHashMap<>();
            PreparedStatement pstmt = connect().prepareStatement("SELECT * FROM " + tableName + " LIMIT 0");
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i < columnCount + 1; i++) {
                list.put(rsmd.getColumnName(i), rsmd.getColumnType(i));
            }
            return list;
        } finally {
            closeConnection();
        }
    }

    @Override
    public ResultSet executeQuery(String query) throws ClassNotFoundException, SQLException {
        return connect().prepareStatement(query).executeQuery();
    }

    @Override
    public PreparedStatement executeSql(String sql) throws ClassNotFoundException, SQLException {
        return connect().prepareStatement(sql);
    }
}
