package org.example.domain.repository;

import org.example.domain.model.estudiante;

import java.util.List;
import java.util.Optional;

public interface estudianteRepository {
    void guardar(estudiante estudiante);
    List<estudiante> listar();
    void guardar(List<estudiante> estudiantes);

    Optional<estudiante> buscarPorId(int id);

    boolean actualizar(estudiante estudiante);

    boolean eliminar(int id);

}
