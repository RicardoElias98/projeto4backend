package dao;

import entities.UserEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UserDao extends AbstractDao<UserEntity>{
    @PersistenceContext
    private EntityManager em;
    private static final long serialVersionUID = 1L;
    public UserDao() {
        super(UserEntity.class);
    }
    public UserEntity findUserByToken(String token) {
        try {
            return (UserEntity) em.createNamedQuery("User.findUserByToken").setParameter("token", token)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }
    public UserEntity findUserByUsername(String username) {
        try {
            return (UserEntity) em.createNamedQuery("User.findUserByUsername").setParameter("username", username)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }
    public void updateToken(UserEntity userEntity) {
        em.createNamedQuery("User.updateToken").setParameter("token", userEntity.getToken()).setParameter("username",userEntity.getName()).executeUpdate();
    }
    public void updateUser(UserEntity userEntity) {
        em.merge(userEntity);
    }
}
