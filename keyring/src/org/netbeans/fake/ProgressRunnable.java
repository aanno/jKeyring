package org.netbeans.fake;

public interface ProgressRunnable<T> {

    T run(ProgressHandle handle);

}
