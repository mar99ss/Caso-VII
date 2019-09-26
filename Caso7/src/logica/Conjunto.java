package logica;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Carlos Vega
 */
public class Conjunto {
  private ArrayList<String> caracteres;
  private ArrayList<String> numeros;
  private double succed;

  public Conjunto() {
    caracteres=new ArrayList<>();
    numeros= new ArrayList<>();
  }

  public ArrayList<String> getCaracteres() {
    return caracteres;
  }

  public ArrayList<String> getNumeros() {
    return numeros;
  }
  
  public double getSucced() {
    return succed;
  }
  
  public void setSucced(double d) {
	  succed=d;
  }
  
  public static Comparator<Conjunto> succedComparator = new Comparator<Conjunto>() {         
    @Override         
    public int compare(Conjunto conjunto1, Conjunto conjunto2) {             
      return (conjunto1.getSucced() < conjunto2.getSucced() ? 1 :                     
              (conjunto2.getSucced() == conjunto1.getSucced() ? 0 : -1));           
    }     
  }; 
  public String toString(){
    String cadena= String.valueOf(succed);
    return cadena;
  }
  
}
