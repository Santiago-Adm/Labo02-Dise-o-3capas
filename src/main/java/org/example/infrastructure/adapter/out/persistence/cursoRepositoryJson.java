package org.example.infrastructure.adapter.out.persistence;

import org.example.domain.model.curso;
import org.example.domain.repository.cursoRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class cursoRepositoryJson implements cursoRepository {
    private final String archivo = "data/cursos.json";
    private final Gson gson = new Gson();

    @Override
    public List<curso> listar(){
        try (Reader reader = new FileReader(archivo)) {
            Type tipo = new TypeToken<List<curso>>(){}.getType();
            List<curso> cursos = gson.fromJson(reader, tipo);
            return cursos != null ? cursos : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void guardar(List<curso> cursos){
        try (Writer writer = new FileWriter(archivo)) {
            gson.toJson(cursos, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo");
        }
    }

    @Override
    public void guardar(curso curso) {
        List<curso> lista = listar();
        lista.add(curso);
        guardar(lista);
    }

    @Override
    public Optional<curso> buscarPorId(int id) {
        return listar().stream().filter(c -> c.getId() == id).findFirst();
    }

    @Override
    public boolean actualizar(curso curso) {
        List<curso> lista = listar();
        for (curso c : lista) {
            if (c.getId() == curso.getId()) {
                c.setNombre(curso.getNombre());
                guardar(lista);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        List<curso> lista = listar();
        boolean eliminado = lista.removeIf(c -> c.getId() == id);
        if (eliminado) {
            guardar(lista);
        }
        return eliminado;
    }

}
