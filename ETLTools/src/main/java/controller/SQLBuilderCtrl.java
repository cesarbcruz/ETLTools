/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.cesar.etltools.dao.CriadorDeSessao;
import com.cesar.etltools.dao.TaskDao;
import com.cesar.etltools.dao.jdbc.factory.DatabaseFactory;
import com.cesar.etltools.dominio.Task;
import com.cesar.etltools.model.ButtonColumn;
import com.cesar.etltools.model.ParamDatabase;
import com.cesar.etltools.model.SGDB;
import com.cesar.etltools.model.SortedListModel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.EdgedBalloonStyle;
import view.SQLBuilderGUI;

/**
 *
 * @author cesar
 */
public class SQLBuilderCtrl {

    SQLBuilderGUI view;
    private BalloonTip b1;
    private BalloonTip b2;
    private BalloonTip b3;

    public SQLBuilderCtrl() {
        view = new SQLBuilderGUI(this);
        addActionButtonConnect();
        loadComboSgdb();
        configureListTransfer();
        configureBaloonTip();
        loadComboTask();
        configureTableRelationship();
    }

    private void configureBaloonTip() {
        b1 = new BalloonTip(view.getScrollTablesSource(), "<html>Primeiro selecione uma tabela de origem<br>e arraste para o relacionamento<br></html>", new EdgedBalloonStyle(Color.WHITE, Color.BLUE), true);
        b1.setVisible(false);
        b2 = new BalloonTip(view.getScrollTablesDestination(), "<html>Depois selecione uma tabela de destino<br>e arraste para o relacionamento</html>", new EdgedBalloonStyle(Color.WHITE, Color.BLUE), true);
        b2.setVisible(false);
        b3 = new BalloonTip(view.getScrollTableRelationship(), "<html>Para desfazer o relacionamento,<br>arraste a tabela para sua respectiva lista</html>", new EdgedBalloonStyle(Color.WHITE, Color.BLUE), true);
        b3.setVisible(false);
    }

    public JInternalFrame getView() {
        return view;
    }

    private void loadComboTask() {
        TaskDao taskDao = new TaskDao(new CriadorDeSessao().getSession());
        List<Task> tasks = taskDao.list();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (Task task : tasks) {
            model.addElement(task);
        }
        view.getComboTask().setModel(model);
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
                    view.getStatus().setText("Conectado com sucesso !");
                } catch (ClassNotFoundException | SQLException | CloneNotSupportedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void configureListTransfer() {
        view.getListTableSource().setDragEnabled(true);
        view.getListTableSource().setName("0");
        view.getListTableSource().setTransferHandler(new ListTransferHandler() {

            @Override
            void eventExportJList() {
                checkJtable(Integer.parseInt(view.getListTableSource().getName()));
            }

            @Override
            void eventCleanup() {
                cleanupRowEmptyTable();
            }

        });

        view.getListTableDestination().setDragEnabled(true);
        view.getListTableDestination().setTransferHandler(new ListTransferHandler() {

            @Override
            void eventExportJList() {
                checkJtable(Integer.parseInt(view.getListTableDestination().getName()));
            }

            @Override
            void eventCleanup() {
                cleanupRowEmptyTable();
            }
        });
        view.getListTableDestination().setName("1");

        view.getTableRelationship().setDragEnabled(true);
        view.getTableRelationship().setTransferHandler(new ListTransferHandler() {

            @Override
            void eventExportJList() {

            }

            @Override
            void eventCleanup() {
                cleanupRowEmptyTable();
            }
        });
        view.getTableRelationship().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void connectSourceDatabase() throws SQLException, ClassNotFoundException {
        ParamDatabase param = new ParamDatabase((SGDB) view.getSgbdSource().getSelectedItem(), view.getIphostSource().getText(), view.getPortSource().getText(), view.getUserSource().getText(), new String(view.getPasswordSource().getPassword()), view.getDatabaseSource().getText());
        SortedListModel model = new SortedListModel();
        List<String> tables = DatabaseFactory.getDatabase(param).listTables();
        for (String tableName : tables) {
            model.add(tableName);
        }
        view.getListTableSource().setModel(model);
        b1.setVisible(true);
    }

    private void connectDestinationDatabase() throws SQLException, ClassNotFoundException {
        ParamDatabase param = new ParamDatabase((SGDB) view.getSgbdDestination().getSelectedItem(), view.getIphostDestination().getText(), view.getPortDestination().getText(), view.getUserDestination().getText(), new String(view.getPasswordDestination().getPassword()), view.getDatabaseDestination().getText());
        SortedListModel model = new SortedListModel();
        List<String> tables = DatabaseFactory.getDatabase(param).listTables();
        for (String tableName : tables) {
            model.add(tableName);
        }
        view.getListTableDestination().setModel(model);
        b2.setVisible(true);
    }

    private void checkJtable(int column) {
        boolean columnEmpty = false;
        for (int i = 0; i < view.getTableRelationship().getRowCount(); i++) {
            if (view.getTableRelationship().getValueAt(i, column).toString().isEmpty()) {
                columnEmpty = true;
            }
        }
        if (!columnEmpty) {
            ((DefaultTableModel) view.getTableRelationship().getModel()).addRow(new Object[]{"", "", ""});
        }
    }

    private void cleanupRowEmptyTable() {
        List<Integer> rowEmpty = new ArrayList<>();
        for (int i = 0; i < view.getTableRelationship().getRowCount(); i++) {
            if (view.getTableRelationship().getValueAt(i, 0).toString().isEmpty()
                    && view.getTableRelationship().getValueAt(i, 1).toString().isEmpty()) {
                rowEmpty.add(i);
            }
        }

        for (Integer row : rowEmpty) {
            ((DefaultTableModel) view.getTableRelationship().getModel()).removeRow(row);
        }
        if (view.getTableRelationship().getRowCount() > 0) {
            b3.setVisible(true);
        }
    }

    private void configureTableRelationship() {
        Action edit = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                ((DefaultTableModel) table.getModel()).removeRow(modelRow);
            }
        };

        ButtonColumn buttonColumn = new ButtonColumn(view.getTableRelationship(), edit, 3);
        buttonColumn.setMnemonic(KeyEvent.VK_E);
    }

}
