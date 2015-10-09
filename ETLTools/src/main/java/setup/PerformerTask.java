/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setup;

import com.cesar.etltools.dao.CriadorDeSessao;
import com.cesar.etltools.dao.TaskDao;
import com.cesar.etltools.dominio.Task;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.SECONDS;

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
        Task task = new Task(1, "teste", 10, 10, TimeUnit.SECONDS.toString());
        new TaskDao(new CriadorDeSessao().getSession()).salvar(task);
        new PerformerTask().beepForAnHour(task);
    }
}
