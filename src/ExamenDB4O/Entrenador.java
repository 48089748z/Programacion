package ExamenDB4O;

/**
 * Created by 48089748z on 26/02/16.
 */
public class Entrenador
{
    private String nombre;
    private Integer añosExperiencia;
    public Entrenador(){}

    public String toString()
    {
        return "\n               ENTRENADOR\n               {\n               Nombre: "+nombre+ "\n               Años Experiencia: "+añosExperiencia+"\n               }";

    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAñosExperiencia() {
        return añosExperiencia;
    }

    public void setAñosExperiencia(Integer añosExperiencia) {
        this.añosExperiencia = añosExperiencia;
    }
}
