package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 * @author Usuario iTC(Francisco Antonio Chamba Diaz).
 * Ejercicio #5.
 * Esta clase calcula serie de primos elevados a impares hasta n térmimos
 * (S = S = 1^1 + 3^3 + 5^5 + 7^7 + 11^9 + 13^11 ..):
 */

public class PrimeSeriesToOddPowerByTermCount implements Successionable {

    private Integer limit;
    private Integer currentTerm;
    private final StringBuilder printableTerms;

    public PrimeSeriesToOddPowerByTermCount(Integer limit) {
        this(0, limit);
    }

    public PrimeSeriesToOddPowerByTermCount(Number start, Integer limit) {
        if (start.intValue() < 0) {
            throw new IllegalArgumentException("Start number must be greater than 0");
        }
        setLimit(limit);
        currentTerm = (isPrime(start.intValue())) ? start.intValue() : nextTerm(start).intValue();
        this.printableTerms = new StringBuilder("S = ");
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
                currentTerm = currentTerm.intValue() + 1;
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
        long result = 0;
        int counterTerm = 0;
        int exponent = 1;
        while (counterTerm < limit) {
            this.printableTerms.append(currentTerm).append("^").append(exponent).append(" + ");
            result = (long) (result + Math.pow(currentTerm, exponent));
            currentTerm = nextTerm(currentTerm).intValue();
            counterTerm++;
            exponent += 2;
        }

        return result;
    }


    @Override
    public String print() {
        return printableTerms.toString();
    }
}