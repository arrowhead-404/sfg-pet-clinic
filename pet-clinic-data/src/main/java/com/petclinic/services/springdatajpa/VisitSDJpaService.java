package com.petclinic.services.springdatajpa;

import com.petclinic.model.Visit;
import com.petclinic.repositories.VisitRepository;
import com.petclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VisitSDJpaService implements VisitService {

    private final VisitRepository visitRepository;

    public VisitSDJpaService(VisitRepository visitRepository) {
        this.visitRepository=visitRepository;
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits=new HashSet<>();
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit findById(Long aLong) {
        Optional<Visit> optionalVisits= visitRepository.findById(aLong);
        return optionalVisits.isPresent()?optionalVisits.get():null;
    }

    @Override
    public Visit save(Visit object) {
        return visitRepository.save(object);
    }

    @Override
    public void delete(Visit object) {
        visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        visitRepository.deleteById(aLong);
    }
}
