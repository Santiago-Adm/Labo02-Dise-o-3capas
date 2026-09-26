package org.example.busines;
import org.example.data.estudianteRepository;
import java.util.List;

public class estudianteService {
    private final estudianteRepository repository;

    public estudianteService(){
        repository=new estudianteRepository();
    }

    public void registrar(estudiante estudiante){
        List<estudiante>estudiantes= repository.listar();
        estudiantes.add(estudiante);
        repository.guardar(estudiantes);
    }

    public List<estudiante> listar(){
        return repository.listar();
    }

    public Boolean actualizar(estudiante estudiante) {
        List<estudiante> estudiantes=repository.listar();
        for(estudiante e:estudiantes){
            if(e.getId()==estudiante.getId()){
                e.setNombre(estudiante.getNombre());
                e.setCorreo(estudiante.getCorreo());
                repository.guardar(estudiantes);
                return true;
            }
        }
        return false;
    }

    public Boolean eliminar(int id) {
        List<estudiante> estudiantes=repository.listar();
        boolean eliminado=estudiantes.removeIf(e->e.getId()==id);
        if (eliminado){
            repository.guardar(estudiantes);
            }
        return eliminado;
    }
}
