package com.trainingcloud.training.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.trainingcloud.training.controller.BatchLoaderController;
import com.trainingcloud.training.entities.BatchLoader;
import com.trainingcloud.training.services.impl.BatchLoaderServiceImpl;

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

import static org.mockito.Mockito.any;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BatchLoaderController.class)
public class BatchLoaderIntegrationTest {

    private final String urlEndpoint = "/v1/batch-loader-metrics";

    @MockBean
    BatchLoaderServiceImpl batchLoaderService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void get_all_batchLoader() throws Exception {
        List<BatchLoader> batchLoaderList = new ArrayList<>();
        batchLoaderList.add(new BatchLoader(1, new Date(2022, 8, 3), null, null, null, null, 0));
        batchLoaderList.add(new BatchLoader(1, new Date(2022, 8, 4), null, null, null, null, 0));

        Mockito.when(batchLoaderService.getAll()).thenReturn(batchLoaderList);

        MvcResult result = mockMvc.perform(get(urlEndpoint))
                .andExpect(status().isOk())
                .andReturn();

        JSONObject content = new JSONObject(result.getResponse().getContentAsString());

        Assertions.assertNotNull(result.getResponse());
        Assertions.assertNotNull(content.has("data"));
        Assertions.assertNotNull(content.has("success"));
    }

    @Test
    public void create_batchLoader_expected_ok() throws Exception {

        Mockito.when(batchLoaderService.create(any(BatchLoader.class))).thenReturn(true);

        JSONObject bodyRequest = new JSONObject();

        bodyRequest.put("timestamp", "2022-08-19T16:03:57.984Z");
        bodyRequest.put("filename", "testing");
        bodyRequest.put("fileCreated", "2022-08-19T16:03:57.984Z");
        bodyRequest.put("filePickedUp", "2022-08-19T16:03:57.984Z");
        bodyRequest.put("fileProcessed", "2022-08-19T16:03:57.984Z");
        bodyRequest.put("numberRecords", "0");

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
