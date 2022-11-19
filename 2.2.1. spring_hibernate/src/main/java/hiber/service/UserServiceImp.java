package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    public User getCarUser(String model, int series) {

        String hql = "from Car where model = :param and series = :param2";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("param", model).setParameter("param2", series);

        Car car = (Car) query.getSingleResult();
        return car.getUser();
    }
}
