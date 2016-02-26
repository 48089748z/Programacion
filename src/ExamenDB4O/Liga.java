package ExamenDB4O;

/**
 * Created by 48089748z on 26/02/16.
 */
public class Liga
{
    private String nombre;
    private String categoria;
    private String patrocinador;
    public Liga(){}

    public String toString()
    {
        return "\n               LIGA\n               {\n               Nombre: "+nombre+"\n               Categoria: "+categoria+"\n               Patrocinador: "+patrocinador+"\n               }";
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }
}
