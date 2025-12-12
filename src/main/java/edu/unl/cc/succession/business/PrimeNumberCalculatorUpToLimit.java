package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 * @author franz ludeña
 * 10.
 */
public class PrimeNumberCalculatorUpToLimit implements Successionable {
    private Integer limit;
    private Integer currentTerm;
    private final StringBuilder printableTerms;

    public PrimeNumberCalculatorUpToLimit(Number limit) {
        this(0,limit);
    }

    public PrimeNumberCalculatorUpToLimit(Number start,Number limit) {
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
        long result = 0;
        while(currentTerm < limit.intValue()){
            this.printableTerms.append(currentTerm).append(" + ");
            result += this.currentTerm;
            currentTerm = nextTerm(currentTerm).intValue();
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
