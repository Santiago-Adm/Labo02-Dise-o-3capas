package org.example.application.port.in;

import org.example.domain.model.estudiante;

import java.util.List;

public abstract class estudianteUseCase {

    public abstract void registrar(estudiante estudiante);

    public abstract List<estudiante> listar();

    public abstract Boolean actualizar(estudiante estudiante);

    public abstract Boolean eliminar(int id);
}
