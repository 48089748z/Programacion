package VectorQueue.Figures;
import java.awt.Color;

public class Triangle02 extends FiguraGeometrica02 {
   private double base;
   private double altura;
   
   public Triangle02 (int ncodi, String nnom, Color ncolor, double nbase, double naltura) {
      super (ncodi, nnom, ncolor);
      if (nbase>0) base=nbase;
      if (naltura>0) altura=naltura;
   }

   public Triangle02() {
   }
   
   public Triangle02 (Triangle02 r) {
      this (r.codi, r.nom, r.color, r.base, r.altura);
   }
   
   public void setBase (double novaBase) {
      if (novaBase>=0) base = novaBase;
   }

   public void setAltura (float novaAltura) {
      if (novaAltura>=0) altura = novaAltura;
   }

   public double getBase() {
      return base;
   }
   
   public double getAltura() {
      return altura;
   }
   
   public double area () {
      return base*altura/2;
   }

   public void visualitzar ()
   {
      System.out.println ("\nSoc un triangle");
      System.out.println ("***************");
      super.visualitzar();
      System.out.println ("Base..........:" + base);
      System.out.println ("Altura........:" + altura);
      System.out.println ("Area..........:" + area());
   }

   public String toString() {
      return "Triangle " + super.toString();
   }

   public static void main (String args[]) {
      Triangle02 t1 = new Triangle02 ();
      Triangle02 t2 = new Triangle02 (1, "Triangle02 1", Color.yellow, 3,4);
      t1.visualitzar();
      t2.visualitzar();
      System.out.println("t1 = " + t1);
      System.out.println("t2 = " + t2);
   }
}