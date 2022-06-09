package com.petclinic.services.map;

import com.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerServiceMapImplTest {

    OwnerServiceMapImpl
            ownerServiceMapImpl;
    Long ownerId = 1L;
    String lastName = "Max";

    @BeforeEach
    void setUp() {

        ownerServiceMapImpl = new OwnerServiceMapImpl(new PetTypeServiceMapImpl(), new PetServiceMapImpl());
        ownerServiceMapImpl.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMapImpl.findAll();

        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMapImpl.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        Owner savedOwner = ownerServiceMapImpl.save(owner2);

        assertEquals(id, savedOwner.getId());

    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerServiceMapImpl.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerServiceMapImpl.delete(ownerServiceMapImpl.findById(ownerId));

        assertEquals(0, ownerServiceMapImpl.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMapImpl.deleteById(ownerId);

        assertEquals(0, ownerServiceMapImpl.findAll().size());
    }

    @Test
    void findByLastName() {

        Owner max = ownerServiceMapImpl.findByLastName(lastName);
        assertNotNull(max);
        assertEquals(ownerId, max.getId());
    }
}