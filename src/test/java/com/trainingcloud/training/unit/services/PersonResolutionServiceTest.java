package com.trainingcloud.training.unit.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.trainingcloud.training.entities.PersonResolution;
import com.trainingcloud.training.services.impl.PersonResolutionServiceImpl;
import com.trainingcloud.training.repository.PersonResolutionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PersonResolutionServiceTest {

    @InjectMocks
    PersonResolutionServiceImpl personResolutionService;

    @Mock
    PersonResolutionRepository personResolutionRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllPersonResolution() throws Exception {
        List<PersonResolution> personResolutionList = new ArrayList<>();
        personResolutionList.add(new PersonResolution(1, new Date(), 0, 0, 0, 0, "test"));
        personResolutionList.add(new PersonResolution(2, new Date(), 2, 1, 0, 0, "test"));
        personResolutionList.add(new PersonResolution(3, new Date(), 0, 3, 0, 0, "test"));

        when(personResolutionRepository.findAll()).thenReturn(personResolutionList);

        //test
        List<PersonResolution> empList = personResolutionService.getAll();

        assertEquals(3, empList.size());
        verify(personResolutionRepository, times(1)).findAll();

    }

    @Test
    public void findByTimestampBetween_null_values() throws Exception {
        List<PersonResolution> personResolutionList = new ArrayList<>();
        personResolutionList.add(new PersonResolution(1, new Date(2022, 8, 2), 0, 0, 0, 0, "test"));
        personResolutionList.add(new PersonResolution(1, new Date(2022, 8, 3), 0, 0, 0, 0, "test"));
        personResolutionList.add(new PersonResolution(1, new Date(2022, 8, 4), 0, 0, 0, 0, "test"));

        when(personResolutionRepository.findByTimestampBetween(null, null)).thenReturn(personResolutionList);
        //test
        List<PersonResolution> empList = personResolutionService.findByTimestampRange(null, null);
        assertEquals(3, empList.size());
    }

    @Test
    public void findByTimestampBetween_with_values() throws Exception {
        List<PersonResolution> personResolutionList = new ArrayList<>();
        personResolutionList.add(new PersonResolution(1, new Date(2022, 8, 3), 0, 0, 0, 0, "test"));
        personResolutionList.add(new PersonResolution(1, new Date(2022, 8, 4), 0, 0, 0, 0, "test"));

        when(personResolutionRepository.findByTimestampBetween(new Date(2022, 8, 3), new Date(2022, 8, 5))).thenReturn(personResolutionList);
        //test
        List<PersonResolution> empList = personResolutionService.findByTimestampRange(new Date(2022, 8, 3), new Date(2022, 8, 5));
        assertEquals(2, empList.size());
    }

    @Test
    public void create_new_PersonResolution() throws Exception {
        PersonResolution personResolution = new PersonResolution(1, new Date(), 0, 0, 0, 0, "test");
        personResolutionService.create(personResolution);
        verify(personResolutionRepository, times(1)).save(personResolution);
    }
}
