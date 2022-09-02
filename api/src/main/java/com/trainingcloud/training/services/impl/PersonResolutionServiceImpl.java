package com.trainingcloud.training.services.impl;

import com.trainingcloud.training.entities.PersonResolution;
import com.trainingcloud.training.services.PersonResolutionService;
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

        try {
            Iterable<PersonResolution> response = this.personResolutionRepository.findAll();
            response.forEach(list::add);
        } catch (Exception e) {
            throw new Exception("Error get all elements");
        }

        return list;
    }

    @Override
    public Boolean create(PersonResolution personResolution) throws Exception {
        try {
            this.personResolutionRepository.save(personResolution);
        } catch (Exception e) {
            throw new Exception("Error create person resolution");
        }

        return true;
    }

    @Override
    public List<PersonResolution> findByTimestampRange(Date start, Date end) throws Exception {
        List<PersonResolution> personResolutionList = new ArrayList<>();
        try {
            Iterable<PersonResolution> response = this.personResolutionRepository.findByTimestampBetween(start, end);
            response.forEach(personResolutionList::add);
        } catch (Exception e) {
            throw new Exception("Error to get elements");
        }
        return personResolutionList;
    }
}
