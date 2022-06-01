package com.petclinic.services.map;

import com.petclinic.model.Visit;
import com.petclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;




@Service
@Profile({"default", "mapImpl"})
public class VisitServiceMapImp extends AbstractMapService<Visit, Long> implements VisitService {

//    private final VisitService visitService;
//
//    public VisitServiceMapImp(VisitService visitService) {
//        this.visitService = visitService;
//    }

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit visit) {
        super.delete(visit);
    }

    @Override
    public Visit save(Visit visit) {
        if(visit.getPet() == null || visit.getPet().getOwner() == null || visit.getPet().getId() == null
            || visit.getPet().getOwner().getId() == null  ){
            throw new RuntimeException("Invalid Visit");
        }
        return super.save(visit);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}
