package ExamenDB4O;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Created by 48089748z on 26/02/16.
 */
public class Controller
{
    /*
        Query query = database.query();
        query.constrain(Caracteristicas.class);
        query.descend("fuerza").constrain(5).smaller().equal();
        imprimirObjectSet(query.execute());
     */
    private static String databasePath = "/home/48089748z/Escriptori/IdeaProjects/Programacion/src/ExamenDB4O/database.data"; //ESTA RUTA HAY QUE CAMBIARLA EN CADA ORDENADOR QUE SE EJECUTE
    private static ObjectContainer database;
    private static boolean stop = false;
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        while (!stop)
        {
            System.out.println("\n| ------------------------------   MENU PRINCIPAL  ------------------------------ |"+
                               "\n| (1)CREAR JUGADOR                (2)CREAR EQUIPO           (3)MOSTRAR JUGADORES  |"+
                               "\n| (4)MOSTRAR EQUIPOS              (5)RETIRAR JUGADOR        (6)TRASPASAR JUGADOR  |"+
                               "\n| (7)AUGMENTAR CARACTERISTICAS                          (8)CAMBIAR EQUIPO DE LIGA |"+
                               "\n| (9)CAMBIAR ENTRENADOR DE EQUIPO                (10)CAMBIAR PATROCINADOR DE LIGA |"+
                               "\n| (11)JUGADORES DE UN EQUIPO                         (12)JUGADORES DE DOS EQUIPOS |"+
                               "\n| (13)JUGADORES DE UN EQUIPO CON FUERZA <= 5      (14)CARACTERISTICAS JUGADOR DADO|"+
                               "\n| (15)JUGADORES QUE ENTRENA UN ENTRENADOR   (0)SALIR      (OTRO NUMERO)INFORMACIÓN|"+
                               "\n| ------------------------------------------------------------------------------- |");
            Integer option = 0;
            try{option = in.nextInt();}
            catch (InputMismatchException e){System.out.println("\nSi sabes que has de poner un Integer, y pones otra cosa, te cierro el programa :)");}
            switch (option)
            {
                case 1:
                {
                    //Te permite crear un jugador, deberas asignarle todos los atributos que se pidan.
                    openDatabase();
                    crearJugador();
                    commitAndCloseDatabase();
                    break;
                }
                case 2:
                {
                    //Te permite crear un equipo, deberas asignarle todos los atributos que se pidan.
                    openDatabase();
                    crearEquipo();
                    commitAndCloseDatabase();
                    break;
                }
                case 3:
                {
                    //Te muestra una lista detallada de todos los Jugadores que hay guardados en la Base de Datos de DB4O.
                    openDatabase();
                    imprimirObjectSet(database.queryByExample(new Jugador()));
                    commitAndCloseDatabase();
                    break;
                }
                case 4:
                {
                    //Te muestra una lista detallada de todos los Equipos que hay guardados en la Base de Datos de DB4O.
                    openDatabase();
                    imprimirObjectSet(database.queryByExample(new Equipo()));
                    commitAndCloseDatabase();
                    break;
                }
                case 5:
                {
                    //Te permite eliminar un Jugador de la Base de Datos a partir de su DNI.
                    openDatabase();
                    borrarUnJugadorEnConcreto();
                    commitAndCloseDatabase();
                    break;
                }
                case 6:
                {
                    //Te permite traspasar un Jugador de un Equipo a otro, o simplemente asignarlo a un Equipo si este no estaba en ninguno anteriormente.
                    openDatabase();
                    traspasarUnJugadorEnConcretoAUnEquipo();
                    commitAndCloseDatabase();
                    break;
                }
                case 7:
                {
                    //Te permite augmentar las Caracteristicas del Jugador que quieras.
                    openDatabase();
                    augmentarCaracteristicasDeUnJugadorEnConcreto();
                    commitAndCloseDatabase();
                    break;
                }
                case 8:
                {
                    //Te permite cambiar a un Equipo de Liga en la que juega.
                    openDatabase();
                    cambiarUnEquipoDeLiga();
                    commitAndCloseDatabase();
                    break;
                }
                case 9:
                {
                    //Te permite cambiar el Entrenador de el Equipo que quieras.
                    openDatabase();
                    cambiarElEntrenadorDeUnEquipoEnConcreto();
                    commitAndCloseDatabase();
                    break;
                }
                case 10:
                {
                    //Te permite cambiar el Patrocinador de la Liga del Equipo que quieras.
                    openDatabase();
                    cambiarPatrocinadorDeUnaLigaEnConcreto();
                    commitAndCloseDatabase();
                    break;
                }
                case 11:
                {
                    //Te muestra una lista detallada de todos los Jugadores del Equipo que quieras.
                    openDatabase();
                    verTodosLosJugadoresDeUnEquipoEnConcreto();
                    commitAndCloseDatabase();
                    break;
                }
                case 12:
                {
                    //Te muestra una lista detallada de todos los Jugadores de los dos Equipos que quieras.
                    openDatabase();
                    verTodosLosJugadoresDeDosEquiposEnConcreto();
                    commitAndCloseDatabase();
                    break;
                }
                case 13:
                {
                    //Te muestra una lista detallada de todos los Jugadores del Equipo que quieras, que además tengan una Fuerza menor o igual a 5.
                    openDatabase();
                    verJugadoresDeUnEquipoEnConcretoConFuerzaMenorOIgualA5();
                    commitAndCloseDatabase();
                    break;
                }
                case 14:
                {
                    //Te muestra todas las Caracteristicas de el Jugador que quieras.
                    openDatabase();
                    verCaracteristicasDeUnJugadorEnConcreto();
                    commitAndCloseDatabase();
                    break;
                }
                case 15:
                {
                    //Te muestra todos los Jugadores a los que entrena el Entrenador que tu quieras buscar.
                    openDatabase();
                    verJugadoresQueEntrenaUnEntrenadorEnConcreto();
                    commitAndCloseDatabase();
                    break;
                }
                case 0:
                {
                    //Cierra la aplicación.
                    stop = true;
                    System.out.println("CERRANDO PROGRAMA!");
                    break;
                }
                default:
                {
                    //CUALQUIER OTRO NUMERO QUE INTRODUZCAS TE ABRE PANTALLA DE AYUDA.
                    informacion();
                    break;
                }
            }
        }
    }
    public static void verJugadoresQueEntrenaUnEntrenadorEnConcreto()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Nombre Entrenador (String)");
        String nombreEntrenador = in.nextLine();
        ObjectSet<Equipo> equipos = database.queryByExample(new Equipo());
        System.out.println("\nJugadores que entrena el entrenador "+nombreEntrenador);
        for (int x=0; x<equipos.size(); x++)
        {
            Equipo selected = equipos.get(x);
            if (selected.getEntrenador().getNombre().equals(nombreEntrenador))
            {
                for (int y=0; y<selected.getJugadores().size(); y++)
                {
                    System.out.println(selected.getJugadores().get(y).toString());
                }
                break;
            }
        }
    }
    public static void verCaracteristicasDeUnJugadorEnConcreto()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Nombre Jugador (String)");
        String nombreJugador = in.nextLine();
        ObjectSet<Jugador> jugadores = database.queryByExample(new Jugador());

        System.out.println("\nCaracteristicas jugador "+nombreJugador);
        for (int x=0; x<jugadores.size(); x++)
        {
            Jugador selected = jugadores.get(x);
            if (selected.getNombre().equals(nombreJugador))
            {
                System.out.println(selected.getCaracteristicas().toString());
                break;
            }
        }
    }
    public static void verJugadoresDeUnEquipoEnConcretoConFuerzaMenorOIgualA5()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Nombre equipo (String)");
        String nombreEquipo = in.nextLine();
        ObjectSet<Equipo> equipos = database.queryByExample(new Equipo());
        Equipo selected = null;
        for (int x=0; x<equipos.size(); x++)
        {
            selected = equipos.get(x);
            if (selected.getNombre().equals(nombreEquipo))
            {
                System.out.println("JUGADOR/ES EN EL EQUIPO "+nombreEquipo+" CON FUERZA <= 5");
                for (int y=0; y<selected.getJugadores().size(); y++)
                {
                    if (selected.getJugadores().get(y).getCaracteristicas().getFuerza()<=5)
                    {
                        System.out.println(selected.getJugadores().get(y).toString());
                    }
                }
                break;
            }
        }
    }
    public static void imprimirObjectSet(ObjectSet objectSet)
    {
        for (int x=0; x<objectSet.size(); x++) {System.out.println(objectSet.get(x).toString());}
    }
    public static void verTodosLosJugadoresDeDosEquiposEnConcreto()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Nombre equipo 1 (String)");
        String nombreEquipo1 = in.nextLine();
        System.out.println("Nombre equipo 2 (String)");
        String nombreEquipo2 = in.nextLine();
        imprimeJugadoresDeElEquipo(nombreEquipo1);
        commitAndCloseDatabase();
        openDatabase();
        imprimeJugadoresDeElEquipo(nombreEquipo2);
    }
    public static void imprimeJugadoresDeElEquipo(String nombreEquipo)
    {
        ObjectSet<Equipo> equipos = database.queryByExample(new Equipo());
        for (int x=0; x<equipos.size(); x++)
        {
            Equipo selected = equipos.get(x);
            if (selected.getNombre().equals(nombreEquipo))
            {
                System.out.println("\nHAY "+selected.getJugadores().size()+ " JUGADOR/ES EN EL EQUIPO "+nombreEquipo);
                System.out.println(selected.getPlayersString());
                break;
            }
        }
    }
    public static void verTodosLosJugadoresDeUnEquipoEnConcreto()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Nombre equipo (String)");
        String nombreEquipo = in.nextLine();
        imprimeJugadoresDeElEquipo(nombreEquipo);
    }
    public static void cambiarPatrocinadorDeUnaLigaEnConcreto()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Nombre equipo (String)");
        String nombreEquipo = in.nextLine();
        System.out.println("Nombre Nuevo Patrocinador (String)");
        String nombrePatrocinador = in.nextLine();
        ObjectSet<Equipo> equipos = database.queryByExample(new Equipo());
        Equipo selected = null;
        for (int x=0; x<equipos.size(); x++)
        {
            selected = equipos.get(x);
            if (selected.getNombre().equals(nombreEquipo))
            {
                Liga liga = (equipos.get(x)).getLiga();
                liga.setPatrocinador(nombrePatrocinador);
                database.delete(equipos.get(x));
                commitAndCloseDatabase();
                break;
            }
        }
        openDatabase();
        database.store(selected);
        System.out.println("\nLiga "+selected.getLiga().getNombre()+" tiene ahora patrocinador "+nombrePatrocinador);
    }
    public static void cambiarElEntrenadorDeUnEquipoEnConcreto()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Nombre equipo (String)");
        String nombreEquipo = in.nextLine();
        System.out.println("Nombre Nuevo Entrenador (String)");
        String nombreEntrenador = in.nextLine();
        ObjectSet<Equipo> equipos = database.queryByExample(new Equipo());

        Equipo selected = null;
        for (int x=0; x<equipos.size(); x++)
        {
            selected = equipos.get(x);
            if (selected.getNombre().equals(nombreEquipo))
            {
                Entrenador entrenador = (equipos.get(x)).getEntrenador();
                entrenador.setNombre(nombreEntrenador);
                database.delete(equipos.get(x));
                commitAndCloseDatabase();
                break;
            }
        }
        openDatabase();
        database.store(selected);
        System.out.println("\nEquipo "+selected.getNombre()+" tiene ahora entrenador "+nombreEntrenador);
    }
    public static void cambiarUnEquipoDeLiga()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Nombre equipo (String)");
        String nombreEquipo = in.nextLine();
        System.out.println("Nombre de Liga (String)");
        String nombreLiga = in.nextLine();
        ObjectSet<Equipo> equipos = database.queryByExample(new Equipo());

        Equipo selected = null;
        for (int x=0; x<equipos.size(); x++)
        {
            selected = equipos.get(x);
            if (selected.getNombre().equals(nombreEquipo))
            {
                Liga liga = (equipos.get(x)).getLiga();
                liga.setNombre(nombreLiga);
                database.delete(equipos.get(x));
                commitAndCloseDatabase();
                break;
            }
        }
        openDatabase();
        database.store(selected);
        System.out.println("\nEquipo "+selected.getNombre()+" cambiado a la Liga "+nombreLiga);
    }
    public static void augmentarCaracteristicasDeUnJugadorEnConcreto()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Introduce DNI JUGADOR (String)");
        String dni = in.nextLine();
        System.out.println("En cuanto quieres augmentar las Caracteristicas? (Integer)");
        Integer cuanto = in.nextInt();
        Jugador selected= null;
        ObjectSet<Jugador> jugadores = database.queryByExample(new Jugador());
        for (int x=0; x<jugadores.size();x++)
        {
            selected = jugadores.get(x);
            if (selected.getDni().equals(dni))
            {
                Caracteristicas caracteristicas = (jugadores.get(x)).getCaracteristicas();
                caracteristicas.setPenalti(caracteristicas.getPenalti()+cuanto);
                caracteristicas.setPase(caracteristicas.getPase()+cuanto);
                caracteristicas.setVelocidad(caracteristicas.getVelocidad()+cuanto);
                caracteristicas.setFuerza(caracteristicas.getFuerza()+cuanto);
                caracteristicas.setAgilidad(caracteristicas.getAgilidad()+cuanto);
                selected.setCaracteristicas(caracteristicas);
                database.delete(jugadores.get(x));
                commitAndCloseDatabase();
                break;
            }
        }
        openDatabase();
        database.store(selected);
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

    public static void guardarJugadorEnDB4O(Jugador jugador)
    {
     //   openDatabase();
        database.store(jugador);
        System.out.println("\nJugador guardado en DB4O!");
    }
    public static void guardarEquipoEnDB4O(Equipo equipo)
    {
        database.store(equipo);
        System.out.println("\nEquipo guardado en DB4O!");
    }
    public static void borrarUnJugadorEnConcreto()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Introduce DNI JUGADOR a eliminar");
        String dni = in.nextLine();
        ObjectSet<Jugador> jugadores = database.queryByExample(new Jugador());
        for (int x=0; x<jugadores.size();x++)
        {
            Jugador selected = jugadores.get(x);
            if (selected.getDni().equals(dni))
            {
                System.out.println("\nELIMINADO JUGADOR:"+selected.toString());
                database.delete(jugadores.get(x));
            }
        }
    }
    public static void traspasarUnJugadorEnConcretoAUnEquipo()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Introduce DNI JUGADOR a traspasar");
        String dni = in.nextLine();
        System.out.println("Introduce NOMBRE EQUIPO a donde traspasa");
        String nombreEquipo = in.nextLine();
        ObjectSet<Jugador> jugadores = database.queryByExample(new Jugador());
        Jugador selectedJugador = null;
        for (int x=0; x<jugadores.size();x++)
        {
            selectedJugador = jugadores.get(x);
            if (selectedJugador.getDni().equals(dni))
            {
                break;
            }
        }
        Equipo selectedEquipo = null;
        ObjectSet<Equipo> equipos = database.queryByExample(new Equipo());
        for (int x=0; x<equipos.size(); x++)
        {
            selectedEquipo = equipos.get(x);
            if (selectedEquipo.getNombre().equals(nombreEquipo))
            {
                database.delete(selectedEquipo);
                commitAndCloseDatabase();
                selectedEquipo.addJugador(selectedJugador);
                break;
            }
        }
        openDatabase();
        database.store(selectedEquipo);
        System.out.println("\nJugador "+selectedJugador.getNombre()+" asignado al Equipo "+selectedEquipo.getNombre());
    }
    public static void openDatabase()
    {
        File databaseFile = new File(databasePath);
        if (!databaseFile.exists())
        {
            try
            {
                if (databaseFile.createNewFile())
                {
                    System.out.println("\nSE HA CREADO LA BASE DE DATOS.");
                    database = Db4o.openFile("database.data");
                }
            } catch (IOException e) {}
        }
        else
        {
            System.out.println("\nSE HA ABIERTO LA BASE DE DATOS.");
            database = Db4o.openFile("database.data");
        }
    }
    public static void commitAndCloseDatabase()
    {
        database.commit();
        database.close();
        System.out.println("\nSE HA CERRADO LA BASE DE DATOS.");
    }
    public static void informacion()
    {
        System.out.println("\nOPCIÓN NO VALIDA!\nIntentalo de nuevo.");
        System.out.print("\n|-------------------------------------------------------- PANTALLA DE INFORMACIÓN --------------------------------------------------------- |" +
                "\n|      ESTAS SON LAS OPCIONES VALIDAS                                                                                                       |" +
                "\n| 1:   Te permite crear un jugador, deberas asignarle todos los atributos que se pidan.                                                     |" +
                "\n| 2:   Te permite crear un equipo, deberas asignarle todos los atributos que se pidan.                                                      |" +
                "\n| 3:   Te muestra una lista detallada de todos los Jugadores que hay guardados en la Base de Datos de DB4O.                                 |" +
                "\n| 4:   Te muestra una lista detallada de todos los Equipos que hay guardados en la Base de Datos de DB4O.                                   |" +
                "\n| 5:   Te permite eliminar un Jugador de la Base de Datos a partir de su DNI.                                                               |" +
                "\n| 6:   Te permite traspasar un Jugador de un Equipo a otro, o simplemente asignarlo a un Equipo si este no estaba en ninguno anteriormente. |" +
                "\n| 7:   Te permite augmentar las Caracteristicas del Jugador que quieras.                                                                    |" +
                "\n| 8:   Te permite cambiar a un Equipo de Liga en la que juega.                                                                              |" +
                "\n| 9:   Te permite cambiar el Entrenador de el Equipo que quieras.                                                                           |" +
                "\n| 10:  Te permite cambiar el Patrocinador de la Liga del Equipo que quieras.                                                                |" +
                "\n| 11:  Te muestra una lista detallada de todos los Jugadores del Equipo que quieras.                                                        |" +
                "\n| 12:  Te muestra una lista detallada de todos los Jugadores de los dos Equipos que quieras.                                                |" +
                "\n| 13:  Te muestra una lista detallada de todos los Jugadores del Equipo que quieras, que además tengan una Fuerza menor o igual a 5.        |" +
                "\n| 14:  Te muestra todas las Caracteristicas de el Jugador que quieras.                                                                      |" +
                "\n| 15:  Te muestra todos los Jugadores a los que entrena el Entrenador que tu quieras buscar.                                                |" +
                "\n| 0:   Cierra la aplicación.                                                                                                                |" +
                "\n|                                                                   CUALQUIER OTRO NUMERO QUE INTRODUZCAS TE ABRE ESTA PANTALLA DE AYUDA.   |" +
                "\n|-------------------------------------------------------------------------------------------------------------------------------------------|");
    }
}
