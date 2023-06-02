package com.udacity.jdnd.course3.critter.employee;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping()
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee newEmployee = convertToEmployee(employeeDTO);
        return convertToEmployeeDTO(employeeService.save(newEmployee));
    }

    @PostMapping("/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.loadById(employeeId);
        return convertToEmployeeDTO(employee);
    }

    @PutMapping("/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        Employee employee = employeeService.loadById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeService.save(employee);
    }

    @GetMapping("/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        List<Employee> employees = employeeService.loadByDayAvailableAndSkills(employeeRequestDTO.getDate().getDayOfWeek(), employeeRequestDTO.getSkills());
        return employees.stream().map(this::convertToEmployeeDTO).collect(Collectors.toList());
    }

    private Employee convertToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    private EmployeeDTO convertToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }
}