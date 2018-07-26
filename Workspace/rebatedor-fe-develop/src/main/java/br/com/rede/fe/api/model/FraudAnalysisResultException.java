/* Copyright 2017 Rede S.A.
* ***********************************************************
*Nome: FraudAnalysisResultException
*Descrição: Classe FraudAnalysisResultException
*Autor : Michel Kahan Apt
*Data : 06/10/2017
*Empresa : Iteris Consultoria e Software
* ***********************************************************
*/
package br.com.rede.fe.api.model;

public class FraudAnalysisResultException extends RuntimeException {

	private static final long serialVersionUID = -1886891619524282975L;

	public FraudAnalysisResultException() {
		super();
	}

	public FraudAnalysisResultException(String message) {
		super(message);
	}

	public FraudAnalysisResultException(Throwable cause) {
		super(cause);
	}
}
