package sessions;

import dao.IDao;
import dao.IDaoLocal;
import entities.Role;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "RoleDao")
public class RoleService implements IDao<Role>/*,IDaoLocal<Role>*/ {

    @PersistenceContext
    private EntityManager em;

    @Override
    @PermitAll
    public Role create(Role role) {
        em.persist(role);
        return role;
    }

    @Override
    @PermitAll
    public Role delete(Role role) {
        Role managedRole = em.find(Role.class, role.getId());
        if (managedRole != null) {
            em.remove(managedRole);
        }
        return managedRole;
    }

    @Override
    @PermitAll
    public Role update(Role role) {
        return em.merge(role);
    }

    @Override
    @PermitAll
    public Role findById(int id) {
        return em.find(Role.class, id);
    }

    @Override
    @PermitAll
    public List<Role> findAll() {
        return em.createQuery("SELECT r FROM Role r").getResultList();
    }
}
