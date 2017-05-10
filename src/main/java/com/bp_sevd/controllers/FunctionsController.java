package com.bp_sevd.controllers;

import com.bp_sevd.model.Appliance;
import com.bp_sevd.model.Household;
import com.bp_sevd.model.Location;
import com.bp_sevd.model.User;
import com.bp_sevd.model.consumption.Example_type;
import com.bp_sevd.model.consumption.Monthly_consumption;
import com.bp_sevd.model.production.Investment;
import com.bp_sevd.service.AnalyseService;
import com.bp_sevd.service.InvestService;
import com.bp_sevd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Martin on 24.04.2017.
 */
@Controller
public class FunctionsController {

    @Autowired
    private UserService userService;
    @Autowired
    private AnalyseService analyseService;
    @Autowired
    private InvestService investService;

    /***************************************ANALYZA DOMACNOSTI**************************************************/

    @RequestMapping(value="/analyza", method = RequestMethod.GET)
    public ModelAndView analyza(){
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();           //identifikacia klienta, ktory poslal poziadavku
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
            household.setOverall(0.0);
            household.setResidents(1);
            appliance = new Appliance();
        }

        modelAndView.addObject("user", user);
        modelAndView.addObject("household", household);
        modelAndView.addObject("appliance", appliance);
        modelAndView.setViewName("analyza");


        return modelAndView;
    }


    @RequestMapping(value = "/analyza", method = RequestMethod.POST)
    public String add_household(@ModelAttribute("household")@Valid Household household,BindingResult bindingResult,
                                 Appliance appliance,  final RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());


        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("analyza");
            return "redirect:/analyza";
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

        redirectAttributes.addFlashAttribute("household",household);
        redirectAttributes.addFlashAttribute("consumption_example",match);
        redirectAttributes.addFlashAttribute("appliance",appliance);


        return "redirect:/analyza_view";
    }


    @RequestMapping(value = "/analyza_view", method = RequestMethod.GET)        //zobrazenie vysledokov
    public ModelAndView show_consumptions(@ModelAttribute("household") Household household, @ModelAttribute("appliance") Appliance appliance, @ModelAttribute("consumption_example")Example_type example_type, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();


        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("analyza_view");
        }


        List<Monthly_consumption> monthly_consumptions_list = analyseService.findConsumptions(example_type,appliance, household);
        modelAndView.addObject("list",monthly_consumptions_list);
        modelAndView.addObject(household);
        modelAndView.addObject(appliance);
        modelAndView.setViewName("analyza_view");


        return modelAndView;
    }

    /*******************************************VYPOCET INVESTICIE****************************************************/

    @RequestMapping(value={"/investicia"}, method = {RequestMethod.GET})
    public ModelAndView investicia(){
        ModelAndView modelAndView = new ModelAndView();

        Location location = new Location();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Investment investment = new Investment();

        if(!user.getAnalysed()){
            return new ModelAndView("redirect:/");
        }

        List<Location> locations = investService.getLocations();
        modelAndView.addObject("locations",locations);
        modelAndView.addObject("locat",location);
        modelAndView.addObject("investment", investment);

        modelAndView.setViewName("investicia");


        return modelAndView;
    }

    @RequestMapping(value={"/investicia"}, method = RequestMethod.POST)
    public String vypocet_investicie(final RedirectAttributes redirectAttributes, @ModelAttribute("locat")Location location, @ModelAttribute("investition") Investment investment, BindingResult bindingResult) throws ParseException {

        Double dotacia, energy_price;

        energy_price= investment.getEnergy_price();
        dotacia= investment.getDotacia();


        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Household household=user.getHousehold();

        investService.addLocation(household,location);          //pridanie polohy k domacnosti
        investment =investService.findInvestment(energy_price, dotacia,household, investService.findBestProduction(household));     //zistenie investicie

        redirectAttributes.addFlashAttribute("investment", investment);


        return "redirect:/invest_view";
    }

    @RequestMapping(value = "/invest_view", method = RequestMethod.GET)         //zobrazenie vysledokov
    public ModelAndView show_investition(@ModelAttribute("investment") Investment investment, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("invest_view");
        }

        modelAndView.addObject(investment);
        modelAndView.setViewName("invest_view");


        return modelAndView;
    }
}
