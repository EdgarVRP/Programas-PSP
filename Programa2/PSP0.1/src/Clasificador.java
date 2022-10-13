import java.util.ArrayList;

public class Clasificador {
	public static int[]  ClasificadorT (ArrayList<String> codigo) {
		//ArrayList<String> ClasificadoRes = new ArrayList();
		int[] Clasificado = new int[]{ 0,0,0,0,0,0,0,0}; 
		int comentarios=0,LineaVacia=0, CicloFor=0,CicloWhile=0, Cicloswitch=0, logica=0
				, CicloIf=0, variables=0,constructor=0,cierre=0, clases=0, GetSet=0, MetodoIO=0;
		boolean Comment= false;
		boolean LineaContada=false;
		for(int b=0;b<codigo.size();b++) {
			//System.out.println(codigo.get(b));			
			if(codigo.get(b).contains("*/")&&LineaContada==false) {
				comentarios++;
				Comment=false;
				LineaContada=true;
			}
			if(codigo.get(b).contains("//")&&LineaContada==false) {
				comentarios++;
				LineaContada=true;
			}
			if(codigo.get(b).contains("/*")&&LineaContada==false) {
				comentarios++;
				Comment=true;
				LineaContada=true;
			}
			if(LineaContada==false&&Comment==true) {
				comentarios++;
				LineaContada=true;
			}
			if(codigo.get(b).contains("for(")&&LineaContada==false) {
				CicloFor++;
				LineaContada=true;
			}
			if(codigo.get(b).contains("while(")&&LineaContada==false) {
				CicloWhile++;
				LineaContada=true;
			}
			if(codigo.get(b).contains("switch(")&&LineaContada==false) {
				Cicloswitch++;
				LineaContada=true;
			}
			if(codigo.get(b).contains("if(")&&LineaContada==false) {
				CicloIf++;
				LineaContada=true;
			}
			if(codigo.get(b).contains("class")&&LineaContada==false){
				clases++;
				LineaContada=true;
			}
			if((codigo.get(b).contains("get")||codigo.get(b).contains("set"))&&LineaContada==false) {
				GetSet++;
				LineaContada=true;
			}
			if((codigo.get(b).contains("public")||codigo.get(b).contains("private")||codigo.get(b).contains("protected"))&&LineaContada==false){
				constructor++;
				LineaContada=true;
			}
			if((codigo.get(b).contains("Scanner")||codigo.get(b).contains("iterate")||codigo.get(b).contains("new"))&&LineaContada==false){
				MetodoIO++;//Metodos entrada salida I/O---Scanner--iterate---=---new 
				LineaContada=true;
			}
			if((codigo.get(b).contains("int ")||codigo.get(b).contains("double ")||codigo.get(b).contains("float ")||codigo.get(b).contains("char ")||codigo.get(b).contains("String ")||codigo.get(b).contains("boolean ")||codigo.get(b).contains("char "))&&LineaContada==false) {
				variables++;
				LineaContada=true;
			}
			if((codigo.get(b).contains(";")||codigo.get(b).contains("try"))&&LineaContada==false){
				logica++;
				LineaContada=true;
			}
			if(codigo.get(b).contains("}")&&LineaContada==false){
				cierre++;
				LineaContada=true;
			}
			if(codigo.get(b).contains("")&&LineaContada==false){
				LineaVacia++;
				LineaContada=true;
			}
			LineaContada=false;
		}
		Clasificado[0]=comentarios;//Lineas de comentarios
		Clasificado[1]=clases;//Cantidad de clases
		Clasificado[2]=constructor;//Metodos 
		Clasificado[3]=GetSet;//Metodos get-set 
		Clasificado[4]=MetodoIO;//Metodos entrada salida I/O---Scanner--iterate---=---new 
		Clasificado[5]=CicloFor+CicloWhile+Cicloswitch+CicloIf;//Lineas de Control
		Clasificado[6]=variables+cierre+logica;//Lineas de Logica
		Clasificado[7]=LineaVacia;//Lineas Vacias
		
		return Clasificado;
	}
}
