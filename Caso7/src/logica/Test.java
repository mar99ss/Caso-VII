/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Carlos Vega
 */
public class Test {
  private final String secretKeyPrin = "29dh120bdk133";
  private final String originalString = "xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0=";
  private final String parte1= "29dh120";
  private final String parte2= "dk1";
  private final String parte3= "3";
  private final String[] letras={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
  private final String[] numeros={"0","1","2","3","4","5","6","7","8","9"};
  private Ranking ranking;
  private ArrayList<String> arrayLetras;
  private ArrayList<String> arrayNumeros;
  
  public Test(){ 
  }
  
  public void randomizarArreglos(){
    arrayLetras= new ArrayList<>();
    arrayNumeros= new ArrayList<>();
    arrayLetras.addAll(Arrays.asList(letras));
    arrayNumeros.addAll(Arrays.asList(numeros));
    
    Collections.shuffle(arrayLetras);
    Collections.shuffle(arrayNumeros);
  }
  
  public void crearRanking(){
    ranking = new Ranking();
     int cantidadConjuntos=(int) (Math.random() * 10);
    for (int indice=0;indice<7;indice++){
      Conjunto conjunto = new Conjunto();
      
      for(int letra =0;letra<(Math.random()*26);letra++){
        int letraAleatorio= (int)(Math.random()*26);
        conjunto.getCaracteres().add(arrayLetras.get(letraAleatorio));
      }
      
      for(int numero =0;numero<(Math.random()*10);numero++){
        int numeroAleatorio= (int)(Math.random()*10);
        conjunto.getNumeros().add(arrayNumeros.get(numeroAleatorio));
      }
      
      ranking.getRanking().add(conjunto);
    }
    calcularSucced();
    ranking.getSortedRankingBysucced();
  }
  
  public void calcularSucced(){
    for(int indice=0;indice<ranking.getRanking().size();indice++){
      ArrayList<String> conjuntoLetras= ranking.getRanking().get(indice).getCaracteres();
      ArrayList<String> conjuntoNumeros= ranking.getRanking().get(indice).getNumeros();
      double cantidadSucced=0;
      for(int letra=0;letra<conjuntoLetras.size();letra++){
        for(int numero=0;numero<conjuntoNumeros.size();numero++) {
	    	String secretKey= parte1+letras[letra]+parte2+Integer.toString(numero)+parte3;
	    	System.out.println("Letra: "+letras[letra]);
	        System.out.println("Numero: "+numeros[numero]); 
	        String decryptedString = AES.decrypt(originalString, secretKey) ;
	        if(decryptedString!=null){
	            System.out.println(originalString); 
	            System.out.println(decryptedString);
	            System.out.println(secretKey);  
	            cantidadSucced++;
	            
	        }
        }
      }
      ranking.getRanking().get(indice).setSucced(cantidadSucced/
    		  (Double.valueOf((ranking.getRanking().get(indice).getCaracteres().size()+ranking.getRanking().get(indice).getNumeros().size())))*100);
    }
  }
  
  public void random(){
    
    randomizarArreglos();
    crearRanking();
    for(int i =0;i<ranking.getRanking().size();i++ ){
      System.out.println(ranking.getRanking().get(i).toString());
    }
     
  }
  
  public ArrayList<Conjunto> conseguirRespuesta(){
	  ArrayList<Conjunto> conjuntoFinal = new ArrayList<>();
	  return conjuntoFinal;
  }
}
