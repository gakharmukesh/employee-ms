package com.mukesh.ms.lab.employee.config;

import java.net.MalformedURLException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.core.io.UrlResource;

import com.mukesh.ms.lab.employee.batch.EmployeeProcessor;
import com.mukesh.ms.lab.employee.batch.mapper.EmployeeFieldSetMapper;
import com.mukesh.ms.lab.employee.batch.writer.EmployeeItemWriter;
import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;




@Configuration
@EnableBatchProcessing
@Lazy
public class CsvFileToDatabaseJobConfig {

	
	
	   
    @Bean
    ItemReader<EmployeeDTO> csvFileItemReader(Environment environment) throws MalformedURLException {
    	FlatFileItemReader<EmployeeDTO> csvFileReader = new FlatFileItemReader<>();
        String filePath=environment.getProperty("filePath");   
        filePath= filePath!=null? filePath:"sample.csv";
        csvFileReader.setResource(new UrlResource(filePath));
        csvFileReader.setLinesToSkip(1);

        csvFileReader.setLineMapper(createEmployeeLineMapper());
        

        return csvFileReader;
    }
    
 

    @Bean
    public LineMapper<EmployeeDTO> createEmployeeLineMapper() {
        DefaultLineMapper<EmployeeDTO> eployeeLineMapper = new DefaultLineMapper<>();

         
        eployeeLineMapper.setLineTokenizer(createEmployeeLineTokenizer());        
        eployeeLineMapper.setFieldSetMapper(createEmployeeFieldSetMapper());

        return eployeeLineMapper;
    }
    
    @Bean
    FieldSetMapper<EmployeeDTO> createEmployeeFieldSetMapper()
    {
    	return new EmployeeFieldSetMapper();
    }
    
 
    @Bean
    public LineTokenizer createEmployeeLineTokenizer() {
    	
        DelimitedLineTokenizer studentLineTokenizer = new DelimitedLineTokenizer();
        studentLineTokenizer.setDelimiter(",");
        studentLineTokenizer.setNames(new String[]{"id", "firstName", "middleInitial","lastName","dateOfBirth","dateOfEmployment","status"});
        return studentLineTokenizer;
    }

    

    @Bean
    ItemProcessor<EmployeeDTO,EmployeeDTO> csvFileItemProcessor() {
        return new EmployeeProcessor();
    }

    @Bean
    public ItemWriter<EmployeeDTO> writer() {
       return  new EmployeeItemWriter();        
    }
   
    @Bean
    Step csvFileToDatabaseStep(ItemReader<EmployeeDTO> csvFileItemReader,
                               ItemProcessor<EmployeeDTO, EmployeeDTO> csvFileItemProcessor,
                               ItemWriter<EmployeeDTO> writer,
                               StepBuilderFactory stepBuilderFactory) {
    	
    	return stepBuilderFactory.get("csvFileToDatabaseStep")
                .<EmployeeDTO, EmployeeDTO>chunk(10)
                .reader(csvFileItemReader)
                .processor(csvFileItemProcessor)
                .writer(writer)
                .build();
        
        
        
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