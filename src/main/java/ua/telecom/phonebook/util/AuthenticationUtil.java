package ua.telecom.phonebook.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import ua.telecom.phonebook.users.AuthorizedUser;
import ua.telecom.phonebook.users.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationUtil {


    public static void manualLoginNewUser(User user) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(new AuthorizedUser(user), null, user.getRoles()));

    }

    public static void manualLogout(HttpServletRequest request, HttpServletResponse response, PersistentTokenBasedRememberMeServices rememberMeServices) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            rememberMeServices.logout(request, response, auth);
            new SecurityContextLogoutHandler().logout(request, response, auth);

        }
    }
}
