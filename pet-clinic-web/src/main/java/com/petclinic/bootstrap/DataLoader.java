package com.petclinic.bootstrap;

import com.petclinic.model.*;
import com.petclinic.services.OwnerService;
import com.petclinic.services.PetTypeService;
import com.petclinic.services.SpecialtyService;
import com.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();

        if(count == 0){
            loadData();
        }
    }




    private void loadData(){

        //------------------------PetsType-------------------------
        PetType dog = new PetType();
        dog.setName("Duddle");
        PetType savedDogType  = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Tom");
        PetType savedCatType  = petTypeService.save(cat);

        System.out.println("PetType Loaded...");


        //------------------------Specialty-------------------------
        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        radiology.setDescription("Surgery");
        Specialty savedSurgery =  specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        radiology.setDescription("Dentistry");
        Specialty savedDentistry =  specialtyService.save(dentistry);

        System.out.println("Specialty Loaded...");

        //------------------------Owners-------------------------
        Owner owner1 = new Owner();
        owner1.setFirstName("Jhon");
        owner1.setLastName("Cena");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("1212121212");

        Pet jhonsPet = new Pet();
        jhonsPet.setName("Rasco");
        jhonsPet.setOwner(owner1);
        jhonsPet.setBirthDate(LocalDate.now());
        jhonsPet.setPetType(savedDogType);

        owner1.getPets().add(jhonsPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Triple");
        owner2.setLastName("H");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("1212121213");

        Pet triplesPet = new Pet();
        triplesPet.setName("Rodio");
        triplesPet.setOwner(owner2);
        triplesPet.setBirthDate(LocalDate.now());
        triplesPet.setPetType(savedCatType);

        owner2.getPets().add(triplesPet);

        ownerService.save(owner2);

        System.out.println("Owners & Pets Loaded...");


        //------------------------Vets-------------------------
        Vet vet1= new Vet();
        vet1.setFirstName("Bruce");
        vet1.setLastName("Banner");
        vet1.getSpecialties().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Ben");
        vet2.setLastName("Aflik");
        vet2.getSpecialties().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("Vets Loaded...");

    }

}
