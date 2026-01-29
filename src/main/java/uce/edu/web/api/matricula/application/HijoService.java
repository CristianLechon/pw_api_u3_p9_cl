package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.matricula.application.representation.HijoRepresentation;
import uce.edu.web.api.matricula.domain.Hijo;
import uce.edu.web.api.matricula.infraestructure.HijoRepository;

@ApplicationScoped
public class HijoService {

    @Inject
    private HijoRepository hijoRepository;

    public List<HijoRepresentation> buscarPorIdEstudiante(Integer idEstudiante){
        List<HijoRepresentation> lista = new ArrayList<>();
        for(Hijo h: this.hijoRepository.buscarPorIdEstudiante(idEstudiante)){
            lista.add(this.mapperToR(h));
        }
        return lista;
    }

    private HijoRepresentation mapperToR(Hijo hijo){
        HijoRepresentation hr = new HijoRepresentation();

        hr.id = hijo.id;
        hr.nombre = hijo.nombre;
        hr.apellido = hijo.apellido;
        return hr;
    }
}
