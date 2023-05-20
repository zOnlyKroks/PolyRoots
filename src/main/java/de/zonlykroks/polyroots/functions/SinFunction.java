package de.zonlykroks.polyroots.functions;

import de.zonlykroks.polyroots.Operations;
import de.zonlykroks.polyroots.Part;

import java.util.Collections;
import java.util.List;

public class SinFunction<T> implements Part<T> {

    private Operations<T> operations;
    public boolean isNegative;

    public SinFunction(Operations<T> operations,boolean isNegative) {
        this.operations = operations;
        this.isNegative = isNegative;
    }

    @Override
    public List<Part<T>> differentiate() {
        return Collections.singletonList(new CosFunction<>(operations, isNegative));
    }

    @Override
    public List<Part<T>> integrate() {
        return null;
    }

    @Override
    public T evaluate(T a) {
        return operations.multiply(isNegative ? operations.negativeOne() : operations.one(), operations.sin(a));
    }

    @Override
    public boolean vorzeichen() {
        return isNegative;
    }

    @Override
    public String toString() {
        return (isNegative ? "-" : "") + "sin(x) #";
    }
}
