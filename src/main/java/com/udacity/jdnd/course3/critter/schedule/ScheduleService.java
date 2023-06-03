package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.employee.Employee;
import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> loadAll() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> loadByPet(Pet pet) {
        return scheduleRepository.findByPet(pet);
    }

    public List<Schedule> loadByEmployee(Employee employee) {
        return scheduleRepository.findByEmployee(employee);
    }

    public List<Schedule> loadByCustomerId(Long customerId) {
        return scheduleRepository.findByCustomerId(customerId);
    }
}