package ua.telecom.phonebook.userrecords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ua.telecom.phonebook.userrecords.model.UserRecord;
import ua.telecom.phonebook.userrecords.repository.UserRecordRepository;
import ua.telecom.phonebook.util.exception.NotFoundException;

import java.util.List;

import static ua.telecom.phonebook.util.ValidationUtil.checkNotFound;
import static ua.telecom.phonebook.util.ValidationUtil.checkNotFoundWithId;



@Service
public class UserRecordServiceImpl implements UserRecordService {

    private final UserRecordRepository userRecordRepository;

    @Autowired
    public UserRecordServiceImpl(UserRecordRepository userRecordRepository) {
        this.userRecordRepository = userRecordRepository;
    }


    public UserRecord get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(userRecordRepository.get(id, userId), id);
    }

    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(userRecordRepository.delete(id, userId), id);
    }

    public List<UserRecord> getAll(int userId) {
        return userRecordRepository.getAll(userId);
    }

    public void update(UserRecord userRecord, int userId) throws NotFoundException {
        Assert.notNull(userRecord, "UserRecord must not be null");
        checkNotFound(userRecordRepository.save(userRecord, userId),
                userRecord.getId() + "id -> Cannot update entity with id=" + userRecord.getId() + " for user with id=" + userId);
    }

    public UserRecord save(UserRecord userRecord, int userId) {
        Assert.notNull(userRecord, "UserRecord must not be null");
        return checkNotFound(userRecordRepository.save(userRecord, userId),
                userRecord.getId() + "id -> Cannot save entity with id=" + userRecord.getId() + " for user with id=" + userId);
    }
}
