package org.netbeans.fake;

public final class Places {

    private Places() {
        // Never invoked
    }

    public static String getUserDirectory() {
        return System.getProperty("user.home");
    }
}
