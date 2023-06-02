package com.udacity.jdnd.course3.critter.customer;

import com.udacity.jdnd.course3.critter.common.BaseEntity;
import com.udacity.jdnd.course3.critter.pet.Pet;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Customer extends BaseEntity {

    private String name;

    private String phoneNumber;

    private String notes;

    @OneToMany(mappedBy = "customer")
    private List<Pet> pets = new ArrayList<>();
}