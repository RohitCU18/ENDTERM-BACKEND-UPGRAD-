package ua.telecom.phonebook;

import ua.telecom.phonebook.userrecords.model.UserRecord;

import static ua.telecom.phonebook.UserTestData.USER_NASTYA;
import static ua.telecom.phonebook.UserTestData.USER_SERGIY;
import static ua.telecom.phonebook.base.BaseEntity.START_SEQ;

/**
 * Created on 05.09.2017.
 *
 * @author Sergiy Dyrda
 */
public class UserRecordTestData {

    public static final UserRecord USER_RECORD_SERGIY_1 = new UserRecord(START_SEQ + 2,
            "Name11", "Surname11",
            "+38(095)123-45-67", "name11@mail.com");

    public static final UserRecord USER_RECORD_SERGIY_2 = new UserRecord(START_SEQ + 3,
            "Name12", "Surname12",
            "+38(095)987-65-43", "name12@mail.com");

    static {
        USER_RECORD_SERGIY_1.setUser(USER_SERGIY);
        USER_RECORD_SERGIY_2.setUser(USER_SERGIY);
    }

    public static final UserRecord USER_RECORD_NASTIA_1 = new UserRecord(START_SEQ + 4,
            "Name21", "Surname21",
            "+38(095)645-49-71", "name21@mail.com");


    public static final UserRecord USER_RECORD_NASTIA_2 = new UserRecord(START_SEQ + 5,
            "Name22", "Surname22",
            "+38(095)432-21-46", "name22@mail.com");

    static {
        USER_RECORD_NASTIA_1.setUser(USER_NASTYA);
        USER_RECORD_NASTIA_2.setUser(USER_NASTYA);
    }

    public static UserRecord getUpdatedURSergiy_1() {
        UserRecord userRecord = new UserRecord(USER_RECORD_SERGIY_1.getId(), USER_RECORD_SERGIY_1.getName(), USER_RECORD_SERGIY_1.getSurName(), USER_RECORD_SERGIY_1.getPhoneNumber(), USER_RECORD_SERGIY_1.getEmail());
        userRecord.setUser(USER_SERGIY);
        return userRecord;
    }

    public static UserRecord getCreated() {
        return new UserRecord("New record name", "New record surname", "+38(095)138-94-24", "new@mail.com");
    }
}
