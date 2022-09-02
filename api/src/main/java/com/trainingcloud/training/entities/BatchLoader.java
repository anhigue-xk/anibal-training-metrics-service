package com.trainingcloud.training.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = BatchLoader.BATCH_LOADER_TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class BatchLoader {
    
    public static final String BATCH_LOADER_TABLE_NAME = "batch_loader";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @NotNull
    private long id;
    private Date timestamp;
    private String filename;
    private Date fileCreated;
    private Date filePickedUp;
    private Date fileProcessed;
    private int numberRecords;

}
