package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 * @author Usuario iTC(Francisco Antonio Chamba Diaz).
 * Ejercicio #4.
 * Estq clase calcula la serie de primos elevados por pares hasta un limite
 * (S = 1^2 + 3^4 + 5^6 + 7^8 + 11^10 + 13^(12) ... + N):
 *
 */

public class PrimeSeriesElevatedByPairsUpToLimit implements Successionable {
    private Integer limit;
    private Integer currentTerm;
    private final StringBuilder printableTerms;

    public PrimeSeriesElevatedByPairsUpToLimit(Integer limit) {
        this(0, limit);
    }

    public PrimeSeriesElevatedByPairsUpToLimit(Number start, Integer limit) {
        if (start.intValue() < 0){
            throw new IllegalArgumentException("Start number must be greater than 0");
        }
        setLimit(limit);
        currentTerm = (isPrime(start.intValue())) ? start.intValue() : nextTerm(start).intValue();
        this.printableTerms = new StringBuilder("S = ");
    }
    private boolean isPrime(Integer number){
        if (number < 1) {
            return false;
        }
        for (int i = 2; i < number; i++){
            if (number % i == 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public Number nextTerm(Number currentTerm) {
        currentTerm= currentTerm.intValue() + 1;
        boolean isPrime = false;
        while (!isPrime){
            isPrime = isPrime(currentTerm.intValue());
            if (!isPrime){
                currentTerm = currentTerm.intValue() + 1;
            }
        }
        return currentTerm;
    }

    @Override
    public void setLimit(Number limit) {
        if (limit.intValue() < 0){
            throw new IllegalArgumentException("Limit must be greater than 0");
        }
        this.limit = limit.intValue();
    }

    @Override
    public Number calculate() {
        long result = 0;
        int exponent = 2;
        while (currentTerm <= limit) {
            this.printableTerms.append(currentTerm).append("^").append(exponent).append(" + ");
            result = (long) (result + Math.pow(currentTerm, exponent));
            currentTerm = nextTerm(currentTerm).intValue();
            exponent+=2;
        }

        return result;
    }

    @Override
    public String print() {
        return printableTerms.toString();
    }
}