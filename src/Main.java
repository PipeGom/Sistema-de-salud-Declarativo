import java.util.*;
import Clases.*;

public class Main {


    public static void main(String[] args) {

      

        Scanner input = new Scanner(System.in);
        System.out.println("Porfavor elija una opción: \n" +
                "1.Agregar paciente \n" +
                "2.Paciente con mayores costos \n" +
                "3.Paciente con mayor número de enfermades \n" +
                "4.Costos por Eps\n" +
                "5.Número de pacientes por Eps \n" +
                "6.Número de pacientes por tipo de afiliación \n" +
                "7.Número de pacientes por tipo de regimen ");
        Integer opcion = Integer.parseInt(input.nextLine());

        switch (opcion){
            case 1:{
                Paciente.agregarPaciente();
                break;
            }
            case 2:{
                Paciente.pacienteMayoresCostos();
                break;
            }
            case 3:{
                Enfermedad.pacienteMasEnfermedades();
                break;
            }
            case 4:{
                Eps.costosPorEps();
                break;
            }
            case 5:{
                Eps.numeroPacientesPorEps();
                break;
            }
            case 6:{
                Eps.numeroPacientesTipoAfiliacion();
                break;
            }
            case 7:{
                Paciente.numeroPacientesTipoRegimen();
                break;
            }
            default: {
                System.out.println("Opcion incorrecta");
            }

        }
        input.close();

    }

    
    
    

    

    
}