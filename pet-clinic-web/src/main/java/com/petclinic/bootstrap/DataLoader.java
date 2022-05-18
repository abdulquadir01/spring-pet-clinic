package com.petclinic.bootstrap;

import com.petclinic.model.Owner;
import com.petclinic.model.Vet;
import com.petclinic.services.OwnerService;
import com.petclinic.services.VetService;
import com.petclinic.services.map.OwnerServiceMapImpl;
import com.petclinic.services.map.VetServiceMapImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerServiceMapImpl();
        vetService = new VetServiceMapImpl();
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Jhon");
        owner1.setLastName("Cena");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Triple");
        owner2.setLastName("H");

        ownerService.save(owner2);

        System.out.println("Owners Loaded...");

        Vet vet1= new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Dr");
        vet1.setLastName("Banner");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Dr");
        vet2.setLastName("Doom");

        vetService.save(vet2);

        System.out.println("Vets Loaded...");

    }
}
