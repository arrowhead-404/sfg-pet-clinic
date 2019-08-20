package com.petclinic.web.bootstrap;

import com.petclinic.model.*;
import com.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService=petTypeService;
        this.specialityService=specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count=petTypeService.findAll().size();
        if(count==0) {
            localData();
        }
    }

    private void localData() {
        PetType dog=new PetType();
        dog.setName("Dog");
        PetType savedDogPetType=petTypeService.save(dog);

        PetType cat=new PetType();
        cat.setName("Cat");
        PetType savedCatPetType=petTypeService.save(cat);

        Speciality radiology=new Speciality();
        radiology.setDescription("Radiology");
        Speciality urology=new Speciality();
        urology.setDescription("Urology");
        radiology=specialityService.save(radiology);
        urology=specialityService.save(urology);
        Speciality surgery=new Speciality();
        surgery.setDescription("Surgery");
        ;
        surgery=specialityService.save(surgery);
        Speciality dentistry=new Speciality();
        dentistry.setDescription("Dentistry");
        dentistry=specialityService.save(dentistry);


        Owner owner1=new Owner();
        owner1.setFirstName("somen");
        owner1.setLastName("datta");
        owner1.setAddress("B4/2, Sai Appt, Diamond Park");
        owner1.setCity("Kolkata");
        Pet doggo=new Pet();
        doggo.setPetType(savedDogPetType);
        doggo.setName("Doggo");
        doggo.setBirthDate(LocalDate.now());
        doggo.setOwner(owner1);
        owner1.getPets().add(doggo);
        ownerService.save(owner1);

        Owner owner2=new Owner();
        owner2.setFirstName("tubatu");
        owner2.setLastName("datta");
        owner2.setAddress("Tubatu house, tubatu street");
        owner2.setCity("Raurkela");

        Pet kitty=new Pet();
        kitty.setPetType(savedCatPetType);
        kitty.setName("Kitty");
        kitty.setBirthDate(LocalDate.now());
        kitty.setOwner(owner2);
        owner2.getPets().add(kitty);
        ownerService.save(owner2);

        Visit catVisit=new Visit();
        catVisit.setPet(kitty);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneexy Kitty");

        visitService.save(catVisit);

        System.out.println("Loaded owners...");

        Vet vet1=new Vet();
        vet1.setFirstName("saurav");
        vet1.setLastName("sharma");
        vet1.getSpecialities().add(radiology);
        vet1.getSpecialities().add(urology);
        vetService.save(vet1);
        Vet vet2=new Vet();
        vet2.setFirstName("gautam");
        vet2.setLastName("sinha");
        vet2.getSpecialities().add(surgery);
        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
