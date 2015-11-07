package controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListTransferHandler extends StringTransferHandler {

    static int indexItemList = -1;
    static int indexRowTable = -1;
    static int indexColumnTable = -1;
    static int column = 0;

    @Override
    protected String exportString(JComponent c) {
        if (c instanceof JList) {
            JList list = (JList) c;
            indexItemList = list.getSelectedIndex();
            column = Integer.parseInt(list.getName());
            return list.getSelectedValue().toString();
        } else if (c instanceof JTable) {
            JTable table = (JTable) c;
            indexRowTable = table.getSelectedRow();
            indexColumnTable = table.getSelectedColumn();
            return table.getValueAt(indexRowTable, indexColumnTable).toString();
        }
        return "";
    }

    @Override
    protected void importString(JComponent c, String str) {
        if (c instanceof JTable) {
            JTable table = (JTable) c;
            if (table.getSelectedRow() < 0) {
                ((DefaultTableModel) table.getModel()).addRow(new Object[]{"", "", ""});
            }
            table.setValueAt(str, table.getSelectedRow(), column);
        } else if (c instanceof JList) {
            JList list = (JList) c;
            ((DefaultListModel) list.getModel()).addElement(str);
        }
    }

    @Override
    protected void cleanup(JComponent c, boolean remove) {
        if (c instanceof JList) {
            if (remove && indexItemList > -1) {
                JList source = (JList) c;
                ((DefaultListModel) source.getModel()).remove(indexItemList);
                indexItemList = -1;
            }
        } else if (c instanceof JTable) {
            JTable table = (JTable) c;
            table.setValueAt("", indexRowTable, indexColumnTable);
            if (table.getValueAt(indexRowTable, 0).toString().isEmpty()
                    && table.getValueAt(indexRowTable, 1).toString().isEmpty()) {
                ((DefaultTableModel) table.getModel()).removeRow(indexRowTable);
            }
            indexRowTable = -1;
            indexColumnTable = -1;
        }
    }

}
