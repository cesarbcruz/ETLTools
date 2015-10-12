/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author cesar
 */
public class Messages {

    public static boolean confirm(Component parentComponent, String message, String title, String labelOK, String labelCancel) {
        int option = JOptionPane.showOptionDialog(parentComponent,
                message,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{labelOK, labelCancel},
                labelOK);

        return option == 0;
    }

    public static void information(Component parentComponent, String message, String title) {
        JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void error(Component parentComponent, String message, String title) {
        JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
