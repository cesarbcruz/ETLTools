package controller;

import com.cesar.etltools.model.SortedListModel;
import javax.swing.*;

public abstract class ListTransferHandler extends StringTransferHandler {

    static String objectItemList = null;
    static int indexRowTable = -1;
    static int indexColumnTable = -1;
    static int column = 0;
    static String substitutedValue = null;
    static JComponent componentExport = null;
    static boolean transferOK = false;

    @Override
    protected String exportString(JComponent c) {
        componentExport = c;
        if (c instanceof JList) {
            JList list = (JList) c;
            objectItemList = list.getSelectedValue().toString();
            column = Integer.parseInt(list.getName());
            eventExportJList();
            return list.getSelectedValue().toString();
        } else if (c instanceof JTable) {
            JTable table = (JTable) c;
            indexRowTable = table.getSelectedRow();
            indexColumnTable = table.getSelectedColumn();
            return table.getValueAt(indexRowTable, indexColumnTable).toString();
        }
        transferOK = false;
        return "";
    }

    @Override
    protected void importString(JComponent c, String str) {
        if (componentExport instanceof JList && c instanceof JTable) {
            JTable table = (JTable) c;
            if (!table.getValueAt(table.getSelectedRow(), column).toString().isEmpty()) {
                substitutedValue = table.getValueAt(table.getSelectedRow(), column).toString();
            }
            table.setValueAt(str, table.getSelectedRow(), column);
            transferOK = true;
        } else if (componentExport instanceof JTable && c instanceof JList) {
            JList list = (JList) c;
            if (indexColumnTable == Integer.parseInt(list.getName())) {
                ((SortedListModel) list.getModel()).add(str);
                transferOK = true;
            }
        }
    }

    @Override
    protected void cleanup(JComponent c, boolean remove) {
        if (transferOK) {
            if (c instanceof JList) {
                if (remove && objectItemList != null && !objectItemList.isEmpty()) {
                    JList source = (JList) c;
                    if (substitutedValue != null && !substitutedValue.isEmpty()) {
                        ((SortedListModel) source.getModel()).add(substitutedValue);
                        substitutedValue = null;
                    }
                    ((SortedListModel) source.getModel()).removeElement(objectItemList);
                    objectItemList = null;
                }
            } else if (c instanceof JTable) {
                JTable table = (JTable) c;
                table.setValueAt("", indexRowTable, indexColumnTable);
                indexRowTable = -1;
                indexColumnTable = -1;
            }
            transferOK = false;
        }
        eventCleanup();
    }

    abstract void eventExportJList();

    abstract void eventCleanup();

}
