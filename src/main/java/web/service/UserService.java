package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void addUser(String name, String surname, int age);

    void updateUser(int id, String name, String surname, Integer age);

    void deleteUser(int id);

    List<User> getUsers();
}
