package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.common.BaseEntity;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.employee.Employee;
import com.udacity.jdnd.course3.critter.employee.EmployeeSkill;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Schedule extends BaseEntity {

    private LocalDate date;

    @ManyToMany
    private List<Employee> employees;

    @ManyToMany
    private List<Pet> pets;

    @ElementCollection(targetClass = EmployeeSkill.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> activities;
}