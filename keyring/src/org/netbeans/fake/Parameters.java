package org.netbeans.fake;

public final class Parameters {

    private Parameters() {
        // Never invoked
    }

    public static void notNull(String name, Object actual) {
        assert actual != null : "notNull(" + name + ") violated";
    }
}
