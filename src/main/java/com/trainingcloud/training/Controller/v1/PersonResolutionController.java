package com.trainingcloud.training.Controller.v1;

import com.trainingcloud.training.Entity.PersonResolution;
import com.trainingcloud.training.Services.PersonResolutionService;
import com.trainingcloud.training.Utils.Constants;
import com.trainingcloud.training.Utils.ResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getByTimestampBetween(@RequestParam(required = false) @DateTimeFormat(pattern=Constants.DATE_FORMAT) Date start,
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
    public ResponseEntity<?> createBatch(@RequestBody PersonResolution personResolution) throws Exception {

        Boolean ok = this.personResolutionService.create(personResolution);

        if (ok) {
            return new ResponseEntity<>(new ResponseApi<>(true, true), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseApi<>(false, null), HttpStatus.BAD_REQUEST);

    }

}
