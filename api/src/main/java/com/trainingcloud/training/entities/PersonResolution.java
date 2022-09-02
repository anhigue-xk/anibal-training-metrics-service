package com.trainingcloud.training.entities;

import java.util.Date;

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
@Table(name = PersonResolution.PERSON_RESOLUTION_TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class PersonResolution {
    
    public static final String PERSON_RESOLUTION_TABLE_NAME = "person_resolution";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private Date timestamp;
    private int individualMatches;
    private int householdMatches;
    private int nonMatches;
    private int errors;
    private String url;

}
