package com.learnwithak.spring.batch.job.model.csv;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StudentCsv {
    private Long id;
    private String firstName;
   // @JsonIgnoreProperties(ignoreUnknown = true)
    private  String lastName;
    private String email;

}
