package Examen3OCunado;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.ServerAddress;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Controller
{
	private static boolean stop = false;
	private static MongoClient client;
	private static  MongoDatabase database;
	private static  MongoCollection<Document> collection;
	public static void main(String[] args) {menu();}
	public static void openDatabase()
    {
	   try
	   {
		   client = new MongoClient(new ServerAddress("172.31.103.61"));
		   database = client.getDatabase("oriolDatabase");
		   System.out.println("\nOPENED DATABASE 'oriolDatabase'");
		   collection = database.getCollection("oriolCollection");
		   System.out.println("\nOPENED COLLECTION 'oriolCollection'");
		   for (Document document: collection.find())
		   {
			   System.out.println(document.toJson());
		   }
		   menu();
	   }
	   catch (Exception e) {e.printStackTrace();} finally{System.out.println("\nCLOSED DATABASE");client.close();}
    }
	public static void menu()
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
					break;
				}
				case 2:
				{
					openDatabase();
					altaConflicto();
					break;
				}
				case 3:
				{
					openDatabase();
					consultaConflicto();
					break;
				}
				case 4:
				{
					openDatabase();
					consultaGrupoArmado();
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
			//STORE
			//collection.aggregate(grupoArmado);
			System.out.println("\nGRUPO ARMADO GUARDADO");
		}
		catch (NumberFormatException e){System.out.println("\nHas introducido algun String donde tenias que poner Integers :(");}
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
			//STORE
			System.out.println("\nCONFLICTO GUARDADO");
		}
		catch (NumberFormatException e)
		{
			System.out.println("\nHas introducido algun String donde tenias que poner Integers :(");
		}
	}
	public static void consultaConflicto()
	{
		Scanner in = new Scanner (System.in);
		System.out.println("\nIntroduce Nombre del Conflicto (STRING)");
		String nombre = in.nextLine();

		//CONSULTA CONFLICTO POR NOMBRE


	}
	public static void consultaGrupoArmado()
	{
	 //CONSULTA GRUPOS ARMADOS CON MAS DE 300 BAJAS
	}
}