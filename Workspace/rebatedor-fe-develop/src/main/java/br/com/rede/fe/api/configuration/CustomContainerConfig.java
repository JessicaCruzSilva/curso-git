/*
*	Copyright 2017 Rede S.A.
*************************************************************
*Nome : CustomContainer
*Descrição: Responsável pelas configurações de url do projeto
*Autor : Michel Kahan Apt
*Data : 09/10/2017
*Empresa : Iteris Consultoria e Software
*************************************************************
*/

package br.com.rede.fe.api.configuration;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

/**
 * Responsável pelas configurações de url do projeto
 */
@Component
public class CustomContainerConfig implements EmbeddedServletContainerCustomizer {

	/**
	 * Efetua a configuração da url base do projeto
	 */
	@Override
	public void customize(final ConfigurableEmbeddedServletContainer container) {
		container.setContextPath("/api/fe");
	}
}