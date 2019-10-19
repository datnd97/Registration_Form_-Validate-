package com.codegym.controller;

import com.codegym.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CustomerController {
    @GetMapping("/")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }
    @PostMapping("/validateUser")
    public String checkValidation(@Validated @ModelAttribute("customer") Customer customer, BindingResult bindingResult, Model model) {
       if(bindingResult.hasFieldErrors()){
           new Customer().validate(customer,bindingResult);
           return "index";
       }
       else {
           model.addAttribute("message","Thanh cong");
           return "rs";
       }
    }
}
