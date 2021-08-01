package com.WuzzufWebProject.com.WuzzufWebProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*
    localhost:5000/CSV?name=Wuzzuf_Jobs.csv
    localhost:5000/count?service=Company
    localhost:5000/count?service=Title
    localhost:5000/count?service=Skills
    localhost:5000/Summary
    localhost:5000/Structure
    localhost:5000/factorized
*/


@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class mainClass {
       /*Used Port = 5000*/
      
       public static void main(String[] args) throws Exception
       {             
           SpringApplication.run(mainClass.class, args);
       }        
}
 