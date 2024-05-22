import java.util.ArrayList;
import java.util.Scanner;

public class Funcionalidad
{
    //Crear un ArrayList de la clase Grupos
    ArrayList<Grupos> grupos = new ArrayList<>();

    //Crear un ArrayList de 2 dimensiones la clase DatosGenerales
    ArrayList<ArrayList<DatosGenerales>> datos = new ArrayList<>();

    //1. Registro de grupos
    void registroGrupos()
    {
        //Crear un objeto de la clase Grupos
        Grupos grupo = new Grupos();
        //Darle la bienvenida al usuario con color azul cian y negrita (HEX: #00FFFF)
        System.out.println("\n\u001B[1;36m-------Bienvenido al registro de grupos-------\u001B[0m");
        //Solicitar los datos del grupo en amarillo (HEX: #FFFF00)
        //Día
        grupo.setDia(solicitarEntradaInt("\u001B[33mIngrese el día del grupo: (1-7)\u001B[0m", 1, 7));
        //Hora
        grupo.setHora(solicitarEntradaString("\u001B[33mIngrese la hora del grupo: \u001B[0m"));
        //Tipo
        grupo.setTipo(solicitarEntradaChar(new char[]{'A', 'I'}, "\u001B[33mIngrese el tipo del grupo: (A: Adultos, I: Infantes)\u001B[0m"));
        //Clave
        grupo.setClave(solicitarEntradaString("\u001B[33mIngrese la clave del grupo: \u001B[0m"));
        //Verificar si la clave ya existe
        if (verificarClave(grupos, grupo.getClave()))
        {
            //Imprimir el mensaje de error en color rojo (HEX: #FF0000)
            System.out.println("\n\u001B[31mLa clave ya existe\u001B[0m");
        }
        else
        {
            grupos.add(grupo);
            //Imprimir el mensaje de éxito en color verde (HEX: #008000)
            System.out.println("\n\u001B[32m¡Grupo registrado!\u001B[0m");

            //Agregar un renglón al ArrayList de 2 dimensiones de la clase DatosGenerales
            datos.add(new ArrayList<>());
        }
    }

