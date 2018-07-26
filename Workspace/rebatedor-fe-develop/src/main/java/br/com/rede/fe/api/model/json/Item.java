/* Copyright 2017 Rede S.A.
* ***********************************************************
*Nome: Item
*Descrição: Classe Item
*Autor : Michel Kahan Apt
*Data : 06/10/2017
*Empresa : Iteris Consultoria e Software
* ***********************************************************
*/package br.com.rede.fe.api.model.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {

	@JsonProperty("id")
	private String id;
	@JsonProperty("shipping_type")
	private String shippingType;
	@JsonProperty("type")
	private String type;
	@JsonProperty("description")
	private String description;
	@JsonProperty("quantity")
	private String quantity;
	@JsonProperty("discount")
	private String discount;
	@JsonProperty("freight")
	private String freight;
	@JsonProperty("amount")
	private String amount;
}
