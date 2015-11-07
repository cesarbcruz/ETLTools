/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dao.jdbc;

import com.cesar.etltools.dao.jdbc.factory.Database;
import com.cesar.etltools.model.ParamDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cesar
 */
public class PostgreSQL extends Database {

    public PostgreSQL(ParamDatabase paramDataBase) {
        super.contruct(paramDataBase);
    }

    @Override
    public List<String> listTables() throws SQLException, ClassNotFoundException {

        List<String> listTable = new ArrayList<>();

        StringBuilder sql = new StringBuilder();

        sql.append("\n SELECT");
        sql.append("\n table_schema || '.' || table_name as tablename");
        sql.append("\n FROM");
        sql.append("\n information_schema.tables");
        sql.append("\n WHERE");
        sql.append("\n table_type = 'BASE TABLE'");
        sql.append("\n AND");
        sql.append("\n table_schema NOT IN ('pg_catalog', 'information_schema');");

        try {

            PreparedStatement pstmt = connect().prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                listTable.add(rs.getString("tablename"));
            }
            return listTable;
        } finally {
            closeConnection();
        }
    }

}
