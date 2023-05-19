package de.zonlykroks.polyroots;

import java.util.List;

public class Main {

    static final Operations<Double> operations = new Operations<>() {
        @Override
        public Double one() {
            return 1.0;
        }

        @Override
        public Double zero() {
            return 0.0;
        }

        @Override
        public Double epsilon() {
            return 1.0;
        }

        @Override
        public Double oneHundred() {
            return 100.0;
        }

        @Override
        public Double plus(Double a, Double b) {
            return a+b;
        }

        @Override
        public Double minus(Double a, Double b) {
            return a-b;
        }

        @Override
        public Double multiply(Double a, Double b) {
            return a*b;
        }

        @Override
        public Double divide(Double a, Double b) {
            return a/b;
        }

        @Override
        public Double pow(Double a, Double b) {
            return Math.pow(a,b);
        }

        @Override
        public Double abs(Double a) {
            return Math.abs(a);
        }

        @Override
        public boolean greaterThan(Double a, Double b) {
            return a > b;
        }

        @Override
        public boolean isNegative(Double a) {
            return a < 0;
        }

        @Override
        public double toDouble(Double a) {
            return a;
        }

        @Override
        public boolean isZero(Double a) {
            return a == 0;
        }};

    public static void main(String[] args) {
        Equation<Double> equation = new Equation<>(
                List.of(
                        new PolyPart<>(1.0,
                                1.0,
                                1.0,
                                operations),
                        new PolyPart<>(2.0,
                                2.0,
                                2.0,
                                operations),
                        new PolyPart<>(-2.0,
                                0.0,
                                6.0,
                                operations),
                        new PolyPart<>(6.0,
                                3.0,
                                7.0,
                                operations)
                ),operations
        );

        System.out.println(equation);
        System.out.println(equation.differentiate());
        System.out.println(equation.integrate());
        System.out.println(equation.newtonRaphonAll(0.0,100));
    }

}
