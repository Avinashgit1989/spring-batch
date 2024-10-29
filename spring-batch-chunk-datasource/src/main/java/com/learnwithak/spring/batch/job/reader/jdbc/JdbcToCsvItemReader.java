package com.learnwithak.spring.batch.job.reader.jdbc;

import com.learnwithak.spring.batch.job.model.csv.StudentCsv;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class JdbcToCsvItemReader {
    public static final Logger LOG = LoggerFactory.getLogger(JdbcToCsvItemReader.class);
    @Autowired
    private DataSource dataSource;
    @Bean
    public JdbcCursorItemReader<StudentCsv> jdbcCursorItemReader(){
        JdbcCursorItemReader<StudentCsv> jdbcCursorItemReader = new JdbcCursorItemReader<StudentCsv>();
        jdbcCursorItemReader.setDataSource(dataSource);

        jdbcCursorItemReader.setSql("select id, first_name as firstName, last_name as lastName, email from student");
        jdbcCursorItemReader.setRowMapper(new BeanPropertyRowMapper<StudentCsv>(){
            {setMappedClass(StudentCsv.class);}
        });
        return jdbcCursorItemReader;

    }
}
