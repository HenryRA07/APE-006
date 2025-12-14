package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 * @author Xander (Alexander Gallo)
 * Ejercicio #6.
 * Esta clase calcula serie de primos elevados a la raiz de numeros pares hasta un limite
 * S = 1^(1/2) + 3^(1/4) + 5^(1/6) + 7^(1/8) + 11^(1/10) + 13^(1/12) ... + N
 */

public class PrimeRootEvenSeriesUpToLimit implements Successionable {
    private Integer limit;
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public PrimeRootEvenSeriesUpToLimit(Number limit) {
        this(0, limit);
    }

    public PrimeRootEvenSeriesUpToLimit(Number start, Number limit) {
        if (start.intValue() < 0) {
            throw new IllegalArgumentException("Start must be greater than 0");
        }
        setLimit(limit);
        currentTerm = (isPrime(start.intValue())) ? start.intValue() : nextTerm(start).intValue();
        printableTerms = new StringBuilder("S = ");
    }

    /**
     * Comprueba si un número es primo.
     *
     * @param number Número que se desea evaluar.
     * @return true si el número es primo; false si no lo es.
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
     * Obtiene el siguiente número primo después del término recibido.
     *
     * @param currentTerm Número desde el cual se comenzará a buscar el siguiente primo.
     * @return El siguiente número primo encontrado.
     */
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

    /**
     * Establece el límite máximo que tendrá la serie.
     * Este valor marca hasta dónde se generarán los términos.
     *
     * @param limit Límite superior permitido para lo términos.
     * @throws IllegalArgumentException Si el límite es negativo.
     */
    @Override
    public void setLimit(Number limit) {
        if (limit.intValue() < 0) {
            throw new IllegalArgumentException("Limit must be greater than 0");
        }
        this.limit = limit.intValue();
    }

    /**
     * Realiza el calculo de la serie completa usando números primos, incrementando su exponente en 2.
     *
     * @return El valor numérico total resultante de sumar todos los términos generados.
     */
    @Override
    public Number calculate() {
        double result = 0.0;
        int exponent = 2;
        while (currentTerm <= limit) {
            this.printableTerms.append(currentTerm).append("^(1/").append(exponent).append(") + ");
            result += Math.pow(currentTerm, 1.0 / exponent);
            currentTerm = nextTerm(currentTerm).intValue();
            exponent += 2;
        }
        return result;
    }

    @Override
    public String print() {
        return printableTerms.toString();
    }
}