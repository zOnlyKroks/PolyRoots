package de.zonlykroks.polyroots;

import java.util.List;

public interface Part<T> {
   List<Part<T>> differentiate();
    List<Part<T>> integrate();
    T evaluate(T x);

    boolean vorzeichen();

    String toString();
}
