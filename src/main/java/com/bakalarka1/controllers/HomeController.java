package com.bakalarka1.controllers;

import com.bakalarka1.model.User;
import com.bakalarka1.service.UserService;
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



    @RequestMapping(value={"/investicia"}, method = RequestMethod.GET)
    public ModelAndView investicia(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("investicia");
        return modelAndView;
    }


   // @RequestMapping("/analyza")
   // public String analyza(){
  //      return "analyza";
  //  }

   /* @RequestMapping(value="/analyza", method = RequestMethod.GET)
        public ModelAndView analyza(){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("analyza");
            return modelAndView;
    }*/

    /*******************************************************************/

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
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registracia");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registracia");

        }
        return modelAndView;
    }
}
