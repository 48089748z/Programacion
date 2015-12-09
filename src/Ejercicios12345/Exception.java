package Ejercicios12345;

import java.util.*;

/**
 * Created by 48089748z on 08/10/15.
 */
public class Exception
{

    public static void main(String[] args)
    {
        //---------------------------------------------------------------------------------------------------------------
        //Ejercicio 3
        System.out.println("\n\n\nEJERCICIO 3");
        int[] myIntArray = new int[5];
        try
        {
            System.out.println("ESTAMOS EN EL TRY");
            for (int x=0; x<=myIntArray.length; x++)
            {
                myIntArray[x]=x;
                System.out.println(myIntArray[x]);
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("\nESTAMOS EN EL CATCH\nERROR ---> "+e);
        }

        //---------------------------------------------------------------------------------------------------------------
        //Ejercicio 4
        System.out.println("\n\n\nEJERCICIO 4");
        try
        {
            MyException e = new MyException("Esto es lo que ha de imprimir");
            System.out.println(e.getCadena());
        }
        catch (Throwable x)
        {
            System.out.println("Error: ---> "+x);
        }

        //---------------------------------------------------------------------------------------------------------------
        //Ejercicio 5
        Scanner in = new Scanner(System.in);
        System.out.println("\n\n\nEJERCICIO 5");
        while (true)
        {
            System.out.println("Escribe un numero para que paren las excepciones");
            String entrada= in.nextLine();
            try
            {
                Integer.parseInt(entrada);
                System.out.println("Has introducido el numero "+entrada+" y han parado las excepciones");
                break;
            }
            catch (Throwable e)
            {
                System.out.println("\nError ---> "+e);
            }
        }
        //---------------------------------------------------------------------------------------------------------------
        //Ejercicio 6



    }
}



























