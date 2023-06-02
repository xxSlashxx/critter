package com.udacity.jdnd.course3.critter.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByPetsId(Long petId);
}