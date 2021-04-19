package com.invillia.SchoolProject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {

        @Bean
        public Docket swagger() {
            return new Docket(DocumentationType.SWAGGER_2).
                    select().
                    apis(RequestHandlerSelectors.basePackage("com.invillia.ProjetoEscola")).
                    paths(PathSelectors.any()).
                    build().
                    apiInfo(mentaInfo());
        }

    private ApiInfo mentaInfo() {
            ApiInfo apiInfo = new ApiInfo(
                    "Escola API REST",
                    "API REST de cadastro de alunos, turmas, mentores e mentorias.",
                    "1.0",
                    "Terms of Service",
                    new Contact("Thalia Schöne", "https://www.linkedin.com/in/thalia-schone/","thalia.schone@gmail.com"),
                    "Apache License Version 2.0",
                    "https://apache.org/licesen.html", new ArrayList<VendorExtension>()
            );
            return apiInfo;
    }

}


