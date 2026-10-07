package org.example.domain.repository;

import org.example.domain.model.curso;
import java.util.List;

public interface cursoRepository {
    List<curso> listar();
    void guardar(List<curso> cursos);
}
