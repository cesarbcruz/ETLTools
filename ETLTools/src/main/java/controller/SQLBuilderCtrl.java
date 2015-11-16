/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.cesar.etltools.dao.AddressSourceDao;
import com.cesar.etltools.dao.CriadorDeSessao;
import com.cesar.etltools.dao.DestinationDao;
import com.cesar.etltools.dao.EntityDao;
import com.cesar.etltools.dao.FieldDao;
import com.cesar.etltools.dao.SourceDao;
import com.cesar.etltools.dao.TaskDao;
import com.cesar.etltools.dao.jdbc.factory.DatabaseFactory;
import com.cesar.etltools.dominio.Destination;
import com.cesar.etltools.dominio.Entity;
import com.cesar.etltools.dominio.AddressSource;
import com.cesar.etltools.dominio.Field;
import com.cesar.etltools.dominio.Source;
import com.cesar.etltools.dominio.Task;
import com.cesar.etltools.model.ButtonColumn;
import com.cesar.etltools.model.DetailsModel;
import com.cesar.etltools.model.ParamDatabase;
import com.cesar.etltools.model.SGDB;
import com.cesar.etltools.model.SortedListModel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.EdgedBalloonStyle;
import view.DetailRelationshipGUI;
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
    private BalloonTip b4;
    private BalloonTip b5;
    private BalloonTip b6;
    DetailRelationshipGUI viewDetail;
    JFrame parentRootFrame;
    SourceDao daoSource;
    DestinationDao daoDestination;
    AddressSourceDao daoAddress;
    private Source source;
    private Destination destination;
    private EntityDao entityDao;
    private FieldDao fieldDao;

    public SQLBuilderCtrl(JFrame parentRootFrame) {
        this.parentRootFrame = parentRootFrame;
        view = new SQLBuilderGUI(this);
        viewDetail = new DetailRelationshipGUI(parentRootFrame, true, this);
        addActionButtonConnect();
        addActionButtonSave();
        addActionCloseDetail();
        loadComboSgdb();
        configureBaloonTip();
        configureListTransfer();
        configureListTransferDetail();
        loadComboTask();
        configureTableRelationship();
        daoSource = new SourceDao(new CriadorDeSessao().getSession());
        daoDestination = new DestinationDao(new CriadorDeSessao().getSession());
        daoAddress = new AddressSourceDao(new CriadorDeSessao().getSession());
        entityDao = new EntityDao(new CriadorDeSessao().getSession());
        fieldDao = new FieldDao(new CriadorDeSessao().getSession());
    }

    private void configureBaloonTip() {
        b1 = new BalloonTip(view.getScrollTablesSource(), "<html>Primeiro selecione uma tabela de origem<br>e arraste para o relacionamento<br></html>", new EdgedBalloonStyle(Color.WHITE, Color.BLUE), true);
        b1.setVisible(false);
        b2 = new BalloonTip(view.getScrollTablesDestination(), "<html>Depois selecione uma tabela de destino<br>e arraste para o relacionamento</html>", new EdgedBalloonStyle(Color.WHITE, Color.BLUE), true);
        b2.setVisible(false);
        b3 = new BalloonTip(view.getScrollTableRelationship(), "<html>Para desfazer o relacionamento,<br>arraste a tabela para sua respectiva lista</html>", new EdgedBalloonStyle(Color.WHITE, Color.BLUE), true);
        b3.setVisible(false);
        b4 = new BalloonTip(viewDetail.getScrollFieldsSource(), "<html>Primeiro selecione um campo de origem<br>e arraste para o relacionamento<br></html>", new EdgedBalloonStyle(Color.WHITE, Color.BLUE), true);
        b4.setVisible(false);
        b5 = new BalloonTip(viewDetail.getScrollFieldsDestination(), "<html>Depois selecione um campo de destino<br>e arraste para o relacionamento</html>", new EdgedBalloonStyle(Color.WHITE, Color.BLUE), true);
        b5.setVisible(false);
        b6 = new BalloonTip(viewDetail.getScrollTableRelationship(), "<html>Para desfazer o relacionamento,<br>arraste o campo para sua respectiva lista</html>", new EdgedBalloonStyle(Color.WHITE, Color.BLUE), true);
        b6.setVisible(false);
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

    private void loadComboFieldKey(List<String> fields) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (String field : fields) {
            model.addElement(field);
        }
        viewDetail.getFieldKey().setModel(model);
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

    private void addActionCloseDetail() {
        viewDetail.getClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewDetail.dispose();
            }
        });
    }

    private void addActionButtonSave() {
        view.getSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (source == null || source.getId() == 0) {
                    source = new Source();
                }

                Task t = (Task) view.getComboTask().getSelectedItem();

                source.setPort(view.getPortSource().getText());
                source.setDatabaseName(view.getDatabaseSource().getText());
                source.setUser(view.getUserSource().getText());
                source.setPassword(new String(view.getPasswordSource().getPassword()));
                source.setTipo(((SGDB) view.getSgbdSource().getSelectedItem()).getId());
                source.setTask(t);

                daoSource.salvar(source);

                AddressSource addressSource = new AddressSource();
                addressSource.setIp(view.getIphostSource().getText());
                addressSource.setSource(source);

                daoAddress.salvar(addressSource);

                if (destination == null || destination.getId() == 0) {
                    destination = new Destination();
                }
                destination.setIp(view.getIphostDestination().getText());
                destination.setPort(view.getPortDestination().getText());
                destination.setDatabaseName(view.getDatabaseDestination().getText());
                destination.setUser(view.getUserDestination().getText());
                destination.setPassword(new String(view.getPasswordDestination().getPassword()));
                destination.setTipo(((SGDB) view.getSgbdDestination().getSelectedItem()).getId());
                destination.setSource(source);

                daoDestination.salvar(destination);

                for (int l = 0; l < view.getTableRelationship().getRowCount(); l++) {
                    Entity entity = new Entity();
                    entity.setEntitySource(view.getTableRelationship().getValueAt(l, 0).toString());
                    entity.setNameKeySource(viewDetail.getFieldKey().getSelectedItem().toString());
                    entity.setConditionSource(viewDetail.getQueryCondition().getText());
                    entity.setEntityDestination(view.getTableRelationship().getValueAt(l, 1).toString());
                    entity.setSource(source);
                    entityDao.salvar(entity);
                    
                    DetailsModel details = (DetailsModel)view.getTableRelationship().getValueAt(l, 2);
                    for (Object detail : details.getFields()) {
                        fieldDao.salvar((Field)detail);
                    }
                    
                }

                JOptionPane.showMessageDialog(parentRootFrame, "Dados salvos com sucesso!");
            }
        });
    }

    private void configureListTransfer() {
        view.getListTableSource().setDragEnabled(true);
        view.getListTableSource().setName("0");
        view.getListTableSource().setTransferHandler(new ListTransferHandler() {

            @Override
            void eventExportJList() {
                checkJtable(view.getTableRelationship(), Integer.parseInt(view.getListTableSource().getName()));
            }

            @Override
            void eventCleanup() {
                cleanupRowEmptyTable(view.getTableRelationship(), b3);
            }

        });

        view.getListTableDestination().setDragEnabled(true);
        view.getListTableDestination().setTransferHandler(new ListTransferHandler() {

            @Override
            void eventExportJList() {
                checkJtable(view.getTableRelationship(), Integer.parseInt(view.getListTableDestination().getName()));
            }

            @Override
            void eventCleanup() {
                cleanupRowEmptyTable(view.getTableRelationship(), b3);
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
                cleanupRowEmptyTable(view.getTableRelationship(), b3);
            }
        });
        view.getTableRelationship().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void configureListTransferDetail() {
        viewDetail.getListFieldSource().setDragEnabled(true);
        viewDetail.getListFieldSource().setName("0");
        viewDetail.getListFieldSource().setTransferHandler(new ListTransferHandler() {

            @Override
            void eventExportJList() {
                checkJtable(viewDetail.getTableRelationship(), Integer.parseInt(viewDetail.getListFieldSource().getName()));
            }

            @Override
            void eventCleanup() {
                cleanupRowEmptyTable(viewDetail.getTableRelationship(), b6);
            }

        });

        viewDetail.getListFieldDestination().setDragEnabled(true);
        viewDetail.getListFieldDestination().setTransferHandler(new ListTransferHandler() {

            @Override
            void eventExportJList() {
                checkJtable(viewDetail.getTableRelationship(), Integer.parseInt(viewDetail.getListFieldDestination().getName()));
            }

            @Override
            void eventCleanup() {
                cleanupRowEmptyTable(viewDetail.getTableRelationship(), b6);
            }
        });
        viewDetail.getListFieldDestination().setName("1");

        viewDetail.getTableRelationship().setDragEnabled(true);
        viewDetail.getTableRelationship().setTransferHandler(new ListTransferHandler() {

            @Override
            void eventExportJList() {

            }

            @Override
            void eventCleanup() {
                cleanupRowEmptyTable(viewDetail.getTableRelationship(), b6);
            }
        });
        viewDetail.getTableRelationship().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

    private void checkJtable(JTable t, int column) {
        boolean columnEmpty = false;
        for (int i = 0; i < t.getRowCount(); i++) {
            if (t.getValueAt(i, column).toString().isEmpty()) {
                columnEmpty = true;
            }
        }
        if (!columnEmpty) {
            ((DefaultTableModel) t.getModel()).addRow(new Object[]{"", "", new DetailsModel(), ""});
        }
    }

    private void cleanupRowEmptyTable(JTable t, BalloonTip b) {
        List<Integer> rowEmpty = new ArrayList<>();
        for (int i = 0; i < t.getRowCount(); i++) {
            if (t.getValueAt(i, 0).toString().isEmpty()
                    && t.getValueAt(i, 1).toString().isEmpty()) {
                rowEmpty.add(i);
            }
        }

        for (Integer row : rowEmpty) {
            ((DefaultTableModel) t.getModel()).removeRow(row);
        }
        if (t.getRowCount() > 0) {
            b.setVisible(true);
        }
    }

    private void configureTableRelationship() {
        Action edit = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                try {
                    showDetail(table.getValueAt(modelRow, 0).toString(), table.getValueAt(modelRow, 1).toString(), (DetailsModel) table.getValueAt(modelRow, 2));
                } catch (ClassNotFoundException | SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }

        };

        ButtonColumn buttonColumn = new ButtonColumn(view.getTableRelationship(), edit, 3);
        buttonColumn.setMnemonic(KeyEvent.VK_E);
    }

    private void showDetail(String tableSource, String tableDestination, DetailsModel detail) throws ClassNotFoundException, SQLException {

        if (tableSource == null || tableSource.isEmpty()) {
            throw new InvalidParameterException("A tabela de origem não foi selecionada!");
        }

        if (tableDestination == null || tableDestination.isEmpty()) {
            throw new InvalidParameterException("A tabela de destino não foi selecionada!");
        }

        b4.setVisible(true);
        b5.setVisible(true);
        ParamDatabase param = new ParamDatabase((SGDB) view.getSgbdSource().getSelectedItem(), view.getIphostSource().getText(), view.getPortSource().getText(), view.getUserSource().getText(), new String(view.getPasswordSource().getPassword()), view.getDatabaseSource().getText());
        SortedListModel model = new SortedListModel();
        List<String> fieldsSource = DatabaseFactory.getDatabase(param).listFieldsTable(tableSource);
        for (String field : fieldsSource) {
            model.add(field);
        }
        loadComboFieldKey(fieldsSource);
        viewDetail.getListFieldSource().setModel(model);
        ((DefaultTableModel) viewDetail.getTableRelationship().getModel()).setRowCount(0);

        param = new ParamDatabase((SGDB) view.getSgbdDestination().getSelectedItem(), view.getIphostDestination().getText(), view.getPortDestination().getText(), view.getUserDestination().getText(), new String(view.getPasswordDestination().getPassword()), view.getDatabaseDestination().getText());
        model = new SortedListModel();
        List<String> fieldsDestination = DatabaseFactory.getDatabase(param).listFieldsTable(tableDestination);
        for (String field : fieldsDestination) {
            model.add(field);
        }
        viewDetail.getListFieldDestination().setModel(model);
        viewDetail.getStatus().setText("<html>Mapeamento campo a campo entre as tabelas:"
                + "<br>" + tableSource + " (" + view.getSgbdSource().getSelectedItem() + ")<br>" + tableDestination + "(" + view.getSgbdDestination().getSelectedItem() + ")</html>");
        viewDetail.setVisible(true);

        detail.getFields().clear();
        for (int i = 0; i < viewDetail.getTableRelationship().getRowCount(); i++) {
            Field field = new Field(viewDetail.getTableRelationship().getValueAt(i, 0).toString(), viewDetail.getTableRelationship().getValueAt(i, 1).toString(), null);
            detail.getFields().add(field);
        }
        view.getTableRelationship().repaint();
    }
}
