package com.room.util;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.Contact;
import springfox.documentation.service.ApiInfo;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	    @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()    
	          .apis(RequestHandlerSelectors.basePackage("com.room"))
	         // .paths(PathSelectors.ant("/api/*/*"))
	         // .apis(RequestHandlerSelectors.any())              
	          .paths(PathSelectors.any())                          
	          .build()
	        .apiInfo(apiDetails());                                           

	    }
	    
	    private ApiInfo apiDetails()
	    {
	   	return new ApiInfo(
	   			"Room-Mangemenet", 
	   			"description", 
	   			"version 1.0", 
	   			"Terms-Free To Use", 
	   			new springfox.documentation.service.Contact("Amar", "amar.com", "amarmahamuni02@gmail.com"), 
	   			"Api license", 
	   			"licenseUrl", 
	   			Collections.emptyList());  	 
	    }

	


}
