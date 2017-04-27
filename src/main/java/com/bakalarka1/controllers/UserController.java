package com.bakalarka1.controllers;

import com.bakalarka1.model.Appliance;
import com.bakalarka1.model.Household;
import com.bakalarka1.model.User;
import com.bakalarka1.model.consumption.Example_type;
import com.bakalarka1.model.consumption.Monthly_consumption;
import com.bakalarka1.model.production.FVE_configurations;
import com.bakalarka1.model.production.FVE_production;
import com.bakalarka1.service.AdminService;
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
    private AdminService adminService;

    @Autowired
    private AnalyseService analyseService;




    @RequestMapping(value={"/home"}, method = RequestMethod.GET)
    public ModelAndView logged(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }


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
