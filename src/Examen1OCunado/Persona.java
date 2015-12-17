package Examen1OCunado;

/**
 * Created by 48089748z on 11/12/15.
 */
public class Persona
{
    private String dni;
    private String nombre;
    public Persona(){}

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String toString()
    {
        return "\nNombre Persona: "+nombre+"\nDNI: "+dni;
    }
}