    //2. Registro de Internos o Externos
    void registroInternosOExternos()
    {
        //Verificar que haya grupos
        if (grupos.isEmpty())
        {
            //Imprimir el mensaje de error en color rojo (HEX: #FF0000)
            System.out.println("\n\u001B[31mError: No hay grupos registrados\u001B[0m");
            return;
        }
        //Darle la bienvenida al usuario con color azul cian y negrita (HEX: #00FFFF)
        System.out.println("\n\u001B[1;36m-------Bienvenido al registro de Internos o Externos-------\u001B[0m");
        //Pregunta si se desea registrar Internos o Externos
        char tipo = solicitarEntradaChar(new char[]{'I', 'E'}, "\u001B[33m¿Desea registrar Internos o Externos? (I: Internos, E: Externos)\u001B[0m");
        //Si el tipo es 'I' se registrarán Internos
        if (tipo == 'I')
        {
            //Solicitar los datos de Internos en color azul cian y negrita (HEX: #00FFFF)
            System.out.println("\n\u001B[1;36m-------Registro de Internos-------\u001B[0m");
            //Solicitar datos del Interno: nombre, direccion, claveGrupo, telefono, nivel, numControl, semestre
            Internos interno = new Internos();
            //Solicitar los datos del grupo en amarillo (HEX: #FFFF00)
            //Clave del grupo
            interno.setClaveGrupo(solicitarEntradaString("\u001B[33mIngrese la clave del grupo: \u001B[0m"));
            //Si la clave no existe, no hay razón para seguir
            if (!verificarClave(grupos, interno.getClaveGrupo()))
            {
                //Imprimir el mensaje de error en color rojo (HEX: #FF0000)
                System.out.println("\n\u001B[31mLa clave del grupo no existe\u001B[0m");
                System.out.println("\u001B[31mIntente de nuevo\u001B[0m");
                return;
            }
            //Nombre
            interno.setNombre(solicitarEntradaString("\u001B[33mIngrese el nombre del externo: \u001B[0m"));
            //Verificar si hay conflictos de nombres
            if (verificarConflictos(interno.getNombre(), datos))
            {
                // Imprimir los mensajes de advertencia en color amarillo (HEX: #FFFF00)
                System.out.println("\n\u001B[31mAdvertencia: Hay conflictos de nombres\u001B[0m");
                System.out.println("\u001B[31mSe le agregará un número separado por un guion bajo al final de su nombre\u001B[0m");

                // Inicializar el contador y generar un nuevo nombre hasta que no haya conflictos
                int contador = 1;
                String nombreOriginal = interno.getNombre();
                String nuevoNombre = nombreOriginal + "_" + contador;
                interno.setNombre(nuevoNombre);

                while (verificarConflictos(interno.getNombre(), datos))
                {
                    contador++;
                    nuevoNombre = nombreOriginal + "_" + contador;
                    interno.setNombre(nuevoNombre);
                }

                // Imprimir el nuevo nombre en color amarillo (HEX: #FFFF00)
                System.out.println("\u001B[33mNuevo nombre: " + interno.getNombre() + "\u001B[0m\n");
            }

            //Dirección
            interno.setDireccion(solicitarEntradaString("\u001B[33mIngrese la dirección del interno: \u001B[0m"));
            //Correo
            interno.setCorreo(solicitarEntradaString("\u001B[33mIngrese el correo del interno: \u001B[0m"));
            //Teléfono
            interno.setTelefono(solicitarEntradaLongTelefono("\u001B[33mIngrese el teléfono del interno: \u001B[0m"));
            //Nivel
            interno.setNivel(solicitarEntradaChar(new char[]{'B', 'I', 'A'}, "\u001B[33mIngrese el nivel del interno (B: Básico, I: Intermedio, A: Avanzado): \u001B[0m"));
            //Precio (si es interno, se le descuenta el 50%, si es externo, se le aumenta el 50%; utilizando el método abstracto calcularPrecio)
            interno.setPrecio(interno.calcularPrecio(interno));
            //Número de control
            interno.setNumControl(solicitarEntradaString("\u001B[33mIngrese el número de control del interno: \u001B[0m"));
            //Semestre
            interno.setSemestre(solicitarEntradaInt("\u001B[33mIngrese el semestre del interno: \u001B[0m"));

            //Verificar si la clave del grupo existe
            if (verificarClave(grupos, interno.getClaveGrupo()))
            {

                //Agregar el objeto interno al ArrayList de datos en el grupo que corresponde con la clave
                for (var grupo : grupos)
                {
                    if (grupo.getClave().equals(interno.getClaveGrupo()))
                    {
                        datos.get(grupos.indexOf(grupo)).add(interno);
                    }
                }

                //Imprimir el mensaje de éxito en color verde (HEX: #008000)
                System.out.println("\n\u001B[32m¡Interno registrado exitosamente!\u001B[0m");
            }
            else
            {
                //Imprimir el mensaje de error en color rojo (HEX: #FF0000)
                System.out.println("\n\u001B[31mLa clave del grupo no existe\u001B[0m");
            }
        }
        //Si el tipo es 'E' se registrarán Externos
        else if (tipo == 'E')
        {
            //Solicitar los datos de Externos en color azul cian y negrita (HEX: #00FFFF)
            System.out.println("\n\u001B[1;36m-------Registro de Externos-------\u001B[0m");
            //Solicitar datos del Externo: nombre, direccion, claveGrupo, telefono, nivel, consecutivo, folio, edad
            Externos externo = new Externos();
            //Solicitar los datos del grupo en amarillo (HEX: #FFFF00)
            //Clave del grupo
            externo.setClaveGrupo(solicitarEntradaString("\u001B[33mIngrese la clave del grupo: \u001B[0m"));
            //Si la clave no existe, no hay razón para seguir
            if (!verificarClave(grupos, externo.getClaveGrupo()))
            {
                //Imprimir el mensaje de error en color rojo (HEX: #FF0000)
                System.out.println("\n\u001B[31mLa clave del grupo no existe\u001B[0m");
                System.out.println("\u001B[31mIntente de nuevo\u001B[0m");
                return;
            }
            //Nombre
            externo.setNombre(solicitarEntradaString("\u001B[33mIngrese el nombre del externo: \u001B[0m"));
            //Verificar si hay conflictos de nombres
            if (verificarConflictos(externo.getNombre(), datos))
            {
                // Imprimir los mensajes de advertencia en color amarillo (HEX: #FFFF00)
                System.out.println("\n\u001B[31mAdvertencia: Hay conflictos de nombres\u001B[0m");
                System.out.println("\u001B[31mSe le agregará un número separado por un guion bajo al final de su nombre\u001B[0m");

                // Inicializar el contador y generar un nuevo nombre hasta que no haya conflictos
                int contador = 1;
                String nombreOriginal = externo.getNombre();
                String nuevoNombre = nombreOriginal + "_" + contador;
                externo.setNombre(nuevoNombre);

                while (verificarConflictos(externo.getNombre(), datos))
                {
                    contador++;
                    nuevoNombre = nombreOriginal + "_" + contador;
                    externo.setNombre(nuevoNombre);
                }

                // Imprimir el nuevo nombre en color amarillo (HEX: #FFFF00)
                System.out.println("\u001B[33mNuevo nombre: " + externo.getNombre() + "\u001B[0m");
            }
            //Dirección
            externo.setDireccion(solicitarEntradaString("\u001B[33mIngrese la dirección del externo: \u001B[0m"));
            //Correo
            externo.setCorreo(solicitarEntradaString("\u001B[33mIngrese el correo del externo: \u001B[0m"));
            //Teléfono
            externo.setTelefono(solicitarEntradaLongTelefono("\u001B[33mIngrese el teléfono del externo: \u001B[0m"));
            //Nivel
            externo.setNivel(solicitarEntradaChar(new char[]{'B', 'I', 'A'}, "\u001B[33mIngrese el nivel del externo (B: Básico, I: Intermedio, A: Avanzado): \u001B[0m"));
            //Precio (si es externo, se le aumenta el 50%, utilizando el método calcularPrecioExterno)
            externo.setPrecio(externo.calcularPrecio(externo));
            //Consecutivo: el consecutivo es una variable de clase que inicia en 0 y que cada que se cree un objeto de este tipo el consecutivo se incrementara en 1 y se pasará al folio en formato cadena conservando ceros a la izquierda con longitud de 5 siempre (ej. 00001)
            externo.setConsecutivo(Externos.getConsecutivoAdd());
            //Folio (consecutivo en formato de cadena con longitud de 5)
            externo.setFolio(String.format("%05d", Externos.getConsecutivo()));
            //Edad
            externo.setEdad(solicitarEntradaInt("\u001B[33mIngrese la edad del externo: \u001B[0m"));

            //Verificar si la clave del grupo existe
            if (verificarClave(grupos, externo.getClaveGrupo()))
            {
                //Agregar el objeto externo al ArrayList de datos en el grupo que corresponde con la clave
                for (var grupo : grupos)
                {
                    if (grupo.getClave().equals(externo.getClaveGrupo()))
                    {
                        datos.get(grupos.indexOf(grupo)).add(externo);
                    }
                }

                //Imprimir el mensaje de éxito en color verde (HEX: #008000)
                System.out.println("\n\u001B[32m¡Externo registrado exitosamente!\u001B[0m");
            }
            else
            {
                //Imprimir el mensaje de error en color rojo (HEX: #FF0000)
                System.out.println("\n\u001B[31mLa clave del grupo no existe\u001B[0m");

            }

            //Guardar "consecutivo" en un archivo binario (.dat) para que no se pierda el valor, para que al volver a correr el programa, el consecutivo siga incrementando

        }
    }

