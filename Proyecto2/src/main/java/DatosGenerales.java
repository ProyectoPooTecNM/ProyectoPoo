public abstract class DatosGenerales
{
    String nombre;
    String direccion;
    String correo;
    String claveGrupo;
    long telefono;
    char nivel;//B: Básico, I: Intermedio, A: Avanzado
    static double precio = 500;

    public DatosGenerales()
    {
        nombre = "";
        direccion = "";
        correo = "";
        claveGrupo = "";
        telefono = 0;
        nivel = ' ';
        precio = 500;
    }

    public DatosGenerales(String nombre, String direccion, String correo, String claveGrupo, long telefono, char nivel, double precio)
    {
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.claveGrupo = claveGrupo;
        this.telefono = telefono;
        this.nivel = nivel;
        DatosGenerales.precio = precio;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public String getCorreo()
    {
        return correo;
    }

    public String getClaveGrupo()
    {
        return claveGrupo;
    }

    public long getTelefono()
    {
        return telefono;
    }

    public char getNivel()
    {
        return nivel;
    }

    public double getPrecio()
    {
        return precio;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public void setClaveGrupo(String claveGrupo)
    {
        this.claveGrupo = claveGrupo;
    }

    public void setTelefono(long telefono)
    {
        this.telefono = telefono;
    }

    public void setNivel(char nivel)
    {
        this.nivel = nivel;
    }

    public void setPrecio(double precio)
    {
        DatosGenerales.precio = precio;
    }

    /*
    Calcular el precio de los internos y externos, si es interno se le descuenta el 50% y si es externo se le aumenta el 50%
    Método abstracto para calcular el precio de los internos y externos (clase hija) con el mismo método (polimorfismo -instanceof-)
    */

    public abstract double calcularPrecio(DatosGenerales obj);

    public abstract void desplegarInfo();


}
