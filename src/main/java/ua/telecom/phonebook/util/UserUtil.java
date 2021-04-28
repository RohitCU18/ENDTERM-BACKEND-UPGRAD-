package ua.telecom.phonebook.util;

import ua.telecom.phonebook.users.model.Role;
import ua.telecom.phonebook.users.model.User;
import ua.telecom.phonebook.users.model.dto.UserTo;


public class UserUtil {

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getSurName(), user.getPhoneNumber(), user.getPassword(), user.getHomeNumber(), user.getAddress(), user.getEmail());
    }

    public static User getNewFromTo(UserTo userTo) {
        return new User(userTo.getId(), userTo.getName(), userTo.getSurName(), userTo.getPhone(), userTo.getHomeNumber(), userTo.getAddress(), userTo.getEmail(), userTo.getPassword(),  Role.USER);
    }

    public static User prepareToSave(User user) {
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        return user;
    }

}