    //3. Modificar precio
    void modificarPrecio()
    {
        //Verificar que haya grupos
        if (grupos.isEmpty())
        {
            //Imprimir el mensaje de error en color rojo (HEX: #FF0000)
            System.out.println("\n\u001B[31mError: No hay grupos registrados\u001B[0m");
            return;
        }
        //Darle la bienvenida al usuario con color azul cian y negrita (HEX: #00FFFF)
        System.out.println("\n\u001B[1;36m-------Bienvenido al sistema de modificación de precios-------\u001B[0m");
        //Preguntar el nombre de la persona a la que se le desea cambiar el grupo y buscar en externos e internos
        String nombre = solicitarEntradaString("\u001B[33mIngrese el nombre de la persona a la que se le desea cambiar el precio: \u001B[0m");
        //Buscar en externos e internos
        for (var grupo : datos)
        {
            for (var persona : grupo)
            {
                if (persona.getNombre().equals(nombre))
                {
                    //Imprimir el nombre de la persona en color azul cian y negrita (HEX: #00FFFF)
                    System.out.println("\n\u001B[1;36m¡Persona encontrada!\u001B[0m");
                    //Preguntar si se desea cambiar el precio
                    System.out.println("\n\u001B[33mPrecio actual: " + persona.getPrecio() + "\u001B[0m");
                    char respuesta = solicitarEntradaChar(new char[]{'S', 'N'}, "\n\u001B[33m¿Desea cambiar el precio? (S: Sí, N: No)\u001B[0m");
                    //Si la respuesta es 'S' se cambiará el precio
                    if (respuesta == 'S')
                    {
                        //Solicitar el nuevo precio
                        double precio = solicitarEntradaDouble();
                        //Cambiar el precio
                        persona.setPrecio(precio);
                        //Imprimir el mensaje de éxito en color verde (HEX: #008000)
                        System.out.println("\n\u001B[32m¡Precio cambiado exitosamente!\u001B[0m");
                    }
                    else if (respuesta == 'N')
                    {
                        //Imprimir el mensaje de éxito en color verde (HEX: #008000)
                        System.out.println("\n\u001B[32mNo se cambió el precio\u001B[0m");
                    }
                    return;
                }
            }
        }
        //Imprimir el mensaje de error en color rojo (HEX: #FF0000)
        System.out.println("\n\u001B[31mError: No se encontró a la persona\u001B[0m");
        System.out.println("\u001B[31mIntente de nuevo\u001B[0m");
    }

