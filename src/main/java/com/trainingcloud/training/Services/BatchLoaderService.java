package com.trainingcloud.training.Services;

import com.trainingcloud.training.Entity.BatchLoader;

import java.util.Date;
import java.util.List;

public interface BatchLoaderService {
    List<BatchLoader> getAll() throws Exception;
    Boolean create(BatchLoader batchLoader) throws Exception;
    List<BatchLoader> findByTimestampRange(Date start, Date end) throws Exception;
}
