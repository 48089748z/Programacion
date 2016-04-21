package Examen3OCunado.Activitat2i3;

import java.sql.*;

/**
 * Created by 48089748z on 19/04/16.
 */
public class PostgreSQLController
{
    /**----------------------------------------------------------- PARA HACERLO SIN JAVA -----------------------------------------------------------
     *----------------------------------------------------------------------------------------------------------------------------------------------
     *
     * CREATE DATABASE nombreBBDD;
     * GRANT ALL PRIVILEGES ON DATABASE nombreBBDD TO USER tuUsuario;
     *
     * ----- ACTIVDAD 2 ----->
     *
     *            CREATES->
     *                CREATE TABLE ave (id varchar(10), nombre varchar(20)) WITH OIDS;
     *                CREATE TABLE ganso INHERITS (ave);
     *                CREATE TABLE pato INHERITS (ave);
     *                CREATE TABLE gallina INHERITS (ave);
     *
     *            INSERTS->
     *                INSERT INTO ganso VALUES ('123','ganso1_oriol_cunado');
     *                INSERT INTO pato VALUES ('234','pato1_oriol_cunado');
     *                INSERT INTO gallina VALUES ('345','gallina1_oriol_cunado');
     *
     *----------------------------------------------------------------------------------------------------------------------------------------------
     *  ----- ACTIVDAD 3 ----->
     *
     *            CREATES->
     *                CREATE TYPE instrumento AS (id varchar(10), nombre varchar(20));
     *                CREATE TABLE guitarra OF instrumento;
     *                CREATE TABLE piano OF instrumento;
     *                CREATE TABLE saxofon OF instrumento;
     *
     *            INSERTS->
     *                INSERT INTO guitarra VALUES ('456','guitarra1_oriol_cunado');
     *                INSERT INTO piano VALUES ('567','piano1_oriol_cunado');
     *                INSERT INTO saxofon VALUES ('678','saxofon1_oriol_cunado');
     *
     *
     *----------------------------------------------------------------------------------------------------------------------------------------------*/







    //LA IP TENDRAS QUE CAMBIARLA POR LA DE TU MAQUINA CON POSTGRES INSTALADO
    private static String IP = "172.31.104.0";
    //EL USUARIO Y CONTRASEÑA TAMBIEN TENDRAS QUE PONER LOS TUYOS
    private static String username = "uri3";
    private static String password = "uri3";

    //EL NOMBRE DE LA BBDD QUE HAYAS CREADO TU EN TU POSTGRES TAMBIEN TENDRAS QUE CAMBIARLO
    private static String databaseName = "peliculas";
    //LA BBDD SE CREA CON :
    //                      CREATE DATABASE peliculas;
    //                      GRANT ALL PRIVILEGES ON DATABASE peliculas to uri3;

    //ESTO SE PUEDE DEJAR IGUAL QUE ESTA
    private static String PostgresDriver = "org.postgresql.Driver";
    private static String PostgresDB = "jdbc:postgresql://"+IP+"/"+databaseName; //MI BASE DE DATOS SE LLAMA PELICULAS
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;

    public static void main(String[] args)
    {
        configurarPostgres();

        actividad2CrearTablas();
        actividad2InsertarCampos();

        actividad3CrearTablas();
        actividad3InsertarCampos();

        cerrarConexion();
    }

    public static void actividad3CrearTablas()
    {
        try
        {
            preparedStatement = connection.prepareStatement("CREATE TYPE instrumento AS (id varchar(10), nombre varchar(20))");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("CREATE TABLE guitarra OF instrumento");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("CREATE TABLE piano OF instrumento");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("CREATE TABLE saxofon OF instrumento");
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
            System.out.println("\nTABLAS ACTIVIDAD 3 CREADAS");
        } catch (SQLException e) {System.out.println("\nError creando las tablas en la Actividad 3.");}
    }
    public static void actividad3InsertarCampos()
    {
        try
        {
            preparedStatement = connection.prepareStatement("INSERT INTO guitarra (id_guitarra, nombre) VALUES(?,?)");
            preparedStatement.setInt(1, 2);
            preparedStatement.setString(2, "guitarra_1_oriol_cunado");
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("INSERT INTO piano (id_piano, nombre) VALUES(?,?)");
            preparedStatement.setInt(1, 3);
            preparedStatement.setString(2, "piano_1_oriol_cunado");
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("INSERT INTO saxofon (id_saxofon, nombre) VALUES(?,?)");
            preparedStatement.setInt(1, 3);
            preparedStatement.setString(2, "saxofon_1_oriol_cunado");
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.commit();
            System.out.println("\nINSERTS ACTIVIDAD 3 HECHOS");
        }
        catch (SQLException e) {System.out.println("\nError insertando en las Tablas en la Actividad 3.");}
    }


    public static void actividad2CrearTablas() //CREAREMOS LAS 4 TABLAS CON DOS CAMPOS, ID QUE NO PODRA SER NUNCA NULL Y UN NOMBRE UNICO QUE TAMPOCO PODRA SER NULL
    {
        try
        {
            preparedStatement = connection.prepareStatement(" CREATE TABLE ave (id varchar(10), nombre varchar(20)) WITH OIDS");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("CREATE TABLE ganso INHERITS (ave)");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("CREATE TABLE pato INHERITS (ave)");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("CREATE TABLE gallina INHERITS (ave)");
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
            System.out.println("\nTABLAS ACTIVIDAD 2 CREADAS");
        } catch (SQLException e) {System.out.println("\nError creando las Tablas en la Actividad 2.");}
    }
    public static void actividad2InsertarCampos()
    {
        try
        {
            preparedStatement = connection.prepareStatement("INSERT INTO ganso (id_ganso, nombre) VALUES(?,?)");
            preparedStatement.setInt(1, 2);
            preparedStatement.setString(2, "ganso_1_oriol_cunado");
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("INSERT INTO pato (id_pato, nombre) VALUES(?,?)");
            preparedStatement.setInt(1, 3);
            preparedStatement.setString(2, "pato_1_oriol_cunado");
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("INSERT INTO gallina (id_gallina, nombre) VALUES(?,?)");
            preparedStatement.setInt(1, 3);
            preparedStatement.setString(2, "gallina_1_oriol_cunado");
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.commit();
            System.out.println("\nINSERTS ACTIVIDAD 2 HECHOS");
        }
        catch (SQLException e) {System.out.println("\nError insertando en las Tablas en la Actividad 2.");}
    }
    public static void configurarPostgres()
    {
        try
        {
            Class.forName(PostgresDriver);
            connection = DriverManager.getConnection(PostgresDB, username, password);
            connection.setAutoCommit(false);
            System.out.println("\nPOSTGRES CONFIGURADO");
        }
        catch (ClassNotFoundException classNotFoundException) {System.out.println("\nNo se ha encontrado el Driver");}
        catch (SQLException sqlException)
        {
            System.out.println("\nNo se ha encontrado la Base De Datos");
            cerrarConexion();
        }
    }
    public static void cerrarConexion()
    {
        try {connection.close();}
        catch (SQLException e) {e.printStackTrace();}
    }
}
