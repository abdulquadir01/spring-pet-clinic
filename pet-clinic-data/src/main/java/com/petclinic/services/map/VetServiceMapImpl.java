package com.petclinic.services.map;

import com.petclinic.model.Specialty;
import com.petclinic.model.Vet;
import com.petclinic.services.SpecialtyService;
import com.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;



@Service
@Profile({"default", "mapImpl"})
public class VetServiceMapImpl extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;
    public VetServiceMapImpl(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {

        if(object.getSpecialties().size()>0){
            object.getSpecialties().forEach(specialty -> {
                if(specialty.getId() == null){
                    Specialty savedSpecialty =  specialtyService.save(specialty);
                    specialty.setId(savedSpecialty.getId());
                }
            });
        }

        return super.save(object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

}
