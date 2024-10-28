package com.learnwithak.spring.batch.job.model.xml;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@XmlRootElement(name="student")
public class StudentXml {
    private Long id;
    private String firstName;
    private  String lastName;
    private String email;

}
