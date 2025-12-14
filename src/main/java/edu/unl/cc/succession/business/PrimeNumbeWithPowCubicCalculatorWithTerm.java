package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 * Ejercicio #8:
 * Esta clase calcula la serie de números primos elevados a la raiz cúbica hasta un n términos
 * (S = 1^(1/3) + 3^(1/3) + 5^(1/3) + 7^(1/3) + 11^(1/3) + 13^(1/3))
 * @author HenryDev (Jeam Romero)
 */

public class PrimeNumbeWithPowCubicCalculatorWithTerm implements Successionable {
    private Integer limit;
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public PrimeNumbeWithPowCubicCalculatorWithTerm(Number limit) {
        this(0, limit);
    }

    public PrimeNumbeWithPowCubicCalculatorWithTerm(Number start, Number limit) {
        if (start.intValue() < 0) {
            throw new IllegalArgumentException("Start must be greater than 0");
        }
        setLimit(limit);
        this.currentTerm = (isPrime(start.intValue())) ? start.intValue() : nextTerm(start).intValue();
        printableTerms = new StringBuilder("S = ");
    }

    @Override
    public void setLimit(Number limit) {
        if (limit.intValue() < 0) {
            throw new IllegalArgumentException("Limit must be greater than 0");
        }
        this.limit = limit.intValue();
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

    /**
     * Calcula la suma de la serie de los números primos elevados al cubo hasta el límite.
     * La lógica implica iterar, encontrar el siguiente primo y acumular su cubo
     * mientras el primo sea menor que el límite.
     *
     * @return La suma total de los términos de la serie (primos al cubo).
     */
    @Override
    public Number calculate() {
        Double result = 0.0;
        int counterTerm = 0;
        int exponent = 3;
        while (counterTerm < limit) {
            this.printableTerms.append(currentTerm).append("^(1/").append(exponent).append(") + ");
            result = result + Math.pow(currentTerm, 1.0 / exponent);
            currentTerm = nextTerm(currentTerm).intValue();
            counterTerm++;
        }
        return result;
    }

    @Override
    public Number nextTerm(Number currentTerm) {
        currentTerm = currentTerm.intValue() + 1;
        Boolean isPrime = false;
        while (!isPrime) {
            isPrime = isPrime(currentTerm.intValue());
            if (!isPrime) {
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

