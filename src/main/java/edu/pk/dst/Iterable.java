package edu.pk.dst;

import java.util.Iterator;
import java.util.function.Consumer;

public interface Iterable<T> {
    Iterator iterator();

    void forEach(Consumer<T> consumer);
}
