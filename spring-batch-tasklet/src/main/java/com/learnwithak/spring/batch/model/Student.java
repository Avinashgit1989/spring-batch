package com.learnwithak.spring.batch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="student")
public class Student {

    private Long id;
    private String firstName;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private  String lastName;
    private String email;
}
