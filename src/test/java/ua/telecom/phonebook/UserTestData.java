package ua.telecom.phonebook;

import ua.telecom.phonebook.users.model.Role;
import ua.telecom.phonebook.users.model.User;

import static ua.telecom.phonebook.users.model.User.START_SEQ;

/**
 * Created on 29.08.2017.
 *
 * @author Sergiy Dyrda
 */

public class UserTestData {

    public static final User USER_SERGIY = new User(START_SEQ, "Sergiy", "Dyrda", "+38(068)044-11-27", "user_sergiy", Role.USER);
    public static final User USER_NASTYA = new User(START_SEQ + 1, "Anastasia", "Kropyva", "+38(097)287-67-37", "anasteisha", Role.USER);

    
}
