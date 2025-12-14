package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 * @author franz ludeña
 * Ejercicio #3.
 * Esta clase calcula serie de primos elevados al cubo  hasta N términos
 * (S = 1^3 + 3^3 + 5^3 + 7^3 + 11^3 + 13^3 ...):
 */

public class PrimeNumberWithPowCalculator implements Successionable {
    private Integer limit;
    private Integer currentTerm;
    private final StringBuilder printableTerms;

    public PrimeNumberWithPowCalculator(Number limit) {
        this(0, limit);
    }

    public PrimeNumberWithPowCalculator(Number start, Number limit) {
        if (start.intValue() < 0) {
            throw new IllegalArgumentException("Start must be greater than 0");
        }
        setLimit(limit);
        currentTerm = (primeValidate(start.intValue())) ? start.intValue() : nextTerm(start).intValue();
        printableTerms = new StringBuilder("S = ");
    }

    /**
     * Valida que un numero sea primo
     * @param number Numero a evaluar
     * @return Verdadero si el numero es primo; falso si no
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
     * Calcula el resultado final de la suma de la serie generada
     * @return El resultado de la serie
     */
    @Override
    public Number calculate() {
        long result = 0;
        int counterTerm = 0;
        int exponentTerm = 3;
        while (counterTerm < limit) {
            this.printableTerms.append(currentTerm).append("^").append(exponentTerm).append(" + ");
            result = (long) (result + Math.pow(currentTerm, exponentTerm));
            currentTerm = nextTerm(currentTerm).intValue();
            counterTerm++;
        }
        return result;
    }

    /**
     * Genera el siguiente termino en la serie
     * @param currentTerm El termino actual en la serie
     * @return El termino siguiente para la serie
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
