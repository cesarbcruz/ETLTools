package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import util.Messages;
import view.MainGUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cesar
 */
public class MainCtrl {

    MainGUI view;
    private final Color defaultColorButto = new JButton().getBackground();
    private final String fileLanguagePtBr = "Bundle_pt_BR";
    private final String fileLanguageEnUs = "Bundle_en_US";
    private ResourceBundle bundle;

    public MainCtrl() {
        setup(fileLanguagePtBr);
    }

    private void addActionButtonCreateTask() {
        view.getButtonCreateTask().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInternalFrame(new CreateTaskCtrl(bundle).getView(), view.getButtonCreateTask().getText());
            }
        });
    }

    private void showInternalFrame(JInternalFrame internalFrame, String title) {
        view.getDesktopPane().add(internalFrame);
        internalFrame.setTitle(title);
        internalFrame.show();
    }

    private void addActionButtonPtBr() {
        view.getButtonPtBr().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setup(fileLanguagePtBr);
            }
        });
    }

    private void addActionButtonEnUS() {
        view.getButtonEn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setup(fileLanguageEnUs);
            }
        });
    }

    private void setup(String fileLanguage) {
        if (view != null) {

            if (view.getBundle().getBaseBundleName().equals("language/" + fileLanguage)) {
                return;
            }

            boolean confirm = Messages.confirm(view, view.getBundle().getString("MainGUI.messageConfirmRestart.text"),
                    view.getBundle().getString("MainGUI.messageConfirmTitle.text"), view.getBundle().getString("MainGUI.messageConfirmOK.text"),
                    view.getBundle().getString("MainGUI.messageConfirmCancel.text"));
            if (confirm) {
                view.dispose();
            } else {
                return;
            }
        }
        bundle = java.util.ResourceBundle.getBundle("language/" + fileLanguage);
        view = new MainGUI(bundle);
        view.setTitle(view.getBundle().getString("MainGUI.title.text"));
        switch (fileLanguage) {
            case fileLanguagePtBr:
                view.getButtonPtBr().setBackground(Color.orange);
                view.getButtonEn().setBackground(defaultColorButto);
                break;
            case fileLanguageEnUs:
                view.getButtonEn().setBackground(Color.orange);
                view.getButtonPtBr().setBackground(defaultColorButto);
                break;
        }
        addActionButtonPtBr();
        addActionButtonEnUS();
        addActionButtonCreateTask();
        view.setVisible(true);
    }

    public static void main(String[] args) {
        new MainCtrl();
    }

}
