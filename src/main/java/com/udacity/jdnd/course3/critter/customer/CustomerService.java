package com.udacity.jdnd.course3.critter.customer;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer loadById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer with id " + id + " not found."));
    }

    public List<Customer> loadAll() {
        return customerRepository.findAll();
    }

    public Customer loadByPetId(Long petId) {
        return customerRepository.findByPetsId(petId);
    }
}
