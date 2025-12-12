package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 *  @autor Grupo2 (PIA)
 *   - Integrantes:
 *      - Francisco Chamba
 *      - Alexander Gallo
 *      - Franz Ludeña
 *      - Jeam Romero
 */
public class EvenNumberCalculatorUpToLimit implements Successionable {


    private Integer limit;
    private Integer currentTerm;
    private final StringBuilder printableTerms;


    public EvenNumberCalculatorUpToLimit(Number limit) {
        this(0,limit);
    }

    public EvenNumberCalculatorUpToLimit(Number start,Number limit) {
        if(start.intValue() < 0 ) {
            throw new IllegalArgumentException("Start must be greater than 0");
        }
        if(limit.intValue() < 0) {
            throw new IllegalArgumentException("Limit must be greater than 0");
        }
        currentTerm = (start.intValue() % 2 != 0) ? start.intValue() + 1 : start.intValue();
        setLimit(limit);
        printableTerms = new StringBuilder("S = ");
    }

    @Override
    public void setLimit(Number limite) {
    }

    @Override
    public Number calculate() {
        return null;
    }

    @Override
    public Number nextTerm(Number currentTerm) {
        return null;
    }

    @Override
    public void print() {

    }
}