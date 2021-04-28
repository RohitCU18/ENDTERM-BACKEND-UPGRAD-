package ua.telecom.phonebook.users.service;


import ua.telecom.phonebook.users.model.User;
import ua.telecom.phonebook.util.exception.NotFoundException;

import java.util.List;


public interface UserService {

    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByPhoneNumber(String phoneNumber) throws NotFoundException;

    void update(User user);

    List<User> getAll();
}