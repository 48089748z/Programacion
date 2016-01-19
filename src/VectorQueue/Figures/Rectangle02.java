package VectorQueue.Figures;
import java.awt.Color;

public class Rectangle02 extends FiguraGeometrica02 {
   private double base;
   private double altura;
   
   public Rectangle02 (int ncodi, String nnom, Color ncolor, double nbase, double naltura) {
      super (ncodi, nnom, ncolor);
      if (nbase>0) base=nbase;
      if (naltura>0) altura=naltura;
   }

   public Rectangle02() {
   }
   
   public Rectangle02 (Rectangle02 r) {
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
   
   public double perimetre () {
      return base*2+altura*2;
   }

   public double area () {
      return base*altura;
   }

   public void visualitzar () {
      System.out.println ("\nSoc un rectangle");
      System.out.println ("****************");
      super.visualitzar();
      System.out.println ("Base..........:" + base);
      System.out.println ("Altura........:" + altura);
      System.out.println ("Perimetre.....:" + perimetre());
      System.out.println ("Area..........:" + area());
   }

   public String toString() {
      return "Rectangle " + super.toString();
   }

   public static void main (String args[]) {
      Rectangle02 r1 = new Rectangle02 ();
      Rectangle02 r2 = new Rectangle02 (1, "Rectangle02 1", Color.green, 3,4);
      r1.visualitzar();
      r2.visualitzar();
      System.out.println("r1 = " + r1);
      System.out.println("r2 = " + r2);
   }
}