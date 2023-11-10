package sessions;

import dao.IDao;
import dao.IDaoLocal;
import entities.Filiere;
import entities.Student;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless(name = "FiliereDao")
public class FiliereService implements IDao<Filiere>/*,IDaoLocal<Filiere>*/ {

    @PersistenceContext
    private EntityManager em;

    @Override
    @PermitAll
    public Filiere create(Filiere filiere) {
        em.persist(filiere);
        return filiere;
    }

    @Override
    @PermitAll
    public Filiere delete(Filiere filiere) {
        Filiere managedFiliere = em.find(Filiere.class, filiere.getId());
        if (managedFiliere != null) {
            em.remove(managedFiliere);
        }
        return managedFiliere;
    }

    @Override
    @PermitAll
    public Filiere update(Filiere filiere) {
        return em.merge(filiere);
    }

    @Override
    @PermitAll
    public Filiere findById(int id) {
        return em.find(Filiere.class, id);
    }

    
    @Override
	@PermitAll
	public List<Filiere> findAll() {
		Query query = em.createQuery("select e from Filiere e");
		return query.getResultList();
	}
    // Method to retrieve students for a specific Filiere
    @PermitAll
    public List<Student> findStudentsByFiliere(Filiere filiere) {
        Filiere managedFiliere = em.find(Filiere.class, filiere.getId());

        if (managedFiliere != null) {
            return managedFiliere.getStudents();
        }

        return null; // Filiere not found or no students associated
    }
}
