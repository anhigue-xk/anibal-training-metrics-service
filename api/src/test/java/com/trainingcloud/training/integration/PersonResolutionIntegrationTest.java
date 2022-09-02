package com.trainingcloud.training.integration;

import com.trainingcloud.training.controller.PersonResolutionController;
import com.trainingcloud.training.entities.BatchLoader;
import com.trainingcloud.training.entities.PersonResolution;
import com.trainingcloud.training.services.PersonResolutionService;
import com.trainingcloud.training.services.impl.PersonResolutionServiceImpl;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.any;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonResolutionController.class)
public class PersonResolutionIntegrationTest {

    private final String urlEndpoint = "/v1/person-resolution-metric";

    @MockBean
    PersonResolutionServiceImpl personResolutionService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void get_all_personResolution() throws Exception {
        List<PersonResolution> personResolutionList = new ArrayList<>();
        personResolutionList.add(new PersonResolution(1, new Date(2022, 8, 2), 0, 0, 0, 0, "test"));
        personResolutionList.add(new PersonResolution(1, new Date(2022, 8, 3), 0, 0, 0, 0, "test"));
        personResolutionList.add(new PersonResolution(1, new Date(2022, 8, 4), 0, 0, 0, 0, "test"));

        Mockito.when(personResolutionService.getAll()).thenReturn(personResolutionList);

        MvcResult result = mockMvc.perform(get(urlEndpoint))
                .andExpect(status().isOk())
                .andReturn();

        JSONObject content = new JSONObject(result.getResponse().getContentAsString());

        Assertions.assertNotNull(result.getResponse());
        Assertions.assertNotNull(content.has("data"));
        Assertions.assertNotNull(content.has("success"));
    }

    @Test
    public void create_personResolution_expected_ok() throws Exception {

        Mockito.when(personResolutionService.create(any(PersonResolution.class))).thenReturn(true);

        JSONObject bodyRequest = new JSONObject();

        bodyRequest.put("timestamp", "2022-08-19T16:03:57.984Z");
        bodyRequest.put("individualMatches", "0");
        bodyRequest.put("householdMatches", "0");
        bodyRequest.put("nonMatches", "0");
        bodyRequest.put("errors", "0");
        bodyRequest.put("url", "testing");

        mockMvc.perform(post(urlEndpoint)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(String.valueOf(bodyRequest))
                )
                .andExpect(status().isOk())
                .andExpect(content().json("{success:true,data:true}"))
                .andReturn();
    }

    @Test
    public void create_batchLoader_expected_badRequest() throws Exception {
        mockMvc.perform(post(urlEndpoint)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isBadRequest());
    }

}
