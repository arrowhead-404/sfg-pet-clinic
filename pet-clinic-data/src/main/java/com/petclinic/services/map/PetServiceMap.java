package com.petclinic.services.map;

import com.petclinic.model.Pet;
import com.petclinic.services.CrudService;
import com.petclinic.services.PerService;

import java.util.Set;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements PerService {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }
}