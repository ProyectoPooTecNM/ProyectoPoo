public class Grupos
{
    int dia;
    String hora;
    char tipo;//A: Adultos, I: Infantes
    String clave;

    public Grupos()
    {
        dia = 0;
        hora = "";
        tipo = ' ';
        clave = "";
    }
    public Grupos(int dia, String hora, char tipo, String clave)
    {
        this.dia = dia;
        this.hora = hora;
        this.tipo = tipo;
        this.clave = clave;
    }

    public int getDia()
    {
        return dia;
    }

    public String getHora()
    {
        return hora;
    }

    public char getTipo()
    {
        return tipo;
    }

    public String getClave()
    {
        return clave;
    }

    public void setDia(int dia)
    {
        this.dia = dia;
    }

    public void setHora(String hora)
    {
        this.hora = hora;
    }

    public void setTipo(char tipo)
    {
        this.tipo = tipo;
    }

    public void setClave(String clave)
    {
        this.clave = clave;
    }
}
