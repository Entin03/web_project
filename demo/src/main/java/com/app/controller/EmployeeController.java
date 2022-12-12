package com.app.controller;

import com.app.model.Employee;
import com.app.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService service;

    @GetMapping("/show")
    public String show() {
        return "EmployeeRegister";
    }

    @PostMapping("/register")
    public String saveEmployee(@ModelAttribute Employee emp) {
        service.saveEmployee(emp);
        return "redirect:all";
    }

    @GetMapping("/all")
    public String showEmployees(Model model) {
        List<Employee> list = service.getAllEmployees();
        model.addAttribute("list", list);
        return "EmployeeData";
    }
    //http://3031/employee/remove?id=2
    @GetMapping("/remove")
    public String removeEmployee(@RequestParam Integer id) {
        service.deleteEmployeeById(id);
        return "redirect:all";
    }

    @GetMapping("/edit")
    public String editEmployee(@RequestParam Integer id,Model model) {
        Employee emp = service.getEmployeeById(id);
        model.addAttribute("emp", emp);
        return "EmployeeEdit";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee emp) {
        service.updateEmployee(emp);
        return "redirect:all";
    }
}
