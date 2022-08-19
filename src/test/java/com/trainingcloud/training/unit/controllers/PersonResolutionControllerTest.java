package com.trainingcloud.training.unit.controllers;

import com.trainingcloud.training.controller.PersonResolutionController;
import com.trainingcloud.training.entities.PersonResolution;
import com.trainingcloud.training.services.PersonResolutionService;
import com.trainingcloud.training.utilities.ResponseApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PersonResolutionController.class)
public class PersonResolutionControllerTest {

    @MockBean
    PersonResolutionService personResolutionService;

    @Autowired
    PersonResolutionController personResolutionController;

    @Test
    public void getAll_personResolutiong_expected_ok() throws Exception {

        List<PersonResolution> personResolutionList = new ArrayList<>();
        personResolutionList.add(new PersonResolution(1, new Date(2022, 8, 2), 0, 0, 0, 0, "test"));
        personResolutionList.add(new PersonResolution(1, new Date(2022, 8, 3), 0, 0, 0, 0, "test"));

        Mockito.when(personResolutionService.getAll()).thenReturn(personResolutionList);

        ResponseEntity<ResponseApi<List<PersonResolution>>> res = personResolutionController.getByTimestampBetween(null, null);
        ResponseApi<List<PersonResolution>> resList = res.getBody();

        Assertions.assertEquals(res.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(2, resList.getData().size());

    }

    @Test
    public void create_personResolution_expected_ok() throws Exception {

        PersonResolution personResolution = new PersonResolution(1, new Date(), 0, 0, 0, 0, "test");

        Mockito.when(personResolutionService.create(any(PersonResolution.class))).thenReturn(true);
        ResponseEntity<ResponseApi<Boolean>> res = personResolutionController.create(personResolution);
        ResponseApi<Boolean> resApi = res.getBody();

        Assertions.assertEquals(res.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(resApi.isSuccess(), true);

    }

    @Test
    public void create_personResolution_expected_badRequest() throws Exception {
        Mockito.when(personResolutionService.create(any(PersonResolution.class))).thenReturn(false);
        ResponseEntity<ResponseApi<Boolean>> res = personResolutionController.create(null);
        ResponseApi<Boolean> resApi = res.getBody();

        Assertions.assertEquals(res.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assertions.assertEquals(resApi.isSuccess(), false);
    }
}
