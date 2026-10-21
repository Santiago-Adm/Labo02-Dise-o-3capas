package org.example.application.service;

import org.example.application.port.in.cursoUseCase;
import org.example.domain.repository.cursoRepository;
import org.example.domain.model.curso;

import java.util.List;

public class cursoService implements cursoUseCase {
    private final cursoRepository repository;

    public cursoService(cursoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registrar(curso curso) {
        repository.guardar(curso);
    }

    @Override
    public List<curso> listar() {
        return repository.listar();
    }

    @Override
    public Boolean actualizar(curso curso) {
        return repository.actualizar(curso);
    }

    @Override
    public Boolean eliminar(int id) {
        return repository.eliminar(id);
    }
}
