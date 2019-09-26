/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

/**
 *
 * @author Carlos Vega
 */
public class Test { 
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
    for (int indice=0;indice<65;indice++){
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
	    	String secretKey= parte1+conjuntoLetras.get(letra)+parte2+conjuntoNumeros.get(numero)+parte3;
	        String decryptedString = AES.decrypt(originalString, secretKey) ;
	        if(decryptedString!=null){
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
    Conjunto respuestaFinal= conseguirRespuesta();
    String respuesta= "Es muy probable que la solución esté entre estos datos \n letras: ";
    for(int indice=0;indice<respuestaFinal.getCaracteres().size();indice++) {
    	respuesta+=respuestaFinal.getCaracteres().get(indice)+"\t";
    }
    respuesta+="\n numeros: ";
	for(int indice=0;indice<respuestaFinal.getNumeros().size();indice++) {
	    respuesta+=respuestaFinal.getNumeros().get(indice)+"\t";
    }
	System.out.println(respuesta);
  }
  
  public Conjunto conseguirRespuesta(){
    ArrayList<Conjunto> arrayFinal = new ArrayList<>();
    Conjunto conjuntoFin = new Conjunto();
    for(int indice =0;indice<ranking.getRanking().size();indice++ ){
      if (ranking.getRanking().get(indice).getSucced()>0.0) {
    	  arrayFinal.add(ranking.getRanking().get(indice));
      }
    }
    for(int indice=0;indice<arrayFinal.size();indice++) {
    	if(indice+1<arrayFinal.size()) {
    		for(int caracter=0;caracter<arrayFinal.get(indice+1).getCaracteres().size();caracter++) {
    			if(arrayFinal.get(0).getCaracteres().contains(arrayFinal.get(indice+1).getCaracteres().get(caracter))){
    				conjuntoFin.getCaracteres().add(arrayFinal.get(indice+1).getCaracteres().get(caracter));
    			}
    		}
    		
    		for(int numero=0;numero<arrayFinal.get(indice+1).getNumeros().size();numero++) {
    			if(arrayFinal.get(0).getNumeros().contains(arrayFinal.get(indice+1).getNumeros().get(numero))){
    				conjuntoFin.getNumeros().add(arrayFinal.get(indice+1).getNumeros().get(numero));
    			}
    		}
    	}
    }
    if(!conjuntoFin.getCaracteres().isEmpty()&&!conjuntoFin.getNumeros().isEmpty()) {
    	Set<String> set = new HashSet<>(conjuntoFin.getCaracteres());
    	conjuntoFin.getCaracteres().clear();
    	conjuntoFin.getCaracteres().addAll(set);
    	set=new HashSet<>(conjuntoFin.getNumeros());
    	conjuntoFin.getNumeros().clear();
    	conjuntoFin.getNumeros().addAll(set);
    }
    
    return conjuntoFin;
  }
}
