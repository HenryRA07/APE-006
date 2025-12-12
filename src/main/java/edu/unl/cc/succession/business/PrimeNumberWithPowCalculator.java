package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 * @author franz ludeña
 *3.
 *
 */

public class PrimeNumberWithPowCalculator implements Successionable {
    private Integer limit;
    private Integer currentTerm;
    private final StringBuilder printableTerms;

    public PrimeNumberWithPowCalculator(Number limit) {
        this(0,limit);
    }

    public PrimeNumberWithPowCalculator(Number start, Number limit) {
        if(start.intValue() < 0 ) {
            throw new IllegalArgumentException("Start must be greater than 0");
        }
        setLimit(limit);
        currentTerm = nextTerm(start).intValue();
        printableTerms = new StringBuilder("S = ");
    }

    private boolean primeValidate(Integer number){
        for (int i = 2; i < number.intValue(); i++){
            if (number.intValue() % i == 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public void setLimit(Number limite) {
        if (limite.intValue() < 0){
            throw new IllegalArgumentException("Limit must be greater than 0");
        }
        this.limit = limite.intValue();
    }

    @Override
    public Number calculate() {
        Double result = 0.0;
        int counterTerm = 0;
        int exponentTerm = 3;
        int currentTerm = this.currentTerm > 0 ? this.currentTerm - 1 : 0;
        while (counterTerm < limit) {
            currentTerm = nextTerm(currentTerm).intValue();
            this.printableTerms.append(currentTerm).append("^").append(exponentTerm).append(" + ");
            result = result + Math.pow(currentTerm, exponentTerm);
            counterTerm++;
        }
        return result;
    }

    @Override
    public Number nextTerm(Number currentTerm) {
        currentTerm = currentTerm.intValue() + 1;
        boolean isprime = false;
        while(!isprime){
            isprime = primeValidate(currentTerm.intValue());
            if(!isprime){
                currentTerm = currentTerm.intValue() + 1;
            }
        }
        return currentTerm;
    }

    @Override
    public String print() {
        return printableTerms.toString();
    }
}
