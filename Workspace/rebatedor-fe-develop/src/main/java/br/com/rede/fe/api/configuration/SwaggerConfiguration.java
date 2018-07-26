/* Copyright 2017 Rede S.A.
* ***********************************************************
*Nome: SwaggerConfiguration
*Descrição: Classe SwaggerConfiguration
*Autor : Michel Kahan Apt
*Data : 06/10/2017
*Empresa : Iteris Consultoria e Software
* ***********************************************************
*/
package br.com.rede.fe.api.configuration;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	/**
	 * Configurações do Swagger
	 * 
	 * @return Configurações do Swagger
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("rede")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.rede.fe.api.controller"))
				.paths(regex("/.*")).build();
	}

	/**
	 * Configuração de informações da Api
	 * 
	 * @return Informações da Api
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Rebatedor FE").description("Rebatedor FE")
				.termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
				.license("Apache License Version 2.0").licenseUrl("").version("1.0").build();
	}
}
