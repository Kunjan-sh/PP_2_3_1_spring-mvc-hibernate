package web.service;

import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(String name, String surname, int age) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setAge(age);
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(int id, String name, String surname, Integer age) {
        User user = userDao.getUser(id);
        if (user != null) {
            if (name != null && !name.isEmpty()) {
                user.setName(name);
            }
            if (surname != null && !surname.isEmpty()) {
                user.setSurname(surname);
            }
            if (age != null) {
                user.setAge(age);
            }
        }
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return userDao.getUsers();
    }
}
