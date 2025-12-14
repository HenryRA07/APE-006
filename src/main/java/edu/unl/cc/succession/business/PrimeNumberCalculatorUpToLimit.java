package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 * @author franz ludeña
 * Ejercicio #10.
 * Esta clase calcula la serie de primos hasta un limite
 * (S = 1 + 2 + 3 + 5 + 7 + 11 + 13 + .. + N):
 */

public class PrimeNumberCalculatorUpToLimit implements Successionable {
    private Integer limit;
    private Integer currentTerm;
    private final StringBuilder printableTerms;

    public PrimeNumberCalculatorUpToLimit(Number limit) {
        this(0, limit);
    }

    public PrimeNumberCalculatorUpToLimit(Number start, Number limit) {
        if (start.intValue() < 0) {
            throw new IllegalArgumentException("Start must be greater than 0");
        }
        setLimit(limit);
        currentTerm = (primeValidate(start.intValue())) ? start.intValue() : nextTerm(start).intValue();
        printableTerms = new StringBuilder("S = ");
    }

    /**
     * Valida que los numero qeu se generaran en la serie sean primos
     * @param number EL numero que se evaluara
     * @return El numero primo que se encontro
     */
    private boolean primeValidate(Integer number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i < number.intValue(); i++) {
            if (number.intValue() % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setLimit(Number limite) {
        if (limite.intValue() < 0) {
            throw new IllegalArgumentException("Limit must be greater than 0");
        }
        this.limit = limite.intValue();
    }

    /**
     * Calcula la suma de la serie de los números primos elevados al cubo hasta el límite.
     *
     * @return La suma total de los términos de la serie.
     */
    @Override
    public Number calculate() {
        long result = 0;
        while (currentTerm < limit.intValue()) {
            this.printableTerms.append(currentTerm).append(" + ");
            result += this.currentTerm;
            currentTerm = nextTerm(currentTerm).intValue();
        }
        return result;
    }

    /**
     * Genera el siguiente termino en la serie de numeros asegurandose que sea primo
     * @param currentTerm El termino actual en la serie
     * @return El siguiente termino para la serie
     */
    @Override
    public Number nextTerm(Number currentTerm) {
        currentTerm = currentTerm.intValue() + 1;
        boolean isprime = false;
        while (!isprime) {
            isprime = primeValidate(currentTerm.intValue());
            if (!isprime) {
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
