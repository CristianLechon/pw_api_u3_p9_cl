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
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Materia> listarTodos() {
        List<Materia> mate = this.materiaService.listarTodos();
        return mate;
    }

    @GET
    @Path("/{id}")
    //@Produces(MediaType.APPLICATION_XML)
    public Materia consultarPorId(@PathParam("id") Long id){
        return this.materiaService.consultarPorId(id);
    }

    @POST
    @Path("")
    public Response guardar(Materia materia) {
        this.materiaService.crear(materia);
        return Response.status(Response.Status.CREATED).entity(materia).build();
    }

    @PUT
    @Path("/{id}")
    public void actualizar(@PathParam("id") Long id, Materia materia){
        this.materiaService.actualizar(materia, id);
    }

    @PATCH
    @Path("/{id}")
    public Response actualizarParcial(@PathParam("id") Long id, Materia materia){
        this.materiaService.actualizarParcial(materia, id);
        return Response.status(209).entity(materia).build();
    }

    @DELETE
    @Path("/{id}")
    public void borrar(@PathParam("id") Long id){
        this.materiaService.eliminar(id);
    }

    @GET
    @Path("/nombre/{nombre}")
    public Materia buscarPorNombre(@PathParam("nombre") String nombre){
        return this.materiaService.buscarPorNombre(nombre);
    }

    @DELETE
    @Path("/{semestre}")
    public Integer borrarPorSemestre(@PathParam("semestre") String semestre){
        return this.materiaService.borrarPorSemestre(semestre);
    }

}
