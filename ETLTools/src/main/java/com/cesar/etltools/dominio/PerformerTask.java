/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar.etltools.dominio;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 *
 * @author cesar
 */
public abstract class PerformerTask {

    public PerformerTask() {
    }

    private final ScheduledExecutorService scheduler
            = Executors.newScheduledThreadPool(1);

    public void execute(final Task t) {
        final Runnable taskRun;
        taskRun = new Runnable() {
            @Override
            public void run() {
                System.out.println("Execute Task: " + t.getDescription());
                new MigrateData().execute(t);
                taskEvent(t);
            }
        };

        final ScheduledFuture<?> executeHandle
                = scheduler.scheduleAtFixedRate(taskRun, t.getInitialDelay(), t.getPeriod(), TimeUnit.valueOf(t.getUnit()));
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                executeHandle.cancel(true);
            }
        }, 60 * 60, SECONDS);
    }

    public abstract void taskEvent(Task t);
}
