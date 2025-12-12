package edu.unl.cc.succession;


import edu.unl.cc.succession.business.*;
import edu.unl.cc.succession.domain.Successionable;

import java.util.Scanner;

public class Main {
    public static void printMenu(){
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("CALCULADORA DE SERIES ");
        System.out.println("1 Serie de numeros pares hasta un limite (S = 2 + 4 + 6 + 8 + ... N): ");
        System.out.println("2 Serie de primos elevados al cubo  hasta un limite (S = 1^3 + 3^3 + 5^3 + 7^3 + 11^3 + 13^3 ... + N^3): ");
        System.out.println("3 Serie de primos elevados al cubo  hasta N términos (S = 1^3 + 3^3 + 5^3 + 7^3 + 11^3 + 13^3 ...): ");
        System.out.println("4 Serie de primos elevados por pares hasta un limite (S = 1^2 + 3^4 + 5^6 + 7^8 + 11^10 + 13^(12) ... + N): ");
        System.out.println("5 Serie de primos elevados a impares hasta n térmimos (S = S = 1^1 + 3^3 + 5^5 + 7^7 + 11^9 + 13^11 ..): ");
        System.out.println("6 Serie de primos elevados a la raiz de numeros pares hasta un limite (S = 1^(1/2) + 3^(1/4) + 5^(1/6) + 7^(1/8) + 11^(1/10) + 13^(1/12) ... + N): ");
        System.out.println("7 Serie de primos elevados a la raiz de numeros impares hasta un n términos (S = 1^(1/1) + 3^(1/3) + 5^(1/5) + 7^(1/7) + 11^(1/9) + 13^(1/11)): ");
        System.out.println("8 Serie de primos elevados a la raiz cúbica hasta un n términos (S = 1^(1/3) + 3^(1/3) + 5^(1/3) + 7^(1/3) + 11^(1/3) + 13^(1/3)): ");
        System.out.println("9 Serie de primos elevados a la raiz cuadrada hasta un limite (S = 1^(1/2) + 3^(1/2) + 5^(1/2) + 7^(1/2) + 11^(1/2) + 13^(1/2)+ .. + N^(1/2): ");
        System.out.println("10 Serie de primos hasta un limite (S = 1 + 2 + 3 + 5 + 7 + 11 + 13 + .. + N: ");
        System.out.println("PRESIONE 0 PARA SALIR DEL PROGRAMA");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    public static int readOption(Scanner scanner){
        int option;
        System.out.println("Elija la serie que quiera calcular: ");
        option = scanner.nextInt();
        return option;
    }
    public static void printEnd(){
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("HA PRESIONADO EL NUMERO 0");
        System.out.println("SALIENDO DEL PROGRAMA");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printMenu();
        int option = 0;
        do {
            option = readOption(scanner);
            System.out.print("Debe ingresar un limite/n termios :");
            int limit = scanner.nextInt();
            scanner.nextLine();
            Successionable succesionable = null;
            switch(option){
                case (1):{
                    succesionable = new EvenNumberCalculatorUpToLimit(limit);
                    break;
                }
                case (2):{
                    succesionable = new PrimeNumberCubedCalculatorUpToLimit(limit);
                    break;
                }
                case (3):{
                    succesionable = new PrimeNumberWithPowCalculator(limit);
                    break;
                }
                case (4):{
                    succesionable = new PrimeSeriesElevatedByPairsUpToLimit(limit);
                    break;
                }
                case (5):{
                    succesionable = new PrimeSeriesToOddPowerByTermCount(limit);
                    break;
                }
                case (6):{
                    succesionable = new PrimeRootEvenSeriesUpToLimit(limit);
                    break;
                }
                case (7):{
                    succesionable = new PrimeNumberWithPowCalculatorWithTerm(limit);
                    break;
                }
                case (8):{
                    succesionable = new PrimeNumbeWithPowCubicCalculatorWithTerm(limit);
                    break;
                }
                case (9):{
                    succesionable = new PrimeNumberSquareRootSeries(limit);
                    break;
                }
                case (10):{
                    succesionable = new PrimeNumberCalculatorUpToLimit(limit);
                    break;
                }
                default:{
                    System.out.println("opcion invalida");
                    break;
                }
            }
            if(succesionable != null){
                Number result = succesionable.calculate();
                System.out.println(succesionable.print());
                System.out.println("S = "+result+"\n");
            }
        } while (option != 0);
        printEnd();

    }
}