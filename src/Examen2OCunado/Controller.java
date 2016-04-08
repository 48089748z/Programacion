package Examen2OCunado;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Created by 48089748z on 08/04/16.
 */
public class Controller
{
    private static String databasePath = "/home/48089748z/Escriptori/IdeaProjects/Programacion/src/Examen2OCunado/database.data";
    private static ObjectContainer database;
    private static boolean stop = false;
    public static void main(String[] args)
    {
        Scanner menu = new Scanner(System.in);
        while (!stop)
        {
            System.out.println("\n| ----------------------- MENU PRINCIPAL -----------------------  |" +
                               "\n| (1)ALTA GRUPO ARMADO                                            |" +
                               "\n| (2)ALTA DE CONFLICTO |                                          |" +
                               "\n| (3)CONSULTA CONFLICTO POR NOMBRE Y MUESTRA SUS GRUPOS ARMADOS   |" +
                               "\n| (4)CONSULTA GRUPOS ARMADOS CON MÁS DE 300 BAJAS                 |" +
                               "\n|                                                                 |" +
                               "\n| CUALQUIER OTRO NUMERO CIERRA EL PROGRAMA                        |" +
                               "\n| --------------------------------------------------------------- |");
            Integer option = 0;
            try {option = menu.nextInt();}
            catch (InputMismatchException e) {System.out.println("\nSi sabes que has de poner un Integer, y pones otra cosa, te cierro el programa :)");}
            switch (option)
            {
                case 1:
                {
                    openDatabase();
                    altaGrupoArmado();
                    commitAndCloseDatabase();
                    break;
                }
                case 2:
                {
                    openDatabase();
                    altaConflicto();
                    commitAndCloseDatabase();
                    break;
                }
                case 3:
                {
                    openDatabase();
                    consultaConflicto();
                    commitAndCloseDatabase();
                    break;
                }
                case 4:
                {
                    openDatabase();
                    consultaGrupoArmado();
                    commitAndCloseDatabase();
                    break;
                }
                default:
                {
                    stop = true;
                    System.out.println("\nCERRANDO PROGRAMA!");
                    break;
                }
            }
        }
    }
    public static void altaGrupoArmado()
    {
        Scanner in = new Scanner (System.in);
        System.out.println("\nIntroduce Nombre del Grupo Armado (STRING)");
        String nombre = in.nextLine();
        System.out.println("\nIntroduce Codigo del Grupo Armado (INTEGER)");
        String codigo = in.nextLine();
        System.out.println("\nIntroduce Bajas del Grupo Armado (INTEGER)");
        String bajas = in.nextLine();
        try
        {
            GrupoArmado grupoArmado = new GrupoArmado(nombre, Integer.parseInt(codigo), Integer.parseInt(bajas));
            database.store(grupoArmado);
            System.out.println("\nGRUPO ARMADO GUARDADO");
        }
        catch (NumberFormatException e){
            System.out.println("\nHas introducido algun String donde tenias que poner Integers :(");
            commitAndCloseDatabase();
        }
    }
    public static void altaConflicto()
    {
        Scanner in = new Scanner (System.in);
        System.out.println("\nIntroduce Codigo del Conflicto (INTEGER)");
        String codigo = in.nextLine();
        System.out.println("\nIntroduce Nombre del Conflicto (STRING)");
        String nombre = in.nextLine();
        System.out.println("\nIntroduce Zona del Conflicto (STRING)");
        String zona = in.nextLine();
        System.out.println("\nIntroduce Numero de Heridos en el Conflicto (INTEGER)");
        String heridos = in.nextLine();
        try
        {
            Conflicto conflicto = new Conflicto(Integer.parseInt(codigo), nombre, zona, Integer.parseInt(heridos));
            database.store(conflicto);
            System.out.println("\nCONFLICTO GUARDADO");
        }
        catch (NumberFormatException e)
        {
            System.out.println("\nHas introducido algun String donde tenias que poner Integers :(");
            commitAndCloseDatabase();
        }
    }
    public static void consultaConflicto()
    {
        Scanner in = new Scanner (System.in);
        System.out.println("\nIntroduce Nombre del Conflicto (STRING)");
        String nombre = in.nextLine();
        ObjectSet<Conflicto> conflictos = database.queryByExample(new Conflicto());
        for (int x=0; x<conflictos.size(); x++)
        {
            if (conflictos.get(x).getNombre().equals(nombre))
            {
                System.out.println(conflictos.get(x).toString());
            }
        }


    }
    public static void consultaGrupoArmado()
    {
        ObjectSet<GrupoArmado> grupoArmados = database.queryByExample(new GrupoArmado());
        for (int x=0; x<grupoArmados.size();x++)
        {
            if (grupoArmados.get(x).getBajas()>300)
            {
                System.out.println(grupoArmados.get(x).toString());
            }
        }
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
}
