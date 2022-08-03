package com.trainingcloud.training.Entity;

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
@Table(name = PersonResolution.PERSON_RESOLUTION_TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class PersonResolution {
    
    public static final String PERSON_RESOLUTION_TABLE_NAME = "person_resolution";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column
    private Date timestamp;
    
    @Column
    private int individualMatches;
    
    @Column
    private int householdMatches;
    
    @Column
    private int nonMatches;
    
    @Column
    private int errors;
    
    @Column
    private String url;

}
