package com.learnwithak.spring.batch.job.writter.jdbc;

import com.learnwithak.spring.batch.job.model.csv.StudentCsv;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
/**If you have requirement to use a PreparedStatement then enable below code.
 *  it will also write the code in my sql database but difference is it is using
 *  prepared statement to store the data.
 *  you can enable one of them. if you will enable both then you will get error.
 */
    /*@Bean
    public JdbcBatchItemWriter<StudentCsv> csvToJdbcBatchItemWriter(){
        JdbcBatchItemWriter<StudentCsv> jdbcBatchItemWriter = new JdbcBatchItemWriter<StudentCsv>();
        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setSql("insert into student(id, first_name,last_name,email) values(?,?,?,?)");
        jdbcBatchItemWriter.setItemPreparedStatementSetter(
                new ItemPreparedStatementSetter<StudentCsv>() {
                    @Override
                    public void setValues(StudentCsv item, PreparedStatement ps) throws SQLException {
                        ps.setLong(1, item.getId());
                        ps.setString(2, item.getFirstName());
                        ps.setString(3, item.getLastName());
                        ps.setString(4, item.getEmail());
                    }
                }
        );
        return jdbcBatchItemWriter;

    }*/
}
