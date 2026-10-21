package org.example.infrastructure.adapter.out.persistence;

import org.example.domain.model.estudiante;
import org.example.domain.repository.estudianteRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class estudianteRepositoryJson implements estudianteRepository {

    private final String archivo="data/estudiantes.js";
    private final Gson gson=new Gson();

    @Override
    public List<estudiante> listar(){
        try(Reader reader= new FileReader(archivo)) {
            Type tipo=new TypeToken<List <estudiante>>() {}.getType();
            List<estudiante> estudiantes=gson.fromJson(reader,tipo);
            return estudiantes!=null? estudiantes: new ArrayList<>();
        } catch (Exception e){
            return new ArrayList<>();
        }
    }

    @Override
    public void guardar(List<estudiante> estudiantes){
        try(Writer writer=new FileWriter(archivo)) {
            gson.toJson(estudiantes,writer);
        } catch (Exception e) {
            System.out.println("Error al guardar Estudiantes");
        }
    }

    @Override
    public void guardar(estudiante estudiante) {
        List<estudiante> lista = listar();
        lista.add(estudiante);
        guardar(lista);

    }

    @Override
    public Optional<estudiante> buscarPorId(int id) {
        return listar().stream().filter(e -> e.getId() == id).findFirst();
    }

    @Override
    public boolean actualizar(estudiante estudiante) {
        List<estudiante> lista = listar();
        for (estudiante e : lista) {
            if (e.getId() == estudiante.getId()) {
                e.setNombre(estudiante.getNombre());
                e.setCorreo(estudiante.getCorreo());
                guardar(lista);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        List<estudiante> lista = listar();
        boolean eliminado = lista.removeIf(e -> e.getId() == id);
        if (eliminado) {
            guardar(lista);
        }
        return false;
    }

}
