package VectorQueue.Queue;

import java.util.Date;

/**
 * Created by 48089748z on 19/01/16.
 */
enum Prioridad {BAJA, MEDIA, ALTA};
public class Peticion
{
    private String usuario;
    private String cadena;
    private Prioridad prioridad;
    private Date fecha;
    private Integer comparator;
    public Peticion(String usuario, String cadena, Integer prioridad, Date fecha)
    {
        this.usuario=usuario;
        this.cadena=cadena;
        if (prioridad==0)
        {
            this.prioridad=Prioridad.BAJA;
            comparator = prioridad;
        }
        else if (prioridad==1)
        {
            this.prioridad=Prioridad.MEDIA;
            comparator = prioridad;
        }
        else if (prioridad==2)
        {
            this.prioridad=Prioridad.ALTA;
            comparator = prioridad;
        }
        else
        {
            System.out.println("\n Los valores de Prioridad son 0 BAJA, 1 MEDIA, 2 ALTA\n Como has introducido un valor no valido le daremos Prioridad BAJA a la Petición:\n"+this.toString());
            comparator=0;
        }
        this.fecha=fecha;
    }
    public Integer getComparator() {return comparator;}
    public String toString() {return "\n Usuario: "+usuario+"\n Cadena a imprimir: "+cadena+"\n Prioridad: "+prioridad+"\n Fecha: "+fecha;}
}
