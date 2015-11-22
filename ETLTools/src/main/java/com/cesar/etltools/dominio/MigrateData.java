/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dominio;

import com.cesar.etltools.dao.CriadorDeSessao;
import com.cesar.etltools.dao.SourceDao;
import com.cesar.etltools.dao.jdbc.factory.Database;
import com.cesar.etltools.dao.jdbc.factory.DatabaseFactory;
import com.cesar.etltools.model.ParamDatabase;
import com.cesar.etltools.model.SGDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cesar
 */
public class MigrateData {

    void execute(Task t) throws ClassNotFoundException, SQLException {
        if (t != null) {
            SourceDao daoSource = new SourceDao(new CriadorDeSessao().getSession());
            Source source = daoSource.byTask(t);
            if (source != null && source.getAddressSource() != null && source.getEntity() != null) {
                for (AddressSource address : source.getAddressSource()) {
                    ParamDatabase param = new ParamDatabase(SGDB.byID(source.getTipo()), address.getIp(), source.getPort(), source.getUserName(), source.getPassword(), source.getDatabaseName());
                    Database database = DatabaseFactory.getDatabase(param);
                    Map<String, MigrationDataTable> map = new HashMap<>();
                    for (MigrationDataTable mdt : address.getMigrationDataTables()) {
                        map.put(mdt.getEntity().getEntitySource(), mdt);
                    }
                    for (Entity entity : source.getEntity()) {
                        MigrationDataTable mdt = null;
                        if (map.containsKey(entity.getEntitySource())) {
                            mdt = map.get(entity.getEntitySource());
                        } else {
                            mdt = new MigrationDataTable();
                            mdt.setAddressSource(address);
                            mdt.setEntity(entity);
                            map.put(entity.getEntitySource(), mdt);
                        }
                        if (entity.getField() != null) {
                            String sqlInsert = "";
                            try {
                                ResultSet rs = database.executeQuery(buildQuery(entity, mdt));
                                sqlInsert = (buildInsert(entity, rs));
                            } finally {
                                database.closeConnection();
                            }                            
                            database.executeSql(sqlInsert);
                        }
                    }
                }

            }
        }
    }

    private String buildQuery(Entity entity, MigrationDataTable migrationDataTable) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT").append("\n");

        Object[] fields = entity.getField().toArray();
        for (int i = 0; i < fields.length; i++) {
            sql.append(((Field) fields[i]).getNameFieldSource());
            if (i < fields.length - 1) {
                sql.append(",\n");
            }
        }
        sql.append("\nFROM ").append(entity.getEntitySource()).append("\n");
        sql.append("WHERE ").append(entity.getNameKeySource()).append(" > ").append(migrationDataTable.getLastKeyField()).append("\n");
        if (entity.getConditionSource() != null && !entity.getConditionSource().isEmpty()) {
            sql.append("AND ").append(entity.getConditionSource());
        }
        return sql.toString();
    }

    private String buildInsert(Entity entity, ResultSet rs) throws SQLException {
        StringBuilder sql = new StringBuilder();
        while (rs.next()) {
            sql.append("INSERT INTO ").append(entity.getEntityDestination()).append("(").append("\n");

            Object[] fields = entity.getField().toArray();
            for (int i = 0; i < fields.length; i++) {
                sql.append(((Field) fields[i]).getNameFieldDestination());
                if (i < fields.length - 1) {
                    sql.append(",\n");
                }
            }
            sql.append("\n").append(") VALUES (").append("\n");
            for (int i = 0; i < fields.length; i++) {
                sql.append(rs.getObject(((Field) fields[i]).getNameFieldSource()));
                if (i < fields.length - 1) {
                    sql.append(",\n");
                }
            }
            sql.append("\n").append(");").append("\n");
        }

        return sql.toString();
    }

}
