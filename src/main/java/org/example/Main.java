package org.example;

import org.example.application.port.in.cursoUseCase;
import org.example.application.port.in.estudianteUseCase;
import org.example.application.service.cursoService;
import org.example.application.service.estudianteService;
import org.example.domain.repository.cursoRepository;
import org.example.domain.repository.estudianteRepository;
import org.example.presentation.estudianteUI;
import org.example.presentation.cursoUI;
import org.example.infrastructure.adapter.out.persistence.estudianteRepositoryPostgres;
import org.example.infrastructure.adapter.out.persistence.cursoRepositoryPostgres;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc= new Scanner(System.in);

        //Ensamblado estudiantes Hexagonal con PotsgreSQl
        estudianteRepository estudianteRepository = new estudianteRepositoryPostgres();
        estudianteService estudianteUseCase = new estudianteService(estudianteRepository);
        estudianteUI estudianteUI = new estudianteUI(estudianteUseCase);

        //Adaptador de salida


        //Ensamblado curso hexagonal con PotsgreSQL
        cursoRepository cursoRepository = new cursoRepositoryPostgres();
        cursoService cursoUseCase = new cursoService(cursoRepository);
        cursoUI cursoUI = new cursoUI(cursoUseCase);

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
                    cursoUI.mostrarMenu(sc);
                }
                case 0 -> System.out.println("Sistema finalizado.");
                default -> System.out.println("Opcion no valida");

            }
        }while(opcion!=0);
        sc.close();
    }
}
