package org.example.domain.repository;

import org.example.domain.model.estudiante;

import java.util.List;

public interface estudianteRepository {
    List<estudiante> listar();
    void guardar(List<estudiante> estudiantes);

}
