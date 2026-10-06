package org.example;

import org.example.application.estudianteService;
import org.example.presentation.estudianteUI;
import org.example.domain.repository.estudianteRepository;
import org.example.infrastructure.persistence.estudianteRepositoryJson;

import org.example.presentation.cursoUI;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc= new Scanner(System.in);

        //Inversion de dependencia para estudiante
        estudianteRepository estudianteRepository = new estudianteRepositoryJson();
        estudianteService estudianteService = new estudianteService(estudianteRepository);
        estudianteUI estudianteUI = new estudianteUI(estudianteService);

        int opcion;

        do{
            System.out.println("\n=== SISTEMA DE GESTIÓN ACADÉMICA ===");
            System.out.println("1. Gestionar estudiantes");
            System.out.println("2. Gestionar cursos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion=sc.nextInt();

            switch (opcion){
                case 1 -> {
                    System.out.println("Ha elegido Gestionar Estudiante.");
                    estudianteUI.mostrarMenu(sc);
                }

                case 2 -> {
                    System.out.println("Ha elegido Gestionar Cursos.");
                    //cursoUI.mostrarMenu(sc);
                }
                case 0 -> System.out.println("Sistema finalizado.");
                default -> System.out.println("Opcion no valida");

            }
        }while(opcion!=0);
        sc.close();
    }
}
