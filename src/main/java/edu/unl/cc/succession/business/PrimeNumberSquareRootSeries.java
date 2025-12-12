package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 * Ejercicio #9.
 * Serie de primos elevados a la raiz cuadrada hasta un limite (S = 1^(1/2)
 * + 3^(1/2) + 5^(1/2) + 7^(1/2) + 11^(1/2) + 13^(1/2)+ .. + N^(1/2):
 * @author Xander (Alexander Gallo)
 */

public class PrimeNumberSquareRootSeries implements Successionable {
    private Integer limit;
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public PrimeNumberSquareRootSeries (Number limit) {
        this(0, limit);
    }

    public PrimeNumberSquareRootSeries (Number start, Number limit) {
        if (start.intValue() < 0) {
            throw new IllegalArgumentException("Start must be greater than 0");
        }
        setLimit(limit);
        currentTerm = (start.intValue() % 2 != 0) ? start.intValue() + 1 : start.intValue();
        printableTerms = new StringBuilder("S = ");
    }
    private boolean isPrime(Integer number) {
        if (number < 1) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Number nextTerm(Number currentTerm) {
        currentTerm = currentTerm.intValue() + 1;
        boolean isPrime = false;
        while (!isPrime) {
            isPrime = isPrime(currentTerm.intValue());
            if (!isPrime) {
                currentTerm =  currentTerm.intValue() + 1;
            }
        }
        return currentTerm;
    }

    @Override
    public void setLimit(Number limit) {
        if (limit.intValue() < 0) {
            throw new IllegalArgumentException("Limit must be greater than 0");
        }
        this.limit = limit.intValue();
    }

    @Override
    public Number calculate() {
        double result = 0.0;
        int exponent = 2;
        int currentTerm = this.currentTerm < 1 ? this.currentTerm -1 : 1;
        while (currentTerm < limit) {
            this.printableTerms.append(currentTerm).append("^(1/").append(exponent).append(") + ");
            currentTerm = nextTerm(currentTerm).intValue();
            result += Math.pow(currentTerm, 1.0 / exponent);
        }
        return result;
    }
    @Override
    public String print() {
        return printableTerms.toString();
    }
}









