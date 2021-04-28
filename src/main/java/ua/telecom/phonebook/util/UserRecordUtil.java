package ua.telecom.phonebook.util;

import ua.telecom.phonebook.userrecords.model.UserRecord;
import ua.telecom.phonebook.userrecords.model.dto.UserRecordTo;

import java.util.List;
import java.util.stream.Collectors;


public class UserRecordUtil {

    public static List<UserRecordTo> getListUserRecordsTo(List<UserRecord> records) {
        return records.stream().map(UserRecordUtil::getUserRecordTo).collect(Collectors.toList());
    }

    public static UserRecordTo getUserRecordTo(UserRecord record) {
        return new UserRecordTo(record.getId(), record.getName(), record.getSurName(), record.getPhoneNumber(), record.getEmail());
    }

    public static UserRecord getUserRecord(UserRecordTo record) {
        return new UserRecord(record.getId(), record.getName(), record.getSurName(), record.getPhone(), record.getEmail());
    }
}
