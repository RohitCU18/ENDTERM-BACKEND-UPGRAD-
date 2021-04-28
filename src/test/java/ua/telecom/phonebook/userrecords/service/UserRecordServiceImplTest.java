package ua.telecom.phonebook.userrecords.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.telecom.phonebook.AbstractServiceTest;
import ua.telecom.phonebook.userrecords.model.UserRecord;
import ua.telecom.phonebook.util.exception.NotFoundException;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static ua.telecom.phonebook.UserRecordTestData.*;
import static ua.telecom.phonebook.UserTestData.USER_NASTYA;
import static ua.telecom.phonebook.UserTestData.USER_SERGIY;

/**
 * Created on 05.09.2017.
 *
 * @author Sergiy Dyrda
 */

//@Transactional
public class UserRecordServiceImplTest extends AbstractServiceTest {

    @Autowired
    private UserRecordService userRecordService;

    @Test
    public void get() throws Exception {
        assertEquals(USER_RECORD_SERGIY_1, userRecordService.get(USER_RECORD_SERGIY_1.getId(), USER_SERGIY.getId()));
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        userRecordService.get(USER_RECORD_SERGIY_1.getId(), USER_NASTYA.getId());
    }

    @Test
    public void delete() throws Exception {
        userRecordService.delete(USER_RECORD_NASTIA_2.getId(), USER_NASTYA.getId());
        assertEquals(Arrays.asList(USER_RECORD_NASTIA_1), userRecordService.getAll(USER_NASTYA.getId()));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        userRecordService.delete(USER_RECORD_SERGIY_2.getId(), USER_NASTYA.getId());
    }

    @Test
    public void getAll() throws Exception {
        assertEquals(Arrays.asList(USER_RECORD_NASTIA_1, USER_RECORD_NASTIA_2), userRecordService.getAll(USER_NASTYA.getId()));
        assertEquals(Arrays.asList(USER_RECORD_SERGIY_1, USER_RECORD_SERGIY_2), userRecordService.getAll(USER_SERGIY.getId()));
    }

    @Test
    public void update() throws Exception {
        userRecordService.update(getUpdatedURSergiy_1(), USER_SERGIY.getId());
        assertEquals(Arrays.asList(getUpdatedURSergiy_1(), USER_RECORD_SERGIY_2), userRecordService.getAll(USER_SERGIY.getId()));
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        userRecordService.update(getUpdatedURSergiy_1(), USER_NASTYA.getId());
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFoundById() throws Exception {
        UserRecord updatedURSergiy_1 = getUpdatedURSergiy_1();
        updatedURSergiy_1.setId(200);
        userRecordService.update(updatedURSergiy_1, USER_SERGIY.getId());
    }

    @Test
    public void save() throws Exception {
        UserRecord saved = userRecordService.save(getCreated(), USER_NASTYA.getId());
        assertEquals(Arrays.asList(USER_RECORD_NASTIA_1, USER_RECORD_NASTIA_2, saved), userRecordService.getAll(USER_NASTYA.getId()));
    }

}