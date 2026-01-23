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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.domain.Estudiante;

@Path("/estudiantes")
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;

    @GET
    @Path("")
    public List<Estudiante> listarTodos() {
        List<Estudiante> est = this.estudianteService.listarTodos();
        System.out.println("Listar todos XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        return est;
        
    }

    @GET
    @Path("/provincia/genero")
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia") String provincia, @QueryParam("genero") String genero){
        System.out.println("Listar por provincia y genero XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        return this.estudianteService.buscarPorProvincia(provincia, genero);
    }

    @GET
    @Path("/{id}")
    public Estudiante consultarPorId(@PathParam("id") Integer iden) { //esto es un Path variable
        return this.estudianteService.consultarPorId(iden);
    }

    @POST
    @Path("")
    public Response guardar (Estudiante estu){
        this.estudianteService.crear(estu);
        return Response.status(Response.Status.CREATED).entity(estu).build();
    }

    @PUT
    @Path("/{id}")
    public void actualizar(@PathParam("id") Integer id, Estudiante estu){
        this.estudianteService.actualizar(id, estu);
    }

    @PATCH
    @Path("/{id}")
    public Response actualizarParcial(@PathParam("id") Integer id, Estudiante estu){
        this.estudianteService.actualizarParcial(id, estu);
        return Response.status(209).entity(estu).build();
    }

    @DELETE
    @Path("/{id}")
    public void borrar(@PathParam("id") Integer id){
        this.estudianteService.eliminar(id);
    }

}
