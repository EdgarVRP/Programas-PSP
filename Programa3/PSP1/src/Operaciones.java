public class Operaciones {
	public double[] RegresionLineal (double DBX[],double DBY[]) {
		double TDBX[]=DBX,TDBY[]=DBY;
		double SUMX=0, SUMY=0, SUMXX=0, SUMXY=0, Beta0=0, Beta1=0, PromX=0, PromY=0;
		for(int M=0;M<TDBX.length; M++) {
			SUMX=TDBX[M]+SUMX;
		}
		for(int M=0;M<TDBY.length; M++) {
			SUMY=TDBY[M]+SUMY;
		}
		for(int M=0;M<TDBX.length; M++) {
			SUMXX=(TDBX[M]*TDBX[M])+SUMXX;
		}
		for(int M=0;M<TDBY.length; M++) {
			SUMXY=(TDBX[M]*TDBY[M])+SUMXY;
		}
		int n=TDBY.length;
		PromX=MediaAritmetica(TDBX);
		PromY=MediaAritmetica(TDBY);
		Beta1=(SUMXY-(n*PromX*PromY))/(SUMXX-(n*PromX*PromX));
		Beta0=PromY-(Beta1*PromX);
		double[] Resultado0 = new double[]{ Beta0,Beta1};
		return Resultado0;
	}
	public double PrediccionY (double Resultado0[],double Xk) {
		double TResultado0[]=Resultado0;
		double TXk=Xk;
		double PrediccionY=TResultado0[0]+(TResultado0[1]*TXk);
		return PrediccionY;
	}
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
}