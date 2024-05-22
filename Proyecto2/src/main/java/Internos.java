class Internos extends DatosGenerales
{
    String numControl;
    int semestre;

    public Internos()
    {
        super();
        numControl = "";
        semestre = 0;
    }

    public Internos(String nombre, String direccion, String correo, String claveGrupo, long telefono, char nivel, double precio, String numControl, int semestre)
    {
        super(nombre, direccion, correo, claveGrupo, telefono, nivel, precio);
        this.numControl = numControl;
        this.semestre = semestre;
    }

    @Override
    public double calcularPrecio(DatosGenerales obj)
    {
        if (obj instanceof Internos)
        {
            return precio - (precio * 0.5);
        }
        else if (obj instanceof Externos)
        {
            return precio + (precio * 0.5);
        }
        return 0;
    }

    public String getNumControl()
    {
        return numControl;
    }

    public int getSemestre()
    {
        return semestre;
    }

    public void setNumControl(String numControl)
    {
        this.numControl = numControl;
    }

    public void setSemestre(int semestre)
    {
        this.semestre = semestre;
    }

    @Override
    public void desplegarInfo()
    {
        // Desplegar la información del alumno interno en formato de tabla y color amarillo (HEX: #FFFF00)
        System.out.printf("\033[1;36m%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\033[0m\n", "Nombre", "Dirección", "Correo", "Clave Grupo", "Teléfono", "Nivel", "Precio", "Número de Control", "Semestre");

        System.out.printf("\033[33m%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\033[0m\n", nombre, direccion, correo, claveGrupo, telefono, nivel, precio, numControl, semestre);
    }
}
