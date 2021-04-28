package ua.telecom.phonebook.userrecords.repository;

import ua.telecom.phonebook.userrecords.model.UserRecord;

import java.util.List;


public interface UserRecordRepository {

    // null if updated user record do not belong to userId
    UserRecord save(UserRecord user, int userId);

    // false if user record do not belong to userId
    boolean delete(int id, int userId);

    // null if user record do not belong to userId
    UserRecord get(int id, int userId);

    // null if not found
    List<UserRecord> getAll(int userId);

}
