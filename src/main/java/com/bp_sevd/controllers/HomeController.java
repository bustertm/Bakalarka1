package com.bp_sevd.controllers;

import com.bp_sevd.model.User;
import com.bp_sevd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


/**
 * Created by Martin on 28.03.2017.
 */
@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    /***********************************HOME PAGE***************************************/

    @RequestMapping(value={"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView guest() {

        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth.getPrincipal()=="anonymousUser")){
            modelAndView.setViewName("home");
            return modelAndView;
        }
        else {

            modelAndView.setViewName("index");
            return modelAndView;
        }
    }

    /***********************************REGISTRACIA******************************************/

    @RequestMapping(value="/registracia", method = RequestMethod.GET)
    public ModelAndView registration(){

        ModelAndView modelAndView = new ModelAndView();
        User user = new User();

        modelAndView.addObject("user", user);
        modelAndView.setViewName("registracia");

        return modelAndView;
    }


    @RequestMapping(value = "/registracia", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());

        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Používateľ so zadanou emailovou adresou, už existuje.");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registracia");
        }
        else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Používateľ bol úspešne registrovaný.");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registracia");

        }
        return modelAndView;
    }
}
