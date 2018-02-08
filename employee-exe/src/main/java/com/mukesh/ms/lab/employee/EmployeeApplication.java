package com.mukesh.ms.lab.employee;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mukesh.ms.lab.employee.entity.EmployeeEntity;
import com.mukesh.ms.lab.employee.repository.EmployeeRepository;






@SpringBootApplication
public class EmployeeApplication  implements CommandLineRunner {

	
	

	public static void main(String[] args) {
		//SpringApplication.run(EmployeeApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(EmployeeApplication.class, args);
		EmployeeRepository repositry=	context.getBean(EmployeeRepository.class);
		System.out.println("Refreshing.....................");
		LocalDate firstDay_2018=LocalDate.of(2018, Month.JANUARY, 1);
		 LocalDate firstDay_2017=LocalDate.of(2017, Month.JANUARY, 1);
		 EmployeeEntity entity=new EmployeeEntity(101,"Mukesh",null,"Kumar",firstDay_2018,firstDay_2018,"ACTIVE");
		 EmployeeEntity entity1=new EmployeeEntity(102,"Rahul","K","Gakhar",firstDay_2017,firstDay_2017,"ACTIVE");
		 repositry.saveAndFlush(entity);
		 repositry.saveAndFlush(entity1);
		 System.out.println(repositry.getClass());
		
	}
	
	public void run(String... strings) throws Exception 
	{
		/* LocalDate firstDay_2018=LocalDate.of(2018, Month.JANUARY, 1);
		 LocalDate firstDay_2017=LocalDate.of(2017, Month.JANUARY, 1);
		 EmployeeEntity entity=new EmployeeEntity(101,"Mukesh",null,"Kumar",firstDay_2018,firstDay_2018,"ACTIVE");
		 EmployeeEntity entity1=new EmployeeEntity(101,"Rahul","K","Gakhar",firstDay_2017,firstDay_2017,"ACTIVE");
		 repository.save(entity);
		 repository.save(entity1);*/
		
		
	}
		
	/*@Bean
	InitializingBean sendDatabase() {
	    return () -> {
	    	 LocalDate firstDay_2018=LocalDate.of(2018, Month.JANUARY, 1);
			 LocalDate firstDay_2017=LocalDate.of(2017, Month.JANUARY, 1);
			 EmployeeEntity entity=new EmployeeEntity(101,"Mukesh",null,"Kumar",firstDay_2018,firstDay_2018,"ACTIVE");
			 EmployeeEntity entity1=new EmployeeEntity(102,"Rahul","K","Gakhar",firstDay_2017,firstDay_2017,"ACTIVE");
			 repository.save(entity);
			 repository.save(entity1);
	      };
	   }*/
	
		
}
