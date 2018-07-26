/* Copyright 2017 Rede S.A.
* ***********************************************************
*Descrição: Classe FraudeValidations
*Autor : Felipe Silva
*Data : 06/10/2017
*Empresa : Iteris Consultoria e Software
* ***********************************************************
*/
package br.com.rede.fe.api.validation;

import java.util.ArrayList;
import java.util.List;

import br.com.rede.fe.api.model.json.request.RequestTransaction;

public class FraudeValidations {
	
	private static String[] fieldsObrigatorios = { 
	         "transactionId", //transaction_id
	         "source", //source
	         "transactionType", //transaction_type
	         "cardLast4", //card_last4
	         "expirationMonth", //expiration_month
	         "expirationYear", //expiration_year
	         "merchantId", //merchant_id
	         "transactionAmount", //transaction_amount
	         "bin" //bin
    };
	
	/**
	 * Valida campos da transacao
	 * @param request Transacao
	 * @return True se for valida
	 * @throws IllegalArgumentException
	 */
	public static Boolean isValid(RequestTransaction request) throws IllegalArgumentException {
		return isRequiredFieldsNotNullOrEmpty(request) && isDataFormatValid(request);
	}
	
	/**
	 * Valida se os campos requeridos nao sao nulos ou vazios
	 * @param request Transacao
	 * @return True se os campos nao forem nulos ou vazios
	 * @throws IllegalArgumentException
	 */
	private static Boolean isRequiredFieldsNotNullOrEmpty(RequestTransaction request) throws IllegalArgumentException {
		List<String> fieldsWithError = 
				new ArrayList<>();
		
		fieldsWithError.addAll(Validator.isEmptyString(request, "id"));
		fieldsWithError.addAll(Validator.isEmptyString(request.getFields(), fieldsObrigatorios));
		
		fieldsWithError.addAll(Validator.isNull(request, "id"));
		fieldsWithError.addAll(Validator.isNull(request.getFields(), fieldsObrigatorios));
		
		if(!fieldsWithError.isEmpty())
			throw new IllegalArgumentException("invalid fields: " + fieldsWithError.toString());
		
		return true;
	}
	
	/**
	 * Verifica se o formato da data eh valido
	 * @param request Transacao
	 * @return True se o formato for valido
	 */
	private static Boolean isDataFormatValid(RequestTransaction request) {
		List<String> fieldsWithError = new ArrayList<>();
		
		fieldsWithError.addAll(Validator.isDataFormatCorrect(request.getFields(), 
				"yyyy-MM-dd HH:mm:ss", new String[] {"transactionTime", "transactionTimePre"}));
		
		if(!fieldsWithError.isEmpty())
			throw new IllegalArgumentException("invalid fields: " + fieldsWithError.toString());
		
		return true;
	}
}
