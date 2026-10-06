package org.example.application;

import org.example.domain.repository.cursoRepository;
import org.example.domain.model.curso;

import java.util.List;

public class cursoService {
    private final cursoRepository repository;

    public cursoService() {
        repository = new cursoRepository();
    }

    public void registrar(curso curso) {
        List<curso> cursos = repository.listar();
        cursos.add(curso);
        repository.guardar(cursos);
    }

    public List<curso> listar() {
        return repository.listar();
    }

    public Boolean actualizar(curso curso) {
        List<curso> cursos = repository.listar();
        for (curso c : cursos) {
            if (c.getId() == curso.getId()) {
                c.setNombre(curso.getNombre());
                repository.guardar(cursos);
                return true;
            }
        }
        return false;
    }

    public Boolean eliminar(int id) {
        List<curso> cursos = repository.listar();
        boolean eliminado = cursos.removeIf(c -> c.getId() == id);
        if (eliminado) {
            repository.guardar(cursos);
        }
        return eliminado;
    }
}
