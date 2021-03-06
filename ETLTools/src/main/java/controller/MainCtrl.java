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
import java.util.concurrent.ScheduledExecutorService;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import util.ExceptionHandler;
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

    private ResourceBundle bundle;
    private TaskDao taskDao;
    private List<Task> tasks;
    private ScheduledExecutorService scheduleExecutor;

    public MainCtrl() {
        setup();
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

        for (int i = 0; i < frames.length; i++) {
            if (!frames[i].isIcon()) {
                try {
                    frames[i].setMaximum(false);

                    frames[i].reshape(x, y, frames[i].getWidth(), frames[i].getHeight());

                    x += 100;
                    y += 100;

                    if (x + frames[i].getWidth() > desktop.getWidth()) {
                        x = 0;
                    }

                    if (y + frames[i].getHeight() > desktop.getHeight()) {
                        y = 0;
                    }

                } catch (PropertyVetoException e) {
                }

            }

        }

    }

    private void addActionButtonRun() {
        view.getButtonRun().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runTask(tasks);
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

    private void setColorButtonRun(boolean run) {
        if (run) {
            view.getButtonRun().setBackground(new Color(51, 153, 255));
            view.getButtonRun().setForeground(new java.awt.Color(255, 255, 255));
        } else {
            view.getButtonRun().setBackground(new Color(255, 99, 71));
            view.getButtonRun().setForeground(new java.awt.Color(255, 255, 255));
        }
    }

    private void setup() {
        view = new MainGUI(bundle);
        setColorButtonRun(false);
        addActionButtonRun();
        addActionButtonCreateTask();
        addActionButtonSQLBuilder();
        view.setVisible(true);
        taskDao = new TaskDao(new CriadorDeSessao().getSession());
        addActionList();
        tasks = taskDao.list();
        reloadList(tasks);
    }

    public static void main(String[] args) {
        try {
            Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
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

    private void addActionButtonSQLBuilder() {
        view.getButtonSQLBuilder().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSQLBuilder();
            }
        });
    }

    private void showSQLBuilder() {
        SQLBuilderCtrl sqlBuilder = new SQLBuilderCtrl(view);
        if (view.getList().getSelectedValue() != null) {
            sqlBuilder.setup((Task) view.getList().getSelectedValue());
            showInternalFrame(sqlBuilder.getView(), view.getButtonSQLBuilder().getText());
        } else {
            new JOptionPane().showMessageDialog(view, "É necessário selecionar uma tarefa!");
        }
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

            if (scheduleExecutor != null) {
                scheduleExecutor.shutdown();
                scheduleExecutor = null;
                setColorButtonRun(false);
            } else {
                for (final Task task : tasks) {
                    if (task.isActive()) {
                        scheduleExecutor = new PerformerTask() {
                            @Override
                            public void taskEvent(final Task t, final String message) {
                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        view.getLog().setText(view.getLog().getText()
                                                .concat(new Date().toString()).concat(" - ")
                                                .concat(message)
                                                .concat(": ").concat(t.getDescription()).concat("\n"));

                                    }
                                });
                            }
                        }.execute(task);

                        setColorButtonRun(true);
                    }
                }
            }
        }
    }

}
