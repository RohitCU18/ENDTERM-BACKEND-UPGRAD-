package ua.telecom.phonebook.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ua.telecom.phonebook.users.AuthorizedUser;
import ua.telecom.phonebook.users.model.User;
import ua.telecom.phonebook.users.repository.UserRepository;
import ua.telecom.phonebook.util.exception.NotFoundException;

import java.util.List;

import static ua.telecom.phonebook.util.ValidationUtil.checkNotFound;
import static ua.telecom.phonebook.util.ValidationUtil.checkNotFoundWithId;


@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) throws NotFoundException {
        Assert.notNull(phoneNumber, "phoneNumber must not be null");
        return checkNotFound(repository.getByPhoneNumber(phoneNumber), "phoneNumber=" + phoneNumber);
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        repository.save(user);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = repository.getByPhoneNumber(phoneNumber);
        if (user == null) {
            throw new UsernameNotFoundException("Not found user with phone number=" + phoneNumber);
        }

        return new AuthorizedUser(user);
    }
}