    //4. Consulta de grupos
    void consultaGrupos()
    {
        //Verificar que haya grupos
        if (grupos.isEmpty())
        {
            //Imprimir el mensaje de error en color rojo (HEX: #FF0000)
            System.out.println("\n\u001B[31mError: No hay grupos registrados\u001B[0m");
            return;
        }
        //Darle la bienvenida al usuario con color azul cian y negrita (HEX: #00FFFF)
        System.out.println("\n\u001B[1;36m-------Consulta de grupos-------\u001B[0m");
        //El día debe coincidir con un día de la semana (1-7); 1: Domingo, 2: Lunes, 3: Martes, 4: Miércoles, 5: Jueves, 6: Viernes, 7: Sábado
        //Imprimir los grupos con formato de tabla, siendo el encabezado en color azul cian y negrita (HEX: #00FFFF); listando la clave, día, hora y tipo
        System.out.printf("\u001B[1;36m%-10s%-10s%-10s%-10s\u001B[0m\n", "Clave", "Día", "Hora", "Tipo");
        //Imprimir los grupos en color amarillo (HEX: #FFFF00); imprimiendo la cadena del día en vez del número; y el tipo en vez de 'A' o 'I' imprimir 'Adultos' o 'Infantes'
        //switch para los días de la semana
        for (var grupo : grupos)
        {
            String dia = getDiaSemana(grupo.getDia());

            String tipo = switch (grupo.getTipo())
            {
                case 'A' -> "Adultos";
                case 'I' -> "Infantes";
                default -> throw new IllegalStateException("Unexpected value: " + grupo.getTipo());
            };
            System.out.printf("\u001B[33m%-10s%-10s%-10s%-10s\u001B[0m\n", grupo.getClave(), dia, grupo.getHora(), tipo);
        }
    }

