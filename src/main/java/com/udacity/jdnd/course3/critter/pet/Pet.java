package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.common.BaseEntity;
import com.udacity.jdnd.course3.critter.customer.Customer;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Pet extends BaseEntity {

    private PetType type;

    private String name;

    private LocalDate birthDate;

    private String notes;

    @ManyToOne
    private Customer customer;

    @ManyToMany(mappedBy = "pets")
    private List<Schedule> schedules;
}