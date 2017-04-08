package com.bakalarka1.controllers;

import com.bakalarka1.model.Appliance;
import com.bakalarka1.model.Household;
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
 * Created by Martin on 07.04.2017.
 */
@Controller
public class UserController {


    @Autowired
    private UserService userService;




    @RequestMapping(value={"/home"}, method = RequestMethod.GET)
    public ModelAndView logged(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }


    @RequestMapping("/add_household")
    public void addHousehold(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth.getPrincipal()=="anonymousUser")) {

            User user = userService.findUserByEmail(auth.getName());
            System.out.println(user.getId());
        }

        /*modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");*/

    }



    @RequestMapping(value="/analyza", method = RequestMethod.GET)
    public ModelAndView analyza(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        Household household = new Household();
        Appliance appliance =new Appliance();
        modelAndView.addObject("user", user);
        modelAndView.addObject("household", household);
        modelAndView.addObject("appliance", appliance);
        modelAndView.setViewName("analyza");
        return modelAndView;
    }


    @RequestMapping(value = "/analyza", method = RequestMethod.POST)
    public ModelAndView add_household(@Valid Household household,@Valid Appliance appliance, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());


        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("analyza");
        }
        else {
            userService.addHousehold(user,household,appliance);
            modelAndView.addObject("successMessage", "Analyza je ulozena");
            modelAndView.addObject("user", new User());
            modelAndView.addObject("appliance", new Appliance());
            modelAndView.addObject("user", new Household());
            modelAndView.setViewName("analyza");

        }
        return modelAndView;
    }
}
