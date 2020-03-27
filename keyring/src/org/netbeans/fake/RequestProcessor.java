package org.netbeans.fake;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RequestProcessor {

    private static final ExecutorService EXECUTOR_SERVICE =
            Executors.newFixedThreadPool(4);

    private Class<?> clazz;

    public RequestProcessor(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void post(Runnable runnable) {
        // TODO tp: better implementation
        // Thread thread = new Thread(runnable, "RequestProcessor:" + clazz.getSimpleName());
        // thread.start();
        EXECUTOR_SERVICE.submit(runnable);
    }

    public <T> Future<T> submit(Callable<T> callable) {
        return EXECUTOR_SERVICE.submit(callable);
    }
}
