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
public class ParamDatabase implements Cloneable {

    private SGDB sgdb;
    private String ipHost;
    private String port;
    private String user;
    private String password;
    private String databaseName;

    public ParamDatabase(SGDB sgdb, String ipHost, String port, String user, String password) {
        this.sgdb = sgdb;
        this.ipHost = ipHost;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    public SGDB getSgdb() {
        return sgdb;
    }

    public void setSgdb(SGDB sgdb) {
        this.sgdb = sgdb;
    }

    public String getIpHost() {
        return ipHost;
    }

    public void setIpHost(String ipHost) {
        this.ipHost = ipHost;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return getDatabaseName();
    }
    
    
}
