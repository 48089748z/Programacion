package Examen1OCunado;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Controlador
{
    private static  Scanner in = new Scanner(System.in);
    private static Map citas = new HashMap<>();
    private static Integer index=0;

    public static void main(String[] args)
    {
        //TE DOY UNA CITA CON PERSONA Y DIASEMANA YA CREADOS PARA QUE HAGAS PRUEBAS, TU PUEDES AÑADIR MAS MANUALMENTE O BORRAR ESTA CITA CON EL MENU DEL PROGRAMA
        /*Persona persona = new Persona();
        persona.setNombre("Oriol");
        persona.setDni("48089748Z");

        DiaSemana diaSemana = DiaSemana.VIERNES;

        Cita cita = new Cita();
        cita.setPersona(persona);
        cita.setDiaSemana(diaSemana);
        cita.setFecha("11/12/2015");
        cita.setMatricula("4827");
        cita.setDescripcionAveria("Falla el motor y la rueda trasera derecha esta pinchada");
        cita.setPresupuesto("270€");
        citas.put(index, cita);*/

        //AQUI EMPIEZA EL PROGRAMA
        boolean stop = false;
        while (!stop)
        {
            System.out.println("\n --- --- --- MENU --- --- ---\n0: SALIR\n1: AÑADIR CITA\n2: BORRAR CITA\n3: MOSTRAR CITAS");
            Integer opcion = in.nextInt();
            switch (opcion)
            {
                case 1:
                {
                    añadirCita();
                    break;
                }
                case 2:
                {
                    try
                    {
                        borrarCita();
                        break;
                    }
                    catch(MapVacio mapVacio)
                    {
                        System.out.println(mapVacio.error() + " (NO HAY CITAS QUE BORRAR EN EL MAP)");
                        break;
                    }
                }
                case 3:
                {
                    try
                    {
                        mostrarCitas();
                        break;
                    }
                    catch(MapVacio mapVacio)
                    {
                        System.out.println(mapVacio.error()+" (NO HAY CITAS QUE MOSTRAR EN EL MAP)");
                        break;
                    }
                }
                default:
                {
                    stop = true;
                    break;
                }
            }
        }
    }
    public static DiaSemana queDiaHaEntradoPorPantalla(String diaSemana)
    {
        switch(diaSemana.toUpperCase())
        {
            case "LUNES":
            {
                return DiaSemana.LUNES;
            }
            case "MARTES":
            {
                return DiaSemana.MARTES;
            }
            case "MIERCOLES":
            {
                return DiaSemana.MIERCOLES;
            }
            case "JUEVES":
            {
                return DiaSemana.JUEVES;
            }
            case "VIERNES":
            {
                return DiaSemana.VIERNES;
            }
            case "SABADO":
            {
                return DiaSemana.SABADO;
            }
            case "DOMINGO":
            {
                return DiaSemana.DOMINGO;
            }
            default:
            {
                return null;
            }
        }
    }
    public static void añadirCita()
    {
        System.out.println("\nPARA CREAR UNA CITA NECESITO CIERTOS DATOS\nIntroduce el nombre del cliente"); //PREGUNTAMOS VARIABLE DE PERSONA Y LAS ASIGNAMOS
        String nombre = in.next();
        System.out.println("\nIntroduce el DNI del cliente");
        String dni = in.next();
        Persona newPersona = new Persona();
        newPersona.setNombre(nombre);
        newPersona.setDni(dni);

        DiaSemana newDiaSemana = null;
        boolean failed = true;
        while (failed)
        {
            System.out.println("\nIntroduce el dia de la semana"); //PREGUNTAMOS VARIABLE DE DIASEMANA Y LA ASIGNAMOS TAMBIEN COMPROVAMOS QUE SEA VALOR VALIDO DE LA ENUM!
            String diaSemana = in.next();
            if (queDiaHaEntradoPorPantalla(diaSemana) == null)
            {
                System.out.println("\nEsto es una enum, los unicos valores validos son LUNES MARTES MIERCOLES JUEVES VIERNES SABADO DOMINGO\nIntentalo de nuevo");
            }
            else
            {
                newDiaSemana = queDiaHaEntradoPorPantalla(diaSemana);
                failed = false;
            }
        }

        System.out.println("\nIntroduce la fecha");//PREGUNTAMOS VARIABLES DE CITA Y LAS ASIGNAMOS
        String fecha = in.next();
        System.out.println("\nIntroduce la matricula");
        String matricula = in.next();
        System.out.println("\nIntroduce la descripcion de la averia");
        String descripcion = in.next();

        Cita newCita = new Cita();
        newCita.setPersona(newPersona);
        newCita.setDiaSemana(newDiaSemana);
        newCita.setFecha(fecha);
        newCita.setMatricula(matricula);
        newCita.setDescripcionAveria(descripcion);

        index++;
        citas.put(index, newCita);
        System.out.println("\nCITA AÑADIDA");
    }
    public static void borrarCita() throws MapVacio
    {
        if (citas.size()==0)
        {
            throw new MapVacio();
        }
        else
        {
            System.out.println("\nPARA BORRAR UNA CITA NECESITO CIERTOS DATOS\nIntroduce el nombre del cliente");
            String nombre = in.next();
            System.out.println("\n Introduce la fecha de la cita");
            String fecha = in.next();
            for (int x=0; x<citas.size(); x++)
            {
                Cita aComprobar = (Cita) citas.get(x);
                if(aComprobar.getPersona().getNombre().toLowerCase().equals(nombre.toLowerCase()) && aComprobar.getFecha().toLowerCase().equals(fecha.toLowerCase()))
                {
                    citas.remove(x);
                    System.out.println("\nCITA BORRADA");
                }
            }
        }
    }
    public static void mostrarCitas() throws MapVacio
    {
        if (citas.size() ==0)
        {
            throw new MapVacio();
        }
        else
        {
            System.out.println("--- --- --- LISTA DE TODAS LAS CITAS DEL MAP --- --- ---");
            System.out.println("\n"+citas.values());
        }
    }
}
