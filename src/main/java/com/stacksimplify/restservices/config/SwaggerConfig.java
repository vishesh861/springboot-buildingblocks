package com.stacksimplify.restservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
//@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.stacksimplify.restservices"))
				.paths(PathSelectors.ant("/users/**"))
				.build();
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("USER MANAGEMENT SERVICE")
				.description("This page list all api's of user management")
				.version("2.0")
				.contact(new Contact("Vishesh Agarwal","http://www.xnxx.tv/","vishesh861994@gmail.com"))
				.license("License 2.0")
				.licenseUrl("https://www.xnxx.com/license.html")
				.build();
	}
}
