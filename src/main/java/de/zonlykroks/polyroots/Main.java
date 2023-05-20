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
                        new SinFunction<>(operations,false)
                ),operations
        );

        System.out.println(equation);
        System.out.println(equation.differentiate());
    }

}
