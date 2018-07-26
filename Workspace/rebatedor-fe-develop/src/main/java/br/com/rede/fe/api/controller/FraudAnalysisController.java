/* Copyright 2017 Rede S.A.
* ***********************************************************
*Nome: FraudAnalysisController
*Descrição: Classe FraudAnalysisController
*Autor : Michel Kahan Apt
*Data : 06/10/2017
*Empresa : Iteris Consultoria e Software
* ***********************************************************
*/
package br.com.rede.fe.api.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rede.fe.api.model.FraudAnalysisResultException;
import br.com.rede.fe.api.model.json.request.RequestTransaction;
import br.com.rede.fe.api.model.json.response.ResponseTransaction;
import br.com.rede.fe.api.service.FraudAnalysisService;
import br.com.rede.fe.api.validation.FraudeValidations;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "FraudAnalysis", description = "Análise de Fraudes")
public class FraudAnalysisController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FraudAnalysisController.class);
	
	@Autowired
	private FraudAnalysisService service;
	
	/**
	 * Método inicial para análise de fraude
	 * @param request Transação a ser analisada
	 * @return ResponseTransaction
	 */
	@RequestMapping(value = "/fraude", method = RequestMethod.POST, produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok", response = ResponseTransaction.class),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	public ResponseEntity<?> submitToFraudAnalysis(@RequestBody RequestTransaction request) {		
		ResponseTransaction response;
		try {
			FraudeValidations.isValid(request);
			response = service.submitTransactionToFraudAnalysis(request);
			LOGGER.info(request.toString());
			return new ResponseEntity<ResponseTransaction>(response, HttpStatus.OK);
		} catch(IllegalArgumentException e) {
			return new ResponseEntity<Object>(new HashMap<String, String>(){
				{
					put("error", e.getMessage());
				}
			}, HttpStatus.BAD_REQUEST);
			
		} catch (FraudAnalysisResultException e) {
			LOGGER.error("Erro ao tentar analisar a transação", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			LOGGER.error("Exception", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
