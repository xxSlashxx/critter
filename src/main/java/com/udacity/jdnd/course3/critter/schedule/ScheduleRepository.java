package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.employee.Employee;
import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s WHERE :pet MEMBER OF s.pets")
    List<Schedule> findByPet(Pet pet);

    @Query("SELECT s FROM Schedule s WHERE :employee MEMBER OF s.employees")
    List<Schedule> findByEmployee(Employee employee);

    @Query("SELECT p.schedules FROM Pet p WHERE p.customer.id = :customerId")
    List<Schedule> findByCustomerId(Long customerId);
}