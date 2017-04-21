package com.bakalarka1.controllers;

import com.bakalarka1.model.Appliance;
import com.bakalarka1.model.Household;
import com.bakalarka1.model.User;
import com.bakalarka1.model.consumption.Example_type;
import com.bakalarka1.service.AnalyseService;
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
import java.util.List;


/**
 * Created by Martin on 07.04.2017.
 */
@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private AnalyseService analyseService;




    @RequestMapping(value={"/home"}, method = RequestMethod.GET)
    public ModelAndView logged(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }




    @RequestMapping(value="/analyza", method = RequestMethod.GET)
    public ModelAndView analyza(){
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        Household household;
        Appliance appliance;

        if(user.getAnalysed()){
            household=user.getHousehold();
            appliance=household.getAppliance();
            modelAndView.addObject("successMessage", "Vasa domacnost je uz nastavena, vase hodnoty budu nahradene novymi");
        }

        else{
            household = new Household();
            appliance = new Appliance();
        }

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
        else if(user.getAnalysed()) {

            userService.updateHousehold(user, household, appliance);
            modelAndView.addObject("successMessage", "Analyza je aktualizovana");
            modelAndView.addObject("user", user);
            modelAndView.addObject("household", household);
            modelAndView.addObject("appliance", appliance);
            modelAndView.setViewName("analyza");
        }

        else{
            userService.addHousehold(user,household,appliance);
            modelAndView.addObject("successMessage", "Analyza je ulozena");
            modelAndView.addObject("user", user);
            modelAndView.addObject("appliance", appliance);
            modelAndView.addObject("user", household);
            modelAndView.setViewName("analyza");
        }

        List<Example_type> matches=analyseService.getBestExample(household,appliance);
        analyseService.findConsumptions(matches);        //return funkcie getBestExample vrati idcka pre funkciu findConsumptions



        return modelAndView;
    }
}
