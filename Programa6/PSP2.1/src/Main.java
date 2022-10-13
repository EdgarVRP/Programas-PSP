/******************************************************************/
/* Program Assignment:  PSP2.1                                    */
/* Name:                Edgar Valentin Ruiz Padilla               */
/* Date:                21-noviembre-2021                         */
/* Description:         Programa para determinar el valor de X    */
/******************************************************************/
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		double ValorIntegral=0,dof=0;
		System.out.println("Programa para calcular el valor de x a partir de ");
		System.out.println("la integral del rango (0,x) para una distribucion normal t");
		ValorIntegral=SolicitarDatos("Ingrese el valor de la integral: ");
		dof=SolicitarDatos("Ingrese Los grados de libertad (dof) a considerar: ");
		Operaciones operacion = new Operaciones(dof,ValorIntegral);
		System.out.println("El valor calculado x es: "+operacion.BuscaX());
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
