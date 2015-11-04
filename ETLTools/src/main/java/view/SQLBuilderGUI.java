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
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
        sgbd = new javax.swing.JComboBox();
        next = new javax.swing.JButton();
        port = new javax.swing.JFormattedTextField();
        user = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listDatabases = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        iphost = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        connect = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        databaseDestination = new javax.swing.JTextField();
        databaseSource = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        listTableSource = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        listTableDestination = new javax.swing.JList();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("language/Bundle_pt_BR"); // NOI18N
        setTitle(bundle.getString("SQLBuilderGUI.title")); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SQLBuilderGUI.jPanel1.border.title"))); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        sgbd.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(sgbd, gridBagConstraints);

        next.setText(bundle.getString("SQLBuilderGUI.next.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(next, gridBagConstraints);

        port.setText(bundle.getString("SQLBuilderGUI.port.text")); // NOI18N
        port.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(port, gridBagConstraints);

        user.setText(bundle.getString("SQLBuilderGUI.user.text")); // NOI18N
        user.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(user, gridBagConstraints);

        jLabel2.setText(bundle.getString("SQLBuilderGUI.jLabel2.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jLabel2, gridBagConstraints);

        listDatabases.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SQLBuilderGUI.listDatabases.border.title"))); // NOI18N
        jScrollPane1.setViewportView(listDatabases);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jLabel1.setText(bundle.getString("SQLBuilderGUI.jLabel1.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel3.setText(bundle.getString("SQLBuilderGUI.jLabel3.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jLabel3, gridBagConstraints);

        iphost.setText(bundle.getString("SQLBuilderGUI.iphost.text")); // NOI18N
        iphost.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(iphost, gridBagConstraints);

        jLabel7.setText(bundle.getString("SQLBuilderGUI.jLabel7.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jLabel7, gridBagConstraints);

        connect.setText(bundle.getString("SQLBuilderGUI.connect.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(connect, gridBagConstraints);

        jLabel4.setText(bundle.getString("SQLBuilderGUI.jLabel4.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jLabel4, gridBagConstraints);

        password.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        password.setText(bundle.getString("SQLBuilderGUI.password.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(password, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        databaseDestination.setText(bundle.getString("SQLBuilderGUI.databaseDestination.text")); // NOI18N
        databaseDestination.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SQLBuilderGUI.databaseDestination.border.title"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel2.add(databaseDestination, gridBagConstraints);

        databaseSource.setText(bundle.getString("SQLBuilderGUI.databaseSource.text")); // NOI18N
        databaseSource.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SQLBuilderGUI.databaseSource.border.title"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel2.add(databaseSource, gridBagConstraints);

        listTableSource.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SQLBuilderGUI.listTableSource.border.title"))); // NOI18N
        jScrollPane2.setViewportView(listTableSource);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane2, gridBagConstraints);

        listTableDestination.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SQLBuilderGUI.listTableDestination.border.title"))); // NOI18N
        jScrollPane3.setViewportView(listTableDestination);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel2, gridBagConstraints);

        getContentPane().add(jPanel1, "card2");

        setBounds(0, 0, 800, 600);
    }// </editor-fold>//GEN-END:initComponents

    public SQLBuilderCtrl getControl() {
        return control;
    }

    public JButton getConnect() {
        return connect;
    }

    public JFormattedTextField getIphost() {
        return iphost;
    }

    public JButton getNext() {
        return next;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public JFormattedTextField getPort() {
        return port;
    }

    public JComboBox getSgbd() {
        return sgbd;
    }

    public JList getTabelas() {
        return listDatabases;
    }

    public JFormattedTextField getUser() {
        return user;
    }

    public JTextField getDatabaseDestination() {
        return databaseDestination;
    }

    public JTextField getDatabaseSource() {
        return databaseSource;
    }

    public JList getListDatabases() {
        return listDatabases;
    }

    public JList getListTableSource() {
        return listTableSource;
    }

    public JList getListTableDestination() {
        return listTableDestination;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connect;
    private javax.swing.JTextField databaseDestination;
    private javax.swing.JTextField databaseSource;
    private javax.swing.JFormattedTextField iphost;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList listDatabases;
    private javax.swing.JList listTableDestination;
    private javax.swing.JList listTableSource;
    private javax.swing.JButton next;
    private javax.swing.JPasswordField password;
    private javax.swing.JFormattedTextField port;
    private javax.swing.JComboBox sgbd;
    private javax.swing.JFormattedTextField user;
    // End of variables declaration//GEN-END:variables
}
