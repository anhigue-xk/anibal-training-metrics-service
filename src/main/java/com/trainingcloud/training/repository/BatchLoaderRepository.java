package com.trainingcloud.training.repository;

import org.springframework.data.repository.CrudRepository;

import com.trainingcloud.training.Entity.BatchLoader;

import java.util.Date;
import java.util.List;

public interface BatchLoaderRepository extends CrudRepository<BatchLoader, Long>{
    List<BatchLoader> findByTimestampBetween(Date start, Date end);
}
