/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author cesar
 */
public final class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread aThread, Throwable aThrowable) {
        aThrowable.printStackTrace();
        new ErrorDialog(aThrowable).setVisible(true);
    }

}
