
public class Operaciones {
	
	public double MediaAritmetica (double Numeros[]) {
		double Media=0;
		double Num[]=Numeros;
		int Total=Num.length;
		for(int M=0;M<Total; M++) {
			Media=Num[M]+Media;
		}
		Media=Media/Total;
		return Media;
	}	
	public double DesviacionEstandar (double Numeros[],double Media) {
		double MediaT=Media,Desviacion=0, Temp=0;
		double Num[]=Numeros;
		int Total=Num.length;
		for(int M=0;M<Total; M++) {
			Temp=Temp+Math.pow((Num[M]-MediaT),2);
		}
		Desviacion=Temp/(Total-1);
		Desviacion=Math.sqrt(Desviacion);;		
		return Desviacion;
	}
}
