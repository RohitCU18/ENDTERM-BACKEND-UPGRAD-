package ua.telecom.phonebook.users.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.telecom.phonebook.users.model.dto.UserTo;

import javax.validation.Valid;



@Controller
public class RootController extends AbstractUserController {


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping({"/register"})
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userTo", new UserTo());
        modelAndView.setViewName("registration/registration");
        return modelAndView;
    }

    @PostMapping({"/register"})
    public ModelAndView registerNewUser(@Valid UserTo userTo, BindingResult bindingResult) {
        checkAlreadyExists(userTo, bindingResult);
        return createUpdate(userTo, bindingResult, "registration/successful", "registration/registration", true);

    }

    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping({"/access-denied"})
    public String accessDenied() {
        return "access-denied";
    }


}
