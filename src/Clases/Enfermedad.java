package Clases;
import java.util.List;


public class Enfermedad {


    private Integer idEnfermedad;
    private String nombre;

    public Enfermedad(Integer idEnfermedad, String nombre){
        this.idEnfermedad = idEnfermedad;
        this.nombre = nombre;
    }

    static List<Enfermedad> enfermedad1 = List.of((new Enfermedad(1,"bronquitis")),
            new Enfermedad(2,"influenza"), new Enfermedad(3,"infeccion"),
            new Enfermedad(4,"cadiopatía"));
    static List<Enfermedad> enfermedad2 = List.of(new Enfermedad(2,"influenza"));
    static List<Enfermedad> enfermedad3 = List.of();
    static List<Enfermedad> enfermedad4 = List.of(new Enfermedad(5,"covid"),
            new Enfermedad(6,"sindrome del sapo"));
    static List<Enfermedad> enfermedad5 = List.of(new Enfermedad(6,"sindrome del sapo"));


    public static void pacienteMasEnfermedades() {
        int mayorEnfermedades = Paciente.pacientes.stream()  ///La primera línea utiliza la API de secuencias de Java 8 para crear una secuencia de pacientes
                .mapToInt(paciente -> paciente.numEnfermedades) //y luego utiliza mapToInt para mapear cada paciente a su número de enfermedades.
                .max() //Luego, usa max para encontrar el valor máximo en la secuencia de números de enfermedades.
                .orElse(0); //Finalmente, utiliza orElse para manejar el caso donde la secuencia está vacía y devuelve 0 en ese caso.
        Paciente.pacientes.stream() //La segunda línea utiliza la API de secuencias de Java 8 para crear otra secuencia de pacientes
                .filter(paciente -> paciente.numEnfermedades == mayorEnfermedades) //y luego usa filter para filtrar solo los pacientes que tienen el número máximo de enfermedades.
                .forEach(System.out::println);  //Luego, utiliza forEach para imprimir cada paciente en la secuencia.
    }

}
