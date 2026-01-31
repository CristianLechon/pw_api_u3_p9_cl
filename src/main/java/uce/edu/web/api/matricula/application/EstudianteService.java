package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> listarTodos() {
        List<EstudianteRepresentation> list = new ArrayList<>();

        for (Estudiante estu: this.estudianteRepository.listAll()) {
            list.add(this.mapperToER(estu));
        }
        return list;
    }

    public EstudianteRepresentation consultarPorId(Integer id) {
        return this.mapperToER(estudianteRepository.findById(id.longValue()));
    }

    @Transactional
    public void crear(EstudianteRepresentation estu) {
        this.estudianteRepository.persist(this.mapperToEstudiante(estu));
    }

    @Transactional
     public void actualizar(Integer id, EstudianteRepresentation est) {
        Estudiante estu = this.mapperToEstudiante(this.consultarPorId(id));
        estu.setApellido(est.getApellido());
        estu.setNombre(est.getNombre());
        estu.setFechaNacimiento(est.getFechaNacimiento());
        estu.setProvincia(est.getProvincia());
        estu.setGenero(est.getGenero());
        this.estudianteRepository.getEntityManager().merge(estu);
        // Se actualiza automáticamente por dirty checking
    }

     @Transactional
    public void actualizarParcial(Integer id, EstudianteRepresentation est) {
        Estudiante estu = this.mapperToEstudiante(this.consultarPorId(id));
        if (est.getNombre() != null) {
            estu.setNombre(est.getNombre());
        }

        if (est.getApellido() != null) {
            estu.setApellido(est.getApellido());
        }

        if (est.getFechaNacimiento() != null) {
            estu.setFechaNacimiento(est.getFechaNacimiento());
        }
        if (est.getProvincia() != null) {
            estu.setProvincia(est.getProvincia());
        }
        if (est.getGenero() != null) {
            estu.setGenero(est.getGenero());
        }
        this.estudianteRepository.getEntityManager().merge(estu);
    }

    @Transactional
    public void eliminar(Integer id) {
        this.estudianteRepository.deleteById(id.longValue());
    }

    public List<EstudianteRepresentation> buscarPorProvincia(String provincia, String genero) {
        //return this.estudianteRepository.find("provincia", provincia).list();
        List<EstudianteRepresentation> list = new ArrayList<>();

        for (Estudiante estu: this.estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero).list()) {
            list.add(this.mapperToER(estu));
        }
        return list;
    }

    private EstudianteRepresentation mapperToER(Estudiante est){
        EstudianteRepresentation estuR = new EstudianteRepresentation();
        estuR.setId(est.getId());
        estuR.setNombre(est.getNombre());
        estuR.setApellido(est.getApellido());
        estuR.setFechaNacimiento(est.getFechaNacimiento());
        estuR.setGenero(est.getGenero());
        estuR.setProvincia(est.getProvincia());

        return estuR;
    }

    private Estudiante mapperToEstudiante(EstudianteRepresentation est) {
        Estudiante estuR = new Estudiante();
        estuR.setId(est.getId());
        estuR.setNombre(est.getNombre());
        estuR.setApellido(est.getApellido());
        estuR.setFechaNacimiento(est.getFechaNacimiento());
        estuR.setGenero(est.getGenero());
        estuR.setProvincia(est.getProvincia());

        return estuR;
    }

}
