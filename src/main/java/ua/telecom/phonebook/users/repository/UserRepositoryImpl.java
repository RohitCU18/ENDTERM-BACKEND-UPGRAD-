package ua.telecom.phonebook.users.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ua.telecom.phonebook.users.model.User;

import java.util.List;



@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final Sort SORT_NAME_SURNAME = new Sort("name", "surName");
    private final CrudUserRepository repository;

    @Autowired
    public UserRepositoryImpl(CrudUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return repository.findOne(id);
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll(SORT_NAME_SURNAME);
    }
}
