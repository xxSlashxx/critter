package com.udacity.jdnd.course3.critter.employee;

import com.udacity.jdnd.course3.critter.common.BaseEntity;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Employee extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy = "employees")
    private List<Schedule> schedules;

    @ElementCollection(targetClass = EmployeeSkill.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;

    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;
}