package com.example.weeklychallengeguestbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class HomeController {
    public static long idCounter = 0;

    ArrayList<Employee> guestbook = new ArrayList<>();

    @RequestMapping("/")
    public String goHome(){
        return "index";
    }

    @GetMapping("/add")
    public String addEntry(Model model){
        Employee newEmployee = new Employee();
        idSetter(newEmployee);
        model.addAttribute("employee", newEmployee);
        return "form";
    }

    @PostMapping("/display")
    public String displayEntry(@Valid Employee employee,
                                  BindingResult result){
        if (result.hasErrors()) {
            return "form";
        } else {
            guestbook.add(employee);
            return "displayentry";
        }
    }

    @RequestMapping("/all")
    public String showAll(Model model){
        model.addAttribute("guestbook", guestbook);
        return "guestbook";
    }


    static void idSetter(Employee gbEntry){
        idCounter = idCounter + 1;
        gbEntry.setId(idCounter);
    }
}


