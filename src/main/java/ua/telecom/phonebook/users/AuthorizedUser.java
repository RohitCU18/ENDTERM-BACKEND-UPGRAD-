package ua.telecom.phonebook.users;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.telecom.phonebook.users.model.User;
import ua.telecom.phonebook.users.model.dto.UserTo;
import ua.telecom.phonebook.util.UserUtil;

import static java.util.Objects.requireNonNull;



public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    private UserTo userTo;

    public AuthorizedUser(User user) {
        super(user.getPhoneNumber(),
                user.getPassword(),
                true, true,
                true, true, user.getRoles());
        this.userTo = UserUtil.asTo(user);
    }


    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public UserTo getUserTo() {
        return userTo;
    }

    public void update(UserTo newTo) {
        userTo = newTo;
    }

    public static int id() {
        return get().getUserTo().getId();
    }
}
