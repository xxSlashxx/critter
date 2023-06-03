package com.udacity.jdnd.course3.critter.employee;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee loadById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee with id " + id + " not found."));
    }

    public List<Employee> loadByIds(List<Long> ids) {
        return employeeRepository.findAllById(ids);
    }

    public List<Employee> loadByDayAvailableAndSkills(DayOfWeek dayAvailable, Set<EmployeeSkill> skills) {
        List<Employee> employees = employeeRepository.findByDayAvailable(dayAvailable);
        return employees.stream().filter(employee -> employee.getSkills().containsAll(skills)).collect(Collectors.toList());
    }
}
