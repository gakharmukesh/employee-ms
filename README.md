# employee-ms
Employee MS

The below documentation explains the maven modules in this Employee MS
-------------------------------------------------------------------------------
# employee-parent
This module manages the dependencies for checkout. It is the parent for other modules.
# employee-resourcegen
This module contains only the POM file to generate the server-side code for the services 
defined in the YAML file found in employee-api module.
# employee-services
This module contains the service (domain) implementation.
# employee-repository
This module contains the data persistance.
# employee-resources
This project contains the interfaces for the resources layer.




-----------------------------------------------------------------------------------------


# Prerequisites
You need to install the following tools if you want to run this application:

  JDK 8
  
  Maven (the application is tested with Maven 3.3.3)

# Running the Application

  1. mvn clean install
  
  2. mvn exec:java -D filePath=file:C:\ATTLS\sample.csv
  
sample.csv file is having employee data which will be saved in db during server start up.

 Sample.csv file data format:-
id,firstName,middleInitial,lastName,dateOfBirth,dateOfEmployment,status
101,Mukesh,K,Kumar,2016-02-03,2016-02-03,ACTIVE


#Delete API call

Delete api requires basic Auth with userid:admin and password:admin

#Swagger documentation 

api/swagger-ui.html

#URL

api/employees




