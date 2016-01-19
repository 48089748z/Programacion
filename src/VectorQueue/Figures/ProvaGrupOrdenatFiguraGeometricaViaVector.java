package VectorQueue.Figures;

import java.awt.*;

/**
 * Created by 48089748z on 19/01/16.
 */
public class ProvaGrupOrdenatFiguraGeometricaViaVector
{
    public static void main(String[] args)
    {
        GrupOrdenatFiguraGeometricaViaVector manager = new GrupOrdenatFiguraGeometricaViaVector(100);

        Triangle02 triangle = new Triangle02(25, "triangle", Color.blue, 5.0, 5.0);
        manager.addTriangle(triangle);
        manager.search(3);

        manager.addCercle(new Cercle02());
        manager.search(0);
        manager.printAll();
    }
}
