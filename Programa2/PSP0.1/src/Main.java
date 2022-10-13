/******************************************************************/
/* Program Assignment:  PSP0.1							*/
/* Name:                Edgar Valentin Ruiz Padilla		*/
/* Date:                24/09/2021						*/
/* Description:         Programa Contador de lineas		*/
/******************************************************************/

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String Ubicacion=null;
		ArrayList<String> Codigo = new ArrayList();
		Scanner entrada=new Scanner(System.in);
		System.out.println("Programa para contar lineas de codigo, se contaran los siguientes aspectos:\r\n");
		System.out.println("Tipos de Metodos, Clases,\r\n");
		System.out.println("Teclear la ruta de ubicacion del archivo que contiene el codigo");
		Ubicacion=entrada.nextLine();
		//
		Ubicacion="C:\\Users\\Edgar\\Desktop\\Texto prueba.txt";
		//
		FileToString ConverString = new FileToString();
		try {
			Codigo=ConverString.ConverString(Ubicacion);
			//Codigo = ConverString.ConverString(Ubicacion);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("El codigo leido es el siguiente:");
		for(int b=0;b<Codigo.size();b++) {
			System.out.println(Codigo.get(b));
		}		
		entrada.close();
		System.out.println("Las lineas totales son:"+Codigo.size());
		
		Clasificador Clasificar= new Clasificador();
		
		int Conteo[]=Clasificar.ClasificadorT(Codigo);
		System.out.println("Se obtuvieron los siguientes resultados del conteo de lineas de codigo (LOC):");
		System.out.println("El programa tiene "+ Conteo[0]+" lineas de comentarios");
		System.out.println("El programa tiene "+ Conteo[1]+" clases");
		System.out.println("El programa tiene "+ Conteo[2]+" Metodos");
		System.out.println("El programa tiene "+ Conteo[3]+" Metodos get-set");
		System.out.println("El programa tiene "+ Conteo[4]+" Lineas de entrada o salida I/O");
		System.out.println("El programa tiene "+ Conteo[5]+" Lineas de Control");
		System.out.println("El programa tiene "+ Conteo[6]+" Lineas de Logica");
		System.out.println("El programa tiene "+ Conteo[7]+" lineas vacias");
	}

}
