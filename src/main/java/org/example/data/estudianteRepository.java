package org.example.data;
import org.example.busines.estudiante;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class estudianteRepository {
    private final String archivo="data/estudiantes.js";
    private final Gson gson=new Gson();

    public List <estudiante> listar(){
        try(Reader reader= new FileReader(archivo)) {
            Type tipo=new TypeToken <List <estudiante>>() {}.getType();
            List<estudiante> estudiantes=gson.fromJson(reader,tipo);
            return estudiantes!=null? estudiantes: new ArrayList<>();
        } catch (Exception e){
            return new ArrayList<>();
        }
    }

    public void guardar(List<estudiante> estudiantes){
        try(Writer writer=new FileWriter(archivo)) {
            gson.toJson(estudiantes,writer);
        } catch (Exception e) {
            System.out.println("Error al guardar Estudiantes");
        }
    }
}
