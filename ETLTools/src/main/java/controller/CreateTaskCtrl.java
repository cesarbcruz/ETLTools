/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.cesar.etltools.dao.CriadorDeSessao;
import com.cesar.etltools.dao.TaskDao;
import com.cesar.etltools.dominio.Task;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import util.Messages;
import view.CreateTaskGUI;

/**
 *
 * @author cesar
 */
public class CreateTaskCtrl {

    CreateTaskGUI view;
    TaskDao dao;

    public CreateTaskCtrl(ResourceBundle bundle) {
        view = new CreateTaskGUI(bundle);
        dao = new TaskDao(new CriadorDeSessao().getSession());
        addActionButtonSave();
        addActionButtonCancel();
        createComboUnit();
    }

    public JInternalFrame getView() {
        return view;
    }

    private void addActionButtonCancel() {
        view.getButtonCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

    private void addActionButtonSave() {
        view.getButtonSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task task = new Task(view.getDescription().getText(), Long.valueOf(view.getInitialDelay().getValue().toString()), Long.valueOf(view.getPeriod().getValue().toString()), getUnitSelected());
                try {
                    validate(task);
                    dao.begin();
                    dao.salvar(task);
                    dao.commit();
                    Messages.information(view, view.getBundle().getString("MainGUI.messageInformationMessage.text"), view.getBundle().getString("MainGUI.messageInformationTitle.text"));
                    view.dispose();
                } catch (Exception ex) {
                    Messages.error(view, ex.getMessage(), view.getBundle().getString("MainGUI.messageErrorTitle.text"));
                }
            }
        });
    }

    private void validate(Task task) throws Exception {
        StringBuilder error = new StringBuilder();
        if (task.getDescription() == null || task.getDescription().trim().isEmpty()) {
            error.append(view.getBundle().getString("CreateTaskGUI.messageErrordescription.text"));
        }
        
        if (task.getInitialDelay()<=0) {
            error.append("\n").append(view.getBundle().getString("CreateTaskGUI.messageErrorInitialDelay.text"));
        }

        if (!error.toString().isEmpty()) {
            throw new Exception(error.toString());
        }
    }

    private String getUnitSelected() {
        int index = view.getUnit().getSelectedIndex();
        switch (index) {
            case 3:
                return TimeUnit.DAYS.toString();
            case 2:
                return TimeUnit.HOURS.toString();
            case 1:
                return TimeUnit.MINUTES.toString();
            default:
                return TimeUnit.SECONDS.toString();
        }
    }

    private void createComboUnit() {
        view.getUnit().setModel(new DefaultComboBoxModel(new String[]{
            view.getBundle().getString("CreateTaskGUI.unitOption1.text"),
            view.getBundle().getString("CreateTaskGUI.unitOption2.text"),
            view.getBundle().getString("CreateTaskGUI.unitOption3.text"),
            view.getBundle().getString("CreateTaskGUI.unitOption4.text")
        }));
    }

}
