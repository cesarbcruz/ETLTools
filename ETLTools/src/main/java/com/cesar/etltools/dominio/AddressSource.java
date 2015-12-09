/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dominio;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;

/**
 *
 * @author cesar
 */
@javax.persistence.Entity
public class AddressSource {

    @Id
    @GeneratedValue
    private int id;
    private String ip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sourceid", insertable = true, updatable = true)
    @Fetch(org.hibernate.annotations.FetchMode.JOIN)
    private Source source;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addressSource", fetch = FetchType.LAZY)
    private Collection<MigrationDataTable> migrationDataTables;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Collection<MigrationDataTable> getMigrationDataTables() {
        return migrationDataTables;
    }

    public void setMigrationDataTables(Collection<MigrationDataTable> migrationDataTables) {
        this.migrationDataTables = migrationDataTables;
    }

    @Override
    public String toString() {
        return ip;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        AddressSource addressSource = (AddressSource) o;
        return this.getIp().equals(addressSource.getIp());
    }

}
