/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.cesar.etltools.dao.jdbc.factory.DatabaseFactory;
import com.cesar.etltools.model.ParamDatabase;
import com.cesar.etltools.model.SGDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import view.SQLBuilderGUI;

/**
 *
 * @author cesar
 */
public class SQLBuilderCtrl {

    SQLBuilderGUI view;

    public SQLBuilderCtrl() {
        view = new SQLBuilderGUI(this);
        addActionButtonConnect();
        loadComboSgdb();
        configureListTransfer();
    }

    public JInternalFrame getView() {
        return view;
    }

    public void connectDatabase() throws ClassNotFoundException, SQLException, CloneNotSupportedException {
        connectSourceDatabase();
        connectDestinationDatabase();
    }

    private void loadComboSgdb() {
        view.getSgbdSource().setModel(new DefaultComboBoxModel(SGDB.values()));
        view.getSgbdDestination().setModel(new DefaultComboBoxModel(SGDB.values()));
    }

    private void addActionButtonConnect() {
        view.getConnect().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connectDatabase();
                } catch (ClassNotFoundException | SQLException | CloneNotSupportedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void configureListTransfer() {
        view.getListTableSource().setDragEnabled(true);
        view.getListTableSource().setTransferHandler(new ListTransferHandler());
        view.getListTableSource().setName("0");
        
        view.getListTableDestination().setDragEnabled(true);
        view.getListTableDestination().setTransferHandler(new ListTransferHandler());
        view.getListTableDestination().setName("1");

        view.getTableRelationship().setDragEnabled(true);
        view.getTableRelationship().setTransferHandler(new ListTransferHandler());
    }

    private void connectSourceDatabase() throws SQLException, ClassNotFoundException {
        ParamDatabase param = new ParamDatabase((SGDB) view.getSgbdSource().getSelectedItem(), view.getIphostSource().getText(), view.getPortSource().getText(), view.getUserSource().getText(), new String(view.getPasswordSource().getPassword()), view.getDatabaseSource().getText());
        DefaultListModel model = new DefaultListModel();
        List<String> tables = DatabaseFactory.getDatabase(param).listTables();
        for (String database : tables) {
            model.addElement(database);
        }
        view.getListTableSource().setModel(model);
    }
    
     private void connectDestinationDatabase() throws SQLException, ClassNotFoundException {
        ParamDatabase param = new ParamDatabase((SGDB) view.getSgbdDestination().getSelectedItem(), view.getIphostDestination().getText(), view.getPortDestination().getText(), view.getUserDestination().getText(), new String(view.getPasswordDestination().getPassword()), view.getDatabaseDestination().getText());
        DefaultListModel model = new DefaultListModel();
        List<String> tables = DatabaseFactory.getDatabase(param).listTables();
        for (String database : tables) {
            model.addElement(database);
        }
        view.getListTableDestination().setModel(model);
    }

}
