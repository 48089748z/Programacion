package PilaCola;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by 48089748z on 29/10/15.
 */
public class ActivitatPilaCola
{
    private static int[] pila = new int[10];
    private static boolean encontrado;

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("\nImprimiendo Array\n------------------");
        for (int x=0; x<pila.length; x++) //Llenamos el Array con numeros aleatorios entre 0 y 10
        {
            int num = 0 + (int)(Math.random()*10); //Numeros entre 0 y 10
            pila[x] = num;
            System.out.println(pila[x]);
        }
        System.out.println("------------------\nNº más alto: "+buscarNumeroMaximo()); //Imprimimos el Numero mas alto del Array
        System.out.println("Nº más bajo: " +buscarNumeroMinimo()); //Imprimimos el Numero mas bajo del Array
        System.out.println("\nAhora escribe el numero que quieres que busque y te doy su posicion en el Array, si es que está");
        int numABuscar = in.nextInt();
        buscarNumeroIndicado(numABuscar);
    }
    public static int buscarNumeroMinimo()
    {
        int numMax = pila[0];
        for (int x=0; x<pila.length; x++)
        {
            if (pila[x] <= numMax) numMax = pila[x];
        }
        return numMax;
    }
    public static int buscarNumeroMaximo()
    {
        int numMin = pila[0];
        for (int x=0; x<pila.length; x++)
        {
            if (pila[x] > numMin) numMin = pila[x];
        }
        return numMin;
    }
    public static void buscarNumeroIndicado(int numABuscar)
    {
        encontrado = false;
        for (int x=0; x<pila.length; x++)
        {
            if (pila[x] == numABuscar)
            {
                System.out.println("Encontrado el Nº "+pila[x]+" en la posición en el Array --->  "+x);
                encontrado = true;
            }
        }
        if (encontrado = false)
        {
            System.out.println("No se ha encontrado el numero");
        }
    }
}
