package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 * @author franz ludeña
 * Ejercicio #7.
 * Esta clase calcula serie de primos elevados a la raiz de numeros impares hasta un n términos
 * (S = 1^(1/1) + 3^(1/3) + 5^(1/5) + 7^(1/7) + 11^(1/9) + 13^(1/11)):
 */

public class PrimeNumberWithPowCalculatorWithTerm implements Successionable {
    private Integer limit;
    private Integer currentTerm;
    private final StringBuilder printableTerms;

    public PrimeNumberWithPowCalculatorWithTerm(Number limit) {
        this(0, limit);
    }

    public PrimeNumberWithPowCalculatorWithTerm(Number start, Number limit) {
        if (start.intValue() < 0) {
            throw new IllegalArgumentException("Start must be greater than 0");
        }
        setLimit(limit);
        currentTerm = (primeValidate(start.intValue())) ? start.intValue() : nextTerm(start).intValue();
        printableTerms = new StringBuilder("S = ");
    }

    /**
     * Valida que un numero sea primo
     * @param number EL que se recive para evaluar
     * @return Verdadero si el numero es primo falso si no
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

    @Override
    public Number calculate() {
        Double result = 0.0;
        int counterTerm = 0;
        int exponentTerm = 1;
        while (counterTerm < limit) {
            this.printableTerms.append(currentTerm).append("^(1/").append(exponentTerm).append(") +");
            result = result + Math.pow(currentTerm, 1.0 / exponentTerm);
            currentTerm = nextTerm(currentTerm).intValue();
            counterTerm++;
            exponentTerm += 2;
        }
        return result;
    }

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
