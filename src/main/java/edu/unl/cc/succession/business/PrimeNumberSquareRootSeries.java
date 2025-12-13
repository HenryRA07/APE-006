package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 * Ejercicio #9:
 * Esta clase calcula la serie de primos elevados a la raiz cuadrada hasta un limite
 * (S = 1^(1/2) + 3^(1/2) + 5^(1/2) + 7^(1/2) + 11^(1/2) + 13^(1/2)+ .. + N^(1/2):
 * @author Xander (Alexander Gallo)
 */

public class PrimeNumberSquareRootSeries implements Successionable {
    private Integer limit;
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public PrimeNumberSquareRootSeries (Number limit) {
        this(0, limit);
    }

    public PrimeNumberSquareRootSeries (Number start, Number limit) {
        if (start.intValue() < 0) {
            throw new IllegalArgumentException("Start must be greater than 0");
        }
        setLimit(limit);
        currentTerm = (start.intValue() % 2 != 0) ? start.intValue() + 1 : start.intValue();
        printableTerms = new StringBuilder("S = ");
    }

    /**
     * Determina si un número es primo.
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
     * Incrementa el valor actual y continúa avanzando hasta encontrar un primo.
     *
     * @param currentTerm El valor desde el cual se comenzará a buscar el siguiente primo.
     * @return El siguiente número primo encontrado.
     */
    @Override
    public Number nextTerm(Number currentTerm) {
        currentTerm = currentTerm.intValue() + 1;
        boolean isPrime = false;
        while (!isPrime) {
            isPrime = isPrime(currentTerm.intValue());
            if (!isPrime) {
                currentTerm =  currentTerm.intValue() + 1;
            }
        }
        return currentTerm;
    }

    /**
     * Establece el limite máximo que tendrá la serie.
     * Este valor marca hasta dónde se generarán los términos.
     *
     * @param limit Límite superior permitido para los términos.
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
     * Realiza el calculo de la serie completa usando números primos, manteniendo su exponente (1/2).
     *
     * @return El valor total obtenido al sumar todos lo términos generados.
     */
    @Override
    public Number calculate() {
        double result = 0.0;
        int exponent = 2;
        int currentTerm = nextTerm(this.currentTerm).intValue();;
        while (currentTerm < limit) {
            this.printableTerms.append(currentTerm).append("^(1/").append(exponent).append(") + ");
            result += Math.pow(currentTerm, 1.0 / exponent);
            currentTerm = nextTerm(currentTerm).intValue();
        }
        return result;
    }

    @Override
    public String print() {
        return printableTerms.toString();
    }
}









