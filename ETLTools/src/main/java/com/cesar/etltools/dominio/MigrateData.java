/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dominio;

import com.cesar.etltools.dao.CriadorDeSessao;
import com.cesar.etltools.dao.MigrationDataTableDao;
import com.cesar.etltools.dao.SourceDao;
import com.cesar.etltools.dao.jdbc.factory.Database;
import com.cesar.etltools.dao.jdbc.factory.DatabaseFactory;
import com.cesar.etltools.model.DataConverter;
import com.cesar.etltools.model.ParamDatabase;
import com.cesar.etltools.model.SGDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.Session;

/**
 *
 * @author cesar
 */
public abstract class MigrateData {
    
    void execute(Task t) throws ClassNotFoundException, SQLException {
        if (t != null) {
            Session sessao = new CriadorDeSessao().getSession();
            SourceDao daoSource = new SourceDao(sessao);
            MigrationDataTableDao migrationDataTableDao = new MigrationDataTableDao(sessao);
            Source source = daoSource.byTask(t);
            if (source != null && source.getAddressSource() != null && source.getEntity() != null) {
                for (AddressSource address : source.getAddressSource()) {
                    Database database = null;
                    try {
                        ParamDatabase param = new ParamDatabase(SGDB.byID(source.getTipo()), address.getIp(), source.getPort(), source.getUserName(), source.getPassword(), source.getDatabaseName());
                        database = DatabaseFactory.getDatabase(param);
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
                                eventNotifcation("Importando "+entity.getEntitySource()+ " do IP: "+address.getIp());
                                ResultSet rs = database.executeQuery(buildQuery(entity, mdt, 0));
                                int maxKey = 0;
                                if (rs.next()) {
                                    maxKey = rs.getInt(entity.getNameKeySource());
                                }
                                if (mdt.getLastKeyField() < maxKey) {
                                    rs = database.executeQuery(buildQuery(entity, mdt, maxKey));
                                    buildInsert(entity, rs, source.getDestination());
                                    mdt.setLastKeyField(maxKey);
                                    mdt.setDateTimeUpdate(new Timestamp(new Date().getTime()));
                                    migrationDataTableDao.salvar(mdt);
                                }
                            }
                        }
                    } catch (ClassNotFoundException | SQLException ex) {
                        eventNotifcation(ex.getMessage());
                    } finally {
                        if (database != null) {
                            database.closeConnection();
                        }
                    }
                }
                
            }
        }
    }
    
    abstract void eventNotifcation(String s);
    
    private String buildQuery(Entity entity, MigrationDataTable migrationDataTable, int maxKey) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT").append("\n");
        
        if (maxKey == 0) {
            sql.append(" MAX(").append(entity.getNameKeySource()).append(") as ").append(entity.getNameKeySource());
        } else {
            Object[] fields = entity.getField().toArray();
            for (int i = 0; i < fields.length; i++) {
                sql.append(((Field) fields[i]).getNameFieldSource());
                if (i < fields.length - 1) {
                    sql.append(",\n");
                }
            }
        }
        sql.append("\nFROM ").append(entity.getEntitySource()).append("\n");
        sql.append(" WHERE ").append(entity.getNameKeySource()).append(" > ").append(migrationDataTable.getLastKeyField()).append("\n");
        if (maxKey > 0) {
            sql.append(" AND ").append(entity.getNameKeySource()).append(" <= ").append(maxKey).append("\n");
        }
        if (entity.getConditionSource() != null && !entity.getConditionSource().isEmpty()) {
            sql.append("AND ").append(entity.getConditionSource());
        }
        return sql.toString();
    }
    
    private void buildInsert(Entity entity, ResultSet rs, Destination destination) throws SQLException, ClassNotFoundException {
        StringBuilder sql = new StringBuilder();
        ParamDatabase param = new ParamDatabase(SGDB.byID(destination.getTipo()), destination.getIp(), destination.getPort(), destination.getUserName(), destination.getPassword(), destination.getDatabaseName());
        Database database = DatabaseFactory.getDatabase(param);
        try {
            PreparedStatement pst = null;
            while (rs.next()) {
                Object[] fields = entity.getField().toArray();
                if (pst == null) {
                    sql.append("INSERT INTO ").append(entity.getEntityDestination()).append("(").append("\n");
                    
                    boolean keyDestination = entity.getNameKeyDestination() != null && !entity.getNameKeyDestination().isEmpty()
                            && entity.getValueKeyDestination() != null && !entity.getValueKeyDestination().isEmpty();
                    
                    if (keyDestination) {
                        sql.append(entity.getNameKeyDestination()).append(",\n");
                    }
                    
                    for (int i = 0; i < fields.length; i++) {
                        sql.append(((Field) fields[i]).getNameFieldDestination());
                        if (i < fields.length - 1) {
                            sql.append(",\n");
                        }
                    }
                    sql.append("\n").append(") VALUES (").append("\n");
                    
                    if (keyDestination) {
                        sql.append(entity.getValueKeyDestination()).append(",\n");
                    }
                    
                    for (int i = 0; i < fields.length; i++) {
                        sql.append("?");
                        if (i < fields.length - 1) {
                            sql.append(",\n");
                        }
                    }
                    sql.append("\n").append(");").append("\n");
                    pst = database.executeSql(sql.toString());
                }
                
                for (int i = 0; i < fields.length; i++) {
                    pst.setObject(i + 1, DataConverter.execute(((Field) fields[i]).getTypeSource(), ((Field) fields[i]).getTypeDestination(), rs.getObject(i + 1)));
                }
                
                System.out.println(pst.toString());
                
                pst.addBatch();
            }
            if (pst != null) {
                pst.executeBatch();
            }
            
        } finally {
            database.closeConnection();
        }
    }
}
