package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;
/**
 *  @autor Grupo2 (PIA)
 *   - Integrantes:
 *      - Francisco Chamba
 *      - Alexander Gallo
 *      - Franz Ludeña
 *      - Jeam Romero
 *
 * Representa el calculo de serie de primos elevados por pares hasta un limite
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
        currentTerm = nextTerm(start).intValue();
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
        double result = 0.0;
        int exponent = 2;
        currentTerm = this.currentTerm > 0 ? this.currentTerm - 1 : 0;
        while (currentTerm < limit) {
            this.printableTerms.append(currentTerm).append("^").append(exponent).append(" + ");
            currentTerm = nextTerm(currentTerm).intValue();
            result = result + Math.pow(currentTerm, exponent);
            exponent+=2;
        }

        return result;
    }

    @Override
    public String print() {
        return printableTerms.toString();
    }
}