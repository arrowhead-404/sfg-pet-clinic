package com.petclinic.repositories;

import com.petclinic.model.Pet;
import com.petclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
