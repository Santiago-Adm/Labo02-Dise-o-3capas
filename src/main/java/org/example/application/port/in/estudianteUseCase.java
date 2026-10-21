package org.example.application.port.in;

import org.example.domain.model.estudiante;

import java.util.List;

public interface estudianteUseCase {

    void registrar(estudiante estudiante);
    List<estudiante> listar();
    Boolean actualizar(estudiante estudiante);
    Boolean eliminar(int id);
}
