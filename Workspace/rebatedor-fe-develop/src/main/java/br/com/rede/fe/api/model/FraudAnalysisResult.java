/* Copyright 2017 Rede S.A.
* ***********************************************************
*Nome: FraudAnalysisResult
*Descrição: Classe FraudAnalysisResult
*Autor : Michel Kahan Apt
*Data : 06/10/2017
*Empresa : Iteris Consultoria e Software
* ***********************************************************
*/
package br.com.rede.fe.api.model;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class FraudAnalysisResult {

	private static final Logger LOGGER = LoggerFactory.getLogger(FraudAnalysisResult.class);
	
	private static final String SCENARIOS_SOURCEFILE = "antifraudScenarios.json";
	
	private Double maxValue;
	
	private Double minValue;
	
	private String decision;
	
	private String riskLevel;
	
	private String recommendation;
	
	/**
	 * Carrega uma lista de cenários parametrizados para análise de fraude
	 * @return Lista com cenários
	 */
	private static List<FraudAnalysisResult> fetchScenariosList() {
		try {
			ClassPathResource file = new ClassPathResource(SCENARIOS_SOURCEFILE);
			byte[] fileData = FileCopyUtils.copyToByteArray(file.getInputStream());
			return new ObjectMapper().readValue(fileData, new TypeReference<List<FraudAnalysisResult>>(){});
		} catch (IOException e) {
			LOGGER.error("Erro ao manipular arquivo com cenários", e);
			throw new FraudAnalysisResultException();
		} catch (Exception e) {
			LOGGER.error("Erro desconhecido ao manipular arquivo com cenários", e);
			throw new FraudAnalysisResultException();
		}
	}
 	
	/**
	 * Retorna o resultado da análise de fraude
	 * @param amount Valor da transação
	 * @return FraudAnalysisResult
	 */
	public static FraudAnalysisResult getFraudAnalysisResult(Double amount) {
		List<FraudAnalysisResult> scenariosList = fetchScenariosList();
		for (FraudAnalysisResult scenario : scenariosList) {
			scenario.minValue = scenario.minValue == null ? 0 : scenario.minValue;
			scenario.maxValue = scenario.maxValue == null ? Double.MAX_VALUE : scenario.maxValue;
			
			if(amount >= scenario.minValue && amount <= scenario.maxValue) {
				return scenario;
			}
		}
		
		LOGGER.error("Não há cenário compatível "
				+ "a partir dos parâmetros utilizados.");
		throw new FraudAnalysisResultException();
	}
}
