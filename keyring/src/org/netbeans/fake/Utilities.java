package org.netbeans.fake;

import org.openide.util.BaseUtilities;

public final class Utilities {

    private Utilities() {
        // Never invoked
    }

    public static boolean isWindows() {
        return BaseUtilities.isWindows();
    }

    public static boolean isMac() {
        return BaseUtilities.isMac();
    }

    public static boolean isUnix() {
        return BaseUtilities.isUnix();
    }
}
