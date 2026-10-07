package org.example.presentation;

import org.example.domain.model.curso;
import org.example.application.cursoService;
import java.util.Scanner;

public class cursoUI {
    private static cursoService service = null;

    private static final String[] CURSOS_DISPONIBLES = {
            "Arquitectura de Software",
            "Desarrollo Web",
            "Informática Forense",
            "Inteligencia Artificial I",
            "Seminario de Tesis I",
            "Servicio Social",
            "Telecomunicaciones"
    };

    public cursoUI(cursoService service) {
        cursoUI.service = service;
    }

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
                case 1 -> registrar(sc);
                case 2 -> listar();
                case 3 -> actualizar(sc);
                case 4 -> eliminar(sc);
                case 0 -> System.out.println("Regresando al menu principal");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void registrar(Scanner sc) {
        System.out.print("ID del estudiante: ");
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
    }

    private static void listar() {
        service.listar().forEach(c ->
                System.out.println(
                        c.getId() + " - " +
                                c.getNombre()
                )
        );
    }

    private static void actualizar(Scanner sc) {
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
    }

    private static void eliminar(Scanner sc) {
        System.out.print("ID del curso a eliminar: ");
        int idEliminar = sc.nextInt();
        sc.nextLine();
        boolean eliminado = service.eliminar(idEliminar);
        System.out.println(eliminado ? "Curso eliminado con éxito." : "Curso no encontrado.");
    }
}
