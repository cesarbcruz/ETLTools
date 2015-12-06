package com.cesar.etltools.dao;

import org.hibernate.Session;

import com.cesar.etltools.dominio.MigrationDataTable;
import java.util.List;

public class MigrationDataTableDao {

    private final Session session;

    public MigrationDataTableDao(Session session) {
        this.session = session;
    }

    public MigrationDataTable porId(int id) {
        return (MigrationDataTable) session.load(MigrationDataTable.class, id);
    }

    public List<MigrationDataTable> list() {
        return (List<MigrationDataTable>) session.createQuery("from MigrationDataTable m").list();
    }

    public void salvar(MigrationDataTable migrationDataTable) {
        try {
            session.getTransaction().begin();
            if(migrationDataTable.getId()>0){
                session.merge(migrationDataTable);                
            }else{
                session.save(migrationDataTable);                
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }

    }
    public void deletar(MigrationDataTable migrationDataTable) {
        try {
            session.getTransaction().begin();
            session.delete(migrationDataTable);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }
}
