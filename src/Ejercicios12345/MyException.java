package Ejercicios12345;

/**
 * Created by 48089748z on 08/10/15.
 */
public class MyException extends Throwable
{
    String cadena;
    public MyException(String cadena)
    {
        this.cadena=cadena;
    }
    public String getCadena()
    {
        return cadena;
    }
    public void setCadena(String cadena)
    {
        this.cadena = cadena;
    }
}
