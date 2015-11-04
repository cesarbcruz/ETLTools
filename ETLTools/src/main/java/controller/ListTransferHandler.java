/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.cesar.etltools.model.ParamDatabase;
import java.awt.Component;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.TransferHandler;



/**
 *
 * @author cesar
 */
public class ListTransferHandler extends TransferHandler {

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return (support.getComponent() instanceof JTextField) && support.isDataFlavorSupported(ListItemTransferable.LIST_ITEM_DATA_FLAVOR);
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            boolean accept = false;
            if (canImport(support)) {
                try {
                    Transferable t = support.getTransferable();
                    Object value = t.getTransferData(ListItemTransferable.LIST_ITEM_DATA_FLAVOR);
                    if (value instanceof ParamDatabase) {
                        Component component = support.getComponent();
                        if (component instanceof JTextField) {
                            ((JTextField)component).setText(((ParamDatabase)value).getIpHost());
                            accept = true;
                        }
                    }
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            return accept;
        }

        @Override
        public int getSourceActions(JComponent c) {
            return DnDConstants.ACTION_COPY_OR_MOVE;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            Transferable t = null;
            if (c instanceof JList) {
                JList list = (JList) c;
                Object value = list.getSelectedValue();
                if (value instanceof ParamDatabase) {
                    ParamDatabase li = (ParamDatabase) value;
                    t = new ListItemTransferable(li);
                }
            }
            return t;
        }

        @Override
        protected void exportDone(JComponent source, Transferable data, int action) {
            System.out.println("ExportDone");
            // Here you need to decide how to handle the completion of the transfer,
            // should you remove the item from the list or not...
        }
    }
