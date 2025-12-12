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
 *     Representa el Calculo de serie de primos elevados a impares hasta n térmimos
 *     (S = S = 1^1 + 3^3 + 5^5 + 7^7 + 11^9 + 13^11 ..):
 */

public class PrimeSeriesToOddPowerByTermCount implements Successionable {

    private Integer limit;
    private Integer currentTerm;
    private final StringBuilder printableTerms;

    public PrimeSeriesToOddPowerByTermCount(Integer limit) {
        this(0, limit);
    }

    public PrimeSeriesToOddPowerByTermCount(Number start, Integer limit) {
        if (start.intValue() < 0){
            throw new IllegalArgumentException("Start number must be greater than 0");
        }
        setLimit(limit);
        currentTerm = nextTerm(start).intValue();
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
        double result = 0.0;
        int counterTerm = 0;
        int exponent = 1;
        currentTerm = this.currentTerm > 0 ? this.currentTerm - 1 : 0;
        while (counterTerm < limit) {
            currentTerm = nextTerm(currentTerm).intValue();
            this.printableTerms.append(currentTerm).append("^").append(exponent).append(" + ");
            result = result + Math.pow(currentTerm, exponent);
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