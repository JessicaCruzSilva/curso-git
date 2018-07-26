/* Copyright 2017 Rede S.A.
* ***********************************************************
*Nome: ResponseTransaction
*Descrição: Classe ResponseTransaction
*Autor : Michel Kahan Apt
*Data : 06/10/2017
*Empresa : Iteris Consultoria e Software
* ***********************************************************
*/
package br.com.rede.fe.api.model.json.response;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.rede.fe.api.model.json.Entry;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseTransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseTransaction.class);
	
    @JsonProperty("entries")
    private List<Entry> entries = new ArrayList<Entry>();
    @JsonProperty("type")
    private String type;
	
    @Override
	public String toString() {
    	try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			LOGGER.error("Erro no toString()", e);
		}
		return "{}";
	}
}
