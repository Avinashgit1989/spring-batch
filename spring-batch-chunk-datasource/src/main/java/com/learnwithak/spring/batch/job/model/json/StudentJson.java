package com.learnwithak.spring.batch.job.model.json;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StudentJson {
    private Long id;
    private String firstName;
    private  String lastName;
    private String email;

}
