package com.trainingcloud.training.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private long id;

    @Column
    private Date timestamp;

    @Column
    private String filename;

    @Column
    private Date fileCreated;

    @Column
    private Date filePickedUp;

    @Column
    private Date fileProcessed;

    @Column
    private int numberRecords;

}
