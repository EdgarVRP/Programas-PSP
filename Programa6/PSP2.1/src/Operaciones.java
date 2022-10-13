public class Operaciones {
	public double dof=0,x1=0,x2=1;	
	public int NumSeg=0;
	double DistribucionT[];
	double Avance=0;
	double Integral=0;//Calculado
	double ValorIntegral=0;//Real
	boolean ErrorBool=true;
	double ErrorCal=0;
	double d=.5;
	public Operaciones(double dof, double Integral) {
		this.dof = dof;
		this.ValorIntegral = Integral;
		NumSeg=100;
	}
	public void DistribucionNormal() {
		DistribucionT= new double[NumSeg+1];
		Avance=(x2-x1)/NumSeg;
		for(int a=0;a<=NumSeg;a++) {
			DistribucionT[a]=x1+(a*Avance);
			//System.out.println(DistribucionT[a]);
		}		
	}
	public double IntegralSimpson () {
		DistribucionNormal();
		Integral=Fx(0);
		//System.out.println(Fx(0)*Avance/3);
		for(int a=1;a<=NumSeg;a+=2) {
			Integral=Integral+(4*Fx(DistribucionT[a]));
			//System.out.println(a*Avance);
			//System.out.println(4*Fx(DistribucionT[a])*Avance/3);
		}
		for(int a=2;a<NumSeg;a+=2) {
			Integral=Integral+(2*Fx(DistribucionT[a]));
			//System.out.println(a*Avance);
			//System.out.println(2*Fx(DistribucionT[a])*Avance/3);
		}
		Integral=Integral+Fx(DistribucionT[NumSeg]);
		Integral=Integral*Avance/3;
		//System.out.println(Fx(DistribucionT[NumSeg])*Avance/3);
		return Integral;
	}
	public double FuncionGamma(double dof) {
		double Gamma=dof-1;
		for(int a=2;a<dof;a++) {
			if((dof-a)==.5) {
				Gamma=Math.sqrt(Math.PI)*Gamma*.5;	
			}else {
				Gamma=(dof-a)*Gamma;
			}
		}
		return Gamma;
	}
	public double Fx (double x) {
		double Fx=0;
		Fx=1+(Math.pow(x,2)/dof);
		Fx=Math.pow(Fx,-((dof+1)/2));
		Fx=Fx*FuncionGamma((dof+1)/2)/((Math.pow(dof*Math.PI,.5))*FuncionGamma(dof/2));
		return Fx;
	}
	public void ErrorA(double Real,double Calculado) {//Error aceptable 0.00001=.001%
		ErrorCal=(Real-Calculado)/(Real);		
		if(Math.abs(ErrorCal)<0.00001) {
			ErrorBool=false;
		}else{
			ErrorBool=true;
		}
	}
	public double BuscaX() {
		IntegralSimpson();
		ErrorA(ValorIntegral,Integral);
		//Math.rint(Integral*100000)/100000
		do {
			if(ValorIntegral<Integral) {
				x2=x2-d;
				IntegralSimpson();
			}
			else {
				x2=x2+d;
				IntegralSimpson();
			}
			double TErrorCal=ErrorCal;
			ErrorA(ValorIntegral,Integral);
			if((TErrorCal*ErrorCal)<0) {
				d=d/2;
			}
			//System.out.println(x2);
		}while(ErrorBool);
		//System.out.println(ValorIntegral);
		//System.out.println(ErrorCal);
		//System.out.println(Integral);
		//System.out.println(x1);
		return x2;
	}
}
