package com.petclinic.services.springdatajpa;

import com.petclinic.model.Owner;
import com.petclinic.repositories.OwnerRepository;
import com.petclinic.repositories.PetRepository;
import com.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    String LAST_NAME="Datta";
    Long ownerId=99L;

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    @BeforeEach
    public void setup(){
    }

    @Test
    void findByLastName() {
        Owner temp=Owner.builder().id(1L).lastName(LAST_NAME).build();
        when(ownerRepository.findByLastName(any())).thenReturn(temp);
        Owner datta=service.findByLastName(LAST_NAME);
        assertNotNull(datta);
        assertEquals(LAST_NAME,datta.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Owner temp1=Owner.builder().id(1L).build();
        Owner temp2=Owner.builder().id(2L).build();
        Owner temp3=Owner.builder().id(3L).build();
        HashSet<Owner> owners=new HashSet<>();
        owners.add(temp1);
        owners.add(temp2);
        owners.add(temp3);
        when(ownerRepository.findAll()).thenReturn(owners);
        Set<Owner> allOwners=service.findAll();
        assertNotNull(allOwners);
        assertEquals(3, allOwners.size());
        verify(ownerRepository).findAll();
    }

    @Test
    void findById() {
        Owner temp=Owner.builder().id(ownerId).build();
        when(ownerRepository.findById(any())).thenReturn(Optional.of(temp));
        Owner owner=service.findById(ownerId);
        assertNotNull(owner);
        assertEquals(ownerId,owner.getId());
        verify(ownerRepository).findById(any());
    }

    @Test
    void save() {
        Owner temp=Owner.builder().id(ownerId).build();
        when(ownerRepository.save(any())).thenReturn(temp);
        Owner tobeSaved=Owner.builder().id(ownerId).build();
        Owner returned=service.save(tobeSaved);
        assertNotNull(returned);
        assertEquals(tobeSaved.getId(), returned.getId());
        assertEquals(tobeSaved.getId(), temp.getId());
    }

    @Test
    void delete() {
        Owner temp=Owner.builder().id(ownerId).build();
        service.delete(temp);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        Owner temp=Owner.builder().id(ownerId).build();
        service.deleteById(ownerId);
        verify(ownerRepository).deleteById(any());
    }
}