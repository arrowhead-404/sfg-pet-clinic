package com.petclinic.web.bootstrap;

import com.petclinic.model.Owner;
import com.petclinic.model.PetType;
import com.petclinic.model.Vet;
import com.petclinic.services.OwnerService;
import com.petclinic.services.PetTypeService;
import com.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService=petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog=new PetType();
        dog.setName("Dog");
        PetType savedDogPetType=petTypeService.save(dog);

        PetType cat=new PetType();
        cat.setName("Cat");
        PetType savedCatPetType=petTypeService.save(cat);

        Owner owner1=new Owner();
        owner1.setFirstName("somen");
        owner1.setLastName("datta");
        ownerService.save(owner1);
        Owner owner2=new Owner();
        owner2.setFirstName("tubatu");
        owner2.setLastName("datta");
        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Vet vet1=new Vet();
        vet1.setFirstName("saurav");
        vet1.setLastName("sharma");
        vetService.save(vet1);
        Vet vet2=new Vet();
        vet2.setFirstName("gautam");
        vet2.setLastName("sinha");
        vetService.save(vet2);

        System.out.println("Loaded vets...");



    }
}
