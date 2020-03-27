package org.netbeans.fake;

import java.util.ResourceBundle;

public class NbBundle {

    private NbBundle() {
        //
    }

    public static ResourceBundle getBundle(String bundle) {
        // TODO tp: caching
        return ResourceBundle.getBundle(bundle);
    }

    public static String getMessage(Class<?> clazz, String key) {
        return getBundle(clazz.getName()).getString(key);
    }
}
