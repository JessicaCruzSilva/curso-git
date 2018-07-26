/* Copyright 2017 Rede S.A.
* ***********************************************************
*Nome: FraudAnalysisService
*Descrição: Classe FraudAnalysisService
*Autor : Michel Kahan Apt
*Data : 06/10/2017
*Empresa : Iteris Consultoria e Software
* ***********************************************************
*/
package br.com.rede.fe.api.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import br.com.rede.fe.api.model.FraudAnalysisResult;
import br.com.rede.fe.api.model.json.Entry;
import br.com.rede.fe.api.model.json.LeadLabels;
import br.com.rede.fe.api.model.json.request.RequestTransaction;
import br.com.rede.fe.api.model.json.response.ResponseTransaction;

@Component
@PropertySource("classpath:entry.properties")
public class FraudAnalysisService {	

	@Autowired
	private Environment env;
	
	private final Logger LOGGER = LoggerFactory.getLogger(FraudAnalysisService.class);
	
	/**
	 * Submete uma transação para análise de fraude
	 * @param request Transação a ser submetida
	 * @return ResponseTransaction Resposta do broker da análise de fraude
	 */
	public ResponseTransaction submitTransactionToFraudAnalysis(RequestTransaction request) {
			LOGGER.info("Submetendo transação para análise de fraude...");
			ResponseTransaction response = fillResponseTransaction(request);
			LOGGER.info("Transação analisada com sucesso.");
			return response;
	}
	
	/**
	 * Preenche o mock de resposta do broker de análise de fraude
	 * @param request Transação submetida à análise de fraude
	 * @return ResponseTransaction Resposta do broker da análise de fraude
	 */
	private ResponseTransaction fillResponseTransaction(RequestTransaction request) {
		
		LOGGER.info("Efetuando comunicação com broker e obtendo o resultado da análise de antifraude...");
		FraudAnalysisResult fraudAnalysisResult = FraudAnalysisResult.
				getFraudAnalysisResult(Double.valueOf(request.getFields().getTransactionAmount()));
		LOGGER.info("Resultado obtido com sucesso. Iniciando montagem do response...");
		
		ResponseTransaction response = new ResponseTransaction();
		List<Entry> listEntry = new ArrayList<>();
		Entry entry = new Entry();
		entry.setId(request.getFields().getTransactionId());
		entry.setEntity(env.getProperty("entity"));
		List<String> decisionLabels = new ArrayList<>();
		decisionLabels.add(fraudAnalysisResult.getDecision());
		entry.setDecisionLabels(decisionLabels);
		entry.setDecisionReasons(new ArrayList<>());
		entry.setTimestamp(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
		entry.setDecisionBy(env.getProperty("decisionBy"));
		entry.setNote(env.getProperty("note"));
		entry.setScore(Entry.generateRandomScore());
		entry.setLeadLabels(new LeadLabels());
		entry.setQueue(env.getProperty("queue"));
		entry.setQueueState(env.getProperty("queueState"));
		entry.setAdditionalData(null);
		entry.setRiskLevel(fraudAnalysisResult.getRiskLevel() != null ? fraudAnalysisResult.getRiskLevel() : null);
		entry.setRecommendation(fraudAnalysisResult.getRecommendation() != null ? fraudAnalysisResult.getRecommendation() : null);
		listEntry.add(entry);
		response.setEntries(listEntry);
		response.setType(env.getProperty("type"));
		
		LOGGER.info("Response montado com sucesso.");
		
		return response;
	}
}
