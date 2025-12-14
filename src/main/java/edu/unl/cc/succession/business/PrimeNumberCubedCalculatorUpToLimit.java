package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 * Ejercicio #2:
 * Esta clase calcula la serie de números primos elevados al cubo hasta un límite:
 * (S = 2^3 + 3^3 + 5^3 + 7^3 + 11^3 + 13^3 +...)
 * @author HenryDev (Jeam Romero)
 */

public class PrimeNumberCubedCalculatorUpToLimit implements Successionable {
    private Integer limit;
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public PrimeNumberCubedCalculatorUpToLimit(Number limit) {
        this(0, limit);
    }

    public PrimeNumberCubedCalculatorUpToLimit(Number start, Number limit) {
        if (start.intValue() < 0) {
            throw new IllegalArgumentException("Start must be greater than or equal to 0");
        }
        setLimit(limit);
        this.currentTerm = (isPrime(start.intValue())) ? start.intValue() : nextTerm(start).intValue();
        printableTerms = new StringBuilder("S = ");
    }

    /**
     * Este metodo verifica si un número dado es un número primo.
     *
     * @param number El número entero a verificar.
     * @return true si el número es primo, false en caso contrario.
     */
    private boolean isPrime(Integer number) {
        if (number < 2) {
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
    public void setLimit(Number limit) {
        if (limit.intValue() < 0) {
            throw new IllegalArgumentException("Limit must be greater than or equal to 0");
        }
        this.limit = limit.intValue();
    }

    /**
     * Calcula la suma de la serie de los números primos elevados al cubo hasta el límite.
     * La lógica implica iterar, encontrar el siguiente primo y acumular su cubo
     * mientras el primo sea menor que el límite.
     *
     * @return La suma total de los términos de la serie (primos al cubo).
     */
    @Override
    public Number calculate() {
        long result = 0;
        int exponent = 3;
        while (currentTerm < limit) {
            this.printableTerms.append(currentTerm).append("^").append(exponent).append(" + ");
            result = (long) (result + Math.pow(currentTerm, exponent));
            currentTerm = nextTerm(currentTerm).intValue();
        }
        return result;
    }

    /**
     * Encuentra el siguiente número primo después del término actual dado.
     * Este método es la parte central de la lógica de la sucesión.
     *
     * @param currentTerm El número desde el cual se comenzará a buscar el siguiente primo.
     * @return El siguiente número primo encontrado.
     */
    @Override
    public Number nextTerm(Number currentTerm) {
        int nextCandidate = currentTerm.intValue() + 1;
        Boolean isPrime = false;
        while (!isPrime) {
            isPrime = isPrime(nextCandidate);
            if (!isPrime) {
                nextCandidate++;
            }
        }
        return nextCandidate;
    }

    @Override
    public String print() {
        return printableTerms.toString();
    }
}