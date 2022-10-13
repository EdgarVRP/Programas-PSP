/******************************************************************/
/* Program Assignment:  PSP1								*/
/* Name:                Edgar Valentin Ruiz Padilla			*/
/* Date:                10/10/2021							*/
/* Description:         Programa Calculo Regresion Lineal	*/
/******************************************************************/
import java.util.Scanner;
public class main {

	public static void main(String[] args) {
		//Declaración de Variables
				int Ntemp=0;
				String Entrada="";
				Boolean VEntrada=false;
				Scanner entrada=new Scanner(System.in);
				System.out.println("Programa para calcular la Regresion Lineal");
				while(VEntrada!=true) {
					System.out.println("Ingresa la cantidad de numeros por conjunto a ingresar");
					Entrada=entrada.nextLine();
					VEntrada=Entrada.matches("[+-]?\\d*(\\.\\d+)?");
				}
				Ntemp=Integer.parseInt(Entrada);
				double [] DBX= new double [Ntemp];
				VEntrada=false;
				for(int b=0;b<Ntemp; b++) {
					while(VEntrada!=true) {
						int bT=b+1;
						System.out.println("Ingrese el numero "+bT+" del conjunto X");
						Entrada=entrada.nextLine();
						VEntrada=Entrada.matches("[+-]?\\d*(\\.\\d+)?");
					}
					DBX[b]=Double.parseDouble(Entrada);
					VEntrada=false;
				}
				double [] DBY= new double [Ntemp];
				VEntrada=false;
				for(int b=0;b<Ntemp; b++) {
					while(VEntrada!=true) {
						int bT=b+1;
						System.out.println("Ingrese el numero "+bT+" del conjunto Y");
						Entrada=entrada.nextLine();
						VEntrada=Entrada.matches("[+-]?\\d*(\\.\\d+)?");
					}
					DBY[b]=Double.parseDouble(Entrada);
					VEntrada=false;
				}
				VEntrada=false;
				Operaciones operacion = new Operaciones();
				double [] Resultado0= new double [2];
				Resultado0=operacion.RegresionLineal(DBX, DBY);
				System.out.println("El valor obtenido en Beta 0 es "+Resultado0[0]);
				System.out.println("El valor obtenido en Beta 1 es "+Resultado0[1]);
				while(VEntrada!=true) {
					System.out.println("Ingresa el valor de estimación Xk");
					Entrada=entrada.nextLine();
					VEntrada=Entrada.matches("[+-]?\\d*(\\.\\d+)?");
				}
				double Xk=Double.parseDouble(Entrada);
				entrada.close();
				double Yk=operacion.PrediccionY(Resultado0,Xk);
				System.out.println("El valor estimado Yk es: "+Yk);
	}

}