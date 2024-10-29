package com.learnwithak.spring.batch.job.writter.jdbc;

import com.learnwithak.spring.batch.job.model.csv.StudentCsv;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class CsvToJdbcItemWriter {
    public static final Logger LOG = LoggerFactory.getLogger(CsvToJdbcItemWriter.class);
    @Autowired
    private DataSource dataSource;
    @Bean
    public JdbcBatchItemWriter<StudentCsv> csvToJdbcBatchItemWriter(){
        JdbcBatchItemWriter<StudentCsv> jdbcBatchItemWriter = new JdbcBatchItemWriter<StudentCsv>();
        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setSql("insert into student(id, first_name,last_name,email) values(:id, :firstName,:lastName,:email)");
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<StudentCsv>());
        return jdbcBatchItemWriter;

    }
}
