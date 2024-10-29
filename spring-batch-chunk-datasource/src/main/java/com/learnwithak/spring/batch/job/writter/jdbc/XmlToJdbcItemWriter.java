package com.learnwithak.spring.batch.job.writter.jdbc;

import com.learnwithak.spring.batch.job.model.json.StudentJson;
import com.learnwithak.spring.batch.job.model.xml.StudentXml;
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
public class XmlToJdbcItemWriter {
    public static final Logger LOG = LoggerFactory.getLogger(XmlToJdbcItemWriter.class);
    @Autowired
    private DataSource dataSource;
    @Bean
    public JdbcBatchItemWriter<StudentXml> xmlToJdbcBatchItemWriter(){
        JdbcBatchItemWriter<StudentXml> jdbcBatchItemWriter = new JdbcBatchItemWriter<StudentXml>();
        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setSql("insert into student(id, first_name,last_name,email) values(:id, :firstName,:lastName,:email)");
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<StudentXml>());
        return jdbcBatchItemWriter;

    }
}
