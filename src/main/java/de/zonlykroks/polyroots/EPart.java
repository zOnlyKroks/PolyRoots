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
    public T evaluate(T x) {
        T toPowPreX = operations.multiply(xStauchStreck, operations.multiply(x,flipY));
        T toPowWithX = operations.plus(toPowPreX,xVerschiebung);
        T powed = operations.pow(operations.E(),toPowWithX);
        T negativeE = operations.multiply(powed,flipX);

        T multiplied = operations.multiply(negativeE,yStauchStreck);

        T withY = operations.plus(multiplied,yRichtung);

        return withY;
    }

    @Override
    public String toString() {
        return (operations.isZero(yStauchStreck) || operations.isPositiveOne(yStauchStreck) ?  "" : operations.toDouble(yStauchStreck) + " * ") + (operations.isNegative(flipX) ? "-" : "") + "e^(" + operations.toDouble(xStauchStreck) + " * " + (operations.isNegative(flipY) ? "-" : "") + "x + " + operations.toDouble(xVerschiebung) + " ) + " + operations.toDouble(yRichtung);
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
        T cd = operations.multiply(xStauchStreck,flipY);

        EPart<T> partOne = new EPart<>(
                operations.divide(yStauchStreck,cd),
                operations.isNegative(flipX),
                operations.divide(xStauchStreck,cd),
                operations.isNegative(flipY),
                operations.divide(xVerschiebung,cd),
                operations.divide(yRichtung,cd),
                operations
        );

        return List.of(partOne,this);
    }

    @Override
    public boolean vorzeichen() {
        return !(operations.toDouble(yStauchStreck) == 0 || operations.toDouble(yStauchStreck) == 1.0);
    }
}
