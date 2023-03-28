package Clases;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.*;
import java.util.Collections;
import java.util.stream.Collectors;

public class Paciente {

    public Integer id;
    public String nombre;
    public String apellido;
    public List<Enfermedad> enfermedades;
    public Regimen regimen;
    public Afiliacion afiliacion;
    public Double costos;
    public Integer numEnfermedades;
    public Eps eps;

    public Paciente(Integer id, String nombre, String apellido, Regimen regimen,
                    Afiliacion afiliacion, Double costos, Integer numEnfermedades,
                    List<Enfermedad> enfermedades, Eps eps){
        this.id = id;
        this.nombre = nombre;
        this.apellido=apellido;
        this.regimen = regimen;
        this.afiliacion = afiliacion;
        this.costos = costos;
        this.numEnfermedades = numEnfermedades;
        this.enfermedades =enfermedades;
        this.eps= eps;
    }


    public static Double getCostos(Paciente paciente) {
        return paciente.costos;
    }


    @Override
    public String toString() {
        return "Paciente{" +
                "nombre='" + nombre + '\'' +
                "apellido='" + apellido + '\'' +
                "tipo de afiliación='" + afiliacion + '\'' +
                "tipo de regimen='" + regimen + '\'' +
                "costos='" + costos + '\'' +
                "número de enfermedades='" + numEnfermedades + '\'' +
                "eps='" + eps + '\'' +
                ", id=" + id +
                '}';
    }



    static List<Paciente> pacientes = List.of(
            new Paciente(1, "Sandra","zapato",Regimen.CONTRIBUTIVO,
                    Afiliacion.COTIZANTE,3589870.98,4,Enfermedad.enfermedad1,
                    new Eps(1,"SURA")),
            new Paciente(2, "Juancho","panza",Regimen.SUBSIDIADO,
                    Afiliacion.BENEFICIARIO,100000.00,1,Enfermedad.enfermedad2,
                    new Eps(3,"SANITAS")),
            new Paciente(3, "Armando","casas",Regimen.CONTRIBUTIVO,
                    Afiliacion.BENEFICIARIO,20000.00,0,Enfermedad.enfermedad3,
                    new Eps(2,"COMFENALCO")),
            new Paciente(4, "Adrian","sapito",Regimen.CONTRIBUTIVO,
                    Afiliacion.COTIZANTE,35898700.98,2,Enfermedad.enfermedad4,
                    new Eps(1,"SURA")),
            new Paciente(5, "Alejo","cordoba",Regimen.SUBSIDIADO,
                    Afiliacion.BENEFICIARIO,0.00,1,Enfermedad.enfermedad5,
                    new Eps(2,"COMFENALCO"))
    );


    static   List<Paciente> pacientesMutables = new ArrayList<>(pacientes);

    public static void agregarPaciente(){
        Scanner input = new Scanner(System.in);

        String nombre = getInput("nombre del paciente", input);
        String apellido = getInput("apellido del paciente", input);
        Integer id = Integer.parseInt(getInput("id del paciente", input));

        Integer afiliacion = Integer.parseInt(getInput("tipo de afiliación del paciente (0:COTIZANTE, 1:BENEFICIARIO)", input));
        Afiliacion afi = afiliacion == 0 ? Afiliacion.COTIZANTE : Afiliacion.BENEFICIARIO;

        Integer regimen = Integer.parseInt(getInput("tipo de régimen del paciente (0:CONTRIBUTIVO, 1:SUBSIDIADO)", input));
        Regimen reg = regimen == 0 ? Regimen.CONTRIBUTIVO : Regimen.SUBSIDIADO;

        Double costos = Double.parseDouble(getInput("costos de tratamiento del paciente", input));
        Integer numEnfermedades = Integer.parseInt(getInput("número de enfermedades del paciente", input));

        List<Enfermedad> enfermedades = new ArrayList<>();
        for (int i = 0; i < numEnfermedades; i++){
            Integer idEnfermedad = Integer.parseInt(getInput("id de la enfemedad", input));
            String nombreEnfermedad = getInput("nombre de la enfermedad", input);
            Enfermedad enfermedad = new Enfermedad(idEnfermedad, nombreEnfermedad);
            enfermedades.add(enfermedad);
        }

        Integer idEps = Integer.parseInt(getInput("ID de la Eps del paciente", input));
        String nombreEps = getInput("nombre de la Eps del paciente", input);
        Eps eps = new Eps(idEps, nombreEps);

        Paciente paciente = new Paciente(id, nombre, apellido, reg, afi, costos, numEnfermedades, enfermedades, eps);
        pacientesMutables.add(paciente);

        System.out.println("El paciente: " + paciente.nombre + " Identificado con Cc: " + paciente.id + " fue registrado de manera exitosa.");
    }

    private static String getInput(String fieldName, Scanner input) {
        System.out.println("Ingrese " + fieldName + ": ");
        return input.nextLine();
    }


    public static void pacienteMayoresCostos() {
        List<Paciente> pacientesMayoresCostos = pacientes.stream()  // Primero, creamos un flujo de datos de pacientes utilizando el método stream().
                .sorted(Comparator.comparingDouble(Paciente::getCostos).reversed()) // Luego, ordenamos los pacientes por su costo en orden descendente usando el método sorted() y el Comparator para comparar los costos de los pacientes.
                .filter(p -> getCostos(p).equals(pacientes.get(0).getCostos(p))) //Después, filtramos los pacientes que tienen el costo más alto utilizando el método filter(). a traves de una funcion lambda que recibe un objeto paciente
                .collect(Collectors.toList()); // Finalmente, utilizamos el método collect() para recopilar los resultados en una lista.

        pacientesMayoresCostos.forEach(System.out::println);
    }


    public static void numeroPacientesTipoRegimen() {
        long contsubsi = pacientes.stream().filter(p -> p.regimen == Regimen.SUBSIDIADO).count();    //Se usa el método stream() de la colección de pacientes para crear un flujo de elementos.
        long contcontri = pacientes.stream().filter(p -> p.regimen == Regimen.CONTRIBUTIVO).count(); // Se usa el método filter() para filtrar los pacientes según su tipo de régimen

        System.out.println("El número de pacientes con tipo de régimen subsidiado es: " + contsubsi);  //Se usa el método count() para contar los elementos que cumplen con el filtro.
        System.out.println("El número de pacientes con tipo de régimen contributivo es: " + contcontri); //Se utiliza el operador lambda p -> p.regimen == Regimen.SUBSIDIADO para definir el criterio de filtro.
    } //Se utilizan las variables contsubsi y contcontri para almacenar la cantidad de pacientes con cada tipo de régimen.


}
