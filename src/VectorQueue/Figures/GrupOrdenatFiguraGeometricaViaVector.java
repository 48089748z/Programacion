package VectorQueue.Figures;

import java.util.Vector;

/**
 * Created by 48089748z on 19/01/16.
 */
public class GrupOrdenatFiguraGeometricaViaVector
{
    private Integer size;
    private Integer dicotomic = size;
    private Vector<FiguraGeometrica02> vector = new Vector<>();
    GrupOrdenatFiguraGeometricaViaVector(Integer vectorSize)
    {
        if (vectorSize>=0)
        {
            size = vectorSize;
            vector.setSize(vectorSize);
        }
        else
        {
            System.out.println("El Tamany del Vector no pot ser menor o igual que 0!");
        }
    }
    public void addTriangle(Triangle02 triangle) {vector.add(triangle.getCodi(), triangle);}
    public void addRectangle(Rectangle02 rectangle) {vector.add(rectangle.getCodi(), rectangle);}
    public void addCercle(Cercle02 cercle) {vector.add(cercle.getCodi(), cercle);}
    public void search(Integer codi)
    {
        if (codi>=0 && codi<size)
        {
            if ( vector.get(codi)!=null)
            {
                vector.get(codi).visualitzar();
            }
            else
            {
                System.out.println("No existeix cap figura amb aquest codi!");
            }
        }
        else {System.out.println("Has posat un codi no valid!");}
    }
    public void empty() {vector.removeAllElements();}
    public boolean equals(FiguraGeometrica02 figura1, FiguraGeometrica02 figura2)
    {
        if (figura1.equals(figura2)) return true;
        else return false;
    }
    public void printAll()
    {
        for (int x=0; x<vector.size(); x++)
        {
            if (vector.get(x)!=null)
            {
                vector.get(x).visualitzar();
            }
        }
    }
}
