package org.netbeans.fake;

import org.atteo.classindex.ClassIndex;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Lookup {

    private static final Lookup DEFAULT = new Lookup();

    public static Lookup getDefault() {
        return DEFAULT;
    }

    private final ConcurrentMap<Class<?>, Object> class2Singleton = new ConcurrentHashMap<>();

    private Lookup() {
    }

    @SuppressWarnings("unchecked")
    public <T> Iterable<? extends T> lookupAll(Class<T> type) {
        List<T> result = new ArrayList<>();
        for (Class<?> clazz : ClassIndex.getSubclasses(type)) {
            Object instance = class2Singleton.get(clazz);
            if (instance == null) {
                try {
                    instance = clazz.getDeclaredConstructor(new Class<?>[0]).newInstance();
                } catch (InstantiationException e) {
                    throw new IllegalStateException(e);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException(e);
                } catch (InvocationTargetException e) {
                    throw new IllegalStateException(e);
                } catch (NoSuchMethodException e) {
                    throw new IllegalStateException(e);
                }
                class2Singleton.putIfAbsent(clazz, instance);
            }
            result.add((T) instance);
        }
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return result.iterator();
            }
        };
    }
}
