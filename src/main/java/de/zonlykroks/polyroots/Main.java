package de.zonlykroks.polyroots;

import de.zonlykroks.polyroots.functions.SinFunction;

import java.util.List;

public class Main {

    static final Operations<Double> operations = new Operations<>() {
        @Override
        public Double one() {
            return 1.0;
        }

        @Override
        public Double negativeOne() {
            return -1.0;
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
        public Double sin(Double a) {
            return Math.sin(a);
        }

        @Override
        public Double cos(Double a) {
            return Math.cos(a);
        }

        @Override
        public Double tan(Double a) {
            return Math.tan(a);
        }

        @Override
        public Double E() {
            return Math.E;
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
        }

        @Override
        public boolean isPositiveOne(Double a) {
            return a == 1.0;
        }
    };

    public static void main(String[] args) {
        Equation<Double> equation = new Equation<>(
                List.of(
                        new EPart<>(
                                1.0,
                                false,
                                1.0,
                                false,
                                0.0,
                                0.0,
                                operations
                        )
                ),operations
        );

        System.out.println(equation);
        System.out.println(equation.integrate());
        System.out.println(equation.integrate().evaluate(1.0));
    }

}
