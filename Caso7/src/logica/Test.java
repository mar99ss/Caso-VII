package logica;

/**
 *
 * @author 1001001238
 */
public class Test { 
  public Test(){ 
  }
  
  public void random(){
    final String secretKeyPrin = "29dh120bdk133";
    String originalString = "xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0=";
    String parte1= "29dh120";
    String parte2= "dk1";
    String parte3= "3";
    String[] letras={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    String[] numeros={"0","1","2","3","4","5","6","7","8","9"};
    boolean verificacion= true;
    double aleatorio;
    
    do{
      aleatorio=Math.random();
      if(aleatorio<=0.39){
        int letra= (int) (Math.random() * 26);
        int numero= (int) (Math.random() * 9);
        String secretKey= parte1+letras[letra]+parte2+Integer.toString(numero)+parte3;

        System.out.println("Letra: "+letras[letra]);
        System.out.println("Numero: "+numeros[numero]); 
        String decryptedString = AES.decrypt(originalString, secretKey) ;

        if(decryptedString!=null){
          System.out.println(originalString); 
          System.out.println(decryptedString);
          System.out.println(secretKey);  
          verificacion= false; 
        }  
      }
     } while(verificacion); 
     System.out.println("Salió"); 
  } 
} 