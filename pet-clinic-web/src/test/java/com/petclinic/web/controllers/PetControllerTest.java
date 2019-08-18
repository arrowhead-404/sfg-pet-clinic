package com.petclinic.web.controllers;

import com.petclinic.model.Owner;
import com.petclinic.model.Pet;
import com.petclinic.model.PetType;
import com.petclinic.model.Vet;
import com.petclinic.services.OwnerService;
import com.petclinic.services.PetService;
import com.petclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";


    @Mock
    PetTypeService petTypeService;

    @Mock
    PetService petService;

    @Mock
    OwnerService ownerService;

    @InjectMocks
    PetController petController;

    Set<Pet> pets=new HashSet<>();

    MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        mockMvc= MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    void initCreationForm() throws Exception{
        HashSet<PetType> types=new HashSet<>();
        types.add(PetType.builder().id(11L).name("Dog").build());
        types.add(PetType.builder().id(12L).name("Cat").build());
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).pets(new HashSet<>()).build());
        when(petTypeService.findAll()).thenReturn(types);
        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEWS_PETS_CREATE_OR_UPDATE_FORM))
                .andExpect(model().attributeExists("pet"));

        verify(petTypeService).findAll();
        verify(ownerService).findById(anyLong());
    }

    @Test
    void processCreationForm() throws Exception{
        HashSet<PetType> types=new HashSet<>();
        types.add(PetType.builder().id(11L).name("Dog").build());
        types.add(PetType.builder().id(12L).name("Cat").build());
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).pets(new HashSet<>()).build());
        when(petTypeService.findAll()).thenReturn(types);
        Pet pet=Pet.builder().build();
        pet.setId(2L);

        when(petService.save(any())).thenReturn(pet);

        mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("pet"));

        verify(petService).save(any());
    }

    @Test
    void initUpdateForm() throws Exception{
        HashSet<PetType> types=new HashSet<>();
        types.add(PetType.builder().id(11L).name("Dog").build());
        types.add(PetType.builder().id(12L).name("Cat").build());
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).pets(new HashSet<>()).build());
        when(petTypeService.findAll()).thenReturn(types);
        Pet pet=Pet.builder().build();
        pet.setId(2L);
        when(petService.findById(anyLong())).thenReturn(pet);
        mockMvc.perform(get("/owners/1/pets/2/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEWS_PETS_CREATE_OR_UPDATE_FORM))
                .andExpect(model().attributeExists("pet"));

        verify(petTypeService).findAll();
        verify(ownerService).findById(anyLong());
        verify(petService).findById(anyLong());
    }

    @Test
    void processUpdateForm() throws Exception{
        HashSet<PetType> types=new HashSet<>();
        types.add(PetType.builder().id(11L).name("Dog").build());
        types.add(PetType.builder().id(12L).name("Cat").build());
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).pets(new HashSet<>()).build());
        when(petTypeService.findAll()).thenReturn(types);
        Pet pet=Pet.builder().build();
        pet.setId(2L);

        when(petService.save(any())).thenReturn(pet);

        mockMvc.perform(post("/owners/1/pets/2/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("pet"));

        verify(petService).save(any());
    }
}