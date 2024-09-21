package com.customerjpa.controller;

import com.customerjpa.model.Customer;
import com.customerjpa.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService ;
    @GetMapping
    public String showList(Model model){
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "/list";
    }
    @GetMapping("/create")
    public String showFormCreate(Model model){
        model.addAttribute("customer", new Customer());
        return "/create";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute("customer") Customer customer, Model model, RedirectAttributes redirect){
        customerService.save(customer);
        redirect.addAttribute("success", " Create Customer successfully");
        return "redirect:/customers";
    }
    @GetMapping("/{id}/view")
    public String showView(@PathVariable("id") Long id, Model model){
        model.addAttribute("customer", customerService.findById(id));
        return "/view";
    }
    @GetMapping("/{id}/delete")
    public String ShowFormDelete(@PathVariable("id") Long id, Model model){
    model.addAttribute("customer", customerService.findById(id));
    return "/delete";
    }
    @PostMapping("/delete")
    public String delete(@ModelAttribute("customer") Customer customer, Model model, RedirectAttributes redirect){
        customerService.remove(customer.getId());
        redirect.addAttribute("success", "Remove customer successfully");
        return "redirect:/customers";
    }
    @GetMapping("/{id}/edit")
    public String showFormUpdate(@PathVariable("id") Long id, Model model){
        model.addAttribute("customer", customerService.findById(id));
        return "/update";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("customer") Customer customer, Model model, RedirectAttributes redirect){
        customerService.save(customer);
        redirect.addAttribute("success", "Update Customer Successfully");
        return "redirect:/customers";
    }

}
