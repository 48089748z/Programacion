package ExamenDB4O;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Constraints;
import com.db4o.query.Query;
import com.db4o.query.QueryComparator;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by 48089748z on 26/02/16.
 */
public class Controller
{
    private static ObjectContainer database;
    private static boolean stop = false;
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        while (!stop)
        {
            System.out.println("\nMENU PRINCIPAL" +
                    "\n1) CREAR JUGADOR" +
                    "\n2) CREAR EQUIPO" +
                    "\n3) MOSTRAR JUGADORES" +
                    "\n4) MOSTRAR EQUIPOS " +
                    "\n\n5)RETIRAR JUGADOR" +
                    "\n6)TRASPASAR JUGADOR" +
                    "\n7)AUGMENTAR CARACTERISTICAS" +
                    "\n8)CAMBIAR EQUIPO DE LIGA "+
                    "\n9) "+
                    "\n10) "+
                    "\n11) "+
                    "\n12) "+
                    "\n\n0) SALIR");
            Integer option = in.nextInt();
            switch (option)
            {
                case 1:
                {
                    crearJugador();
                    break;
                }
                case 2:
                {
                    crearEquipo();
                    break;
                }
                case 3:
                {
                    mostrarJugadores();
                    break;
                }
                case 4:
                {
                    mostrarEquipos();
                    break;
                }
                case 5:
                {
                    retirarJugador();
                    break;
                }
                case 6:
                {
                    traspasarJugador();
                    break;
                }
                case 7:
                {
                    augmentarCaracteristicas();
                    break;
                }
                case 8:
                {
                    cambiarEquipoDeLiga();
                    break;
                }
                case 0:
                {
                    stop = true;
                    database.close();
                    break;
                }
                default:
                {
                    System.out.println("\nEntrada invalida intentalo de nuevo");
                    break;
                }
            }
        }
    }
    public static void cambiarEquipoDeLiga()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Nombre equipo (String)");
        String nombreEquipo = in.nextLine();
        System.out.println("Nombre de Liga (String)");
        String nombreLiga = in.nextLine();
        openDatabase();
        ObjectSet equipos = database.queryByExample(new Equipo());

        Equipo selected = null;
        for (int x=0; x<equipos.size(); x++)
        {
            selected = (Equipo) equipos.get(x);
            if (selected.getNombre().equals(nombreEquipo))
            {
                Liga liga = ((Equipo) equipos.get(x)).getLiga();
                liga.setNombre(nombreLiga);
                database.delete(equipos.get(x));
                database.commit();
                database.close();
                break;
            }
        }
        openDatabase();
        database.store(selected);
        database.commit();
        database.close();
        System.out.println("\nEquipo "+selected.getNombre()+" cambiado de Liga");
    }
    public static void augmentarCaracteristicas()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Introduce DNI JUGADOR (String)");
        String dni = in.nextLine();
        System.out.println("En cuanto quieres augmentar las Caracteristicas? (Integer)");
        Integer cuanto = in.nextInt();
        openDatabase();

        Jugador byExample = new Jugador();
        byExample.setDni(dni);

        Jugador selected= null;
        ObjectSet jugadores = database.queryByExample(new Jugador());
        for (int x=0; x<jugadores.size();x++)
        {
            selected = (Jugador) jugadores.get(x);
            if (selected.getDni().equals(dni))
            {
                Caracteristicas caracteristicas = ((Jugador) jugadores.get(x)).getCaracteristicas();
                caracteristicas.setPenalti(caracteristicas.getPenalti()+cuanto);
                caracteristicas.setPase(caracteristicas.getPase()+cuanto);
                caracteristicas.setVelocidad(caracteristicas.getVelocidad()+cuanto);
                caracteristicas.setFuerza(caracteristicas.getFuerza()+cuanto);
                caracteristicas.setAgilidad(caracteristicas.getAgilidad()+cuanto);
                selected.setCaracteristicas(caracteristicas);
                database.delete(jugadores.get(x));
                database.commit();
                database.close();
                break;
            }
        }
        openDatabase();
        database.store(selected);
        database.commit();
        database.close();
        System.out.println("\nCaracteristicas augmentadas en "+cuanto+" puntos!");
    }
    public static void crearJugador()
    {
        Scanner in = new Scanner(System.in);
        Jugador jugador = new Jugador();
        Caracteristicas caracteristicas = new Caracteristicas();

        System.out.println("Introduce DNI JUGADOR (String)");
        String dni = in.nextLine();
        jugador.setDni(dni);

        System.out.println("Introduce NOMBRE JUGADOR (String)");
        String nombre = in.nextLine();
        jugador.setNombre(nombre);

        System.out.println("Introduce APELLIDO JUGADOR (String)");
        String apellido = in.nextLine();
        jugador.setApellido(apellido);

        System.out.println("Introduce ALTURA JUGADOR (String)");
        String altura = in.nextLine();
        jugador.setAltura(altura);

        System.out.println("Introduce PESO JUGADOR (String)");
        String peso = in.nextLine();
        jugador.setPeso(peso);

        System.out.println("Introduce AGILIDAD JUGADOR (Integer)");
        Integer agilidad = in.nextInt();
        caracteristicas.setAgilidad(agilidad);

        System.out.println("Introduce FUERZA JUGADOR (Integer)");
        Integer fuerza = in.nextInt();
        caracteristicas.setFuerza(fuerza);

        System.out.println("Introduce VELOCIDAD JUGADOR (Integer)");
        Integer velocidad = in.nextInt();
        caracteristicas.setVelocidad(velocidad);

        System.out.println("Introduce PASE JUGADOR (Integer)");
        Integer pase = in.nextInt();
        caracteristicas.setPase(pase);

        System.out.println("Introduce PENALTI JUGADOR (Integer)");
        Integer penalti = in.nextInt();
        caracteristicas.setPenalti(penalti);
        jugador.setCaracteristicas(caracteristicas);
        guardarJugadorEnDB4O(jugador);
    }

    public static void crearEquipo()
    {
        Scanner in = new Scanner(System.in);
        Equipo equipo = new Equipo();
        Liga liga = new Liga();
        Entrenador entrenador = new Entrenador();

        System.out.println("Introduce NOMBRE ENTRENADOR del equipo (String)");
        String nombreEntrenador = in.nextLine();
        entrenador.setNombre(nombreEntrenador);

        System.out.println("Introduce AÑOS EXPERIENCIA ENTRENADOR del equipo (Integer)");
        Integer añosExperienciaEntrenador = in.nextInt();
        entrenador.setAñosExperiencia(añosExperienciaEntrenador);

        equipo.setEntrenador(entrenador);

        System.out.println("Introduce NOMBRE LIGA del equipo (String)");
        in.nextLine();
        String nombreLiga= in.nextLine();
        liga.setNombre(nombreLiga);

        System.out.println("Introduce CATEGORIA LIGA del equipo (String)");
        String categoriaLiga = in.nextLine();
        liga.setCategoria(categoriaLiga);

        System.out.println("Introduce PATROCINADOR LIGA del equipo (String)");
        String patrocinadorLiga = in.nextLine();
        liga.setPatrocinador(patrocinadorLiga);

        equipo.setLiga(liga);

        System.out.println("Introduce NOMBRE EQUIPO (String)");
        String nombreEquipo = in.nextLine();
        equipo.setNombre(nombreEquipo);

        System.out.println("Introduce NOMBRE ESTADIO EQUIPO (String)");
        String nombreEstadioEquipo = in.nextLine();
        equipo.setEstadio(nombreEstadioEquipo);

        guardarEquipoEnDB4O(equipo);
    }
    public static void openDatabase()
    {
        File databaseFile = new File("/home/48089748z/Escriptori/IdeaProjects/Programacion/database.data");
        if (!databaseFile.exists())
        {
            try
            {
                databaseFile.createNewFile();
                database = Db4o.openFile("database.data");

            } catch (IOException e) {}
        }
        else
        {
            database = Db4o.openFile("database.data");
        }
    }
    public static void guardarJugadorEnDB4O(Jugador jugador)
    {
        openDatabase();
        database.store(jugador);
        database.commit();
        database.close();
        System.out.println("\nJugador guardado en DB4O!");
    }
    public static void guardarEquipoEnDB4O(Equipo equipo)
    {
        openDatabase();
        database.store(equipo);
        database.commit();
        database.close();
        System.out.println("\nEquipo guardado en DB4O!");

    }
    public static void mostrarJugadores()
    {
        openDatabase();
        ObjectSet jugadores = database.queryByExample(new Jugador());
        while (jugadores.hasNext())
        {
            System.out.println(jugadores.next().toString());
        }
        database.close();
    }
    public static void mostrarEquipos()
    {
        openDatabase();
        ObjectSet equipos = database.queryByExample(new Equipo());
        while (equipos.hasNext())
        {
            System.out.println(equipos.next().toString());
        }
        database.close();
    }
    public static void retirarJugador()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Introduce DNI JUGADOR a eliminar");
        String dni = in.nextLine();

        Jugador byExample = new Jugador();
        byExample.setDni(dni);

        ObjectSet jugadores = database.queryByExample(new Jugador());
        for (int x=0; x<jugadores.size();x++)
        {
            Jugador selected = (Jugador) jugadores.get(x);
            if (selected.getDni().equals(dni))
            {
                database.delete(jugadores.get(x));
            }
        }
        database.commit();
        database.close();
    }
    public static void traspasarJugador()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Introduce DNI JUGADOR a traspasar");
        String dni = in.nextLine();

        System.out.println("Introduce NOMBRE EQUIPO a donde traspasa");
        String nombreEquipo = in.nextLine();

        openDatabase();
        Jugador selectedJugador=null;
        ObjectSet jugadores = database.queryByExample(new Jugador());
        for (int x=0; x<jugadores.size();x++)
        {
            selectedJugador = (Jugador) jugadores.get(x);
            if (selectedJugador.getDni().equals(dni))
            {
                break;
            }
        }
        Equipo selectedEquipo=null;
        ObjectSet equipos = database.queryByExample(new Equipo());
        for (int x=0; x<equipos.size(); x++)
        {
            selectedEquipo = (Equipo) equipos.get(x);
            if (selectedEquipo.getNombre().equals(nombreEquipo))
            {
                database.delete(selectedEquipo);
                database.commit();
                database.close();
                selectedEquipo.addJugador(selectedJugador);
                break;
            }
        }
        openDatabase();
        database.store(selectedEquipo);
        database.commit();
        database.close();
        System.out.println("\nJugador "+selectedJugador.getNombre()+" asignado al Equipo "+selectedEquipo.getNombre());
    }
}
