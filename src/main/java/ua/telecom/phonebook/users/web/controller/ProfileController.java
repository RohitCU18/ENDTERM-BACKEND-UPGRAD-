package ua.telecom.phonebook.users.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.telecom.phonebook.users.AuthorizedUser;
import ua.telecom.phonebook.users.model.User;
import ua.telecom.phonebook.users.model.dto.UserPasswordDto;
import ua.telecom.phonebook.users.model.dto.UserTo;
import ua.telecom.phonebook.util.AuthenticationUtil;
import ua.telecom.phonebook.util.PasswordUtil;
import ua.telecom.phonebook.util.ValidationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.ValidationException;

import static ua.telecom.phonebook.util.UserUtil.prepareToSave;



@Controller
public class ProfileController extends AbstractUserController {

    @Autowired
    private PersistentTokenBasedRememberMeServices rememberMeServices;

    @GetMapping("/profile")
    public ModelAndView profile() {
        ModelAndView view = new ModelAndView();
        view.addObject("userTo", AuthorizedUser.get().getUserTo());
        view.setViewName("profile/profile");
        return view;
    }

    @PostMapping("/profile")
    public ModelAndView updateProfile(@Valid UserTo userTo, BindingResult bindingResult) {
        userTo.setPassword(AuthorizedUser.get().getUserTo().getPassword());
        ModelAndView view = createUpdate(userTo, bindingResult, "registration/successful", "profile/profile", false);
        view.addObject("updated", true);
        AuthorizedUser.get().update(userTo);
        return view;
    }

    @PostMapping("/profile/password")
    public ResponseEntity<String> changePassword(@Valid UserPasswordDto passwordDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(ValidationUtil.getErrorResponse(bindingResult));
        }

        User user = userService.get(AuthorizedUser.id());
        String storedPassword = user.getPassword();
        if (!PasswordUtil.comparePassword(passwordDto.getCurrentPassword(), storedPassword)) {
            throw new ValidationException("Your password is incorrect!");
        }

        user.setPassword(passwordDto.getNewPassword());
        userService.update(prepareToSave(user));
        AuthorizedUser.get().getUserTo().setPassword(passwordDto.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/profile")
    public String deleteAccount(HttpServletRequest request, HttpServletResponse response) {
        userService.delete(AuthorizedUser.id());
        AuthenticationUtil.manualLogout(request, response, rememberMeServices);
        return "home";
    }
}
