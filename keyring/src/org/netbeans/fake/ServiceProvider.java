package org.netbeans.fake;

import org.atteo.classindex.IndexAnnotated;

import java.lang.annotation.Inherited;

@Inherited
@IndexAnnotated
public @interface ServiceProvider {

    Class<?> service();

    int position();
}
