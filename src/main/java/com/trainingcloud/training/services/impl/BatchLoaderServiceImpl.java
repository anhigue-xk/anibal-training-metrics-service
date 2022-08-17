package com.trainingcloud.training.services.impl;

import com.trainingcloud.training.entities.BatchLoader;
import com.trainingcloud.training.services.BatchLoaderService;
import com.trainingcloud.training.repository.BatchLoaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BatchLoaderServiceImpl implements BatchLoaderService {

    @Autowired
    private BatchLoaderRepository batchLoaderRepository;

    @Override
    public List<BatchLoader> getAll() throws Exception {
        List<BatchLoader> list = new ArrayList<>();

        try {
            Iterable<BatchLoader> response = this.batchLoaderRepository.findAll();
            response.forEach(list::add);
        } catch (Exception e) {
            throw new Exception("Error get all elements");
        }

        return list;
    }

    @Override
    public Boolean create(BatchLoader batchLoader) throws Exception {
        try {
            this.batchLoaderRepository.save(batchLoader);
        } catch (Exception e) {
            throw new Exception("Cannot create a new Batch loader");
        }
        return true;
    }

    @Override
    public List<BatchLoader> findByTimestampRange(Date start, Date end) throws Exception {
        List<BatchLoader> batchLoaderList = new ArrayList<>();
        try {
            Iterable<BatchLoader> response = this.batchLoaderRepository.findByTimestampBetween(start, end);
            response.forEach(batchLoaderList::add);
        } catch (Exception e) {
            throw new Exception("Error to get elements");
        }
        return batchLoaderList;
    }
}
