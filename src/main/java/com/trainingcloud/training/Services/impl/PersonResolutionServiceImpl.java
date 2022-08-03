package com.trainingcloud.training.Services.impl;

import com.trainingcloud.training.Entity.PersonResolution;
import com.trainingcloud.training.Services.PersonResolutionService;
import com.trainingcloud.training.repository.PersonResolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PersonResolutionServiceImpl implements PersonResolutionService {

    @Autowired
    PersonResolutionRepository personResolutionRepository;

    @Override
    public List<PersonResolution> getAll() throws Exception {
        List<PersonResolution> list = new ArrayList<>();
        Iterable<PersonResolution> response = this.personResolutionRepository.findAll();

        if (response != null){
            response.forEach(list::add);
        } else {
            throw new Exception("No elements in the list");
        }

        return list;
    }

    @Override
    public Boolean create(PersonResolution personResolution) throws Exception {

        PersonResolution response = this.personResolutionRepository.save(personResolution);

        if (response != null) {
            return true;
        }

        return false;
    }

    @Override
    public List<PersonResolution> findByTimestampRange(Date start, Date end) throws Exception {
        return this.personResolutionRepository.findByTimestampBetween(start, end);
    }
}
