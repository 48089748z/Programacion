package ExamenDB4O;

/**
 * Created by 48089748z on 26/02/16.
 */
public class Caracteristicas
{
    private Integer agilidad;
    private Integer fuerza;
    private Integer velocidad;
    private Integer pase;
    private Integer penalti;
    public Caracteristicas(){}

    public String toString()
    {
        return "\n               CARACTERISTICAS\n               {\n               Agilidad: "+agilidad+ "\n               Fuerza: "+fuerza+"\n               Pase: "+pase+"\n               Penalti: "+penalti+"\n               Velocidad: "+velocidad+"\n               }";
    }
    public Integer getAgilidad() {
        return agilidad;
    }

    public void setAgilidad(Integer agilidad) {
        this.agilidad = agilidad;
    }

    public Integer getFuerza() {
        return fuerza;
    }

    public void setFuerza(Integer fuerza) {
        this.fuerza = fuerza;
    }

    public Integer getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Integer velocidad) {
        this.velocidad = velocidad;
    }

    public Integer getPenalti() {
        return penalti;
    }

    public void setPenalti(Integer penalti) {
        this.penalti = penalti;
    }

    public Integer getPase() {
        return pase;
    }

    public void setPase(Integer pase) {
        this.pase = pase;
    }
}
