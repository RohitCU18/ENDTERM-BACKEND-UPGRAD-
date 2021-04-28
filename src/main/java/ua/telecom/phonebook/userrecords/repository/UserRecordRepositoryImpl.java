package ua.telecom.phonebook.userrecords.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.telecom.phonebook.userrecords.model.UserRecord;
import ua.telecom.phonebook.users.repository.CrudUserRepository;

import java.util.List;


@Repository
public class UserRecordRepositoryImpl implements UserRecordRepository {

    private final CrudUserRecordRepository crudUserRecordRepository;

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public UserRecordRepositoryImpl(CrudUserRecordRepository crudUserRecordRepository, CrudUserRepository crudUserRepository) {
        this.crudUserRecordRepository = crudUserRecordRepository;
        this.crudUserRepository = crudUserRepository;
    }

    public UserRecord save(UserRecord userRecord, int userId) {
        if (!userRecord.isNew() && get(userRecord.getId(), userId) == null) {
            return null;
        }
        userRecord.setUser(crudUserRepository.getOne(userId));
        return crudUserRecordRepository.save(userRecord);
    }

    public boolean delete(int id, int userId) {
        return crudUserRecordRepository.delete(id, userId) != 0;
    }

    public UserRecord get(int id, int userId) {
        UserRecord userRecord = crudUserRecordRepository.findOne(id);
        return userRecord != null && userRecord.getUser().getId() == userId ? userRecord : null;
    }

    public List<UserRecord> getAll(int userId) {
        return crudUserRecordRepository.findByUserId(userId);
    }
}
