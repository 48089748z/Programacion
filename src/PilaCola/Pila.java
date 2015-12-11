package PilaCola;

import java.util.ArrayList;

/**
 * Created by 48089748z on 11/12/15.
 */
public class Pila <Anything>
{
    public ArrayList<Anything> vector = new ArrayList<>();

    public Pila(){}

    public void add(Anything variable)
    {
        vector.add(variable);
    }
    public void remove()
    {
        vector.remove(vector.size()-1);
    }
    public Integer size()
    {
        return vector.size();
    }
    public Anything get(Integer position)
    {
        return vector.get(position);
    }
    public void clear()
    {
        vector.clear();
    }
    public boolean contains(Anything variable)
    {
        return vector.contains(variable);
    }
}
