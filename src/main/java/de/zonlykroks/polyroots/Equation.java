package de.zonlykroks.polyroots;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Equation<T> {

    public List<Part<T>> equationParts;
    public Operations<T> operations;

    public Equation(List<Part<T>> equtionParts,Operations<T> operations) {
        this.equationParts = equtionParts;
        this.operations = operations;
    }

    public Equation<T> differentiate() {
        List<Part<T>> difParts = new ArrayList<>();

        equationParts.forEach(tPart -> {
            List<Part<T>> dif = tPart.differentiate();
            difParts.addAll(dif);
        });

        return new Equation<>(difParts,operations);
    }

    public Equation<T> integrate() {
        List<Part<T>> intParts = new ArrayList<>();

        equationParts.forEach(tPart -> {
            List<Part<T>> dif = tPart.integrate();
            intParts.addAll(dif);
        });

        return new Equation<>(intParts,operations);
    }

    public T evaluate(T x) {
        final T[] temp = (T[]) new Object[]{operations.zero()};

        equationParts.forEach(tPart -> {
            temp[0] = operations.plus(temp[0],tPart.evaluate(x));
        });

        return temp[0];
    }

    public List<T> newtonRaphonAll(T initial, int iterationCount) {
        List<T> results = new ArrayList<>();

        T x0 = initial;

        for(int i = 0; i < iterationCount; i++) {
            T result = newtonRaphson(x0);
            results.add(result);
            x0 = operations.plus(x0,operations.epsilon());

            if(operations.toDouble(result) > 2) {
                results.remove(result);
            }
        }

        return results.stream().distinct().collect(Collectors.toList());
    }

    public T newtonRaphson(T x0) {
        int it_count=0; /* iteration counter */
        int count_max=1000; /* maximum allowed number of iterations*/
        double tol = 1E-24;
        double error = 1.0;

        T x = null;

        while((it_count<=count_max) && (error>=tol))
        {
            /* loop while count<=10 and Error >= 1E-12 */
            x = operations.minus(x0,operations.divide(evaluate(x0), differentiate().evaluate(x0))); /* Newton-Raphson method*/
            error = operations.toDouble(operations.abs(operations.minus(x,x0))); /* error  */
            it_count++; /*update count*/
            x0=x; /* update root*/
        }
        return x;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < equationParts.size(); i++) {
            String current = equationParts.get(i).toString();

            if(i + 1 < equationParts.size()) {
                Part<T> next = equationParts.get(i + 1);

                boolean isNegative = next.vorzeichen();

                if(isNegative) {
                    current = current.replace("#", "");
                }else {
                    current = current.replace("#","+");
                }

                sb.append(current);
            }else {
                sb.append(current.replace("#",""));
            }
        }

        return sb.toString();
    }
}
