/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.model;

/**
 *
 * @author cesar
 */
public enum SGDB {

    POSTGRES(1, "PostgreSQL", "org.postgresql.Driver", "jdbc:postgresql://"),
    MYSQL(2, "MySQL", "com.mysql.jdbc.Driver", "jdbc:mysql://");

    private final int id;
    private final String description;
    private final String driver;
    private final String url;

    private SGDB(int id, String description, String driver, String url) {
        this.id = id;
        this.description = description;
        this.driver = driver;
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public String getDriver() {
        return driver;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public static SGDB byID(int id) {
        for (SGDB sgdb : SGDB.values()) {
            if (sgdb.getId() == id) {
                return sgdb;
            }
        }
        return null;
    }

}
