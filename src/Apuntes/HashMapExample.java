package Apuntes;
import java.util.HashMap;
import java.util.Map;
public class HashMapExample
{
    public static void main(String[] args) {
        Map map = new HashMap(); //HashMap is similar to an ArrayList but instead of index, it has a key-value.
        map.put("KeyA", 1);
        map.put("KeyB", "1");
        map.put("KeyC",  2);
        map.put("KeyD", "2");
        map.put("KeyE", "3");
        map.put("KeyF", true);
        map.put("KeyG", false);
        map.put("KeyH", 2.0);
        map.put("KeyI", 3.23423);
        map.put("KeyJ", null);

        System.out.println(map.values()); //Prints the values 1, 1, 2, 2, 3, true, false, 2.0, 3.23423, null, BUT NOT IN THIS ORDER
    }
}
