package de.zonlykroks.polyroots;

public interface Operations<T> {

    T one();

    T negativeOne();

    T zero();

    T epsilon();

    T plus(T a, T b);
    T minus(T a, T b);
    T multiply(T a, T b);
    T divide(T a, T b);

    T pow(T a, T b);

    T abs(T a);

    T sin(T a);

    T cos(T a);

    T tan(T a);

    T E();

    boolean isNegative(T a);

    double toDouble(T a);

    boolean isZero(T a);

    boolean isPositiveOne(T a);
}
