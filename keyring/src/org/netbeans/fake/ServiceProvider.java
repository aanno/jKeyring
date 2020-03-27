package org.netbeans.fake;

public @interface ServiceProvider {

    Class<?> service();

    int position();
}
