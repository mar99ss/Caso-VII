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
  private static int contador;
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
    int totalConjuntos=(int)(Math.random()*100);
    for (int indice=0;indice<18;indice++){
      Conjunto conjunto = new Conjunto(); 
      int totalLetras=(int)(Math.random()*7)+1;
      for(int letra =0;letra<totalLetras;letra++){
        int letraAleatorio= (int)(Math.random()*26);
    	conjunto.getCaracteres().add(arrayLetras.get(letraAleatorio));
      }
      Set<String> set = new HashSet<>(conjunto.getCaracteres());
      conjunto.getCaracteres().clear();
  	  conjunto.getCaracteres().addAll(set);
      int totalNumeros=(int)(Math.random()*4)+1;
      for(int numero =0;numero<totalNumeros;numero++){
        int numeroAleatorio= (int)(Math.random()*10);
    	conjunto.getNumeros().add(arrayNumeros.get(numeroAleatorio));
      }
      
      ranking.getRanking().add(conjunto);
    }
    calcularSucced(); 
    desecharPosibilidades();
    ranking.getSortedRankingBysucced(); 
  }
  
  public void calcularSucced(){
    for(int indice=0;indice<ranking.getRanking().size();indice++){
      ArrayList<String> conjuntoLetras= ranking.getRanking().get(indice).getCaracteres();
      ArrayList<String> conjuntoNumeros= ranking.getRanking().get(indice).getNumeros();
     
      double cantidadSucced=0;
      if(conjuntoLetras.size()>0 && conjuntoNumeros.size()>0) {
	      for(int letra=0;letra<conjuntoLetras.size();letra++){
	        for(int numero=0;numero<conjuntoNumeros.size();numero++) {
		    	String secretKey= parte1+conjuntoLetras.get(letra)+parte2+conjuntoNumeros.get(numero)+parte3;
		        String decryptedString = AES.decrypt(originalString, secretKey) ;
		        contador++;
		        if(decryptedString!=null){
		            cantidadSucced++;
		        }
	        }
	      }
      }
      
      ranking.getRanking().get(indice).setSucced((cantidadSucced/
    		  (Double.valueOf((ranking.getRanking().get(indice).getCaracteres().size()+ranking.getRanking().get(indice).getNumeros().size()))))*100);
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
	System.out.println(contador);
  }
  
  public Conjunto conseguirRespuesta(){
    ArrayList<Conjunto> arrayFinal = ranking.getRanking();
    Conjunto conjuntoFin = new Conjunto();
    if(arrayFinal.size()!=1&& arrayFinal.size()!=2) {
	    for(int indice=0;indice<arrayFinal.size();indice++) {
	    	System.out.println(arrayFinal.get(indice).getCaracteres());
	    	System.out.println(arrayFinal.get(indice).getNumeros());
	    	if(indice+1<arrayFinal.size()) {
	    		//System.out.println(arrayFinal.get(indice).toString());
	    		for(int contador=indice+1;contador<arrayFinal.size();contador++) {
	    			for(int caracter=0;caracter<arrayFinal.get(contador).getCaracteres().size();caracter++) {
		    			
		    			if(arrayFinal.get(indice).getCaracteres().contains(arrayFinal.get(contador).getCaracteres().get(caracter))){
		    				conjuntoFin.getCaracteres().add(arrayFinal.get(contador).getCaracteres().get(caracter));
		    			}
		    		}
		    		
		    		for(int numero=0;numero<arrayFinal.get(contador).getNumeros().size();numero++) {
						if(arrayFinal.get(indice).getNumeros().contains(arrayFinal.get(contador).getNumeros().get(numero))){
							conjuntoFin.getNumeros().add(arrayFinal.get(contador).getNumeros().get(numero));
						}
		    		}
		    	}
    		}
	    		
	    }
    }else if(arrayFinal.size()==2) {
    	conjuntoFin.getCaracteres().addAll(arrayFinal.get(0).getCaracteres());
    	conjuntoFin.getNumeros().addAll(arrayFinal.get(0).getNumeros());
    	conjuntoFin.getCaracteres().addAll(arrayFinal.get(1).getCaracteres());
    	conjuntoFin.getNumeros().addAll(arrayFinal.get(1).getNumeros());
    }else {
    	conjuntoFin=arrayFinal.get(0);
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
  
  void desecharPosibilidades() {
	  int indice=0;
	  while(indice<ranking.getRanking().size()) {
		  if (ranking.getRanking().get(indice).getSucced()<1.0) {
	    	  ranking.getRanking().remove(indice);
	      }else {
	    	  indice++;
	      }
		  
	  }
  }
}
