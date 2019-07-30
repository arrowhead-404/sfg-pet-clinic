package com.petclinic.services.springdatajpa;

import com.petclinic.model.Speciality;
import com.petclinic.repositories.SpecialityRepository;
import com.petclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialitySDJpaService implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    public SpecialitySDJpaService(SpecialityRepository specialityRepository) {
        this.specialityRepository=specialityRepository;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialitys=new HashSet<>();
        specialityRepository.findAll().forEach(specialitys::add);
        return specialitys;
    }

    @Override
    public Speciality findById(Long aLong) {
        Optional<Speciality> optionalSpecialitys= specialityRepository.findById(aLong);
        return optionalSpecialitys.isPresent()?optionalSpecialitys.get():null;
    }

    @Override
    public Speciality save(Speciality object) {
        return specialityRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        specialityRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specialityRepository.deleteById(aLong);
    }
}
