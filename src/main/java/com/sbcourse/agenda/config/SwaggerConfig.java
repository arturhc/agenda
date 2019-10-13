package com.sbcourse.agenda.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import lombok.Data;

@Data
@Component
@EnableSwagger2
@ConfigurationProperties(prefix = "app.swagger")
public class SwaggerConfig {

    private String name;
    private String version;
    private String description;
    private String maintainerName;
    private String maintainerEmail;
    private String maintainerUrl;

    @Bean
    public Docket blogDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Blog Project")
                .select()
                .apis(RequestHandlerSelectors.basePackage(
                        "com.sbcourse.agenda"
                ))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(name)
                .description(description)
                .version(version)
                .contact(new Contact(
                        maintainerName,
                        maintainerUrl,
                        maintainerEmail
                )).build();
    }

}

