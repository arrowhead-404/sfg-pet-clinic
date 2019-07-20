package com.petclinic.web.bootstrap;

import com.petclinic.model.Owner;
import com.petclinic.model.Vet;
import com.petclinic.services.OwnerService;
import com.petclinic.services.VetService;
import com.petclinic.services.map.OwnerServiceMap;
import com.petclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService=new OwnerServiceMap();
        vetService=new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1=new Owner();
        owner1.setId(1L);
        owner1.setFirstName("somen");
        owner1.setLastName("datta");
        ownerService.save(owner1);
        Owner owner2=new Owner();
        owner2.setId(1L);
        owner2.setFirstName("tubatu");
        owner2.setLastName("datta");
        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Vet vet1=new Vet();
        vet1.setId(1L);
        vet1.setFirstName("saurav");
        vet1.setLastName("sharma");
        vetService.save(vet1);
        Vet vet2=new Vet();
        vet2.setId(1L);
        vet2.setFirstName("gautam");
        vet2.setLastName("sinha");
        vetService.save(vet2);

        System.out.println("Loaded vets...");



    }
}