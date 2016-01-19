package VectorQueue.Figures;
import java.awt.Color;

public class Cercle02 extends FiguraGeometrica02
{
   private double radi;
   
   public Cercle02 (int ncodi, String nnom, Color ncolor, double nradi) {
      super (ncodi, nnom, ncolor);
      if (nradi>0) radi=nradi;
   }
   public Cercle02 (Cercle02 c) {
      this (c.codi, c.nom, c.color, c.radi);
   }
   public Cercle02 () {}

   public void setRadi (double nouRadi) {
      if (nouRadi>=0) radi = nouRadi;
   }

   public double getRadi () {
      return radi;
   }
   
   public double perimetre () {
      return 2*Math.PI*radi;
   }

   public double area () {
      return Math.PI * radi * radi;
   }
   
   public void visualitzar () {
      System.out.println ("\nSoc un cercle");
      System.out.println ("*************");
      super.visualitzar();
      System.out.println ("Radi..........:" + radi);
      System.out.println ("Longitud......:" + perimetre());
      System.out.println ("Area..........:" + area());
   }
   
   public String toString() {
      return "Cercle " + super.toString();
   }

   public static void main (String args[]) {
      Cercle02 c1 = new Cercle02 ();
      Cercle02 c2 = new Cercle02 (1, "Cercle02 1", Color.green, 42.42);
      c1.visualitzar();
      c2.visualitzar();
      System.out.println("c1 = " + c1);
      System.out.println("c2 = " + c2);
  }
}