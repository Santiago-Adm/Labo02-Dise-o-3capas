package org.example.application.service;
import org.example.domain.repository.estudianteRepository;
import org.example.domain.model.estudiante;
import org.example.application.port.in.estudianteUseCase;

import java.util.List;

public class estudianteService extends estudianteUseCase {
    private final estudianteRepository repository;

    public estudianteService(estudianteRepository repository) {
        this.repository=repository;
        // repository=new estudianteRepository();
    }

    @Override
    public void registrar(estudiante estudiante) {
        repository.guardar(estudiante);
    }

    @Override
    public List<estudiante> listar(){

        return repository.listar();
    }

    @Override
    public Boolean actualizar(estudiante estudiante){
        return repository.actualizar(estudiante);
    }

    @Override
    public Boolean eliminar(int id){
        return repository.eliminar(id);
    }
}
