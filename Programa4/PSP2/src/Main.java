/******************************************************************/
/* Program Assignment:  PSP2                                      */
/* Name:                Edgar Valentin Ruiz Padilla               */
/* Date:                21-noviembre-2021                         */
/* Description:         Programa Calcular integral simpson        */
/******************************************************************/
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		double x1=0,x2=0,dof=0;
		System.out.println("Programa para calcular la integral por metodo de simpson usando la funcion de distribucion normal");
		x1=SolicitarDatos("Ingrese el valor inicial del rango de la distribucion normal");
		x2=SolicitarDatos("Ingrese el valor final del rango de la distribucion normal");
		dof=SolicitarDatos("Los grados de libertad (dof) a considerar");
		Operaciones operacion = new Operaciones(dof,x1,x2);
		System.out.println("El resultado de la integral es: "+operacion.IntegralSimpson());
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
