/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.SQLBuilderCtrl;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author cesar
 */
public class SQLBuilderGUI extends javax.swing.JInternalFrame {

    SQLBuilderCtrl control;

    public SQLBuilderGUI(SQLBuilderCtrl control) {
        initComponents();
        this.control = control;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        sgbdSource = new javax.swing.JComboBox();
        portSource = new javax.swing.JFormattedTextField();
        userSource = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        databaseSource = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        passwordSource = new javax.swing.JPasswordField();
        iphostSource = new javax.swing.JComboBox();
        editAddressSource = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        scrollTablesSource = new javax.swing.JScrollPane();
        listTableSource = new javax.swing.JList();
        scrollTablesDestination = new javax.swing.JScrollPane();
        listTablesDestination = new javax.swing.JList();
        scrollTableRelationship = new javax.swing.JScrollPane();
        tableRelationship = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        sgbdDestination = new javax.swing.JComboBox();
        portDestination = new javax.swing.JFormattedTextField();
        userDestination = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        databaseDestination = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        passwordDestination = new javax.swing.JPasswordField();
        iphostDestination = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        status = new javax.swing.JLabel();
        connect = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        comboTask = new javax.swing.JComboBox();
        save = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("language/Bundle_pt_BR"); // NOI18N
        setTitle(bundle.getString("SQLBuilderGUI.title")); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SQLBuilderGUI.jPanel1.border.title"))); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(sgbdSource, gridBagConstraints);

        portSource.setText(bundle.getString("SQLBuilderGUI.portSource.text")); // NOI18N
        portSource.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(portSource, gridBagConstraints);

        userSource.setText(bundle.getString("SQLBuilderGUI.userSource.text")); // NOI18N
        userSource.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(userSource, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel2.setText(bundle.getString("SQLBuilderGUI.jLabel2.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel1.setText(bundle.getString("SQLBuilderGUI.jLabel1.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel3.setText(bundle.getString("SQLBuilderGUI.jLabel3.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jLabel3, gridBagConstraints);

        databaseSource.setText(bundle.getString("SQLBuilderGUI.databaseSource.text")); // NOI18N
        databaseSource.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(databaseSource, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel7.setText(bundle.getString("SQLBuilderGUI.jLabel7.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jLabel7, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel4.setText(bundle.getString("SQLBuilderGUI.jLabel4.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel5.setText(bundle.getString("SQLBuilderGUI.jLabel5.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jLabel5, gridBagConstraints);

        passwordSource.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        passwordSource.setText(bundle.getString("SQLBuilderGUI.passwordSource.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(passwordSource, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(iphostSource, gridBagConstraints);

        editAddressSource.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/edit.png"))); // NOI18N
        editAddressSource.setText(bundle.getString("SQLBuilderGUI.editAddressSource.text")); // NOI18N
        editAddressSource.setPreferredSize(new java.awt.Dimension(44, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel1.add(editAddressSource, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        scrollTablesSource.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SQLBuilderGUI.scrollTablesSource.border.title"))); // NOI18N

        listTableSource.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollTablesSource.setViewportView(listTableSource);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(scrollTablesSource, gridBagConstraints);

        scrollTablesDestination.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SQLBuilderGUI.scrollTablesDestination.border.title"))); // NOI18N

        listTablesDestination.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollTablesDestination.setViewportView(listTablesDestination);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(scrollTablesDestination, gridBagConstraints);

        scrollTableRelationship.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SQLBuilderGUI.scrollTableRelationship.border.title"))); // NOI18N

        tableRelationship.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Origem", "Destino", "Detalhes", "E"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableRelationship.getTableHeader().setReorderingAllowed(false);
        scrollTableRelationship.setViewportView(tableRelationship);
        if (tableRelationship.getColumnModel().getColumnCount() > 0) {
            tableRelationship.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("SQLBuilderGUI.tableRelationship.columnModel.title0")); // NOI18N
            tableRelationship.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("SQLBuilderGUI.tableRelationship.columnModel.title1")); // NOI18N
            tableRelationship.getColumnModel().getColumn(2).setMinWidth(80);
            tableRelationship.getColumnModel().getColumn(2).setPreferredWidth(80);
            tableRelationship.getColumnModel().getColumn(2).setMaxWidth(80);
            tableRelationship.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("SQLBuilderGUI.tableRelationship.columnModel.title2")); // NOI18N
            tableRelationship.getColumnModel().getColumn(3).setMinWidth(25);
            tableRelationship.getColumnModel().getColumn(3).setPreferredWidth(25);
            tableRelationship.getColumnModel().getColumn(3).setMaxWidth(25);
            tableRelationship.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("SQLBuilderGUI.tableRelationship.columnModel.title3")); // NOI18N
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(scrollTableRelationship, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SQLBuilderGUI.jPanel3.border.title"))); // NOI18N
        jPanel3.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(sgbdDestination, gridBagConstraints);

        portDestination.setText(bundle.getString("SQLBuilderGUI.portDestination.text")); // NOI18N
        portDestination.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(portDestination, gridBagConstraints);

        userDestination.setText(bundle.getString("SQLBuilderGUI.userDestination.text")); // NOI18N
        userDestination.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(userDestination, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel6.setText(bundle.getString("SQLBuilderGUI.jLabel6.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jLabel6, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel8.setText(bundle.getString("SQLBuilderGUI.jLabel8.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel9.setText(bundle.getString("SQLBuilderGUI.jLabel9.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jLabel9, gridBagConstraints);

        databaseDestination.setText(bundle.getString("SQLBuilderGUI.databaseDestination.text")); // NOI18N
        databaseDestination.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(databaseDestination, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel10.setText(bundle.getString("SQLBuilderGUI.jLabel10.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jLabel10, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel11.setText(bundle.getString("SQLBuilderGUI.jLabel11.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jLabel11, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel12.setText(bundle.getString("SQLBuilderGUI.jLabel12.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jLabel12, gridBagConstraints);

        passwordDestination.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        passwordDestination.setText(bundle.getString("SQLBuilderGUI.passwordDestination.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(passwordDestination, gridBagConstraints);

        iphostDestination.setText(bundle.getString("SQLBuilderGUI.iphostDestination.text")); // NOI18N
        iphostDestination.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(iphostDestination, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        status.setForeground(new java.awt.Color(0, 153, 204));
        status.setText(bundle.getString("SQLBuilderGUI.status.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(status, gridBagConstraints);

        connect.setText(bundle.getString("SQLBuilderGUI.connect.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(connect, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jPanel4, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel13.setText(bundle.getString("SQLBuilderGUI.jLabel13.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(comboTask, gridBagConstraints);

        save.setText(bundle.getString("SQLBuilderGUI.save.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel5.add(save, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel5, gridBagConstraints);

        setBounds(0, 0, 800, 600);
    }// </editor-fold>//GEN-END:initComponents

    public SQLBuilderCtrl getControl() {
        return control;
    }

    public JButton getConnect() {
        return connect;
    }

    public JFormattedTextField getDatabaseDestination() {
        return databaseDestination;
    }

    public JComboBox getComboTask() {
        return comboTask;
    }

    public JFormattedTextField getDatabaseSource() {
        return databaseSource;
    }

    public JFormattedTextField getIphostDestination() {
        return iphostDestination;
    }

    public JComboBox getIphostSource() {
        return iphostSource;
    }

    public JList getListTableDestination() {
        return listTablesDestination;
    }

    public JList getListTableSource() {
        return listTableSource;
    }

    public JPasswordField getPasswordDestination() {
        return passwordDestination;
    }

    public JPasswordField getPasswordSource() {
        return passwordSource;
    }

    public JScrollPane getScrollTablesDestination() {
        return scrollTablesDestination;
    }

    public JScrollPane getScrollTablesSource() {
        return scrollTablesSource;
    }

    public JFormattedTextField getPortDestination() {
        return portDestination;
    }

    public JScrollPane getScrollTableRelationship() {
        return scrollTableRelationship;
    }

    public JFormattedTextField getPortSource() {
        return portSource;
    }

    public JComboBox getSgbdDestination() {
        return sgbdDestination;
    }

    public JComboBox getSgbdSource() {
        return sgbdSource;
    }

    public JLabel getStatus() {
        return status;
    }

    public JTable getTableRelationship() {
        return tableRelationship;
    }

    public JFormattedTextField getUserDestination() {
        return userDestination;
    }

    public JFormattedTextField getUserSource() {
        return userSource;
    }

    public JButton getSave() {
        return save;
    }

    public JButton getEditAddressSource() {
        return editAddressSource;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboTask;
    private javax.swing.JButton connect;
    private javax.swing.JFormattedTextField databaseDestination;
    private javax.swing.JFormattedTextField databaseSource;
    private javax.swing.JButton editAddressSource;
    private javax.swing.JFormattedTextField iphostDestination;
    private javax.swing.JComboBox iphostSource;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JList listTableSource;
    private javax.swing.JList listTablesDestination;
    private javax.swing.JPasswordField passwordDestination;
    private javax.swing.JPasswordField passwordSource;
    private javax.swing.JFormattedTextField portDestination;
    private javax.swing.JFormattedTextField portSource;
    private javax.swing.JButton save;
    private javax.swing.JScrollPane scrollTableRelationship;
    private javax.swing.JScrollPane scrollTablesDestination;
    private javax.swing.JScrollPane scrollTablesSource;
    private javax.swing.JComboBox sgbdDestination;
    private javax.swing.JComboBox sgbdSource;
    private javax.swing.JLabel status;
    private javax.swing.JTable tableRelationship;
    private javax.swing.JFormattedTextField userDestination;
    private javax.swing.JFormattedTextField userSource;
    // End of variables declaration//GEN-END:variables
}
