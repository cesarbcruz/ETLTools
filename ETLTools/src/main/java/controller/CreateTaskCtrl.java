/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.cesar.etltools.dao.TaskDao;
import com.cesar.etltools.dominio.Task;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
public abstract class CreateTaskCtrl {
    
    CreateTaskGUI view;
    TaskDao dao;
    Task task;
    
    public CreateTaskCtrl(ResourceBundle bundle, TaskDao dao) {
        view = new CreateTaskGUI(bundle);
        this.dao = dao;
        addActionButtonSave();
        addActionButtonCancel();
        createComboUnit();
        addActionButtonDelete();
    }
    
    public void setTask(Task t) {
        task = t;
        if (task != null) {
            view.getDescription().setText(task.getDescription());
            view.getInitialDelay().setValue(task.getInitialDelay());
            view.getPeriod().setValue(task.getPeriod());
            view.getActive().setSelected(task.isActive());
            setUnitSelected(task.getUnit());
            view.getButtonDelete().setEnabled(true);
        } else {
            view.getButtonDelete().setEnabled(false);
        }
    }
    
    public JInternalFrame getView() {
        return view;
    }
    
    public abstract void event(List<Task> tasks);
    
    private void addActionButtonCancel() {
        view.getButtonCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }
    
    private void addActionButtonDelete() {
        view.getButtonDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean confirm = Messages.confirm(view, view.getBundle().getString("CreateTaskGUI.messageConfirmDelete.text"),
                        view.getBundle().getString("MainGUI.messageConfirmTitle.text"), view.getBundle().getString("MainGUI.messageConfirmOK.text"),
                        view.getBundle().getString("MainGUI.messageConfirmCancel.text"));
                if (confirm) {
                    dao.deletar(task);
                    view.dispose();
                    event(dao.list());
                }
            }
        });
    }
    
    private void addActionButtonSave() {
        view.getButtonSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (task == null) {
                    task = new Task(view.getDescription().getText(), Long.valueOf(view.getInitialDelay().getValue().toString()), Long.valueOf(view.getPeriod().getValue().toString()), getUnitSelected(), view.getActive().isSelected());
                } else {
                    int id = task.getId();
                    task = new Task(view.getDescription().getText(), Long.valueOf(view.getInitialDelay().getValue().toString()), Long.valueOf(view.getPeriod().getValue().toString()), getUnitSelected(), view.getActive().isSelected());
                    task.setId(id);
                }
                try {
                    validate(task);
                    if (task.getId() > 0) {
                        dao.atualizar(task);
                    } else {
                        dao.salvar(task);
                    }
                    Messages.information(view, view.getBundle().getString("MainGUI.messageInformationMessage.text"), view.getBundle().getString("MainGUI.messageInformationTitle.text"));
                    view.dispose();
                    event(dao.list());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    
    private void validate(Task task) throws Exception {
        StringBuilder error = new StringBuilder();
        if (task.getDescription() == null || task.getDescription().trim().isEmpty()) {
            error.append(view.getBundle().getString("CreateTaskGUI.messageErrordescription.text"));
        }
        
        if (task.getInitialDelay() <= 0) {
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
    
    private void setUnitSelected(String unit) {
        switch (unit) {
            case "DAYS":
                view.getUnit().setSelectedIndex(3);
                break;
            case "HOURS":
                view.getUnit().setSelectedIndex(2);
                break;
            case "MINUTES":
                view.getUnit().setSelectedIndex(1);
                break;
            default:
                view.getUnit().setSelectedIndex(0);
                break;
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