    //5. Cambio de grupo solicitando el nombre de la persona
    void cambioGrupo()
    {
        //Verificar que haya grupos
        if (grupos.isEmpty())
        {
            //Imprimir el mensaje de error en color rojo (HEX: #FF0000)
            System.out.println("\n\u001B[31mError: No hay grupos registrados\u001B[0m");
            return;
        }
        //Darle la bienvenida al usuario con color azul cian y negrita (HEX: #00FFFF)
        System.out.println("\n\u001B[1;36m-------Bienvenido al sistema de cambio de grupo-------\u001B[0m");
        //Preguntar el nombre de la persona a la que se le desea cambiar el grupo y buscar en externos e internos
        String nombre = solicitarEntradaString("\u001B[33mIngrese el nombre de la persona a la que se le desea cambiar el grupo: \u001B[0m");

        //Buscar en externos e internos
        for (var grupo : datos)
        {
            for (var persona : grupo)
            {
                //Si no hay conflictos, se buscará por nombre
                if (persona.getNombre().equals(nombre))
                {
                    //Imprimir el nombre de la persona en color azul cian y negrita (HEX: #00FFFF)
                    System.out.println("\n\u001B[1;36m¡Persona encontrada!\u001B[0m");
                    System.out.println("\n\u001B[1;36m-------Información de la persona-------\u001B[0m");
                    //Desplegar la información de la persona
                    persona.desplegarInfo();
                    //Preguntar si se desea cambiar el grupo
                    char respuesta = solicitarEntradaChar(new char[]{'S', 'N'}, "\n\u001B[33m¿Desea cambiar el grupo? (S: Sí, N: No)\u001B[0m");
                    //Si la respuesta es 'S' se cambiará el grupo
                    if (respuesta == 'S')
                    {
                        //Solicitar la clave del nuevo grupo
                        String clave = solicitarEntradaString("\u001B[33mIngrese la clave del nuevo grupo: \u001B[0m");

                        //Comprobar que la clave no sea la misma en la que ya está
                        if (clave.equals(persona.getClaveGrupo()))
                        {
                            //Imprimir el mensaje de error en color rojo (HEX: #FF0000)
                            System.out.println("\n\u001B[31mError: La clave del grupo es la misma en la que ya está\u001B[0m");
                            System.out.println("\u001B[31mIntente de nuevo\u001B[0m");
                            return;
                        }
                        //Verificar si la clave del grupo existe
                        if (!verificarClave(grupos, clave))
                        {
                            //Imprimir el mensaje de error en color rojo (HEX: #FF0000)
                            System.out.println("\n\u001B[31mLa clave del grupo no existe\u001B[0m");
                            return;
                        }
                        //Determinar si la persona es interna o externa
                        if (persona instanceof Internos)
                        {
                            //Crear un nuevo objeto de la clase Internos con los datos de la persona
                            Internos interno = new Internos(persona.getNombre(), persona.getDireccion(), persona.getCorreo(), clave, persona.getTelefono(), persona.getNivel(), persona.getPrecio(), ((Internos) persona).getNumControl(), ((Internos) persona).getSemestre());
                            //Agregar el nuevo objeto al grupo correspondiente
                            for (var grupoNuevo : grupos)
                            {
                                if (grupoNuevo.getClave().equals(clave))
                                {
                                    datos.get(grupos.indexOf(grupoNuevo)).add(interno);
                                }
                            }

                            //Eliminar a la persona y sus datos del grupo anterior
                            for (var grupo1 : datos)
                            {
                                if (grupo1.contains(persona))
                                {
                                    grupo1.remove(persona);
                                }
                            }

                            //Informar al usuario que el cambio se realizó con éxito
                            System.out.println("\n\u001B[32m¡Grupo cambiado exitosamente!\u001B[0m");
                            return;
                        }
                        else if (persona instanceof Externos)
                        {
                            //Crear un nuevo objeto de la clase Externos con los datos de la persona
                            Externos externo = new Externos(persona.getNombre(), persona.getDireccion(), persona.getCorreo(), clave, persona.getTelefono(), persona.getNivel(), persona.getPrecio(), Externos.getConsecutivo(), ((Externos) persona).getFolio(), ((Externos) persona).getEdad());
                            //Agregar el nuevo objeto al grupo correspondiente
                            for (var grupoNuevo : grupos)
                            {
                                if (grupoNuevo.getClave().equals(clave))
                                {
                                    datos.get(grupos.indexOf(grupoNuevo)).add(externo);
                                }
                            }

                            //Eliminar a la persona y sus datos del grupo anterior
                            for (var grupo1 : datos)
                            {
                                if (grupo1.contains(persona))
                                {
                                    grupo1.remove(persona);
                                }
                            }

                            //Informar al usuario que el cambio se realizó con éxito
                            System.out.println("\n\u001B[32m¡Grupo cambiado exitosamente!\u001B[0m");
                            return;
                        }

                    }
                    else if (respuesta == 'N')
                    {
                        //Imprimir el mensaje de éxito en color verde (HEX: #008000)
                        System.out.println("\n\u001B[32mNo se cambió el grupo\u001B[0m");
                    }
                }

            }
        }
    }


