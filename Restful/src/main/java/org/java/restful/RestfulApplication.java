package org.java.restful;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info=@Info(
                title = "Spring REST API Documentation",
                description = "Spring REST API Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "Sabina",
                        email = "javaguides.net@gmsil.com",
                        url = "https://mvnrepository.com"
                ),
                license = @License(
                        name="Apache 2.0",
                        url="https://mvnrepository.com/artifact"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot User Management Documentation",
                url = "https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui"
        )
)
public class RestfulApplication {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(RestfulApplication.class, args);
    }

}
