import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class FileToString {
	public ArrayList<String> ConverString   (String UbicacionT) throws FileNotFoundException {		
		File archivo = null;
	    FileReader fr = null;
	    BufferedReader br = null;
	    String Temps=null;
	    ArrayList<String> ConvertedToString = new ArrayList();
	    int Tempi=0;
	    try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File (UbicacionT);
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	         // Lectura del fichero
	         while((Temps=br.readLine())!=null)
	        	//Contador de lineas
	         	{
	        	 ConvertedToString.add(Temps);
	        	 //System.out.println("Se guarda el valor"+ ConvertedToString.get(Tempi));
	        	 Tempi++;
	         	}
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
		return ConvertedToString;
	}	
}
