package ExamenDB4O;

import java.util.ArrayList;

/**
 * Created by 48089748z on 26/02/16.
 */
public class Equipo
{
    private String nombre;
    private String estadio;
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private Entrenador entrenador;
    private Liga liga;
    public Equipo(){}

    public String toString()
    {
        return "\nEQUIPO\n{\n     Nombre: "+nombre+"\n     Estadio: "+estadio+"\n     "+getPlayersString()+entrenador.toString()+liga.toString()+"\n}";
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }
    public void addJugador(Jugador jugador)
    {
        jugadores.add(jugador);
    }
    public Liga getLiga() {
        return liga;
    }
    public void setLiga(Liga liga) {
        this.liga = liga;
    }
    public String getPlayersString()
    {
        if (jugadores.size()==0)
        {
            return " No hay jugadores asignados a este Equipo ";
        }
        else
        {
            String toReturn = "Jugadores: [ ";
            for (int x=0; x<jugadores.size(); x++)
            {
                toReturn=toReturn+jugadores.get(x).getNombre()+", ";
            }
            toReturn=toReturn+"]";
            return toReturn;
        }
    }
}