    //6. Consulta por grupo
    void consultaPorGrupo()
    {
        //Verificar que haya grupos
        if (grupos.isEmpty())
        {
            //Imprimir el mensaje de error en color rojo (HEX: #FF0000)
            System.out.println("\n\u001B[31mError: No hay grupos registrados\u001B[0m");
            return;
        }
        //Darle la bienvenida al usuario con color azul cian y negrita (HEX: #00FFFF)
        System.out.println("\n\u001B[1;36m-------Consulta por grupo-------\u001B[0m");
        //Solicitar la clave del grupo
        String clave = solicitarEntradaString("\u001B[33mIngrese la clave del grupo: \u001B[0m");
        //Verificar si la clave del grupo existe
        if (verificarClave(grupos, clave))
        {
            //Imprimir el mensaje de éxito en color verde (HEX: #008000)
            System.out.println("\n\u001B[32m¡Grupo encontrado!\u001B[0m");
            //Imprimir los datos de las personas en el grupo
            for (var grupo : grupos)
            {
                if (grupo.getClave().equals(clave))
                {
                    System.out.println("\n\u001B[1;36m-------Información del grupo-------\u001B[0m");
                    //Imprimir los datos del grupo en formato de tabla y color amarillo (HEX: #FFFF00)
                    System.out.printf("\u001B[1;36m%-10s%-10s%-10s%-10s\u001B[0m\n", "Clave", "Día", "Hora", "Tipo");
                    String dia = getDiaSemana(grupo.getDia());
                    String tipo = switch (grupo.getTipo())
                    {
                        case 'A' -> "Adultos";
                        case 'I' -> "Infantes";
                        default -> throw new IllegalStateException("Unexpected value: " + grupo.getTipo());
                    };
                    System.out.printf("\u001B[33m%-10s%-10s%-10s%-10s\u001B[0m\n", grupo.getClave(), dia, grupo.getHora(), tipo);

                    //Desplegar la información de las personas en el grupo en formato de tabla y color amarillo (HEX: #FFFF00)
                    //Primero imprimir los internos
                    for (var persona : datos.get(grupos.indexOf(grupo)))
                    {
                        if (persona instanceof Internos)
                        {
                            System.out.println("\n\u001B[1;36m-------Información de Internos-------\u001B[0m");
                            persona.desplegarInfo();
                        }
                    }
                    //Luego imprimir los externos
                    for (var persona : datos.get(grupos.indexOf(grupo)))
                    {
                        if (persona instanceof Externos)
                        {
                            System.out.println("\n\u001B[1;36m-------Información de Externos-------\u001B[0m");
                            persona.desplegarInfo();
                        }
                    }
                }
            }
        }
        else
        {
            //Imprimir el mensaje de error en color rojo (HEX: #FF0000)
            System.out.println("\n\u001B[31mLa clave del grupo no existe\u001B[0m");
        }
    }

    //Método para verificar si la clave ya existe
    boolean verificarClave(ArrayList<Grupos> grupos, String clave)
    {
        for (Grupos grupo : grupos)
        {
            if (grupo.getClave().equals(clave))
            {
                return true;
            }
        }
        return false;
    }

