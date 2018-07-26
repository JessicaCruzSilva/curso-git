/* Copyright 2017 Rede S.A.
* ***********************************************************
*Nome: RequestTransaction
*Descrição: Classe RequestTransaction
*Autor : Michel Kahan Apt
*Data : 06/10/2017
*Empresa : Iteris Consultoria e Software
* ***********************************************************
*/
package br.com.rede.fe.api.model.json.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.rede.fe.api.model.json.Fields;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestTransaction {

	@JsonProperty("id")
	private String id;
	@JsonProperty("entity")
	private String entity;
	@JsonProperty("fields")
	private Fields fields;
}
