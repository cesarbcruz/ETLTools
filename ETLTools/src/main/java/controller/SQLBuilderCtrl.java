/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.cesar.etltools.dao.jdbc.factory.DatabaseFactory;
import com.cesar.etltools.model.SGDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
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
    }

    public JInternalFrame getView() {
        return view;
    }

    public void connectDatabase() throws ClassNotFoundException, SQLException {
        Connection con = DatabaseFactory.getDatabase((SGDB) view.getSgbd().getSelectedItem()).connectDatabase(view.getIphost().getText(), view.getPort().getText(), view.getUser().getText(), view.getPassword().getText());
    }

    private void loadComboSgdb() {
        DefaultComboBoxModel model = new DefaultComboBoxModel(SGDB.values());
        view.getSgbd().setModel(model);
    }

    private void addActionButtonConnect() {
        view.getConnect().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connectDatabase();
                } catch (ClassNotFoundException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

}
