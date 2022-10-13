/******************************************************************/
/* Program Assignment:  PSP2.1                                    */
/* Name:                Edgar Valentin Ruiz Padilla               */
/* Date:                24-noviembre-2021                         */
/* Description:         Programa para determinar correlacion,     */
/* desviacion estandar, "tail area", regresion Lineal, prediccion */
/* Y Rango, UPI y LPI                                             */
/******************************************************************/
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		System.out.println("Programa para calcular la correlacion de 2 conjuntos de numeros, ");
		System.out.println("Se calculara la regresion lineal, ");
		System.out.println("Tambien se calculara una prediccion Yk a partir un valor dado");
		System.out.println("y por ultimo calculara un intervalo de prediccion del 70%");
		int Ntemp=(int) SolicitarDatos("Ingresa la cantidad de numeros por conjunto a ingresar");
		
		double [] DBX= new double [Ntemp];
		for(int b=0;b<Ntemp; b++) {
			int bT=b+1;
			DBX[b]=SolicitarDatos("Ingrese el numero "+ bT +" del conjunto X");
		}
		double [] DBY= new double [Ntemp];
		for(int b=0;b<Ntemp; b++) {
			int bT=b+1;
			DBY[b]=SolicitarDatos("Ingrese el numero "+ bT +" del conjunto Y");
		}
		double Xk =SolicitarDatos("el valor Xk a estimar");
		
		
		//testing
		/*Test 1
		double [] DBX= {130,650,99,150,128,302,95,945,368,961}; //Estimated Proxy Size
		double [] DBY= {186,699,132,272,291,331,199,1890,788,1601}; //Actual Added and Modified Size
		//int Xk=386;
		Test 2
		double [] DBX= {130,650,99,150,128,302,95,945,368,961}; //Estimated Proxy Size
		double [] DBY= {15.0,69.9,6.5,22.4,28.4,65.9,19.4,198.7,38.8,138.2}; //Actual Added and Modified Size
		int Xk=386;		
		*/
		
		Operaciones operacion = new Operaciones(DBX,DBY,Xk);
		operacion.Correlacion(DBX,DBY);
		operacion.Correlacion2();
		operacion.RegresionLineal(DBX, DBY);
		operacion.PrediccionY(operacion.Beta0,operacion.Beta1,Xk);
		operacion.Rango();
		System.out.println(operacion);
	}
	public static double SolicitarDatos (String titulo) {
		String Entrada = "";
		double dato=0;
		Boolean VEntrada = false;
		Scanner entrada = new Scanner(System.in);
		while (VEntrada != true) {
			System.out.println(titulo);
			Entrada = entrada.nextLine();
			VEntrada = Entrada.matches("[+-]?\\d*(\\.\\d+)?");
		}
		dato=Double.parseDouble(Entrada);
		return dato;
	}

}
