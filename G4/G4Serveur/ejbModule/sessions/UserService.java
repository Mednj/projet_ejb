package sessions;

import dao.IDao;
import dao.IDaoLocal;
import entities.Role;
import entities.User;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

@Stateless(name = "UserDao")
public class UserService implements IDao<User>/*,IDaoLocal<User> */{

    @PersistenceContext
    private EntityManager em;

    @Override
    @PermitAll
    public User create(User user) {
        em.persist(user);
        return user;
    }

    @Override
    @PermitAll
    public User delete(User user) {
        User managedUser = em.find(User.class, user.getId());
        if (managedUser != null) {
            em.remove(managedUser);
        }
        return managedUser;
    }

    @Override
    @PermitAll
    public User update(User user) {
        return em.merge(user);
    }

    @Override
    @PermitAll
    public User findById(int id) {
        return em.find(User.class, id);
    }

    @Override
    @PermitAll
    public List<User> findAll() {
        Query query = em.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }
    
    // Method to add a role to a user
    @PermitAll
    public User addRoleToUser(User user, Role role) {
        User managedUser = em.find(User.class, user.getId());
        Role managedRole = em.find(Role.class, role.getId());

        if (managedUser != null && managedRole != null) {
            // Establish the many-to-many relationship
            managedUser.getRoles().add(managedRole);
            managedRole.getUsers().add(managedUser);
        }

        return managedUser;
    }
}
