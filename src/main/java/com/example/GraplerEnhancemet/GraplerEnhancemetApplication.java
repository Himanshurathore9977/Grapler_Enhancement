package com.example.GraplerEnhancemet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
@SpringBootApplication
@OpenAPIDefinition(
	       info= @Info ( title = "Grappler Enhancement - Module 1",
	             version="1.0.0",
	             description="The project is to define the structure of APIs in Role Based Access Controll and Hierarchy",
	             termsOfService="https://grappler.innogent.in/dashboard"  ,
	             contact = @Contact ( name = "Himanshu Rathore , Vanshika , Niraj" , email ="dishika.jain@innogent.in ,abhishek.patidar@innogent.in , rishi.raghuvanshi@innogent.in " ) ,
	             license = @License ( name = "licence",url="https://github.com/rishiraghuvanshi08/Grappler-Enhancement")

       )
)
public class GraplerEnhancemetApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraplerEnhancemetApplication.class, args);
	}

}
