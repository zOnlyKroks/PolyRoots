package de.zonlykroks.polyroots;

import java.util.List;

public class EPart<T> implements Part<T>{

    public Operations<T> operations;

    public T yStauchStreck,flipX,xStauchStreck,flipY,xVerschiebung,yRichtung;

    //flipY -> -1 = Spieglung y Achse
    //flipX -> -1 = Spiegelung x-Achse
    //yRichtung -> Verschiebung oben, unten
    //xVerschiebung -> links = +Zahl, rechts = -Zahl
    //yStauchStreck -> Streckung bei x > 1,Stauchung bei 0 < x < 1
    //xStauchStreck -> Stauchung bei x > 1, Streckung bei 0 < x < 1

    //yStauchStreck * flipX(1,-1) * e ^ (xStauchStreck * x * flipY - xVerschiebung) + yRichtung
    public EPart(T yStauchStreck, boolean flipX, T xStauchStreck, boolean flipY, T xVerschiebung, T yRichtung, Operations<T> operations) {
        this.operations = operations;
        this.yStauchStreck = yStauchStreck;
        this.flipX = flipX ?  operations.negativeOne() : operations.one();
        this.xStauchStreck = xStauchStreck;
        this.flipY = flipY ? operations.negativeOne() : operations.one();
        this.xVerschiebung = xVerschiebung;
        this.yRichtung = yRichtung;
    }

    @Override
    public String toString() {
        return (operations.isZero(yStauchStreck) || operations.isPositiveOne(yStauchStreck) ?  "" : operations.toDouble(yStauchStreck) + " * ") + (operations.isNegative(flipX) ? "-" : "") +  "e^(" + operations.toDouble(xStauchStreck) + " * " + (operations.isNegative(flipY) ? "-" : "") + "x + " + operations.toDouble(xVerschiebung) + " ) + " + operations.toDouble(yRichtung);
    }

    @Override
    public List<Part<T>> differentiate() {
        T temp = operations.multiply(yStauchStreck,flipY);
        temp =operations.multiply(temp,xStauchStreck);

        EPart<T> ePart = new EPart<>(
                temp,
                operations.isNegative(flipX),
                xStauchStreck,
                operations.isNegative(flipY),
                xVerschiebung,
                operations.zero(),
                operations
        );

        return List.of(ePart);
    }

    @Override
    public List<Part<T>> integrate() {
        return null;
    }

    @Override
    public T evaluate(T x) {
        T partOne = operations.multiply(yStauchStreck, flipX);
        T temp = operations.multiply(xStauchStreck,operations.multiply(x,flipY));
        T temp2 = operations.plus(temp,xVerschiebung);
        T partTwo = operations.pow(operations.E(),temp2);

        T preY = operations.multiply(partOne,partTwo);

        return operations.plus(preY,yRichtung);
    }

    @Override
    public boolean vorzeichen() {
        return !(operations.toDouble(yStauchStreck) == 0 || operations.toDouble(yStauchStreck) == 1.0);
    }
}
