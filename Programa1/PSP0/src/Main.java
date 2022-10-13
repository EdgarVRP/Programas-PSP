import java.util.Scanner;

//Programa 1 Calculo de Media y desviacion estandar de conjuntos de numeros
//Lectura de datos

public class Main {

	public static void main(String[] args) {
		//Declaración de Variables
		int Ntemp=0;
		String Entrada="";
		Boolean VEntrada=false;
		Scanner entrada=new Scanner(System.in);
		System.out.println("Programa para calcular la media y la desviacion estandar de números reales.");
		
		while(VEntrada!=true) {
			System.out.println("Ingresa la cantidad de numeros a considerar");
			Entrada=entrada.nextLine();
			VEntrada=Entrada.matches("[+-]?\\d*(\\.\\d+)?");
		}
		Ntemp=Integer.parseInt(Entrada);
		double [] DB= new double [Ntemp];
		VEntrada=false;
		for(int b=0;b<Ntemp; b++) {
			while(VEntrada!=true) {
				int bT=b+1;
				System.out.println("Ingrese el numero "+ bT);
				Entrada=entrada.nextLine();
				VEntrada=Entrada.matches("[+-]?\\d*(\\.\\d+)?");
			}
			DB[b]=Double.parseDouble(Entrada);
			VEntrada=false;
		}
		entrada.close();
		Operaciones operacion = new Operaciones();
		double Media =operacion.MediaAritmetica(DB);
		System.out.println("La Media Aritmetica es: "+ Media);
		double Desviacion =operacion.DesviacionEstandar(DB, Media);
		System.out.println("La Desviación Estandar es: "+ Desviacion);
	}
	
	
	
	
}
