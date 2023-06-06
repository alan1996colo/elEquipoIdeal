package negocio;

public class Requerimiento {
	public int getCantLiderProyecto() {
		return cantLiderProyecto;
	}

	public void setCantLiderProyecto(int cantLiderProyecto) {
		this.cantLiderProyecto = cantLiderProyecto;
	}

	public int getCantArquitecto() {
		return cantArquitecto;
	}

	public void setCantArquitecto(int cantArquitecto) {
		this.cantArquitecto = cantArquitecto;
	}

	public int getCantProgramador() {
		return cantProgramador;
	}

	public void setCantProgramador(int cantProgramador) {
		this.cantProgramador = cantProgramador;
	}

	public int getCantTester() {
		return cantTester;
	}

	public void setCantTester(int cantTester) {
		this.cantTester = cantTester;
	}

	int cantLiderProyecto;
	int cantArquitecto;
	int cantProgramador;
	int cantTester;

	/** cantidad de :lider , arquitecto , programador, tester **/
	public Requerimiento(int cantLiderProyecto, int cantArquitecto, int cantProgramador, int cantTester) {

		this.cantArquitecto = cantArquitecto;
		this.cantLiderProyecto = cantLiderProyecto;
		this.cantProgramador = cantProgramador;
		this.cantTester = cantTester;
	}

	public boolean cumpleRequerimientos(int cantLiderProyecto, int cantArquitecto, int cantProgramador,
			int cantTester) {

		return (this.cantArquitecto == cantArquitecto && this.cantLiderProyecto == cantLiderProyecto
				&& this.cantProgramador == cantProgramador && this.cantTester == cantTester);
	}
	
	
	public boolean superaRequerimientos(int cantLiderProyecto, int cantArquitecto, int cantProgramador,
			int cantTester) {return (this.cantArquitecto <= cantArquitecto && this.cantLiderProyecto <= cantLiderProyecto
			&& this.cantProgramador <= cantProgramador && this.cantTester <= cantTester);
		
	}
	public String toString() {
		return "("+this.cantLiderProyecto+" "+this.cantArquitecto+" "+this.cantProgramador+" "+this.cantTester+")";
	}

}
