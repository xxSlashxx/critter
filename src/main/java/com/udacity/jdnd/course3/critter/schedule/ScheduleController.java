package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.employee.Employee;
import com.udacity.jdnd.course3.critter.employee.EmployeeService;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    private final PetService petService;

    private final EmployeeService employeeService;

    public ScheduleController(ScheduleService scheduleService, PetService petService, EmployeeService employeeService) {
        this.scheduleService = scheduleService;
        this.petService = petService;
        this.employeeService = employeeService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule newSchedule = convertToSchedule(scheduleDTO);

        if (!scheduleDTO.getEmployeeIds().isEmpty()) {
            newSchedule.setEmployees(employeeService.loadByIds(scheduleDTO.getEmployeeIds()));
        }

        if (!scheduleDTO.getPetIds().isEmpty()) {
            newSchedule.setPets(petService.loadByIds(scheduleDTO.getPetIds()));
        }

        return convertToScheduleDTO(scheduleService.save(newSchedule));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> allSchedules = scheduleService.loadAll();
        return allSchedules.stream().map(this::convertToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        Pet pet = petService.loadById(petId);
        List<Schedule> petSchedules = scheduleService.loadByPet(pet);
        return petSchedules.stream().map(this::convertToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.loadById(employeeId);
        List<Schedule> employeeSchedules = scheduleService.loadByEmployee(employee);
        return employeeSchedules.stream().map(this::convertToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> customerSchedules = scheduleService.loadByCustomerId(customerId);
        return customerSchedules.stream().map(this::convertToScheduleDTO).collect(Collectors.toList());
    }

    private Schedule convertToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        return schedule;
    }

    private ScheduleDTO convertToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        if (schedule.getEmployees() != null) {
            schedule.getEmployees().forEach(employee -> scheduleDTO.getEmployeeIds().add(employee.getId()));
        }

        if (schedule.getPets() != null) {
            schedule.getPets().forEach(pet -> scheduleDTO.getPetIds().add(pet.getId()));
        }

        return scheduleDTO;
    }
}