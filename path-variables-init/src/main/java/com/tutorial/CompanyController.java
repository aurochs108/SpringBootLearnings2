package com.tutorial;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RequestMapping("${urls.company.root}")
@RestController
public class CompanyController {
    private final CompanyRepository companyRepository;


    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping
    List<Company> findAll() {
        return companyRepository.findAll();
    }

    @GetMapping("/{companyName}")
    Company findOne(@PathVariable("companyName") String name){
        return companyRepository.findOne(name);
    }

    @GetMapping("{companyName}/${urls.company.employees.root}/{firstName}")
    List<Employee> findCompanyEmployeesWithFirstName
            (@PathVariable("companyName")String companyName,
            @PathVariable("firstName") String name){
        return findOne(companyName)
                .getEmployees()
                .stream()
                .filter(employee -> Objects.equals(employee.getFirstName(), name))
                .collect(Collectors.toList());
    }

    @GetMapping("{companyName}/${urls.company.employees.root}/{lastName}/{firstName}")
    List<Employee> findCompanyEmployeesWithLastNameAndFirstName(@PathVariable Map<String, String> pathVariable){
        return findOne(pathVariable.get("companyName"))
                .getEmployees()
                .stream()
                .filter(employee -> Objects.equals(employee.getFirstName(), (pathVariable.get("firstName"))))
                .filter(employee -> Objects.equals(employee.getFirstName(), (pathVariable.get("lastName"))))
                .collect(Collectors.toList());
    }
}
