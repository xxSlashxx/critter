package com.udacity.jdnd.course3.critter.pet;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet loadById(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pet with id " + id + " not found."));
    }

    public List<Pet> loadByIds(List<Long> ids) {
        return petRepository.findAllById(ids);
    }

    public List<Pet> loadAll() {
        return petRepository.findAll();
    }

    public List<Pet> loadByCustomerId(Long customerId) {
        return petRepository.findByCustomerId(customerId);
    }
}