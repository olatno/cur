# CURRENCY

We are using REST COUNTRIES Api to get information about countries via a RESTful API. We only interested in the country ISO 3166-1 3-letter codes and the list of the bordering countries. The list of bordering countries is then used as a filter to get list of countries using different currency. 

# Building and Running local environment

The task completed using microservices with Spring Boot. The IDE used for the task is intellij, and it is maven build. Below are steps to be taken in order to deploy in the local environment.

 1. Unzip the file and please the folder 'cur' in the build directory.
  
 2. If using intellij, please do file -> open -> cur. This allows to open the cur project in the intellij.
 
 3. Select the maven task in the right hand side of the project.
 
 4. Expand the CUR maven module, the is parent module of the project.
 
 5. Click on clean and thereafter click on compile.
 
 6. At the top bar click on Edit Configuration and select CURServerApplication.
 
 7. Click on the green arrow button to run the CURServerApplication.
 
 8. After running, project will be deployed inside tomcat localhost on default port 8080. 
 
 9. Open postman and input thr uri localhost:8080/borderInfo?currency=eur selecting GET request. 
