package ExamenDB4O;

import com.db4o.ObjectContainer;

/**
 * Created by 48089748z on 26/02/16.
 */
public class Jugador
{
    private String dni;
    private String nombre;
    private String apellido;
    private String altura;
    private String peso;
    private Caracteristicas caracteristicas;
    public Jugador(){}

    public String toString()
    {
        return "\nJUGADOR\n{\n     DNI: "+dni+"\n     Nombre: "+nombre+"\n     Apellido: "+apellido+"\n     Altura: "+altura+"\n     Peso: "+peso+caracteristicas.toString()+"\n}";
    }
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public Caracteristicas getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Caracteristicas caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }
}
