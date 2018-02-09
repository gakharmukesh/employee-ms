package com.mukesh.ms.lab.employee.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.mukesh.ms.lab.employee.batch.EmployeeProcessor;
import com.mukesh.ms.lab.employee.batch.mapper.EmployeeFieldSetMapper;
import com.mukesh.ms.lab.employee.batch.writer.EmployeeItemWriter;
import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;

import javax.sql.DataSource;


@Configuration
@EnableBatchProcessing
public class CsvFileToDatabaseJobConfig {

    
    @Bean
    ItemReader<EmployeeDTO> csvFileItemReader(Environment environment) {
    	FlatFileItemReader<EmployeeDTO> csvFileReader = new FlatFileItemReader<>();
        String Filepath=environment.getProperty("filePath");
        if(Filepath==null)
        {
        	Filepath="sample.csv";
        }
        csvFileReader.setResource(new ClassPathResource(Filepath));
        csvFileReader.setLinesToSkip(1);

        LineMapper<EmployeeDTO> studentLineMapper = createStudentLineMapper();
        csvFileReader.setLineMapper(studentLineMapper);
        

        return csvFileReader;
    }

    private LineMapper<EmployeeDTO> createStudentLineMapper() {
        DefaultLineMapper<EmployeeDTO> studentLineMapper = new DefaultLineMapper<>();

        LineTokenizer employeeLineTokenizer = createEmployeeLineTokenizer();
        studentLineMapper.setLineTokenizer(employeeLineTokenizer);

        FieldSetMapper<EmployeeDTO> employeeInformationMapper = createEmployeeInformationMapper();
        studentLineMapper.setFieldSetMapper(employeeInformationMapper);

        return studentLineMapper;
    }

    private LineTokenizer createEmployeeLineTokenizer() {
    	
        DelimitedLineTokenizer studentLineTokenizer = new DelimitedLineTokenizer();
        studentLineTokenizer.setDelimiter(",");
        studentLineTokenizer.setNames(new String[]{"id", "firstName", "middleInitial","lastName","dateOfBirth","dateOfEmployment","status"});
        return studentLineTokenizer;
    }

    private FieldSetMapper createEmployeeInformationMapper() {        
        return new EmployeeFieldSetMapper();
    }

    @Bean
    ItemProcessor csvFileItemProcessor() {
        return new EmployeeProcessor();
    }

    @Bean
    public ItemWriter writer() {
        EmployeeItemWriter writer = new EmployeeItemWriter();
        return writer;
    }
   
    @Bean
    Step csvFileToDatabaseStep(ItemReader<EmployeeDTO> csvFileItemReader,
                               ItemProcessor<EmployeeDTO, EmployeeDTO> csvFileItemProcessor,
                               ItemWriter<EmployeeDTO> writer,
                               StepBuilderFactory stepBuilderFactory) {
    	
    	Step step= stepBuilderFactory.get("csvFileToDatabaseStep")
                .<EmployeeDTO, EmployeeDTO>chunk(10)
                .reader(csvFileItemReader)
                .processor(csvFileItemProcessor)
                .writer(writer)
                .build();
        
        
        return step;
    }

    @Bean
    Job csvFileToDatabaseJob(JobBuilderFactory jobBuilderFactory,
                             @Qualifier("csvFileToDatabaseStep") Step csvStudentStep) {
    	
        return jobBuilderFactory.get("csvFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .flow(csvStudentStep)
                .end()
                .build();
    }
}