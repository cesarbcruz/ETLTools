/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dominio;

import com.cesar.etltools.dao.CriadorDeSessao;
import com.cesar.etltools.dao.TaskDao;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.SECONDS;
import org.hibernate.Session;

/**
 *
 * @author cesar
 */
public class PerformerTask {

    private final ScheduledExecutorService scheduler
            = Executors.newScheduledThreadPool(1);

    public void beepForAnHour(Task task) {
        final Runnable beeper = new Runnable() {
            public void run() {
                System.out.println("beep");
            }
        };

        final ScheduledFuture<?> beeperHandle
                = scheduler.scheduleAtFixedRate(beeper, task.getInitialDelay(), task.getPeriod(), TimeUnit.valueOf(task.getUnit()));
        scheduler.schedule(new Runnable() {
            public void run() {
                beeperHandle.cancel(true);
            }
        }, 60 * 60, SECONDS);
    }

    public static void main(String[] args) {
        Session s = new CriadorDeSessao().getSession();
        TaskDao dao = new TaskDao(s);
        Task task = new Task("teste", 10, 10, TimeUnit.SECONDS.toString());
        dao.begin();
        dao.salvar(task);
        dao.commit();

        System.out.println(task.getId());

        List<Task> tasks = dao.porDescricao("teste");

        if (tasks != null && !tasks.isEmpty()) {
            new PerformerTask().beepForAnHour(tasks.get(0));
        }
        s.close();
    }
}
