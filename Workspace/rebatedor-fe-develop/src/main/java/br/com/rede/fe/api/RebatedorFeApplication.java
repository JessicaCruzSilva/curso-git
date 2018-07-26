/* Copyright 2017 Rede S.A.
* ***********************************************************
*Nome: RebatedorFeApplication
*Descrição: Classe RebatedorFeApplication
*Autor : Michel Kahan Apt
*Data : 06/10/2017
*Empresa : Iteris Consultoria e Software
* ***********************************************************
*/
package br.com.rede.fe.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class RebatedorFeApplication {
	public static void main(String[] args) {
		SpringApplication.run(RebatedorFeApplication.class, args);
	}
}
