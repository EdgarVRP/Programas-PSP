/******************************************************************/
/* Program Assignment:  PSP2.1                                    */
/* Name:                Edgar Valentin Ruiz Padilla               */
/* Date:                25-noviembre-2021                         */
/* Description:         Programa para determinar regresion        */
/* multiple de 3 variables obteniendo Beta0, Beta1, Beta2, Beta3  */
/* se calcula tambien un valor de prediccion,UPI y LPI            */
/******************************************************************/
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		System.out.println("Programa programa para calcular los parámetros de estimación de regresión "
				+ "\nmúltiple de tres variables, ingresando un sistema de ecuaciones 4 X 4"
				+ "\nse resolvera el sistema de ecuaciones por el metodo de Gauss");
		int Ntemp=(int) SolicitarDatos("Ingresa la cantidad de numeros por conjunto a ingresar");
		double W[]= new double [Ntemp];
		double X[]= new double [Ntemp];
		double Y[]= new double [Ntemp];
		double Z[]= new double [Ntemp];
		for(int b=0;b<Ntemp; b++) {
			int bT=b+1;
			W[b]=SolicitarDatos("Ingrese el numero "+ bT +" del conjunto W");
		}
		for(int b=0;b<Ntemp; b++) {
			int bT=b+1;
			X[b]=SolicitarDatos("Ingrese el numero "+ bT +" del conjunto X");
		}
		for(int b=0;b<Ntemp; b++) {
			int bT=b+1;
			Y[b]=SolicitarDatos("Ingrese el numero "+ bT +" del conjunto Y");
		}
		for(int b=0;b<Ntemp; b++) {
			int bT=b+1;
			Z[b]=SolicitarDatos("Ingrese el numero "+ bT +" del conjunto Z");
		}
		System.out.println("Ingrese los datos solicitados para realizar el calculo de la prediccion");
		double Wk=SolicitarDatos("Ingrese wk ");
		double Xk=SolicitarDatos("Ingrese Xk ");
		double Yk=SolicitarDatos("Ingrese Yk ");
		
		//Testing
		/*
		double W[]= {1142,863,1065,554,983,256};
		double X[]= {1060,995,3205,120,2896,485};
		double Y[]= {325,98,23,0,120,88};
		double Z[]= {201,98,162,54,138,61};
		double Wk=650;
		double Xk=3000;
		double Yk=155;
		*/
		
		Operaciones operacion = new Operaciones(W,X,Y,Z,Wk,Xk,Yk);
		operacion.MatrizGauss();
		operacion.Gauss();
		operacion.Rango();
		operacion.PrediccionY();
		operacion.LUPI();
		System.out.println(operacion.toString());
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