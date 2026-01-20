package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infraestructure.MateriaRepository;

@ApplicationScoped
public class MateriaService {

    @Inject
    private MateriaRepository materiaRepository;

    public List<Materia> listarTodos(){
        return this.materiaRepository.listAll();
    }

    public Materia consultarPorId(Long id){
        return this.materiaRepository.findById(id);
    }

    @Transactional
    public void crear(Materia materia){
        this.materiaRepository.persist(materia);
    }

    @Transactional
    public void actualizar(Materia materia, Long id){
        Materia mate = this.consultarPorId(id);

        mate.setNombreMateria(materia.getNombreMateria());
        mate.setFechaInicio(materia.getFechaInicio());
        mate.setCreditos(materia.getCreditos());
        mate.setSemestre(materia.getSemestre());
    }

    @Transactional
    public void actualizarParcial(Materia materia, Long id){
        Materia mate = this.consultarPorId(id);

        if (materia.getNombreMateria() != null) {
            mate.setNombreMateria(materia.getNombreMateria());
        }
        if (materia.getFechaInicio() != null) {
            mate.setFechaInicio(materia.getFechaInicio());
        }
        if (materia.getSemestre() != null) {
            mate.setSemestre(materia.getSemestre());
        }
        if (materia.getCreditos() != null) {
            mate.setCreditos(materia.getCreditos());
        }
    }

    @Transactional
    public void eliminar(Long id){
        this.materiaRepository.deleteById(id);
    }

    public Materia buscarPorNombre(String nombre){
        return this.materiaRepository.buscarPorNombre(nombre);
    }

    @Transactional
    public Integer borrarPorSemestre(String semestre){
        return this.materiaRepository.borrarPorSemestre(semestre);
    } 
}
