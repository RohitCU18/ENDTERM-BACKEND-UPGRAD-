package ua.telecom.phonebook.users.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import ua.telecom.phonebook.users.model.User;
import ua.telecom.phonebook.users.model.dto.UserTo;
import ua.telecom.phonebook.users.service.UserService;
import ua.telecom.phonebook.util.AuthenticationUtil;
import ua.telecom.phonebook.util.UserUtil;
import ua.telecom.phonebook.util.exception.NotFoundException;

import javax.validation.Valid;

import static ua.telecom.phonebook.util.UserUtil.prepareToSave;


abstract public class AbstractUserController {

    @Autowired
    protected UserService userService;

    protected void checkAlreadyExists(@Valid UserTo userTo, BindingResult bindingResult) {
        try {
            userService.getByPhoneNumber(userTo.getPhone());
            bindingResult
                    .rejectValue("phone", "error.user",
                            "*User with this phone number already registered");

        } catch (NotFoundException ignored) {
        }
    }

    protected ModelAndView createUpdate(UserTo userTo, BindingResult bindingResult, String successView, String failureView, boolean login) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(failureView);
            return modelAndView;
        }

        User newUser = UserUtil.getNewFromTo(userTo);
        User preparedNewUser = prepareToSave(newUser);
        userService.save(preparedNewUser);

        if (login) AuthenticationUtil.manualLoginNewUser(preparedNewUser);

        modelAndView.setViewName(successView);
        return modelAndView;
    }

}
