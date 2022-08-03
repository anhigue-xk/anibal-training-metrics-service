package com.trainingcloud.training.Services.impl;

import com.trainingcloud.training.Entity.BatchLoader;
import com.trainingcloud.training.Services.BatchLoaderService;
import com.trainingcloud.training.repository.BatchLoaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BatchLoaderServiceImpl implements BatchLoaderService {

    @Autowired
    private BatchLoaderRepository batchLoaderRepository;

    @Override
    public List<BatchLoader> getAll() throws Exception {
        return null;
    }

    @Override
    public Boolean create(BatchLoader batchLoader) throws Exception {

        BatchLoader response = this.batchLoaderRepository.save(batchLoader);

        if (response != null) {
            return true;
        }

        return false;
    }

    @Override
    public List<BatchLoader> findByTimestampRange(Date start, Date end) throws Exception {
        return null;
    }
}
