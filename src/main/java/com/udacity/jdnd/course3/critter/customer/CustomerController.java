package com.udacity.jdnd.course3.critter.customer;

import com.udacity.jdnd.course3.critter.common.BaseEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer newCustomer = convertToCustomer(customerDTO);
        return convertToCustomerDTO(customerService.save(newCustomer));
    }

    @GetMapping()
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> allCustomers = customerService.loadAll();
        return allCustomers.stream().map(this::convertToCustomerDTO).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        return convertToCustomerDTO(customerService.loadByPetId(petId));
    }

    private Customer convertToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    private CustomerDTO convertToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);

        if (customer.getPets() != null) {
            List<Long> petIds = customer.getPets().stream().map(BaseEntity::getId).collect(Collectors.toList());
            customerDTO.setPetIds(petIds);
        }

        return customerDTO;
    }
}