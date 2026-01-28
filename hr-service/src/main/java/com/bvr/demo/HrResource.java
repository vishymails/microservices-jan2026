package com.bvr.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvr.demo.Employee;

@RestController
@RequestMapping("/hr")
public class HrResource {
    
    public HrResource() {
    }

    List<Employee> employees = Arrays.asList(
        new Employee("1", "Ram", "Kumar", "MedTech"),
        new Employee("2", "Sita", "Devi", "Surgery"),
        new Employee("3", "Lakshman", "Rao", "Dentistry")
    );


    @RequestMapping("/employees")
    public EmployeeList getEmployees() {
        EmployeeList empList = new EmployeeList();
        empList.setEmployees(employees);
        return empList;
    }

    @RequestMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable("id") String id) {
        Employee emp = employees.stream()
            .filter(e -> id.equals(e.getId()))
            .findAny()
            .orElse(null);

        return emp;
    }
}

