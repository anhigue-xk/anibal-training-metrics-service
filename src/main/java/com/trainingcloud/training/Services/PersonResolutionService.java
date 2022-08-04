package com.trainingcloud.training.Services;


import com.trainingcloud.training.Entity.PersonResolution;

import java.util.Date;
import java.util.List;

public interface PersonResolutionService {
    List<PersonResolution> getAll() throws Exception;
    Boolean create(PersonResolution personResolution) throws Exception;
    List<PersonResolution> findByTimestampRange(Date start, Date end) throws Exception;
}
