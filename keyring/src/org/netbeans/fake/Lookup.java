package org.netbeans.fake;

public class Lookup {

    private static final Lookup DEFAULT = new Lookup();

    public static Lookup getDefault() {
        return DEFAULT;
    }

    private Lookup() {

    }

    public <T> T[] lookupAll(Class<T> type) {
        // TODO tp: implement
        return (T[]) new Object[0];
    }
}
