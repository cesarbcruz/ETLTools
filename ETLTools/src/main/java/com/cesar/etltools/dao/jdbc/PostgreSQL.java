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
    public List<String> listDatabase() throws SQLException, ClassNotFoundException {

        List<String> listaDatabase = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("datname as nm_database ");
        sql.append("FROM ");
        sql.append("pg_database ");
        sql.append("WHERE ");
        sql.append("datistemplate = false ");
        sql.append("AND ");
        sql.append("datname != 'postgres' ");
        sql.append("ORDER BY nm_database");

        try {

            PreparedStatement pstmt = connect().prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                listaDatabase.add(rs.getString("nm_database"));
            }
        } finally {
            closeConnection();

        }
        return listaDatabase;
    }

}
