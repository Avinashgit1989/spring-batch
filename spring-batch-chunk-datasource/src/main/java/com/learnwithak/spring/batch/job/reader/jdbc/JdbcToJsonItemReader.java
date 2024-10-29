package com.learnwithak.spring.batch.job.reader.jdbc;

import com.learnwithak.spring.batch.job.model.json.StudentJson;
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
public class JdbcToJsonItemReader {
    public static final Logger LOG = LoggerFactory.getLogger(JdbcToJsonItemReader.class);
    @Autowired
    private DataSource dataSource;
    @Bean
    public JdbcCursorItemReader<StudentJson> jdbcJsonCursorItemReader(){
        JdbcCursorItemReader<StudentJson> jdbcCursorItemReader = new JdbcCursorItemReader<StudentJson>();
        jdbcCursorItemReader.setDataSource(dataSource);

        jdbcCursorItemReader.setSql("select id, first_name as firstName, last_name as lastName, email from student");
        jdbcCursorItemReader.setRowMapper(new BeanPropertyRowMapper<StudentJson>(){
            {setMappedClass(StudentJson.class);}
        });
        return jdbcCursorItemReader;

    }
}