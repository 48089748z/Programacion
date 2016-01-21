package VectorQueue.Queue;

import java.util.Date;
import java.util.PriorityQueue;

/**
 * Created by 48089748z on 19/01/16.
 */
public class Impresora
{
    private static PriorityQueue<Peticion> priorityQueue = new PriorityQueue<>(10, new Ordenador());


    public static void main(String[] args)
    {
        Peticion peticion1 = new Peticion("Oriol", "PRIMERA PETICIÓN - Prioridad ALTA", 2, new Date());
        priorityQueue.add(peticion1);
        Peticion peticion2 = new Peticion("Sandra", "SEGUNDA PETICIÓN - Prioridad BAJA", 0, new Date());
        priorityQueue.add(peticion2);
        Peticion peticion3 = new Peticion("Alejandro", "TERCERA PETICIÓN - Prioridad MEDIA", 1, new Date());
        priorityQueue.add(peticion3);
        Peticion peticion4 = new Peticion("Sergi", "CUARTA PETICIÓN - Prioridad ALTA", 2, new Date());
        priorityQueue.add(peticion4);
        Peticion peticion5 = new Peticion("Manuel", "QUINTA PETICIÓN - Prioridad BAJA", 0, new Date());
        priorityQueue.add(peticion5);

        System.out.println("\nNUMERO DE PETICIONES QUE TIENE LA IMPRESORA: "+priorityQueue.size());

        while(priorityQueue.size()!=0)
        {
            System.out.println(priorityQueue.poll().toString());
        }
    }
}
