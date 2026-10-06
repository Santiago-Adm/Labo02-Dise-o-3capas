package org.example.domain.repository;

import org.example.domain.model.curso;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class cursoRepository {
    private final String archivo = "data/cursos.json";
    private final Gson gson = new Gson();

    public List<curso> listar(){
        try (Reader reader = new FileReader(archivo)) {
            Type tipo = new TypeToken<List<curso>>(){}.getType();
            List<curso> cursos = gson.fromJson(reader, tipo);
            return cursos != null ? cursos : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void guardar(List<curso> cursos){
        try (Writer writer = new FileWriter(archivo)) {
            gson.toJson(cursos, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo");
        }
    }
}
