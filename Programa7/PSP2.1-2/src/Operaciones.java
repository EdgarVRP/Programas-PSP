public class Operaciones {
	public double dof = 0, x1 = 0, x2 = 1;
	public int NumSeg = 0;
	double DistribucionT[], DBX[], DBY[];
	double Avance = 0;
	double Integral = 0;// Calculado
	double Integral2 = 0; // tail area
	double ValorIntegral = 0;// Real
	boolean ErrorBool = true;
	double ErrorCal = 0;
	double d = .5;
	double Cor = 0, Std = 0, Beta0 = 0, Beta1 = 0, Yk = 0, Rango = 0, UPI = 0, LPI = 0, Xk;
	double SUMX = 0, SUMY = 0, SUMXX = 0, SUMXY = 0, PromX = 0, PromY = 0, SUMYY;

	public Operaciones(double DBX[], double DBY[], double Xk) {
		this.DBX = DBX;
		this.DBY = DBY;
		this.Xk = Xk;
		NumSeg = 100;
	}

	// Origen PSP2.1
	public void DistribucionNormal() {
		DistribucionT = new double[NumSeg + 1];
		Avance = (x2 - x1) / NumSeg;
		for (int a = 0; a <= NumSeg; a++) {
			DistribucionT[a] = x1 + (a * Avance);
			// System.out.println(DistribucionT[a]);
		}

	}

	// Origen PSP2.1
	public double IntegralSimpson() {
		DistribucionNormal();
		Integral = Fx(0);
		// System.out.println(Fx(0)*Avance/3);
		for (int a = 1; a <= NumSeg; a += 2) {
			Integral = Integral + (4 * Fx(DistribucionT[a]));
			// System.out.println(a*Avance);
			// System.out.println(4*Fx(DistribucionT[a])*Avance/3);
		}
		for (int a = 2; a < NumSeg; a += 2) {
			Integral = Integral + (2 * Fx(DistribucionT[a]));
			// System.out.println(a*Avance);
			// System.out.println(2*Fx(DistribucionT[a])*Avance/3);
		}
		Integral = Integral + Fx(DistribucionT[NumSeg]);
		Integral = Integral * Avance / 3;
		// System.out.println(Fx(DistribucionT[NumSeg])*Avance/3);
		return Integral;
	}

	// Origen PSP2.1
	public double FuncionGamma(double dof) {
		double Gamma=dof-1;
		if(Gamma==0&&dof<2) {
			Gamma=1;
		}
		if(Gamma==1&&dof<2) {
			Gamma=2;
		}
		if(Gamma==.5&&dof<2) {
			Gamma=Math.sqrt(Math.PI);
		}
		
		for(int a=2;a<dof;a++) {
			if((dof-a)==.5) {
				Gamma=Math.sqrt(Math.PI)*Gamma*.5;	
			}else {
				Gamma=(dof-a)*Gamma;
			}
		}
		return Gamma;
	}

	// Origen PSP2.1
	public double Fx(double x) {
		double Fx = 0;
		Fx = 1 + (Math.pow(x, 2) / dof);
		Fx = Math.pow(Fx, -((dof + 1) / 2));
		Fx = Fx * FuncionGamma((dof + 1) / 2) / ((Math.pow(dof * Math.PI, .5)) * FuncionGamma(dof / 2));
		return Fx;
	}

	// Origen PSP2.1
	public void ErrorA(double Real, double Calculado) {// Error aceptable 0.00001=.001%
		ErrorCal = (Real - Calculado) / (Real);
		if (Math.abs(ErrorCal) < 0.00001) {
			ErrorBool = false;
		} else {
			ErrorBool = true;
		}
	}

	// Reuso Programa PSP1
	public double MediaAritmetica(double Numeros[]) {
		double Media = 0;
		double Num[] = Numeros;
		int Total = Num.length;
		for (int M = 0; M < Total; M++) {
			Media = Num[M] + Media;
		}
		Media = Media / Total;
		return Media;
	}

	public void PrediccionY(double tBeta0, double tBeta1, double Xk) {
		Yk = tBeta0 + (tBeta1 * Xk);
	}

	public double Sumatoria(double Resultado0[]) {
		double SUM = 0;
		for (int M = 0; M < Resultado0.length; M++) {
			SUM = Resultado0[M] + SUM;
		}
		return SUM;
	}

	public double SumatoriaProducto(double Resultado0[], double Resultado1[]) {
		double SUMSUM = 0;
		for (int M = 0; M < Resultado0.length; M++) {
			SUMSUM = (Resultado0[M] * Resultado1[M]) + SUMSUM;
		}
		return SUMSUM;
	}

	public void RegresionLineal(double tDBX[], double tDBY[]) {
		double TDBX[] = tDBX, TDBY[] = tDBY;
		SUMX = Sumatoria(TDBX);
		SUMY = Sumatoria(TDBY);
		SUMXX = SumatoriaProducto(TDBX, TDBX);
		SUMXY = SumatoriaProducto(TDBX, TDBY);
		int n = TDBY.length;
		PromX = MediaAritmetica(TDBX);
		PromY = MediaAritmetica(TDBY);
		Beta1 = (SUMXY - (n * PromX * PromY)) / (SUMXX - (n * PromX * PromX));
		Beta0 = PromY - (Beta1 * PromX);
	}

	// Origen PSP1.1
	public void Correlacion(double DBX[], double DBY[]) {
		double TDBX[] = DBX, TDBY[] = DBY;
		SUMX = Sumatoria(TDBX);
		SUMY = Sumatoria(TDBY);
		SUMXX = SumatoriaProducto(TDBX, TDBX);
		SUMXY = SumatoriaProducto(TDBX, TDBY);
		SUMYY = SumatoriaProducto(TDBY, TDBY);
		int n = TDBY.length;
		double sum1 = (n * SUMYY) - (SUMY * SUMY);
		double sum2 = (n * SUMXX) - (SUMX * SUMX);
		double sum3 = Math.sqrt(sum1 * sum2);
		Cor = ((n * SUMXY) - (SUMX * SUMY)) / sum3;
		Std = Cor * Cor;
	}

	// Metodo Nuevo
	public void Correlacion2() {
		int n = DBX.length;
		double sum1 = Math.sqrt(n - 2);
		x2 = Math.abs(Cor) * (sum1);
		sum1 = 1 - Math.pow(Cor, 2);
		x2 = x2 / (Math.sqrt(sum1));
		dof = n - 2;
		// integral
		IntegralSimpson();
		Integral2 = (1 - (2 * Integral));
	}

	// Metodo Nuevo 2
	public void Rango() {
		ValorIntegral = .35;
		int n = DBX.length;
		dof = n - 2;
		BuscaX();
		// System.out.println(x2);
		double sum = 0;
		double Media = MediaAritmetica(DBX);
		for (int a = 0; a < DBX.length; a++) {
			sum = Math.pow((DBX[a] - Media), 2);
		}
		double nn = n;
		sum = (Math.pow((Xk - Media), 2)) / sum;
		sum = (1 / nn) + sum;
		sum = 1 + sum;
		sum = Math.sqrt(sum);
		sum = DesviacionEstandar2() * sum;
		Rango = x2 * sum;
		// UPI LPI
		UPI = Yk + Rango;
		LPI = Yk - Rango;
	}

	// Metodo Nuevo 3
	public double DesviacionEstandar2() {
		double std2 = 0;
		int n = DBX.length;
		for (int a = 0; a < n; a++) {
			std2 = Math.pow(DBY[a] - Beta0 - Beta1 * DBX[a], 2) + std2;
		}
		double nn = n;
		std2 = std2 * (1 / (nn - 2));
		std2 = Math.sqrt(std2);
		return std2;
	}

	// Metodo PSP2.1 Prog 6
	public double BuscaX() {
		IntegralSimpson();
		ErrorA(ValorIntegral, Integral);
		// Math.rint(Integral*100000)/100000
		do {
			if (ValorIntegral < Integral) {
				x2 = x2 - d;
				IntegralSimpson();
			} else {
				x2 = x2 + d;
				IntegralSimpson();
			}
			double TErrorCal = ErrorCal;
			ErrorA(ValorIntegral, Integral);
			if ((TErrorCal * ErrorCal) < 0) {
				d = d / 2;
			}
			// System.out.println(x2);
		} while (ErrorBool);
		// System.out.println(ValorIntegral);
		// System.out.println(ErrorCal);
		// System.out.println(Integral);
		// System.out.println(x1);
		return x2;
	}

	// Devolviendo el estado del objeto
	public String toString() {
		return " La correlación de los 2 conjuntos de numero es: " + Cor + "\n La Disviacion Estandar es: " + Std
				+ "\n El area de la integral es: " + Integral2 + "\n Beta 0 es: " + Beta0 + "\n Beta 1 es: " + Beta1
				+ "\n El valor de Prediccion Yk es: " + Yk + "\n El Rango es: " + Rango
				+ "\n El valor de UPI (70%)- es: " + UPI + "\n El valor de LPI (70%) es: " + LPI;
	}
}
