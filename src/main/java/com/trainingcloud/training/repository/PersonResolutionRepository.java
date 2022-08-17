package com.trainingcloud.training.repository;

import org.springframework.data.repository.CrudRepository;

import com.trainingcloud.training.entities.PersonResolution;

import java.util.Date;
import java.util.List;

public interface PersonResolutionRepository extends CrudRepository<PersonResolution, Long>{
    List<PersonResolution> findByTimestampBetween(Date start, Date end);
}