    //Método para conseguir los días de la semana
    String getDiaSemana(int dia)
    {
        return switch (dia)
        {
            case 1 -> "Domingo";
            case 2 -> "Lunes";
            case 3 -> "Martes";
            case 4 -> "Miércoles";
            case 5 -> "Jueves";
            case 6 -> "Viernes";
            case 7 -> "Sábado";
            default -> "Día no válido";
        };
    }

    //Método booleano para verificar si hay conflictos de nombres
    boolean verificarConflictos(String nombre, ArrayList<ArrayList<DatosGenerales>> grupo)
    {
        // Regresar true si hay conflictos (si el nombre aparece más de una vez)
        int contador = 0;
        for (var grupo1 : grupo)
        {
            for (var persona : grupo1)
            {
                if (persona.getNombre().equals(nombre))
                {
                    contador++;
                }
            }
        }

        return contador >= 1;
    }


    //Método para solicitar una entrada de tipo entero
    public int solicitarEntradaInt(String mensaje)
    {
        Scanner sc = new Scanner(System.in);

        while (true)
        {
            try
            {
                System.out.println(mensaje);
                int numero = sc.nextInt();

                if (numero >= 0)
                {
                    return numero;
                }
                else
                {
                    // Imprimir el mensaje de error en color rojo (HEX: #FF0000)
                    System.out.println("\n\u001B[31mError: Ingrese un número entero mayor o igual a 0\u001B[0m");

                }
            } catch (Exception e)
            {
                // Imprimir el mensaje de error en color rojo (HEX: #FF0000)
                System.out.println("\n\u001B[31mError: Ingrese un número entero\u001B[0m");
                sc.next(); // Consumir la entrada no válida
            }
        }
    }


    public int solicitarEntradaInt(String mensaje, Integer min, Integer max)
    {
        Scanner sc = new Scanner(System.in);

        while (true)
        {
            try
            {
                System.out.println(mensaje);
                int numero = sc.nextInt();

                // Verificar si se debe aplicar un rango
                if ((min == null || numero >= min) && (max == null || numero <= max))
                {
                    return numero;
                }
                else
                {
                    // Imprimir el mensaje de error en color rojo (HEX: #FF0000)
                    if (min != null && max != null)
                    {
                        System.out.printf("\u001B[31mError: Ingrese un número entero entre %d y %d\u001B[0m\n\n", min, max);
                    }
                    else if (min != null)
                    {
                        System.out.printf("\u001B[31mError: Ingrese un número entero mayor o igual a %d\u001B[0m\n\n", min);
                    }
                    else
                    {
                        System.out.printf("\u001B[31mError: Ingrese un número entero menor o igual a %d\u001B[0m\n\n", max);
                    }
                }
            } catch (Exception e)
            {
                // Imprimir el mensaje de error en color rojo (HEX: #FF0000)
                System.out.println("\u001B[31mError: Ingrese un número entero\u001B[0m\n");
                sc.next(); // Consumir la entrada no válida
            }
        }
    }


    //Método para solicitar una entrada de tipo cadena
    static String solicitarEntradaString(String mensaje)
    {
        Scanner sc = new Scanner(System.in);
        String entrada;

        do
        {
            System.out.println(mensaje);
            entrada = sc.nextLine();
            if (entrada.isEmpty())
            {
                System.out.println("\u001B[31mError: La cadena no puede estar vacía\u001B[0m");
            }
        } while (entrada.isEmpty());

        return entrada;

    }

