package de.zonlykroks.polyroots;


import java.util.Collections;
import java.util.List;

public class PolyPart<T> implements Part<T> {

    public T a, b, c;
    public Operations<T> operations;

    //a * (x + b) ^ c
    public PolyPart(T a, T b, T c, Operations<T> operations) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.operations = operations;
    }

    @Override
    public List<Part<T>> differentiate() {
        if (operations.isZero(c))
            return Collections.emptyList();

        return List.of(new PolyPart<>(operations.multiply(a, c), b, operations.minus(c, operations.one()), operations));
    }

    @Override
    public List<Part<T>> integrate() {
        if (operations.isZero(c))
            return List.of(new PolyPart<>(a, b, operations.one(), operations));

        return List.of(new PolyPart<>(
                operations.divide(a, operations.plus(c, operations.one())),
                b,
                operations.plus(c, operations.one()), operations));
    }

    @Override
    public T evaluate(T x) {
        return operations.multiply(a, operations.pow(operations.plus(x,b),c));
    }

    @Override
    public boolean vorzeichen() {
        return operations.isNegative(a);
    }

    @Override
    public String toString() {
        if(operations.isZero(c))
            return a + " # ";

        return (operations.toDouble(a) != 0 && operations.toDouble(a) != 1.0 ? a + "*" : "") + (operations.toDouble(b) != 0 ? "(" : "") + "x" + (operations.toDouble(b) == 0 ? "" : " + " + b) + (operations.toDouble(b) != 0 ? ")" : "") + (operations.toDouble(c) == 1.0 ? "" : "^" + c) + " # ";
    }
}