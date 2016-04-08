package Examen2OCunado;

import java.util.ArrayList;

public class Conflicto {
	private int codigo;
	private String nombre;
	private String zona;
	private int heridos;
	private ArrayList<GrupoArmado> gruposArmados = new ArrayList<>();

	public Conflicto(){}
	public Conflicto(int codigo, String nombre, String zona, int heridos) {
		this.codigo= codigo;
		this.nombre = nombre;
		this.zona = zona;
		this.heridos = heridos;
	}
	public int getCodigo( ) {
		return codigo;
	}
	public String getNombre( ) {
		return nombre;
	}
	public String getZona( ) {
		return zona;
	}
	public int getHeridos( ) {
		return heridos;
	}
	public String toString()
	{
		return "\n CONFLICTO \nNombre: "+nombre+"\nCodigo: "+codigo+"\nZona: "+zona+"\nHeridos: "+heridos+"\nGrupos Armados: "+getGrupos();
	}
	public String getGrupos()
	{
		String toReturn = "Ninguno asignado";
		for (int x=0; x<gruposArmados.size(); x++)
		{
			toReturn = toReturn +gruposArmados.get(x).getNombre()+" ";
		}
		return toReturn;
	}
}
