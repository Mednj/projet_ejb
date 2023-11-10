package sessions;

import java.util.List;


import dao.IDao;
import dao.IDaoLocal;
import entities.Student;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless (name = "G4EmS")
public class StudentService implements IDao<Student>,IDaoLocal<Student> {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@PermitAll
	public Student create(Student o) {
		em.persist(o);
		return o;
	}

	@Override
	@PermitAll
	public Student delete(Student o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PermitAll
	public Student update(Student o) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
    @PermitAll
    public Student findById(int id) {
        return em.find(Student.class, id);
    }

	@Override
	@PermitAll
	public List<Student> findAll() {
		Query query = em.createQuery("select e from Student e");
		return query.getResultList();
	}
	


}
