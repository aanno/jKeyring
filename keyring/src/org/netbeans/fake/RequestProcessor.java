package org.netbeans.fake;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RequestProcessor {

    // TODO tp: better implementation
    private static final ExecutorService EXECUTOR_SERVICE =
            Executors.newFixedThreadPool(4);

    private Class<?> clazz;

    public RequestProcessor(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void post(Runnable runnable) {
        EXECUTOR_SERVICE.submit(runnable);
    }

    public <T> Future<T> submit(Callable<T> callable) {
        return EXECUTOR_SERVICE.submit(callable);
    }
}
