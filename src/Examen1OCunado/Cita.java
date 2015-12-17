package Examen1OCunado;

/**
 * Created by 48089748z on 11/12/15.
 */
public class Cita
{
    private Persona persona;
    private DiaSemana diaSemana;
    private String fecha;
    private String descripcionAveria;
    private String matricula;
    private String presupuesto;
    public Cita(){}

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcionAveria() {
        return descripcionAveria;
    }

    public void setDescripcionAveria(String descripcionAveria) {
        this.descripcionAveria = descripcionAveria;
    }

    public String getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String toString()
    {
        return persona.toString()+"\nDia de la semana: "+diaSemana+"\n Fecha: "+fecha+"\nDescripción Averia: "+descripcionAveria+"\n Matricula: "+matricula+"\nPresupuesto: "+presupuesto+"\n";
    }
}
