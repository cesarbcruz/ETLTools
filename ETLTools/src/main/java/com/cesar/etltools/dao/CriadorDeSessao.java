package com.cesar.etltools.dao;

import com.cesar.etltools.dominio.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class CriadorDeSessao {

    private static AnnotationConfiguration config;
    private static SessionFactory sf;

    public Session getSession() {
        if (sf == null) {
            sf = getConfig().buildSessionFactory();
        }
        return sf.openSession();
    }

    public Configuration getConfig() {
        if (config == null) {
            config = new AnnotationConfiguration()
                    .addAnnotatedClass(Task.class)
                    .setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver")
                    //.setProperty("hibernate.connection.url", "jdbc:hsqldb:ETLTools.db;shutdown=true")
                    .setProperty("hibernate.connection.url", "jdbc:hsqldb:file:./db/ETLTools")
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
                    .setProperty("hibernate.connection.username", "sa")
                    .setProperty("hibernate.connection.password", "")
                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hibernate.format_sql", "true")
                    .setProperty("hibernate.hbm2ddl.auto", "update");

        }
        return config;
    }

    public Configuration getConfigMysql() {
        if (config == null) {
            config = new AnnotationConfiguration()
                    .addAnnotatedClass(Task.class)
                    .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost/ETLTools")
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.connection.username", "root")
                    .setProperty("hibernate.connection.password", "")
                    .setProperty("hibernate.show_sql", "true");
        }
        return config;
    }
}