    //Método para solicitar una entrada de tipo char
    public static char solicitarEntradaChar(char[] opcionesValidas, String mensaje)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("\u001B[33m" + mensaje + "\u001B[0m");
        while (true)
        {
            try
            {
                char tipo = sc.next().toUpperCase().charAt(0);
                for (char opcion : opcionesValidas)
                {
                    if (tipo == opcion)
                    {
                        return tipo;
                    }
                }
                // Imprimir el mensaje de error en color rojo (HEX: #FF0000)
                System.out.println("\u001B[31mError: Ingrese una opción válida\u001B[0m");
                System.out.println("\n\u001B[33m" + mensaje + "\u001B[0m");

            } catch (Exception e)
            {
                // Imprimir el mensaje de error en color rojo (HEX: #FF0000)
                System.out.println("\u001B[31mError: Ingrese una opción válida\u001B[0m");
                System.out.println("\n\u001B[33m" + mensaje + "\u001B[0m");
                sc.next(); // Limpiar el buffer del scanner
            }
        }
    }

    //Método para solicitar una entrada de tipo long
    long solicitarEntradaLongTelefono(String mensaje)
    {
        Scanner sc = new Scanner(System.in);
        long numero;

        while (true)
        {
            System.out.println(mensaje);

            if (sc.hasNextLong())
            {
                numero = sc.nextLong();

                if (numero > 0 && String.valueOf(numero).length() <= 10)
                {
                    return numero;
                }
                else
                {
                    // Imprimir el mensaje de error en color rojo (HEX: #FF0000)
                    System.out.println("\u001B[31mError: El número no debe ser menor que 0 y debe tener 10 dígitos\u001B[0m");
                }
            }
            else
            {
                // Imprimir el mensaje de error en color rojo (HEX: #FF0000)
                System.out.println("\u001B[31mError: Ingrese un número entero\u001B[0m");
                sc.next(); // Consumir la entrada no válida
            }
        }
    }

    //Método para solicitar una entrada de tipo double mayor a 0
    double solicitarEntradaDouble()
    {
        Scanner sc = new Scanner(System.in);
        double numero;

        while (true)
        {
            System.out.println("\u001B[33mIngrese el nuevo precio: \u001B[0m");

            if (sc.hasNextDouble())
            {
                numero = sc.nextDouble();

                if (numero >= 0)
                {
                    return numero;
                }
                else
                {
                    // Imprimir el mensaje de error en color rojo (HEX: #FF0000)
                    System.out.println("\u001B[31mError: El número no debe ser menor que 0\u001B[0m\n");

                }
            }
            else
            {
                // Imprimir el mensaje de error en color rojo (HEX: #FF0000)
                System.out.println("\u001B[31mError: Ingrese un número decimal\u001B[0m\n");
                sc.next(); // Consumir la entrada no válida
            }
        }
    }


    //Método para desplegar el menú
    void desplegarMenu(Funcionalidad obj)
    {
        //Variable para la opción del menú
        int opcion;
        //Menú de opciones
        do
        {
            //Imprimir el menú en color azul cian y negrita (HEX: #00FFFF)
            System.out.println("\n\u001B[1;36mMenú de opciones\u001B[0m");
            System.out.println("1.- Registrar grupos");
            System.out.println("2.- Registrar Internos o Externos");
            System.out.println("3.- Modificar precio");
            System.out.println("4.- Consulta de grupos");
            System.out.println("5.- Cambio de grupo");
            System.out.println("6.- Consulta por grupo");
            System.out.println("100.- Salir");
            //Solicitar la opción en amarillo (HEX: #FFFF00)
            opcion = solicitarEntradaInt("\u001B[33mIngrese una opción: \u001B[0m");
            //Switch para las opciones
            switch (opcion)
            {
                case 1:
                    obj.registroGrupos();
                    break;
                case 2:
                    obj.registroInternosOExternos();
                    break;
                case 3:
                    obj.modificarPrecio();
                    break;
                case 4:
                    obj.consultaGrupos();
                    break;
                case 5:
                    obj.cambioGrupo();
                    break;
                case 6:
                    obj.consultaPorGrupo();
                    break;
                case 100:
                    //Imprimir el mensaje de despedida en color azul cian y negrita (HEX: #00FFFF)
                    System.out.println("\u001B[1;36mGracias por usar el programa\u001B[0m");
                    break;
                default:
                    //Imprimir el mensaje de error en color rojo (HEX: #FF0000)
                    System.out.println("\u001B[31mError: Ingrese una opción válida\u001B[0m");
                    break;
            }
        } while (opcion != 100);
    }

}
