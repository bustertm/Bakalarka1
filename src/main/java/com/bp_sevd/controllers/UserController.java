package com.bp_sevd.controllers;

import com.bp_sevd.model.Appliance;
import com.bp_sevd.model.Household;
import com.bp_sevd.model.User;
import com.bp_sevd.model.production.FVE_configurations;
import com.bp_sevd.service.AdminService;
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
 * Created by Martin on 07.04.2017.
 */
@Controller
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    /*****************************HOME PAGE po prihlaseni******************************/

    @RequestMapping(value={"/home"}, method = RequestMethod.GET)
    public ModelAndView logged(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");

        return modelAndView;
    }

    /*************************************PROFIL****************************************/

    @RequestMapping(value={"/profil"}, method = RequestMethod.GET)
    public ModelAndView profil(){

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Household household;
        Appliance appliance;

        if(!user.getAnalysed()){
            household=new Household();
            appliance = new Appliance();
        }
        else{
            household= user.getHousehold();
            appliance = household.getAppliance();
        }

        modelAndView.addObject("user",user);
        modelAndView.addObject("household",household);
        modelAndView.addObject("appliance",appliance);
        modelAndView.setViewName("profil");

        return modelAndView;
    }

    /*****************************PRIDAVANIE FVE KONFIGURACII****************************/

    @RequestMapping(value={"/admin/fve"}, method = RequestMethod.GET)
    public ModelAndView fve_form(){

        ModelAndView modelAndView = new ModelAndView();
        FVE_configurations fve_configuration = new FVE_configurations();

        modelAndView.addObject("fve_configuration",fve_configuration);
        modelAndView.setViewName("fve_admin");

        return modelAndView;
    }

    @RequestMapping(value={"/admin/fve"}, method = RequestMethod.POST)
    public ModelAndView fve_add(@Valid FVE_configurations fve_configuration, BindingResult bindingResult){

        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("index");
        }
        else {
            adminService.add_fve_configuration(fve_configuration);
            modelAndView.addObject("fve_configuration",new FVE_configurations());
            modelAndView.setViewName("fve_admin");
        }

        return modelAndView;
    }











}
