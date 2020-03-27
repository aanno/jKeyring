package org.netbeans.fake;

import java.util.prefs.Preferences;

public class NbPreferences {

    public static final String APP_ROOT = "com.github.aanno.jkeyring";

    private static final Preferences appPrefs = Preferences.userRoot().node(APP_ROOT);

    private NbPreferences() {
        // Never invoked
    }

    public static <T> Preferences forModule(Class<T> clazz) {
        return appPrefs.node(clazz.getName());
    }
}
