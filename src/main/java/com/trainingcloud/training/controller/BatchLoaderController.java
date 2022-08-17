package com.trainingcloud.training.controller;

import com.trainingcloud.training.entities.BatchLoader;
import com.trainingcloud.training.services.BatchLoaderService;
import com.trainingcloud.training.utils.Constants;
import com.trainingcloud.training.utils.ResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/batch-loader-metrics", produces = MediaType.APPLICATION_JSON_VALUE)
public class BatchLoaderController {

    @Autowired
    private final BatchLoaderService batchLoaderService;

    public BatchLoaderController(BatchLoaderService batchLoaderService) {
        this.batchLoaderService = batchLoaderService;
    }

    @GetMapping
    public ResponseEntity<?> getByTimestampBetween(@RequestParam(required = false) @DateTimeFormat(pattern= Constants.DATE_FORMAT) Date start,
                                                        @RequestParam(required = false) @DateTimeFormat(pattern=Constants.DATE_FORMAT)Date end) throws Exception {
        List<BatchLoader> batchLoaderList;
        if (start != null && end != null) {
            batchLoaderList = this.batchLoaderService.findByTimestampRange(start, end);
        } else {
            batchLoaderList = this.batchLoaderService.getAll();
        }

        return new ResponseEntity<>(new ResponseApi<>(true, batchLoaderList), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BatchLoader batchLoader) throws Exception {
        Boolean ok = this.batchLoaderService.create(batchLoader);

        if (ok) {
            return new ResponseEntity<>(new ResponseApi<>(true, true), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseApi<>(false, null), HttpStatus.BAD_REQUEST);
    }
}
