/*
*	Copyright 2017 Rede S.A.
*************************************************************
*Nome : WebConfig
*Descrição: Redirecionamento da URL do Swagger
*Autor : Michel Kahan Apt
*Data : 09/10/2017
*Empresa : Iteris Consultoria e Software
*************************************************************
*/

package br.com.rede.fe.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	/**
	 * Registro da configuração do redirecionamento da url do Swagger
	 */
	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addRedirectViewController("/swagger", "swagger-ui.html");
	}
}