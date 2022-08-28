package com.petclinic.bootstrap;

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
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
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
        dog.setName("Dog");
        PetType savedDogPetType  = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType  = petTypeService.save(cat);

        System.out.println("PetType Loaded...");


        //------------------------Specialty-------------------------
        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery =  specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry =  specialtyService.save(dentistry);

        System.out.println("Specialty Loaded...");

        //------------------------Owners-------------------------
        Owner owner1 = new Owner();
        owner1.setFirstName("Jhon");
        owner1.setLastName("Cena");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("1212121212");

//        Owner.builder().address("ASDF").build();


        Pet jhonsDog = new Pet();
        jhonsDog.setPetType(savedDogPetType);
        jhonsDog.setOwner(owner1);
        jhonsDog.setBirthDate(LocalDate.now());
        jhonsDog.setName("Rosco");

        owner1.getPets().add(jhonsDog);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Triple");
        owner2.setLastName("H");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("1212121213");

        Pet triplesCat = new Pet();
        triplesCat.setName("Rodio");
        triplesCat.setOwner(owner2);
        triplesCat.setBirthDate(LocalDate.now());
        triplesCat.setPetType(savedCatPetType);

        owner2.getPets().add(triplesCat);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(triplesCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

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
        vet2.getSpecialties().add(savedDentistry);

        vetService.save(vet2);

        System.out.println("Vets Loaded...");

    }



}
