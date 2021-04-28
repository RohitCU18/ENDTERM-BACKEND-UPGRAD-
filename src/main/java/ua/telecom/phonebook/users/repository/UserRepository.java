package ua.telecom.phonebook.users.repository;

import ua.telecom.phonebook.users.model.User;

import java.util.List;


public interface UserRepository {

    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByPhoneNumber(String phoneNumber);

    List<User> getAll();
}
