package Examen3OCunado.Activitat1;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.ServerAddress;

import java.util.*;

public class MongoController
{
	private static boolean stop = false;
	private static MongoClient client;
	private static  MongoCollection<Document> collection;
	public static void main(String[] args) {menu();}
	public static void openDatabase()
    {
	   try
	   {
		   client = new MongoClient(new ServerAddress("172.31.103.61"));
		   MongoDatabase database = client.getDatabase("oriolDatabase");
		   System.out.println("\nOPENED DATABASE 'oriolDatabase'");
		   collection = database.getCollection("oriolCollection");
		   System.out.println("\nOPENED COLLECTION 'oriolCollection'");
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
					"\n| (5)MOSTRAR TODO                                                 |" +
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
					gruposArmadoConMasDe300Bajas();
					break;
				}
				case 5:
				{
					openDatabase();
					mostrarTodo();
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

		Map<String,Object> grupoArmado = new HashMap<>();
		grupoArmado.put("_id", codigo);
		grupoArmado.put("nombre", nombre);
		grupoArmado.put("bajas", bajas);
		collection.insertOne(new Document(grupoArmado));
	}
	public static void altaConflicto()
	{
		Scanner in = new Scanner (System.in);

		System.out.println("\nIntroduce Nombre del Conflicto (STRING)");
		String nombre = in.nextLine();
		System.out.println("\nIntroduce Zona del Conflicto (STRING)");
		String zona = in.nextLine();
		System.out.println("\nIntroduce Numero de Heridos en el Conflicto (INTEGER)");
		String heridos = in.nextLine();
		for (Document doc : collection.find())
		{
			System.out.println(doc.toJson());
		}
		System.out.println("\nIntroduce Codigo del Conflicto (INTEGER)");
		String codigo = in.nextLine();
		Map<String,Object> conflicto = new HashMap<>();
		conflicto.put("_id", codigo);
		conflicto.put("nombre", nombre);
		conflicto.put("zona", zona);
		conflicto.put("heridos", heridos);
		conflicto.put("Grupos Armados", Arrays.asList(codigo));
		collection.insertOne(new Document(conflicto));
	}
	public static void consultaConflicto()
	{
		Scanner in = new Scanner (System.in);
		System.out.println("\nIntroduce Nombre del Conflicto (STRING)");
		String nombre = in.nextLine();
		FindIterable<Document> cursor = collection.find(new BasicDBObject("nombre", nombre));
		for (Document document : cursor)
			{
			System.out.println(document.toJson());
		}
	}
	public static void gruposArmadoConMasDe300Bajas()
	{
		FindIterable<Document> cursor = collection.find(new BasicDBObject("heridos", new BasicDBObject("$gt", 300)));
		for (Document document : cursor)
		{
			System.out.println(document.toJson());
		}
	}
	public static void mostrarTodo()
	{
		for (Document doc : collection.find())
		{
			System.out.println(doc.toJson());
		}
	}
}