package negocio;

public class Requerimiento {
    int cantLiderProyecto;
    int cantArquitecto;
    int cantProgramador;
    int cantTester;

    public Requerimiento(int cantLiderProyecto,
            int cantArquitecto,
            int cantProgramador,
            int cantTester) {

        this.cantArquitecto = cantArquitecto;
        this.cantLiderProyecto = cantLiderProyecto;
        this.cantProgramador = cantProgramador;
        this.cantTester = cantTester;
    }

    public boolean cumpleRequerimientos(int cantLiderProyecto, int cantArquitecto, int cantProgramador,
            int cantTester) {

        return this.cantArquitecto == cantArquitecto && this.cantLiderProyecto == cantLiderProyecto
                && this.cantProgramador == cantProgramador && this.cantTester == cantTester;
    }

}
