package Examen3OCunado;
import java.io.Serializable;
public class GrupoArmado implements Serializable
{
	private String nombre;
	private int codigo;
	private int bajas;
	public GrupoArmado(){}
	public GrupoArmado (String nombre,int cod, int bajas)
	{
		this.nombre = nombre;
		this.codigo = cod;
		this.bajas = bajas;
	}
	public String getNombre() {
		return nombre;
	}
	public  int getCodigo() {
		return codigo;
	}
	public int getBajas() {
		return bajas;
	}
	public void setNombre( String nombre) {
		this.nombre= nombre;
	}
	public String toString()
	{
		return "\n GRUPO ARMADO \nNombre: "+nombre+"\nCodigo: "+codigo+"\nBajas: "+bajas;
	}
}
