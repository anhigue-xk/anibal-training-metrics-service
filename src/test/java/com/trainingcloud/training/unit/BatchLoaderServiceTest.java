package com.trainingcloud.training.unit;

import com.trainingcloud.training.Entity.BatchLoader;
import com.trainingcloud.training.Services.impl.BatchLoaderServiceImpl;
import com.trainingcloud.training.repository.BatchLoaderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BatchLoaderServiceTest {

    @InjectMocks
    BatchLoaderServiceImpl batchLoaderService;

    @Mock
    BatchLoaderRepository batchLoaderRepository;

    List<BatchLoader> batchLoaderList = new ArrayList<>();

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        batchLoaderList.add(new BatchLoader(1,new Date(2022, 8, 3), null, null, null, null, 0));
        batchLoaderList.add(new BatchLoader(1,new Date(2022, 8, 4), null, null, null, null, 0));

    }

    @Test
    public void findByTimestampBetween_null_values() throws Exception {
        when(batchLoaderRepository.findByTimestampBetween(null, null)).thenReturn(batchLoaderList);
        // test
        List<BatchLoader> empList = batchLoaderService.findByTimestampRange(null, null);
        assertEquals(2, empList.size());
    }

    @Test
    public void findByTimestampBetween_with_values() throws Exception {
        batchLoaderList.remove(0);
        when(batchLoaderRepository.findByTimestampBetween(new Date(2022, 8, 3), new Date(2022, 8, 5))).thenReturn(batchLoaderList);
        // test
        List<BatchLoader> empList = batchLoaderService.findByTimestampRange(new Date(2022, 8, 3), new Date(2022, 8, 5));
        assertEquals(1, empList.size());
    }

    @Test
    public void create_new_PersonResolution() throws Exception {
        BatchLoader batchLoader = new BatchLoader(1,new Date(), null, null, null, null, 0);
        batchLoaderService.create(batchLoader);
        verify(batchLoaderRepository, times(1)).save(batchLoader);
    }

}
