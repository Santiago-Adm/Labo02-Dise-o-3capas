package org.example.presentacion;
import org.example.busines.estudiante;
import org.example.busines.estudianteService;
import java.util.Scanner;

public class estudianteUI {
    private static final estudianteService service=new estudianteService();
    public static void mostrarMenu(Scanner sc){
        int opcion;
        do{
            System.out.println("\n=== GESTION ESTUDIANTE ===");
            System.out.println("1. Registrar");
            System.out.println("2. Listar");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");
            opcion=sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Id:");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Nombre:");
                    String nombre=sc.nextLine();
                    System.out.println("Correo:");
                    String correo=sc.nextLine();
                    service.registrar(new estudiante(id,nombre,correo));
                    System.out.println("Estudiante Registrado");
                    break;
                case 2:
                    service.listar().forEach(e-> System.out.println(e.getId()+""+e.getNombre()+""+e.getCorreo()+"\n"));
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        }while (opcion!=0);
    }
}
