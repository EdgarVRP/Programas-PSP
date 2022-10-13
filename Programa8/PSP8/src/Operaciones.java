public class Operaciones {
	public double dof = 0;
	double x1=0,x2=1;
	public int NumSeg = 0;
	double DistribucionT[];
	double MatrizGauss[][];
	int N = 0;
	double w[];
	double x[];
	double y[];
	double z[];
	double r[];
	double Betas[];
	double Avance=0;
	double Integral=0;//Calculado
	double ValorIntegral = 0;// Real
	boolean ErrorBool=true;
	double ErrorCal=0;
	double d=.5;
	double Wk=0;
	double Xk=0;
	double Yk=0;
	double Zk=0;
	double Rango=0;
	double UPI=0,LPI=0;
	//ok
	public Operaciones(double W[], double X[], double Y[], double Z[], double Wk, double Xk, double Yk) {
		this.w = W;
		this.x = X;
		this.y = Y;
		this.z = Z;
		this.Wk=Wk;
		this.Xk=Xk;
		this.Yk=Yk;
		NumSeg = 100;
		this.MatrizGauss = new double[4][4];
		N = W.length;
		this.r = new double[4];
		dof=N-4;
	}
	//ok
	public void MatrizGauss() {
		MatrizGauss[0][0] = N;
		MatrizGauss[0][1] = Sumatoria(w);
		MatrizGauss[0][2] = Sumatoria(x);
		MatrizGauss[0][3] = Sumatoria(y);
		r[0] = Sumatoria(z);
		MatrizGauss[1][0] = Sumatoria(w);
		MatrizGauss[1][1] = SumatoriaProducto(w, w);
		MatrizGauss[1][2] = SumatoriaProducto(w, x);
		MatrizGauss[1][3] = SumatoriaProducto(w, y);
		r[1] = SumatoriaProducto(w, z);
		MatrizGauss[2][0] = Sumatoria(x);
		MatrizGauss[2][1] = SumatoriaProducto(w, x);
		MatrizGauss[2][2] = SumatoriaProducto(x, x);
		MatrizGauss[2][3] = SumatoriaProducto(x, y);
		r[2] = SumatoriaProducto(x, z);
		MatrizGauss[3][0] = Sumatoria(y);
		MatrizGauss[3][1] = SumatoriaProducto(w, y);
		MatrizGauss[3][2] = SumatoriaProducto(x, y);
		MatrizGauss[3][3] = SumatoriaProducto(y, y);
		r[3] = SumatoriaProducto(y, z);
	}
	//ok
	public double Sumatoria(double Resultado0[]) {
		double SUM = 0;
		for (int M = 0; M < Resultado0.length; M++) {
			SUM = Resultado0[M] + SUM;
		}
		return SUM;
	}
	//ok
	public double SumatoriaProducto(double Resultado0[], double Resultado1[]) {
		double SUMSUM = 0;
		for (int M = 0; M < Resultado0.length; M++) {
			SUMSUM = (Resultado0[M] * Resultado1[M]) + SUMSUM;
		}
		return SUMSUM;
	}
	//ok
	public void Gauss() {
		int n = 4, i = 0, j = 0, s = 0;
		double d = 0;
		Betas = new double[n];
		for (i = 0; i < n; i++) {
			for (j = i; j < n; j++) {
				if (i == j) {
					d = MatrizGauss[i][j];
					for (s = 0; s < n; s++) {
						MatrizGauss[i][s] = ((MatrizGauss[i][s]) / d);
					}
					r[i] = ((r[i]) / d);
				} else {
					d = MatrizGauss[j][i];
					for (s = 0; s < n; s++) {
						MatrizGauss[j][s] = MatrizGauss[j][s] - (d * MatrizGauss[i][s]);
					}
					r[j] = r[j] - (d * r[i]);
				}
			}
		}
		for (i = n - 1; i >= 0; i--) {
			double y = r[i];
			for (j = n - 1; j >= i; j--) {
				y = y - Betas[j] * MatrizGauss[i][j];
			}
			Betas[i] = y;
		}
	}

	// Metodo Nuevo 2
	public void Rango() {
		ValorIntegral = .35;
		BuscaX();
		double Mediaw = MediaAritmetica(w);
		double Mediax = MediaAritmetica(x);
		double Mediay = MediaAritmetica(y);
		double Mediaz = MediaAritmetica(z);
		//Numeradores
		double sum1=Math.pow((Wk - Mediaw), 2);
		double sum2=Math.pow((Xk - Mediax), 2);
		double sum3=Math.pow((Yk - Mediay), 2);
		//Denominadores
		double sum11=0;
		for (int a = 0; a < w.length; a++) {
			sum11 += Math.pow((w[a] - Mediaw), 2);
		}
		double sum22=0;
		for (int a = 0; a < x.length; a++) {
			sum22 += Math.pow((x[a] - Mediax), 2);
		}
		double sum33=0;
		for (int a = 0; a < y.length; a++) {
			sum33 += Math.pow((y[a] - Mediay), 2);
		}
		//Operacion Rango
		double sum=0;
		sum=sum3/sum33;
		sum+=sum2/sum22;
		sum+=sum1/sum11;
		double nn=N;
		sum+=1/nn;
		sum+=1;
		sum = Math.sqrt(sum);
		sum = DesviacionEstandar2() * sum;
		Rango = x2 * sum;
	}
	public void LUPI () {
		UPI = Zk + Rango;
		LPI = Zk - Rango;
	}
	// Metodo PSP2.1 Prog 6
	public double BuscaX() {
		IntegralSimpson();
		ErrorA(ValorIntegral, Integral);
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
	// Origen PSP2.1
		public void ErrorA(double Real, double Calculado) {// Error aceptable 0.00001=.001%
			ErrorCal = (Real - Calculado) / (Real);
			if (Math.abs(ErrorCal) < 0.00001) {
				ErrorBool = false;
			} else {
				ErrorBool = true;
			}
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

	public void PrediccionY() {
		Zk = Betas[0]+Betas[1]*Wk+Betas[2]*Xk+Betas[3]*Yk;
	}
	// Metodo Nuevo 3 ok
	public double DesviacionEstandar2() {
		double std2 = 0;
		int n = w.length;
		for (int a = 0; a < n; a++) {
			std2 = Math.pow(z[a] - Betas[0] -Betas[1] *w[a]-Betas[2]*x[a]-Betas[3]*y[a], 2) + std2;
		}
		double nn = n;
		std2 = std2 * (1 / (nn - 4));
		std2 = Math.sqrt(std2);
		//System.out.println(std2);
		return std2;
	}

	// Devolviendo el estado del objeto
	public String toString() {
		String Resultado = "";
		for (int a = 0; a < 4; a++) {
			Resultado +=("Beta " + a + " es: " + Betas[a]) + "\n";
		}
		Resultado += ("Zk (Las Horas proyectadas son: )"+ Zk +"\n");
		Resultado += ("UPI (70%):"+ UPI +"\n");
		Resultado += ("LPI (70%):"+ LPI +"\n");
		return Resultado;
	}
}