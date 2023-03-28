package Clases;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


public class Eps {
    public Integer idEps;
    public String nombre;

    public Eps(Integer idEps, String nombre){
        this.idEps = idEps;
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Integer getIdEps() {
        return this.idEps;
    }

    static List<Eps> lasEps = List.of(new Eps(1,"SURA"), new Eps(2,"COMFENALCO")
            ,new Eps(3,"SANITAS"));



    public static void costosPorEps() {
        Double[] mayor = {0.0};
        String[] nombreMayor = {""};

        // Imprimir el costo total de cada EPS
        lasEps.forEach(eps -> {   ////En esta implementación, usamos forEach() para recorrer cada objeto Eps en lasEps y para cada uno
            Double costoTotal = Paciente.pacientes.stream()
                    .filter(paciente -> paciente.eps.idEps == eps.idEps) //, calculamos el costo total de los pacientes que pertenecen a esa EPS usando filter() para filtrar solo los pacientes que pertenecen a esa EPS,
                    .mapToDouble(Paciente::getCostos) //mapToDouble() para obtener los costos de los pacientes y sum() para sumarlos. Luego, imprimimos el costo total de cada EPS.
                    .sum();
            System.out.println("Los costos de la EPS " + eps.nombre + " son: " + costoTotal);

            // Actualizar el costo y nombre de la EPS con los mayores costos
            if (costoTotal > mayor[0]) {
                mayor[0] = costoTotal;
                nombreMayor[0] = eps.nombre;
            }
        });

        // Imprimir la EPS con los mayores costos
        System.out.println("La EPS con mayores costos es: " + nombreMayor[0] + " con unos costos de: " + mayor[0]);
    }



    public static void numeroPacientesPorEps(){
        Map<String, Long> pacientesPorEps = Paciente.pacientes.stream()  //En esta solución se utiliza el método stream() para convertir la lista de pacientes en un stream de objetos, lo que permite aplicar operaciones funcionales.
                .collect(Collectors.groupingBy(p -> p.eps.nombre, Collectors.counting())); //Luego se utiliza Collectors.groupingBy() para agrupar los pacientes por EPS y contar la cantidad de pacientes por cada EPS.

        pacientesPorEps.forEach((eps, cantidadPacientes) ->    //Finalmente, se utiliza forEach() para imprimir la cantidad de pacientes por EPS,
                System.out.println("La EPS " + eps + " tiene un total de " + cantidadPacientes + " pacientes")
        );

        Optional<Map.Entry<String, Long>> epsConMasPacientes = pacientesPorEps.entrySet().stream()  //y se utiliza max() para obtener la EPS con mayor cantidad de pacientes.
                .max(Map.Entry.comparingByValue());                                                 // La función max() devuelve un Optional, por lo que se debe verificar si existe un valor antes de imprimirlo

        if (epsConMasPacientes.isPresent()) {
            Map.Entry<String, Long> eps = epsConMasPacientes.get();
            System.out.println("La EPS con mayor número de pacientes es " + eps.getKey()
                    + " con un total de pacientes de " + eps.getValue());
        } else {
            System.out.println("No hay pacientes registrados");
        }
    }


    public static void numeroPacientesTipoAfiliacion(){
        long contcoti = Paciente.pacientes.stream()  //Aquí, en lugar de usar un bucle for, estamos usando stream para filtrar los pacientes según su tipo de afiliación,
                .filter(p -> p.afiliacion == Afiliacion.COTIZANTE)  // contando la cantidad de pacientes para cada tipo de afiliación usando el método count.
                .count();
        long contbene = Paciente.pacientes.stream()   //La función ahora es más declarativa ya que se centra en qué se quiere hacer en lugar de cómo hacerlo.
                .filter(p -> p.afiliacion == Afiliacion.BENEFICIARIO)
                .count();
        System.out.println("El número de pacientes con tipo de afiliación cotizante es: " + contcoti);
        System.out.println("El número de pacientes con tipo de afiliación beneficiario es: " + contbene);
    }


}
