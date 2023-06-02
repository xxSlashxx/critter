package com.udacity.jdnd.course3.critter.customer;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer loadEagerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer with id " + id + " not found."));
        Hibernate.initialize(customer.getPets());
        return customer;
    }

    public List<Customer> loadAll() {
        return customerRepository.findAll();
    }

    @Transactional
    public Customer loadEagerByPetId(Long petId) {
        Customer customer = customerRepository.findByPetsId(petId);
        Hibernate.initialize(customer.getPets());
        return customer;
    }
}
