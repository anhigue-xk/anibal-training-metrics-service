package com.trainingcloud.training.controller;

import com.trainingcloud.training.entities.PersonResolution;
import com.trainingcloud.training.services.PersonResolutionService;
import com.trainingcloud.training.utilities.Constants;
import com.trainingcloud.training.utilities.ResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/person-resolution-metric", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonResolutionController {

    @Autowired
    private final PersonResolutionService personResolutionService;

    public PersonResolutionController(PersonResolutionService personResolutionService) {
        this.personResolutionService = personResolutionService;
    }

    @GetMapping
    public ResponseEntity<ResponseApi<List<PersonResolution>>> getByTimestampBetween(@RequestParam(required = false) @DateTimeFormat(pattern=Constants.DATE_FORMAT) Date start,
                                                                                @RequestParam(required = false) @DateTimeFormat(pattern=Constants.DATE_FORMAT)Date end) throws Exception {

        List<PersonResolution> personResolutionList;

        if (start != null && end != null) {
            personResolutionList = this.personResolutionService.findByTimestampRange(start, end);
        } else {
            personResolutionList = this.personResolutionService.getAll();
        }

        return new ResponseEntity<>(new ResponseApi<>(true, personResolutionList), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseApi<Boolean>> createBatch(@RequestBody PersonResolution personResolution) throws Exception {

        Boolean ok = this.personResolutionService.create(personResolution);

        if (ok) {
            return new ResponseEntity<>(new ResponseApi<>(true, true), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseApi<>(false, null), HttpStatus.BAD_REQUEST);

    }

}
