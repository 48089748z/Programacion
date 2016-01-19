package VectorQueue.Figures;
import java.awt.Color;

public abstract class FiguraGeometrica02
{
   protected int codi;
   protected String nom;
   protected Color color=Color.white;

   public FiguraGeometrica02 (int codi, String nom, Color color)
   {
      if (codi>0) this.codi = codi;
      this.nom = nom;
      this.color = color;
   }

   public FiguraGeometrica02(FiguraGeometrica02 f) {
      this (f.codi, f.nom, f.color);
   }

   public FiguraGeometrica02 () {}

   public void setCodi(int nouCodi) {
      if (nouCodi>=0) codi = nouCodi;
   }

   public void setNom (String nouNom) {
      nom = nouNom;
   }

   public void setColor (Color nouColor) {
      color = nouColor;
   }   

   public int getCodi () {
      return codi;
   }

   public String getNom () {
      return nom;
   }
   
   public Color getColor () {
      return color;
   }

   public void visualitzar ()
   {
      System.out.println ("Codi..........:" + codi);
      System.out.println ("Nom...........:" + nom);
      System.out.println ("Color.........:" + color);
   }
   public boolean equals (Object obj)
   {
      if (this == obj) return true;
      else return false;
   }
   public String toString ()
   {
      return codi + " - " + nom;
   }
   abstract public double area ();   
}