package uce.edu.web.api.matricula.infraestructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import uce.edu.web.api.matricula.domain.Materia;

@ApplicationScoped
public class MateriaRepository implements PanacheRepository<Materia> {

    public Materia buscarPorNombre(String nombreMateria) {
        try {
            TypedQuery<Materia> query = getEntityManager().createQuery("SELECT m FROM Materia m WHERE m.nombreMateria = :nombre", Materia.class);
            query.setParameter("nombre", nombreMateria);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Integer borrarPorSemestre(String semestre) {
        Query query = getEntityManager().createQuery("DELETE FROM Materia m WHERE m.semestre = :semestre");
        query.setParameter("semestre", semestre); 
        return query.executeUpdate();
    }
}
