package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.customer.Customer;
import com.udacity.jdnd.course3.critter.customer.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    private final CustomerService customerService;

    public PetController(PetService petService, CustomerService customerService) {
        this.petService = petService;
        this.customerService = customerService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = convertToPet(petDTO);
        Customer customer = customerService.loadEagerById(petDTO.getOwnerId());
        customer.getPets().add(pet);
        pet.setCustomer(customer);
        return convertToPetDTO(petService.save(pet));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.loadById(petId);
        return convertToPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets() {
        List<Pet> allPets = petService.loadAll();
        return allPets.stream().map(this::convertToPetDTO).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> ownersPets = petService.loadByCustomerId(ownerId);
        return ownersPets.stream().map(this::convertToPetDTO).collect(Collectors.toList());
    }

    private Pet convertToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    private PetDTO convertToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);

        if (pet.getCustomer() != null) {
            petDTO.setOwnerId(pet.getCustomer().getId());
        }

        return petDTO;
    }
}