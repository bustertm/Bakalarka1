package com.bakalarka1.controllers;

import com.bakalarka1.model.Appliance;
import com.bakalarka1.model.Household;
import com.bakalarka1.model.User;
import com.bakalarka1.model.consumption.Example_type;
import com.bakalarka1.model.consumption.Monthly_consumption;
import com.bakalarka1.service.AnalyseService;
import com.bakalarka1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
    public String add_household(@ModelAttribute("household") Household household,
                                final RedirectAttributes redirectAttributes, @Valid Appliance appliance, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
       // MW_analyse.clear();
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

        Example_type match=analyseService.getBestExample(household,appliance);
      //  analyseService.findConsumptions(matches);        //return funkcie getBestExample vrati idcka pre funkciu findConsumptions

        //   List<Monthly_consumption> monthly_consumptions_list = analyseService.findConsumptions(matches);

        // modelAndView.addObject(analyseService.findConsumptions(matches));
        redirectAttributes.addFlashAttribute("household",household);
        redirectAttributes.addFlashAttribute("consumption_example",match);
        redirectAttributes.addFlashAttribute("appliance",appliance);

        return "redirect:/analyza_view";

    }


    @RequestMapping(value = "/analyza_view", method = RequestMethod.GET)
    public ModelAndView show_consumptions(@ModelAttribute("household") Household household, @ModelAttribute("appliance") Appliance appliance, @ModelAttribute("consumption_example")Example_type example_type, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("analyza_view");
        }
        modelAndView.addObject(household);
        modelAndView.addObject(appliance);

        List<Monthly_consumption> monthly_consumptions_list = analyseService.findConsumptions(example_type);
        modelAndView.addObject("list",monthly_consumptions_list);
        modelAndView.setViewName("analyza_view");

        userService.printSomething(household,monthly_consumptions_list);

        return modelAndView;

    }






}
