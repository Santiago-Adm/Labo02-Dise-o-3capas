package org.example.domain.repository;

import org.example.domain.model.curso;
import java.util.List;
import java.util.Optional;

public interface cursoRepository {
    void guardar(curso curso);
    List<curso> listar();
    void guardar(List<curso> cursos);
    Optional<curso> buscarPorId(int id);
    boolean actualizar(curso curso);
    boolean eliminar(int id);
}
