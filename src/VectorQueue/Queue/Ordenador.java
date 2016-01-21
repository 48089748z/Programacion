package VectorQueue.Queue;

import java.util.Comparator;

/**
 * Created by 48089748z on 21/01/16.
 */

public class Ordenador implements Comparator<Peticion>
{
    public int compare(Peticion peticionA, Peticion peticionB)
    {
        return peticionB.getComparator() - peticionA.getComparator();
    }
}

