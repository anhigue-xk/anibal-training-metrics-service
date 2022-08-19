package com.trainingcloud.training.unit.controllers;

import com.trainingcloud.training.controller.BatchLoaderController;
import com.trainingcloud.training.entities.BatchLoader;
import com.trainingcloud.training.services.BatchLoaderService;
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

import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BatchLoaderController.class)
public class BatchLoaderControllerTest {

    @MockBean
    BatchLoaderService batchLoaderService;

    @Autowired
    BatchLoaderController batchLoaderController;

    @Test
    public void getAll_batchLoader_expected_ok() throws Exception {

        List<BatchLoader> batchLoaderList = new ArrayList<>();
        batchLoaderList.add(new BatchLoader(1, new Date(2022, 8, 3), null, null, null, null, 0));
        batchLoaderList.add(new BatchLoader(1, new Date(2022, 8, 4), null, null, null, null, 0));

        Mockito.when(batchLoaderService.getAll()).thenReturn(batchLoaderList);

        ResponseEntity<ResponseApi<List<BatchLoader>>> res = batchLoaderController.getByTimestampBetween(null, null);
        ResponseApi<List<BatchLoader>> resList = res.getBody();

        Assertions.assertEquals(res.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(2, resList.getData().size());

    }

    @Test
    public void create_batchLoader_expected_ok() throws Exception {

        BatchLoader batchLoader = new BatchLoader(1,new Date(), null, null, null, null, 0);

        Mockito.when(batchLoaderService.create(any(BatchLoader.class))).thenReturn(true);
        ResponseEntity<ResponseApi<Boolean>> res = batchLoaderController.create(batchLoader);
        ResponseApi<Boolean> resApi = res.getBody();

        Assertions.assertEquals(res.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(resApi.isSuccess(), true);

    }

    @Test
    public void create_batchLoader_expected_badRequest() throws Exception {
        Mockito.when(batchLoaderService.create(any(BatchLoader.class))).thenReturn(false);
        ResponseEntity<ResponseApi<Boolean>> res = batchLoaderController.create(null);
        ResponseApi<Boolean> resApi = res.getBody();

        Assertions.assertEquals(res.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assertions.assertEquals(resApi.isSuccess(), false);
    }
}
