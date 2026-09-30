package org.example.presentacion;

import org.example.busines.curso;
import org.example.busines.cursoService;
import java.util.Scanner;

public class cursoUI {
    private static final cursoService service = new cursoService();

    private static final String[] CURSOS_DISPONIBLES = {
            "Arquitectura de Software",
            "Desarrollo Web",
            "Informática Forense",
            "Inteligencia Artificial I",
            "Seminario de Tesis I",
            "Servicio Social",
            "Telecomunicaciones"
    };

    public static void mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE CURSOS ===");
            System.out.println("1. Registrar curso");
            System.out.println("2. Listar cursos");
            System.out.println("3. Actualizar curso");
            System.out.println("4. Eliminar curso");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("ID del curso: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("\nSeleccione el curso a registrar:");
                    for (int i = 0; i < CURSOS_DISPONIBLES.length; i++) {
                        System.out.println((i + 1) + ". " + CURSOS_DISPONIBLES[i]);
                    }
                    System.out.print("Opción (1-" + CURSOS_DISPONIBLES.length + "): ");
                    int seleccion = sc.nextInt();
                    sc.nextLine();

                    if (seleccion >= 1 && seleccion <= CURSOS_DISPONIBLES.length) {
                        String nombreCurso = CURSOS_DISPONIBLES[seleccion - 1];
                        service.registrar(new curso(id, nombreCurso));
                        System.out.println("Curso registrado con éxito.");
                    } else {
                        System.out.println("Opción de curso no válida.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- LISTA DE CURSOS ---");
                    if (service.listar().isEmpty()) {
                        System.out.println("No hay cursos registrados.");
                    } else {
                        service.listar().forEach(c ->
                                System.out.println("ID: " + c.getId() + " | Nombre: " + c.getNombre())
                        );
                    }
                    break;

                case 3:
                    System.out.print("ID del curso a actualizar: ");
                    int idAct = sc.nextInt();
                    sc.nextLine();

                    System.out.println("\nSeleccione el nuevo curso:");
                    for (int i = 0; i < CURSOS_DISPONIBLES.length; i++) {
                        System.out.println((i + 1) + ". " + CURSOS_DISPONIBLES[i]);
                    }
                    System.out.print("Opción (1-" + CURSOS_DISPONIBLES.length + "): ");
                    int selAct = sc.nextInt();
                    sc.nextLine();

                    if (selAct >= 1 && selAct <= CURSOS_DISPONIBLES.length) {
                        String nuevoNombre = CURSOS_DISPONIBLES[selAct - 1];
                        boolean actualizado = service.actualizar(new curso(idAct, nuevoNombre));
                        System.out.println(actualizado ? "Curso actualizado con éxito." : "Curso no encontrado.");
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    break;

                case 4:
                    System.out.print("ID del curso a eliminar: ");
                    int idEliminar = sc.nextInt();
                    sc.nextLine();
                    boolean eliminado = service.eliminar(idEliminar);
                    System.out.println(eliminado ? "Curso eliminado con éxito." : "Curso no encontrado.");
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}
