/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author cesar
 */
public class CreateTaskGUI extends javax.swing.JInternalFrame {

    /**
     * Creates new form CreateTaskGUI
     */
    public CreateTaskGUI(ResourceBundle bundle) {
        initComponents();
        buttonSave.setBackground(new Color(51, 153, 255));
        buttonSave.setForeground(new java.awt.Color(255, 255, 255));
        buttonCancel.setBackground(new Color(255,215,0));
        buttonCancel.setForeground(new java.awt.Color(0, 0, 0));
        buttonDelete.setBackground(new Color(255,99,71));
        buttonDelete.setForeground(new java.awt.Color(255, 255, 255));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        labelDescription = new javax.swing.JLabel();
        labelInitialDelay = new javax.swing.JLabel();
        labelPeriod = new javax.swing.JLabel();
        jLabelUnit = new javax.swing.JLabel();
        unit = new javax.swing.JComboBox();
        initialDelay = new javax.swing.JSpinner();
        period = new javax.swing.JSpinner();
        description = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        buttonSave = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        active = new javax.swing.JCheckBox();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        labelDescription.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        labelDescription.setText("Descrição");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(labelDescription, gridBagConstraints);

        labelInitialDelay.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        labelInitialDelay.setText("Espera Inicial");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(labelInitialDelay, gridBagConstraints);

        labelPeriod.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        labelPeriod.setText("Período");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(labelPeriod, gridBagConstraints);

        jLabelUnit.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelUnit.setText("Unidade Tempo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(jLabelUnit, gridBagConstraints);

        unit.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(unit, gridBagConstraints);

        initialDelay.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        initialDelay.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(1L), Long.valueOf(1L), Long.valueOf(9999L), Long.valueOf(1L)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(initialDelay, gridBagConstraints);

        period.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        period.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(1L), Long.valueOf(1L), Long.valueOf(9999L), Long.valueOf(1L)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(period, gridBagConstraints);

        description.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(description, gridBagConstraints);

        buttonSave.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBoxMenuItem.acceleratorForeground"));
        buttonSave.setForeground(new java.awt.Color(255, 255, 255));
        buttonSave.setText("Salvar");
        jPanel1.add(buttonSave);

        buttonCancel.setBackground(javax.swing.UIManager.getDefaults().getColor("OptionPane.errorDialog.titlePane.shadow"));
        buttonCancel.setForeground(new java.awt.Color(255, 255, 255));
        buttonCancel.setText("Cancelar");
        jPanel1.add(buttonCancel);

        buttonDelete.setBackground(javax.swing.UIManager.getDefaults().getColor("OptionPane.errorDialog.titlePane.shadow"));
        buttonDelete.setForeground(new java.awt.Color(255, 255, 255));
        buttonDelete.setText("Excluir");
        jPanel1.add(buttonDelete);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        getContentPane().add(jPanel1, gridBagConstraints);

        active.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        active.setSelected(true);
        active.setText("Ativo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(active, gridBagConstraints);

        setBounds(0, 0, 401, 239);
    }// </editor-fold>//GEN-END:initComponents

    public JButton getButtonCancel() {
        return buttonCancel;
    }
    
    public JButton getButtonSave() {
        return buttonSave;
    }
    
    public JTextField getDescription() {
        return description;
    }
    
    public JSpinner getInitialDelay() {
        return initialDelay;
    }
    
    public JSpinner getPeriod() {
        return period;
    }
        
    public JComboBox getUnit() {
        return unit;
    }
    
    public JButton getButtonDelete() {
        return buttonDelete;
    }

    public JCheckBox getActive() {
        return active;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox active;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonSave;
    private javax.swing.JTextField description;
    private javax.swing.JSpinner initialDelay;
    private javax.swing.JLabel jLabelUnit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelInitialDelay;
    private javax.swing.JLabel labelPeriod;
    private javax.swing.JSpinner period;
    private javax.swing.JComboBox unit;
    // End of variables declaration//GEN-END:variables
}
