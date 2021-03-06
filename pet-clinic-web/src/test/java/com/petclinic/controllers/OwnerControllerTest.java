package com.petclinic.controllers;

import com.petclinic.model.Owner;
import com.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;


@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    Set<Owner> owners;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

//    @Test
//    void ownerList() throws Exception {
//
//        when(ownerService.findAll()).thenReturn(owners);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
////                .andExpect(status().is(200)); same as
//                .andExpect(status().isOk())
//                .andExpect(view().name("ownerPage"))
//                .andExpect(model().attribute("owners", hasSize(2)));
//
//    }

//    @Test
//    void findOwners() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("notImplementedPage"));
//
//
//        Mockito.verifyNoInteractions(ownerService);
//    }
}