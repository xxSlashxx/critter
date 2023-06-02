package com.udacity.jdnd.course3.critter.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE :dayAvailable MEMBER OF e.daysAvailable")
    List<Employee> findByDayAvailable(DayOfWeek dayAvailable);
}