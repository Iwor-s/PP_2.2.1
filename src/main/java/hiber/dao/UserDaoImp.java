package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      return sessionFactory.getCurrentSession()
              .createQuery("from User")
              .getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsersByCar(String model, int series) {
      return sessionFactory.getCurrentSession()
              .createQuery("SELECT u FROM User u JOIN u.car " +
                      "WHERE u.car.model = :model " +
                      "AND u.car.series = :series")
              .setParameter("model", model)
              .setParameter("series", series)
              .getResultList();
   }
}
