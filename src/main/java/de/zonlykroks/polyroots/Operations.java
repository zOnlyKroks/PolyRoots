package de.zonlykroks.polyroots;

public interface Operations<T> {

    T one();

    T zero();

    T epsilon();

    T oneHundred();
    T plus(T a, T b);
    T minus(T a, T b);
    T multiply(T a, T b);
    T divide(T a, T b);

    T pow(T a, T b);

    T abs(T a);

    boolean greaterThan(T a, T b);

    boolean isNegative(T a);

    double toDouble(T a);

    boolean isZero(T a);
}
