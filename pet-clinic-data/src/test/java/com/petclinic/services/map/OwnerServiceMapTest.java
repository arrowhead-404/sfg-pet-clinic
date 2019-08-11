package com.petclinic.services.map;

import com.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;
    final Long ownerId=1L;
    final String lastName="Datta";


    @BeforeEach
    public void setup()
    {
        ownerServiceMap=new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners=ownerServiceMap.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void saveNoId() {
        Owner owner2=Owner.builder().build();
        Owner saved=ownerServiceMap.save(owner2);
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    void saveExistingId() {
        Long id=2L;
        Owner owner2=Owner.builder().id(id).build();
        Owner saved=ownerServiceMap.save(owner2);
        assertEquals(id, saved.getId());
    }

    @Test
    void findById() {
        Owner owner=ownerServiceMap.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner datta=ownerServiceMap.findByLastName(lastName);
        assertNotNull(datta);

        assertEquals(ownerId, datta.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner datta=ownerServiceMap.findByLastName("notfound");
        assertNull(datta);
    }
}