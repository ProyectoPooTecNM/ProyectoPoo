class Externos extends DatosGenerales
{
    static int consecutivo = 0;
    String folio;
    int edad;

    public Externos()
    {
        super();
        consecutivo = 0;
        folio = "";
        edad = 0;
    }


    public Externos(String nombre, String direccion, String correo, String claveGrupo, long telefono, char nivel, double precio, int consecutivo, String folio, int edad)
    {
        super(nombre, direccion, correo, claveGrupo, telefono, nivel, precio);
        Externos.consecutivo = consecutivo;
        this.folio = folio;
        this.edad = edad;
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

    public static int getConsecutivo()
    {
        return consecutivo;
    }

    // Segundo getter para consecutivo para aumentar la variable en 1 cada vez que se cree un nuevo objeto
    public static int getConsecutivoAdd()
    {
        return ++consecutivo;
    }

    public String getFolio()
    {
        return folio;
    }

    public int getEdad()
    {
        return edad;
    }

    public void setConsecutivo(int consecutivo)
    {

        Externos.consecutivo = consecutivo;
    }


    public void setFolio(String folio)
    {
        this.folio = folio;
    }

    public void setEdad(int edad)
    {
        this.edad = edad;
    }

    @Override
    public void desplegarInfo()
    {
        // Desplegar la información del alumno externo en formato de tabla y color amarillo (HEX: #FFFF00)
        // Encabezado de la tabla en azul cian y negritas (HEX: #00FFFF)
        System.out.printf("\033[1;36m%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\033[0m\n", "Nombre", "Dirección", "Correo", "Clave Grupo", "Teléfono", "Nivel", "Precio", "Folio", "Edad");
        System.out.printf("\033[33m%-20s%-20s%-20s%-20s%-20d%-20c%-20f%-20s%-20d\n\033[0m\n", getNombre(), getDireccion(), getCorreo(), getClaveGrupo(), getTelefono(), getNivel(), getPrecio(), folio, edad);
    }



}
