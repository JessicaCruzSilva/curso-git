/* Copyright 2017 Rede S.A.
* ***********************************************************
*Nome: Entry
*Descrição: Classe Entry
*Autor : Michel Kahan Apt
*Data : 06/10/2017
*Empresa : Iteris Consultoria e Software
* ***********************************************************
*/
package br.com.rede.fe.api.model.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Entry {

	@JsonProperty("id")
	private String id;
	@JsonProperty("entity")
	private String entity;
	@JsonProperty("decisionLabels")	
	private List<String> decisionLabels = new ArrayList<String>();
	@JsonIgnore
	private List<Object> decisionReasons = new ArrayList<Object>();
	@JsonProperty("timestamp")
	private String timestamp;
	@JsonProperty("decisionBy")
	private String decisionBy;
	@JsonProperty("note")
	private String note;
	@JsonProperty("score")
	private int score;
	@JsonIgnore
	private LeadLabels leadLabels;
	@JsonProperty("queue")
	private Object queue;
	@JsonProperty("queueState")
	private String queueState;
	@JsonProperty("additionalData")
	private Object additionalData;
	@JsonProperty("risk_level")
	private String riskLevel;
	@JsonProperty("recommendation")
	private Object recommendation;
	
	/**
	 * Gera um score aleatório negativo entre 0 e 100 para a transação
	 * @return int
	 */
	@JsonIgnore
	public static int generateRandomScore() {
		Random random = new Random();
		return random.nextInt(100) * -1;
	}
}
