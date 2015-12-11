package PilaCola;
import java.util.ArrayList;

public class Cola <Anything>
{
    public ArrayList<Anything> vector = new ArrayList<>();

    public Cola(){}

    public void add(Anything variable)
    {
        vector.add(variable);
    }
    public void remove(Integer position)
    {
        vector.remove(position);
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
