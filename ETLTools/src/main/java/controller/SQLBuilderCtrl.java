/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.cesar.etltools.dao.AddressSourceDao;
import com.cesar.etltools.dao.CriadorDeSessao;
import com.cesar.etltools.dao.EntityDao;
import com.cesar.etltools.dao.FieldDao;
import com.cesar.etltools.dao.SourceDao;
import com.cesar.etltools.dao.jdbc.factory.DatabaseFactory;
import com.cesar.etltools.dominio.Destination;
import com.cesar.etltools.dominio.Entity;
import com.cesar.etltools.dominio.AddressSource;
import com.cesar.etltools.dominio.Field;
import com.cesar.etltools.dominio.MigrationDataTable;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import org.hibernate.Session;
import view.DetailRelationshipGUI;
import view.EditAddressSourceGUI;
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
    AddressSourceDao daoAddress;
    private Source source;
    private EntityDao entityDao;
    FieldDao fieldDao;
    private EditAddressSourceGUI viewAddressSource;

    public SQLBuilderCtrl(JFrame parentRootFrame) {
        this.parentRootFrame = parentRootFrame;
        construct();
    }

    public void setup(Task t) {
        view.getComboTask().setModel(new DefaultComboBoxModel(new Object[]{t}));
        source = daoSource.byTask(t);
        if (source != null) {
            fillDataSource(source);
            fillDataDestination(source.getDestination());
            fillDataEntity(source.getEntity());
        }
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

    private void loadComboFieldKey(Map<String, Integer> fieldsSource) {
        DefaultComboBoxModel modelCombo = new DefaultComboBoxModel();
        for (Map.Entry<String, Integer> entrySet : fieldsSource.entrySet()) {
            String field = entrySet.getKey();
            modelCombo.addElement(field);
        }
        viewDetail.getFieldKey().setModel(modelCombo);
    }

    private void loadComboFieldKeyDestination(Map<String, Integer> fieldsDestination) {
        DefaultComboBoxModel modelCombo = new DefaultComboBoxModel();
        for (Map.Entry<String, Integer> entrySet : fieldsDestination.entrySet()) {
            String field = entrySet.getKey();
            modelCombo.addElement(field);
        }
        viewDetail.getFieldKeyDestination().setModel(modelCombo);
    }

    public void connectDatabase() throws ClassNotFoundException, SQLException, CloneNotSupportedException {

        ObjectAlreadyExists objectAlreadyExists = getObjectAlreadyExists(view.getTableRelationship());

        connectSourceDatabase(objectAlreadyExists.source);
        connectDestinationDatabase(objectAlreadyExists.destination);
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

    private void editAddressSource() {
        viewAddressSource.getIpHostSource().setText("");
        viewAddressSource.getListAddressSource().setModel(view.getIphostSource().getModel());
        viewAddressSource.setVisible(true);
    }

    private void addActionButtonAddAddressSource() {
        viewAddressSource.getAddIPSource().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newIp = viewAddressSource.getIpHostSource().getText();

                if (newIp == null || newIp.isEmpty()) {
                    JOptionPane.showMessageDialog(parentRootFrame, "O IP não foi informado!");
                    viewAddressSource.getIpHostSource().grabFocus();
                } else if (!validateIp(newIp)) {
                    JOptionPane.showMessageDialog(parentRootFrame, "O IP informado é inválido!");
                    viewAddressSource.getIpHostSource().grabFocus();
                } else {
                    AddressSource addressSource = new AddressSource();
                    addressSource.setIp(newIp);
                    ((DefaultComboBoxModel) viewAddressSource.getListAddressSource().getModel()).addElement(addressSource);
                    viewAddressSource.getIpHostSource().setText("");
                    viewAddressSource.getIpHostSource().grabFocus();
                }
            }
        });
    }

    public static boolean validateIp(final String ip) {

        if (ip == null) {
            return false;
        }

        String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";

        return ip.matches(PATTERN) || ip.equals("localhost");
    }

    private void addActionButtonRemoveAddressSource() {
        viewAddressSource.getRemoveIPSource().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List selectedValues = viewAddressSource.getListAddressSource().getSelectedValuesList();
                if (selectedValues != null && !selectedValues.isEmpty()) {
                    if (JOptionPane.showConfirmDialog(parentRootFrame, "Os dados de controle vinculados ao IP serão perdidos.\nConfirma a remoção dos itens selecionados?", "Confirmação", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                        for (Object obj : selectedValues) {
                            if (((AddressSource) obj).getId() > 0) {
                                daoAddress.deletar(((AddressSource) obj));
                            }
                            ((DefaultComboBoxModel) viewAddressSource.getListAddressSource().getModel()).removeElement(obj);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(parentRootFrame, "Algum item deve ser selecionado!");
                }
            }
        });
    }

    private void addActionButtonCloseEditAddressSource() {
        viewAddressSource.getCloseEditAddressSource().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAddressSource.dispose();
            }
        });
    }

    private void addActionButtonHistoricEditAddressSource() {
        viewAddressSource.getHistoric().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List selectedValues = viewAddressSource.getListAddressSource().getSelectedValuesList();
                if (selectedValues != null && !selectedValues.isEmpty()) {
                    if (selectedValues.size() > 1) {
                        JOptionPane.showMessageDialog(parentRootFrame, "Selecone apenas um item para verificar o histórico!");
                    } else {
                        StringBuilder view = new StringBuilder();
                        if (((AddressSource) selectedValues.get(0)).getMigrationDataTables() != null) {
                            for (MigrationDataTable migrationDataTable : ((AddressSource) selectedValues.get(0)).getMigrationDataTables()) {
                                view.append("Entidade: ").append(migrationDataTable.getEntity().getEntitySource())
                                        .append(", último valor campo chave: ").append(migrationDataTable.getLastKeyField())
                                        .append(", data/hora atualização: ").append(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(migrationDataTable.getDateTimeUpdate()))
                                        .append("\n");
                            }
                        }
                        if (view.toString().isEmpty()) {
                            JOptionPane.showMessageDialog(parentRootFrame, "Nenhum histórico foi encontrado!");
                        } else {
                            JOptionPane.showMessageDialog(parentRootFrame, view);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(parentRootFrame, "Algum item deve ser selecionado!");
                }
            }
        });
    }

    private void addActionButtonEditAddressSource() {
        view.getEditAddressSource().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editAddressSource();
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
                if (source == null) {
                    source = new Source();
                }

                Task t = (Task) view.getComboTask().getSelectedItem();

                source.setPort(view.getPortSource().getText());
                source.setDatabaseName(view.getDatabaseSource().getText());
                source.setUserName(view.getUserSource().getText());
                source.setPassword(new String(view.getPasswordSource().getPassword()));
                source.setTipo(((SGDB) view.getSgbdSource().getSelectedItem()).getId());
                source.setTask(t);

                List<AddressSource> addressSources = new ArrayList<>();
                for (int i = 0; i < view.getIphostSource().getItemCount(); i++) {
                    AddressSource address = (AddressSource) view.getIphostSource().getItemAt(i);
                    address.setSource(source);
                    addressSources.add(address);
                }

                if (!addressSources.isEmpty()) {
                    source.setAddressSource(addressSources);
                }

                Destination destination = source.getDestination();
                if (source.getDestination() == null) {
                    destination = new Destination();
                    source.setDestination(destination);
                }
                destination.setIp(view.getIphostDestination().getText());
                destination.setPort(view.getPortDestination().getText());
                destination.setDatabaseName(view.getDatabaseDestination().getText());
                destination.setUserName(view.getUserDestination().getText());
                destination.setPassword(new String(view.getPasswordDestination().getPassword()));
                destination.setTipo(((SGDB) view.getSgbdDestination().getSelectedItem()).getId());

                List<Entity> entitys = new ArrayList<>();
                for (int l = 0; l < view.getTableRelationship().getRowCount(); l++) {
                    DetailsModel details = (DetailsModel) view.getTableRelationship().getValueAt(l, 2);

                    Entity entity = details.getEntity();

                    if (entity == null) {
                        entity = new Entity();
                    }

                    entity.setEntitySource(view.getTableRelationship().getValueAt(l, 0).toString());
                    entity.setEntityDestination(view.getTableRelationship().getValueAt(l, 1).toString());
                    entity.setSource(source);
                    entity.setNameKeySource(details.getNameKeySource());
                    entity.setConditionSource(details.getConditionSource());

                    entity.setNameKeyDestination(details.getNameKeyDestination());
                    entity.setValueKeyDestination(details.getValueKeyDestination());

                    entity.setField(details.getFields());
                    entitys.add(entity);
                }
                source.setEntity(entitys);

                daoSource.salvar(source);

                JOptionPane.showMessageDialog(parentRootFrame, "Dados salvos com sucesso!");
                view.dispose();
            }
        });
    }

    private void configureListTransfer() {
        view.getListTableSource().setModel(new SortedListModel());
        view.getListTableSource().setDragEnabled(true);
        view.getListTableSource().setName("0");
        view.getListTableSource().setTransferHandler(new ListTransferHandler() {

            @Override
            void eventExportJList() {
                checkJtable(view.getTableRelationship(), Integer.parseInt(view.getListTableSource().getName()));
            }

            @Override
            void eventCleanup() {
                cleanupRowEmptyTableEntity(view.getTableRelationship(), b3);
            }

        });

        view.getListTableDestination().setModel(new SortedListModel());
        view.getListTableDestination().setDragEnabled(true);
        view.getListTableDestination().setTransferHandler(new ListTransferHandler() {

            @Override
            void eventExportJList() {
                checkJtable(view.getTableRelationship(), Integer.parseInt(view.getListTableDestination().getName()));
            }

            @Override
            void eventCleanup() {
                cleanupRowEmptyTableEntity(view.getTableRelationship(), b3);
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
                cleanupRowEmptyTableEntity(view.getTableRelationship(), b3);
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
                cleanupRowEmptyTableField(viewDetail.getTableRelationship(), b6);
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
                cleanupRowEmptyTableField(viewDetail.getTableRelationship(), b6);
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
                cleanupRowEmptyTableField(viewDetail.getTableRelationship(), b6);
            }
        });
        viewDetail.getTableRelationship().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void connectSourceDatabase(List<String> sourceAlreadyExists) throws SQLException, ClassNotFoundException {

        if (view.getIphostSource().getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(parentRootFrame, "O IP de Origem deve ser informado!");
        } else {
            ParamDatabase param = new ParamDatabase((SGDB) view.getSgbdSource().getSelectedItem(), ((AddressSource) view.getIphostSource().getSelectedItem()).getIp(), view.getPortSource().getText(), view.getUserSource().getText(), new String(view.getPasswordSource().getPassword()), view.getDatabaseSource().getText());
            SortedListModel model = new SortedListModel();
            List<String> tables = DatabaseFactory.getDatabase(param).listTables();
            for (String tableName : tables) {
                if (!sourceAlreadyExists.contains(tableName)) {
                    model.add(tableName);
                }
            }
            view.getListTableSource().setModel(model);
            b1.setVisible(true);
        }
    }

    private ObjectAlreadyExists getObjectAlreadyExists(JTable table) {
        ObjectAlreadyExists objectAlreadyExists = new ObjectAlreadyExists();
        for (int l = 0; l < table.getRowCount(); l++) {
            objectAlreadyExists.source.add(table.getValueAt(l, 0).toString());
            objectAlreadyExists.destination.add(table.getValueAt(l, 1).toString());
        }
        return objectAlreadyExists;
    }

    class ObjectAlreadyExists {

        List<String> source = new ArrayList<>();
        List<String> destination = new ArrayList<>();
    }

    private void connectDestinationDatabase(List<String> destinationAlreadyExists) throws SQLException, ClassNotFoundException {

        String newIp = view.getIphostDestination().getText();

        if (newIp == null || newIp.isEmpty()) {
            JOptionPane.showMessageDialog(parentRootFrame, "O IP de destino não foi informado!");
            view.getIphostDestination().grabFocus();
        } else if (!validateIp(newIp)) {
            JOptionPane.showMessageDialog(parentRootFrame, "O IP de destino informado é inválido!");
            view.getIphostDestination().grabFocus();
        } else {
            ParamDatabase param = new ParamDatabase((SGDB) view.getSgbdDestination().getSelectedItem(), view.getIphostDestination().getText(), view.getPortDestination().getText(), view.getUserDestination().getText(), new String(view.getPasswordDestination().getPassword()), view.getDatabaseDestination().getText());
            SortedListModel model = new SortedListModel();
            List<String> tables = DatabaseFactory.getDatabase(param).listTables();
            for (String tableName : tables) {
                if (!destinationAlreadyExists.contains(tableName)) {
                    model.add(tableName);
                }
            }
            view.getListTableDestination().setModel(model);
            b2.setVisible(true);
        }
    }

    private void checkJtable(JTable t, int column) {
        boolean columnEmpty = false;
        for (int i = 0; i < t.getRowCount(); i++) {
            if (t.getValueAt(i, column) == null || t.getValueAt(i, column).toString().isEmpty()) {
                columnEmpty = true;
            }
        }
        if (!columnEmpty) {
            ((DefaultTableModel) t.getModel()).addRow(new Object[]{"", "", new DetailsModel(), ""});
        }
    }

    private void cleanupRowEmptyTableEntity(JTable t, BalloonTip b) {
        List<Integer> rowEmpty = new ArrayList<>();
        for (int i = 0; i < t.getRowCount(); i++) {
            if ((t.getValueAt(i, 0) == null || t.getValueAt(i, 0).toString().isEmpty())
                    && (t.getValueAt(i, 1) == null
                    || t.getValueAt(i, 1).toString().isEmpty())) {
                rowEmpty.add(i);
            }
        }

        for (Integer row : rowEmpty) {

            Object obj = t.getValueAt(row, 2);
            if (obj != null && (obj instanceof DetailsModel)) {
                DetailsModel dm = (DetailsModel) obj;
                if (dm.getEntity() != null && dm.getEntity().getId() > 0) {
                    entityDao.deletar(dm.getEntity());
                    ((DefaultTableModel) t.getModel()).removeRow(row);
                }
            }
        }
        if (t.getRowCount() > 0) {
            b.setVisible(true);
        }
    }

    private void cleanupRowEmptyTableField(JTable t, BalloonTip b) {
        List<Integer> rowEmpty = new ArrayList<>();
        for (int i = 0; i < t.getRowCount(); i++) {
            if ((t.getValueAt(i, 0) == null || t.getValueAt(i, 0).toString().isEmpty())
                    && (t.getValueAt(i, 1) == null
                    || t.getValueAt(i, 1).toString().isEmpty())) {
                rowEmpty.add(i);
            }
        }

        for (Integer row : rowEmpty) {
            Object obj = t.getValueAt(row, 2);
            if (obj != null && (obj instanceof Field)) {
                Field field = (Field) obj;
                if (field.getId() > 0) {
                    Entity entity = ((DetailsModel)view.getTableRelationship().getValueAt(view.getTableRelationship().getSelectedRow(), 2)).getEntity();
                    entity.getField().remove(field);
                    fieldDao.deletar(field);
                    ((DefaultTableModel) t.getModel()).removeRow(row);
                }
            }
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

        fillDataField(detail.getFields());

        ObjectAlreadyExists objectAlreadyExists = getObjectAlreadyExists(viewDetail.getTableRelationship());

        b4.setVisible(true);
        b5.setVisible(true);
        ParamDatabase param = new ParamDatabase((SGDB) view.getSgbdSource().getSelectedItem(), ((AddressSource) view.getIphostSource().getSelectedItem()).getIp(), view.getPortSource().getText(), view.getUserSource().getText(), new String(view.getPasswordSource().getPassword()), view.getDatabaseSource().getText());
        SortedListModel model = new SortedListModel();
        Map<String, Integer> fieldsSource = DatabaseFactory.getDatabase(param).listFieldsTable(tableSource);

        for (Map.Entry<String, Integer> entrySet : fieldsSource.entrySet()) {
            String field = entrySet.getKey();
            if (!objectAlreadyExists.source.contains(field)) {
                model.add(field);
            }
        }
        loadComboFieldKey(fieldsSource);
        viewDetail.getListFieldSource().setModel(model);

        param = new ParamDatabase((SGDB) view.getSgbdDestination().getSelectedItem(), view.getIphostDestination().getText(), view.getPortDestination().getText(), view.getUserDestination().getText(), new String(view.getPasswordDestination().getPassword()), view.getDatabaseDestination().getText());
        model = new SortedListModel();
        Map<String, Integer> fieldsDestination = DatabaseFactory.getDatabase(param).listFieldsTable(tableDestination);
        for (Map.Entry<String, Integer> entrySet : fieldsDestination.entrySet()) {
            String field = entrySet.getKey();
            if (!objectAlreadyExists.source.contains(field)) {
                model.add(field);
            }
        }
        loadComboFieldKeyDestination(fieldsDestination);
        viewDetail.getListFieldDestination().setModel(model);
        viewDetail.getStatus().setText("<html>Mapeamento campo a campo entre as tabelas:"
                + "<br>" + tableSource + " (" + view.getSgbdSource().getSelectedItem() + ")<br>" + tableDestination + "(" + view.getSgbdDestination().getSelectedItem() + ")</html>");

        viewDetail.getFieldKey().setSelectedItem(detail.getNameKeySource());
        viewDetail.getQueryCondition().setText(detail.getConditionSource() != null ? detail.getConditionSource() : "");

        viewDetail.getFieldKeyDestination().setSelectedItem(detail.getNameKeyDestination());
        viewDetail.getValueKeyDestination().setText(detail.getValueKeyDestination() != null ? detail.getValueKeyDestination() : "");

        viewDetail.setVisible(true);

        detail.setNameKeySource(viewDetail.getFieldKey().getSelectedIndex() >= 0 ? viewDetail.getFieldKey().getSelectedItem().toString() : "");
        detail.setConditionSource(viewDetail.getQueryCondition().getText());
        detail.setNameKeyDestination(viewDetail.getFieldKeyDestination().getSelectedIndex() >= 0 ? viewDetail.getFieldKeyDestination().getSelectedItem().toString() : "");
        detail.setValueKeyDestination(viewDetail.getValueKeyDestination().getText());

        detail.getFields().clear();
        for (int i = 0; i < viewDetail.getTableRelationship().getRowCount(); i++) {

            Field field = null;
            Object obj = viewDetail.getTableRelationship().getValueAt(i, 2);

            if (obj != null && (obj instanceof Field)) {
                field = (Field) obj;
            } else {
                field = new Field();
            }

            field.setNameFieldSource(viewDetail.getTableRelationship().getValueAt(i, 0).toString());
            field.setTypeSource(fieldsSource.get(field.getNameFieldSource()));
            field.setNameFieldDestination(viewDetail.getTableRelationship().getValueAt(i, 1).toString());
            field.setTypeDestination(fieldsDestination.get(field.getNameFieldDestination()));
            detail.getFields().add(field);
        }
        view.getTableRelationship().repaint();
    }

    private void construct() {
        view = new SQLBuilderGUI(this);
        viewDetail = new DetailRelationshipGUI(parentRootFrame, true, this);
        viewAddressSource = new EditAddressSourceGUI(parentRootFrame, true);
        addActionButtonConnect();
        addActionButtonSave();
        addActionButtonEditAddressSource();
        addActionButtonRemoveAddressSource();
        addActionButtonAddAddressSource();
        addActionCloseDetail();
        addActionButtonCloseEditAddressSource();
        addActionButtonHistoricEditAddressSource();
        loadComboSgdb();
        configureBaloonTip();
        configureListTransfer();
        configureListTransferDetail();
        configureTableRelationship();
        Session sessao = new CriadorDeSessao().getSession();
        daoSource = new SourceDao(sessao);
        daoAddress = new AddressSourceDao(sessao);
        entityDao = new EntityDao(sessao);
        fieldDao = new FieldDao(sessao);
        view.getComboTask().setEnabled(false);
    }

    private void fillDataSource(Source source) {
        List<AddressSource> addressSources = daoAddress.bySource(source);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (AddressSource addressSource : addressSources) {
            model.addElement(addressSource);
        }
        view.getIphostSource().setModel(model);
        view.getPortSource().setText(source.getPort());
        view.getUserSource().setText(source.getUserName());
        view.getPasswordSource().setText(source.getPassword());
        view.getDatabaseSource().setText(source.getDatabaseName());
        view.getSgbdSource().setSelectedItem(SGDB.byID(source.getTipo()));
    }

    private void fillDataDestination(Destination destination) {
        view.getIphostDestination().setText(destination.getIp());
        view.getPortDestination().setText(destination.getPort());
        view.getUserDestination().setText(destination.getUserName());
        view.getPasswordDestination().setText(destination.getPassword());
        view.getDatabaseDestination().setText(destination.getDatabaseName());
        view.getSgbdDestination().setSelectedItem(SGDB.byID(destination.getTipo()));
    }

    private void fillDataEntity(Collection<Entity> entitys) {
        ((DefaultTableModel) view.getTableRelationship().getModel()).setRowCount(0);
        for (Entity entity : entitys) {
            DetailsModel dm = new DetailsModel();
            dm.setEntity(entity);
            dm.setNameKeySource(entity.getNameKeySource());
            dm.setConditionSource(entity.getConditionSource());
            dm.setNameKeyDestination(entity.getNameKeyDestination());
            dm.setValueKeyDestination(entity.getValueKeyDestination());
            dm.setFields(new ArrayList<>(entity.getField()));
            ((DefaultTableModel) view.getTableRelationship().getModel()).addRow(new Object[]{
                entity.getEntitySource(),
                entity.getEntityDestination(),
                dm,
                ""});
        }
    }

    private void fillDataField(Collection<Field> fields) {
        ((DefaultTableModel) viewDetail.getTableRelationship().getModel()).setRowCount(0);
        for (Field field : fields) {
            ((DefaultTableModel) viewDetail.getTableRelationship().getModel()).addRow(new Object[]{
                field.getNameFieldSource(),
                field.getNameFieldDestination(),
                field
            });
        }
    }

}
