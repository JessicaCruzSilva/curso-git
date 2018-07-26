/* Copyright 2017 Rede S.A.
* ***********************************************************
*Descrição: Classe FraudAnalysisController
*Autor : Felipe Silva / Frank Wendel
*Data : 06/10/2017
*Empresa : Iteris Consultoria e Software
* ***********************************************************
*/
package br.com.rede.fe.api.validation;


import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;


public class Validator {
	/**
	 * Validador de numero de documento CPF
	 * @param documentNumber Numero do documento CPF
	 * @return True se for invalido
	 */
	public static boolean DocumentNumberInvalid(String documentNumber) {
		// considera-se erro CPF's formadocumentNumber por uma sequencia de numeros
		// iguais
		if (documentNumber.equals("00000000000") || documentNumber.equals("11111111111")
				|| documentNumber.equals("22222222222") || documentNumber.equals("33333333333")
				|| documentNumber.equals("44444444444") || documentNumber.equals("55555555555")
				|| documentNumber.equals("66666666666") || documentNumber.equals("77777777777")
				|| documentNumber.equals("88888888888") || documentNumber.equals("99999999999")
				|| (documentNumber.length() != 11)) {
			return (true);
		}
			

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do documentNumber em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (documentNumber.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (documentNumber.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == documentNumber.charAt(9)) && (dig11 == documentNumber.charAt(10)))
				return (false);
			else
				return (true);
		} catch (InputMismatchException erro) {
			return (true);
		}
	}

	/**
	 * Validador de numeros
	 * @param number Numero
	 * @return True se o numero for invalido
	 */
	public static boolean ValidateInvalidNumber(String number) {
		return !tryParseInt(number);
	}

	/**
	 * Valida se o campo tem caracteres especiais
	 * @param field Campo que sera validado
	 * @return True se conter caracteres especiais
	 */
	public static boolean ValidateAlphaNumericSpace(String field) {
		return !field.matches("^[\\p{IsAlphabetic}\\p{IsDigit} ]+$");
	}

	/**
	 * Valida se o campo tem caracteres especiais ou espacos
	 * @param field Campo que sera validado
	 * @return True se conter caracteres especiais ou espacos
	 */
	public static boolean ValidateAlphaNumericOnly(String field) {
		return !field.matches("^[\\p{IsAlphabetic}\\p{IsDigit}]+$");
	}

	/**
	 * Valida se o campo contem unicamente espacos em branco
	 * @param field Campo que sera validado
	 * @return True se conter somente espacos em branco
	 */
	public static boolean ValidateWhiteSpaceOnly(String field) {
		return field.matches("^\\s+$");
	}

	/**
	 * Valida se o campo eh NULL, vazio ou com espacos em branco
	 * @param field Campo que sera validado
	 * @return True se for Null, Vazio ou com espacos em branco
	 */
	public static boolean ValidateEmptyField(String field) {
		return field.isEmpty();
	}

	/**
	 * Valida se o campo contem espacos em branco
	 * @param field Campo que sera validado
	 * @return True se estiver vazio ou contiver espacos em branco
	 */
	public static boolean ValidateContainsWhiteSpace(String field) {
		if (field.equals("")) {
			return true;
		}
		return field.contains(" ");
	}

	/**
	 * Valida URL
	 * @param url
	 * @return True se o URL for invalido
	 */
	public static boolean ValidateUrl(String url) {
		return !(url.matches("^http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?"));
	}

	/**
	 * Valida se o valor passado respeita o tamanho maximo
	 * @param field Campo que sera validado
	 * @param length Tamanho maximo
	 * @return True se o campo for maior que o tamanho maximo
	 */
	public static boolean ValidateLength(String field, int length) {
		if (field.length() > length) {
			return true;
		}
		return false;
	}

	/**
	 * Valida se o montante possui tamanho valido
	 * @param amount montante
	 * @return True se o montante for maior que 10 (Invalido)
	 */
	public static boolean AmountLengthTooLong(String amount) {
		if(!tryParseInt(amount)){
			return true;
		}
		return ValidateLength(amount, 10);
	}

	/**
	 * Valida o montante
	 * @param amount montante
	 * @return True se o montante for invalido
	 */
	public static boolean AmountFormatInvalid(String amount) {
        return !tryParseInt(amount) || amount.contains(",") || amount.contains(".") || ValidateContainsWhiteSpace(amount);
	}

	/**
	 * Checa se a data Iso eh valida
	 * @param date data
	 * @return True se a data Iso for invalida
	 */
	public static boolean IsInvalidIsoDate(String date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			formatter.parse(date);
		} catch (DateTimeParseException ex) {
			System.out.println("Erro ao formatar data: " + ex.getMessage());
			return true;
		} catch (DateTimeException ex) {
			System.out.println("Erro ao formatar data: " + ex.getMessage());
			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return true;
		}
		return false;
	}
	
	/**
	 * Valida se a String esta vazia
	 * @param o
	 * @param fields
	 * @return Lista de String
	 */
	public static List<String> isEmptyString(Object o, String... fields){
		List<String> fieldsCheck =
				new ArrayList<>();
		
		Arrays.asList(fields).forEach((field) -> {
			try {
				Class<?> c = o.getClass();
				Field f = c.getDeclaredField(field);
				f.setAccessible(true);
				if(((String) f.get(o)).trim().equals(""))
					fieldsCheck.add(field);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});

		return fieldsCheck;
	}
	
	/**
	 * Valida se o formato da data esta correto
	 * @param o
	 * @param format
	 * @param fields
	 * @return Lista de String
	 */
	public static List<String> isDataFormatCorrect(Object o, String format, String... fields){
		List<String> fieldsCheck =
				new ArrayList<>();
		
		Arrays.asList(fields).forEach((field) -> {
			
				Class<?> c = o.getClass();
				Field f;
				try {
					f = c.getDeclaredField(field);
					f.setAccessible(true);
					if(f.get(o) != null) {
						String date = (String) f.get(o);
						DateTime.parse(date, DateTimeFormat.forPattern(format));				
					}						
				} catch (NoSuchFieldException | SecurityException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException | IllegalAccessException e) {
					fieldsCheck.add(field);
				} 
				
			
		});

		return fieldsCheck;
	}
	
	/**
	 * Checa se o valor dos atributos eh invalido em um objeto
	 * @param str Valor
	 * @return True se o valor for invalido
	 */
	public static List<String> isNull(Object o, String... fields) {
		List<String> fieldsCheck =
				new ArrayList<>();
		
		Arrays.asList(fields).forEach((field) -> {
			try {
				Class<?> c = o.getClass();
				Field f = c.getDeclaredField(field);
				f.setAccessible(true);
				if(f.get(o) == null)
					fieldsCheck.add(field);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});

		return fieldsCheck;
	}
	
	/**
	 * Valida o tamanho maximo do campo Affiliation
	 * @param field Campo que sera validado
	 * @return True se o tamanho for maior que 9 (Invalido)
	 */
	public static boolean AffiliationLengthTooLong(String field) {
		return ValidateLength(field, 9);
	}

	/**
	 * Valida se o montante for igual a 0
	 * @param amount valor do montante
	 * @return True se o montante for igual a zero
	 */
	public static boolean AmountEqualsZero(String amount) {
		return Integer.parseInt(amount) == 0;
	}

	/**
	 * Valida se o TID eh maior que o tamanho maximo
	 * @param tid TID
	 * @return True se o tamanho for maior que o permitido
	 */
	public static boolean TidLengthTooLong(String tid) {
		return ValidateLength(tid, 20);
	}
	
	/**
	 * Valida se o TID eh um numero
	 * @param tid TID
	 * @return True se tid for um numero
	 */
	public static boolean TidFormatInvalid(String tid) {
		// Usando Regex porque o valor de TID pode ser maior que o tamanho de um Long
		if (tid.matches("\\d+")) {
			return true;
		}
		return false;
	}

	/**
	 * Valida se a referencia eh muito longa
	 * @param reference Referencia
	 * @return Verdadeiro se o tamanho for maior que o permitido
	 */
	public static boolean ReferenceLengthTooLong(String reference) {
		return ValidateLength(reference, 16);
	}

	/**
	 * Valida se o mes for invalido
	 * @param month Mes
	 * @return True se o mes for invalido
	 */
	public static boolean MonthInvalid(String month) {
		int checkMonth;
		checkMonth = Integer.parseInt(month);
		return month.length() != 2 || ValidateContainsWhiteSpace(month) || !tryParseInt(month) || checkMonth <= 0 || checkMonth > 12;
	}

	/**
	 * Verifica se a String passada pode ser convertida em INT
	 * @param value Valor em String
	 * @return True se for possivel converter com sucesso
	 */
	public static boolean tryParseInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException ex) {
			System.out.println("Erro ao formatar numero: " + ex.getMessage());
			return false;
		} catch (Exception ex) {
			System.out.println("Erro ao formatar numero: " + ex.getMessage());
			return false;
		}
	}
	
	/**
	 * Verifica se a String passada pode ser convertida em LONG
	 * @param value Valor em String
	 * @return True se for possivel converter com sucesso
	 */
	public static boolean tryParseLong(String value) {
		try {
			Long.parseLong(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * Verifica se a String passada pode ser convertida em BOOLEAN
	 * @param value Valor em String
	 * @return True se for possivel converter com sucesso
	 */
	public static boolean tryParseBoolean(String value) {
		try {
			Boolean.parseBoolean(value);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	/**
	 * Valida ano
	 * @param year Ano
	 * @return True se o tamanho do ano eh 2 ou 4 (Invalido)
	 */
	public static boolean YearLengthInvalid(String year) {
		if (year.length() != 2 && year.length() != 4) {
			return true;
		}
		return false;
	}

	/**
	 * Valida digitacao do ano
	 * @param year ano
	 * @return True se o ano for invalido
	 */
	public static boolean YearInvalid(String year) {
		return ValidateContainsWhiteSpace(year) || !tryParseInt(year);
	}

	/**
	 * Valida se o cartao de credito possui data expirada
	 * @param year Ano
	 * @param month Mes
	 * @return True se data for invalida
	 */
	public static boolean CreditCardExpiredDate(String year, String month) {
		int checkMonth = Integer.parseInt(month);
		int checkYear = Integer.parseInt(year);

		if (checkYear < 100) {
			checkYear += 2000;
		}
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH);

		if (checkYear < currentYear || (checkYear == currentYear && checkMonth < currentMonth)) {
			return true;
		}

		return false;
	}

	/**
	 * Valida Origem
	 * @param origin Origem
	 * @return True se a Origin for invalida
	 */
	public static boolean OriginInvalid(String origin) {
		return origin.equals("01") != false && origin.equals("02") != false && origin.equals("04") != false
				&& origin.equals("05") != false && origin.equals("06") != false && origin.equals("1") != false
				&& origin.equals("2") != false && origin.equals("4") != false && origin.equals("5") != false
				&& origin.equals("6") != false;
	}

	/**
	 * Checa se a referencia contem caracteres invalidos
	 * @param reference referencia
	 * @return True se conter caracteres invalidos
	 */
	public static boolean ReferenceContainsInvalidCharacters(String reference) {
		return !(reference.matches("^[a-zA-Z0-9\\.\\-]+$"));
	}

	/**
	 * Valida se tamanho do numero de cart�o de cr�dito � invalido
	 * @param cardNumber Numero de cart�o de cr�dito
	 * @return True se o numero do cart�o de cr�dito for invalido
	 */
	public static boolean CardNumberInvalidLength(String cardNumber) {
		return cardNumber.length() < 13 || cardNumber.length() > 19;
	}

	/**
	 * Valida o numero de cartao de credito usando 'Algoritmo de Luhn' 
	 * @param cardNumber
	 * @return True se o numero de cartao de credito for invalido
	 */
	public static boolean CardNumberInvalid(String cardNumber) {
		if (!tryParseLong(cardNumber) || ValidateContainsWhiteSpace(cardNumber)) {
			return true;
		}

		// Algoritmo de Luhn
		int tmp, sum = 0;

		int limit = cardNumber.length() - 1;
		int position = 1;

		for (int i = limit; i >= 0; i--, position++) {
			tmp = ((int) cardNumber.charAt(i) - 48);

			if (position % 2 == 0) {
				tmp *= 2;

				if (tmp >= 10)
					tmp -= 9;
			}
			sum += tmp;
		}
		return sum % 10 != 0;
	}

	/**
	 * Checa se o titular do cartao tem um tamanho de nome invalido
	 * @param cardholderName Titular do cartao
	 * @return True se o tamanho for invalido
	 */
	public static boolean CardholderNameInvalidLength(String cardholderName) {
		return cardholderName.isEmpty() || cardholderName.length() > 30;
	}
	
	/**
	 * Valida se as prestacoes sao invalidas
	 * @param installments Prestacoes
	 * @return True se as prestacoes forem invalidas
	 */
	public static boolean InstallmentsInvalid(String installments){
        int checkMonth = Integer.parseInt(installments);
        return !tryParseInt(installments) || checkMonth < 0 || checkMonth > 21;
    }
	
	/**
	 * Valida se os AVS estao preenchidos
	 * @param documentNumber numero do documento (CPF)
	 * @param addressStreet Endereco de rua
	 * @param addressNumber Endereco de numero
	 * @param addressPostalCode Endereco de codigo postal
	 * @return True se os parametros estao preenchidos
	 */
    public static boolean AvsParametersFilled(String documentNumber, String addressStreet, String addressNumber, String addressPostalCode){
        List<String> avsParameters = new ArrayList<String>();
        avsParameters.add(documentNumber);
        avsParameters.add(addressStreet);
        avsParameters.add(addressNumber);
        avsParameters.add(addressPostalCode);

        int emptyCount = 0;
        for(int i = 0; i < avsParameters.size(); i++) {
        	emptyCount += (ValidateEmptyField(avsParameters.get(i)) ? 0 : 1);
        }
        return emptyCount != 4;
    }
}
