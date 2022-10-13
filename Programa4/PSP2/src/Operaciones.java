public class Operaciones {
	public double dof=0,x1=0,x2=0;	
	public int NumSeg=0;
	double DistribucionT[];
	double Avance=0;
	public Operaciones(double dof, double x1, double x2) {
		this.dof = dof;
		this.x1 = x1;
		this.x2 = x2;
		NumSeg=10;
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
		double Integral=0;
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
}
