package ua.telecom.phonebook.users.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ua.telecom.phonebook.AbstractServiceTest;
import ua.telecom.phonebook.users.model.Role;
import ua.telecom.phonebook.users.model.User;
import ua.telecom.phonebook.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static ua.telecom.phonebook.UserTestData.USER_NASTYA;
import static ua.telecom.phonebook.UserTestData.USER_SERGIY;

/**
 * Created on 29.08.2017.
 *
 * @author Sergiy Dyrda
 */

public class UserServiceImplTest extends AbstractServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void save() throws Exception {
        User saved = userService.save(new User("NewName", "NewSurName", "+380645123458", "password", Role.USER));
        assertEquals(Arrays.asList(USER_NASTYA, saved, USER_SERGIY), userService.getAll());
    }

    @Test
    public void delete() throws Exception {
        userService.delete(USER_NASTYA.getId());
        assertEquals(Collections.singletonList(USER_SERGIY), userService.getAll());
    }

    @Test
    public void get() throws Exception {
        assertEquals(USER_NASTYA, userService.get(USER_NASTYA.getId()));
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        userService.get(5);
    }

    @Test
    public void getByPhoneNumber() throws Exception {
        assertEquals(USER_SERGIY, userService.getByPhoneNumber(USER_SERGIY.getPhoneNumber()));
    }

    @Test(expected = NotFoundException.class)
    public void getByPhoneNumberNotFound() throws Exception {
        userService.getByPhoneNumber(USER_SERGIY.getPhoneNumber() + "34");
    }

    @Test
    public void update() throws Exception {
        User updatedNastya = new User(USER_NASTYA.getId(), "New Name", "Surname", USER_NASTYA.getPhoneNumber(), "passsswordd@", Role.USER);
        userService.update(updatedNastya);
        assertEquals(Arrays.asList(updatedNastya, USER_SERGIY), userService.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void updateDuplicateNumber() throws Exception {
        User updatedNastya = new User(10, "New Name", "Surname", USER_NASTYA.getPhoneNumber(), "passsswordd@", Role.USER);
        userService.update(updatedNastya);
    }

}