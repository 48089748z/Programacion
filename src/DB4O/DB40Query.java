package DB4O;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.io.File;

/**
 * Created by 48089748z on 19/01/16.
 */
public class DB40Query
{
    private static ObjectContainer database;
    public static void main(String[] args) throws Exception
    {

        try
        {
            //Si existe el fichero lo borramos para que no se duplique la informacion cada vez que le damos a Run.
            File databaseFile = new File("/home/48089748z/Escriptori/IdeaProjects/Programacion/persons.data");
            if (databaseFile.exists())
            {
                databaseFile.delete();
            }
            //Abrimos el archivo de la base de datos.
            database = Db4o.openFile("persons.data");
            //Creamos 6 Persons
            Person brian = new Person("Brian", "Goetz", 39);
            Person jason = new Person("Jason", "Hunter", 35);
            Person brians = new Person("Brian", "Sletten", 38);
            Person david = new Person("David", "Geary", 55);
            Person glenn = new Person("Glenn", "Vanderberg", 40);
            Person neal = new Person("Neal", "Ford", 39);

            //Los guardamos en la base de datos y hacemos commit.
            database.store(brian);
            database.store(jason);
            database.store(brians);
            database.store(david);
            database.store(glenn);
            database.store(neal);
            database.commit();

            //Hacemos una consulta que nos devuelva todos los objectos que se llamen Brian
            ObjectSet allBrians = database.queryByExample(new Person("Brian", null, 0));
            while (allBrians.hasNext())
            {
                System.out.println(allBrians.next());
            }
        }
        finally
        {
            if (database != null)
                database.close();
        }
    }
}
