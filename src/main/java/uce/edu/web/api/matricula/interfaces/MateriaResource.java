package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    @GET
    @Path("/todos")
    public List<Materia> listarTodos() {
        return this.materiaService.listarTodos();
    }

    @GET
    @Path("/consultarPorId/{id}")
    public Materia consultarPorId(@PathParam("id") Long id){
        return this.materiaService.consultarPorId(id);
    }

    @POST
    @Path("/crear")
    public void guardar(Materia materia) {
        this.materiaService.crear(materia);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizar(@PathParam("id") Long id, Materia materia){
        this.materiaService.actualizar(materia, id);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarParcial(@PathParam("id") Long id, Materia materia){
        this.materiaService.actualizarParcial(materia, id);
    }

    @DELETE
    @Path("/borrar/{id}")
    public void borrar(@PathParam("id") Long id){
        this.materiaService.eliminar(id);
    }

    @GET
    @Path("/buscarPorNombre/{nombre}")
    public Materia buscarPorNombre(@PathParam("nombre") String nombre){
        return this.materiaService.buscarPorNombre(nombre);
    }

    @DELETE
    @Path("/borrarPorSemestre/{semestre}")
    public Integer borrarPorSemestre(@PathParam("semestre") String semestre){
        return this.materiaService.borrarPorSemestre(semestre);
    }

}
