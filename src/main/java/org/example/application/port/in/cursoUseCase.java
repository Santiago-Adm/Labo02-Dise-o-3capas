package org.example.application.port.in;

import org.example.domain.model.curso;
import java.util.List;

public interface cursoUseCase {
    void registrar(curso curso);
    List<curso> listar();
    Boolean actualizar(curso curso);
    Boolean eliminar(int id);
}
