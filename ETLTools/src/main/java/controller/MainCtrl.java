package controller;

import com.cesar.etltools.dao.CriadorDeSessao;
import com.cesar.etltools.dao.TaskDao;
import com.cesar.etltools.dominio.PerformerTask;
import com.cesar.etltools.dominio.Task;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import org.hibernate.Session;
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
    private TaskDao taskDao;

    public MainCtrl() {
        setup(fileLanguagePtBr);
    }

    private void addActionButtonCreateTask() {
        view.getButtonCreateTask().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCreateTask(null);
            }
        });

    }

    private void reloadList(List<Task> tasks) {
        DefaultComboBoxModel model = new DefaultComboBoxModel(tasks.toArray());
        view.getList().setModel(model);
    }

    private void showInternalFrame(JInternalFrame internalFrame, String title) {
        view.getDesktopPane().add(internalFrame);
        internalFrame.setTitle(title);
        internalFrame.show();
        cascadeWindows(view.getDesktopPane());
    }

    public void cascadeWindows(JDesktopPane desktop) {
        JInternalFrame[] frames = desktop.getAllFrames();
        int x = 0;
        int y = 0;
        int width = 389;
        int height = 214;

        for (int i = 0; i < frames.length; i++) {
            if (!frames[i].isIcon()) {
                try {
                    frames[i].setMaximum(false);

                    frames[i].reshape(x, y, width, height);

                    x += 100;
                    y += 100;

                    if (x + width > desktop.getWidth()) {
                        x = 0;
                    }

                    if (y + height > desktop.getHeight()) {
                        y = 0;
                    }

                } catch (PropertyVetoException e) {
                }

            }

        }

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

    private void addActionList() {
        view.getList().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                JList theList = (JList) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2) {
                    int index = theList.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        JList source = (JList) mouseEvent.getSource();
                        Task task = (Task) source.getSelectedValue();
                        showCreateTask(task);
                    }
                }
            }
        });

        view.getList().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    JList source = (JList) ke.getSource();
                    Task task = (Task) source.getSelectedValue();
                    showCreateTask(task);
                }
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
        Session sessao = new CriadorDeSessao().getSession();
        taskDao = new TaskDao(sessao);
        addActionList();
        List<Task> tasks = taskDao.list();
        reloadList(tasks);
        sessao.close();
        runTask(tasks);
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        new MainCtrl();
    }

    private void showCreateTask(Task t) {
        CreateTaskCtrl ctv = new CreateTaskCtrl(bundle, taskDao) {
            @Override
            public void event(List<Task> tasks) {
                reloadList(tasks);
            }
        };
        ctv.setTask(t);
        showInternalFrame(ctv.getView(), view.getButtonCreateTask().getText());
    }

    private void runTask(final List<Task> tasks) {
        if (tasks != null && !tasks.isEmpty()) {
            new Thread(){
                @Override
                public void run() {
                    for (Task task : tasks) {
                        new PerformerTask() {
                            @Override
                            public void taskEvent(Task t) {
                                view.getLog().setText(view.getLog().getText()
                                        .concat(new Date().toString()).concat(" - ")
                                        .concat(view.getBundle().getString("MainGUI.messageRunTask.text"))
                                        .concat(": ").concat(t.getDescription()).concat("\n"));
                            }
                        }.execute(task);
                    }
                }
            }.start();
        }
    }

}
