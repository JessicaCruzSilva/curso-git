/* Copyright 2017 Rede S.A.
* ***********************************************************
*Nome: Fields
*Descrição: Classe Fields
*Autor : Michel Kahan Apt
*Data : 06/10/2017
*Empresa : Iteris Consultoria e Software
* ***********************************************************
*/
package br.com.rede.fe.api.model.json;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Fields {

	@JsonProperty("transaction_id")
	private String transactionId;
	@JsonProperty("source")
	private String source;
	@JsonProperty("transaction_type")
	private String transactionType;
	@JsonProperty("number_of_attemps")
	private int numberOfAttemps;
	@JsonProperty("card_last4")
	private String cardLast4;
	@JsonProperty("expiration_month")
	private String expirationMonth;
	@JsonProperty("expiration_year")
	private String expirationYear;
	@JsonProperty("card_holder_name")
	private String cardHolderName;
	@JsonProperty("cvv")
	private String cvv;
	@JsonProperty("client_type")
	private String clientType;
	@JsonProperty("card_type")
	private String cardType;
	@JsonProperty("ip")
	private String ip;
	@JsonProperty("fingerprint")
	private String fingerprint;
	@JsonProperty("merchant_name_registered")
	private String merchantNameRegistered;
	@JsonProperty("merchant_id")
	private String merchantId;
	@JsonProperty("merchant_doc_type")
	private String merchantDocType;
	@JsonProperty("merchant_doc")
	private String merchantDoc;
	@JsonProperty("mcc")
	private String mcc;
	@JsonProperty("merchant_account_creation_date")
	private String merchantAccountCreationDate;
	@JsonProperty("cpf")
	private String cpf;
	@JsonProperty("user_name")
	private String userName;
	@JsonProperty("user_gender")
	private String userGender;
	@JsonProperty("user_document_type")
	private String userDocumentType;
	@JsonProperty("user_document_number")
	private String userDocumentNumber;
	@JsonProperty("phone_number_type")
	private String phoneNumberType;
	@JsonProperty("phone_number_ddd")
	private String phoneNumberDdd;
	@JsonProperty("phone_number")
	private String phoneNumber;
	@JsonProperty("user_email")
	private String userEmail;
	@JsonProperty("shipping_addressee_name")
	private String shippingAddresseeName;
	@JsonProperty("shipping_address_type")
	private String shippingAddressType;
	@JsonProperty("shipping_street")
	private String shippingStreet;
	@JsonProperty("shipping_address_number")
	private String shippingAddressNumber;
	@JsonProperty("shipping_complemento")
	private String shippingComplemento;
	@JsonProperty("shipping_neighbourhood")
	private String shippingNeighbourhood;
	@JsonProperty("shipping_city")
	private String shippingCity;
	@JsonProperty("shipping_state")
	private String shippingState;
	@JsonProperty("shipping_zipcode")
	private String shippingZipcode;
	@JsonProperty("billing_addressee_name")
	private String billingAddresseeName;
	@JsonProperty("billing_address_type")
	private String billingAddressType;
	@JsonProperty("billing_street")
	private String billingStreet;
	@JsonProperty("billing_address_number")
	private String billingAddressNumber;
	@JsonProperty("billing_complemento")
	private String billingComplemento;
	@JsonProperty("billing_neighbourhood")
	private String billingNeighbourhood;
	@JsonProperty("billing_city")
	private String billingCity;
	@JsonProperty("billing_state")
	private String billingState;
	@JsonProperty("billing_zipcode")
	private String billingZipcode;
	@JsonProperty("account_creation_date")
	private String accountCreationDate;
	@JsonProperty("password_forgetten_last_timestamp")
	private Object passwordForgettenLastTimestamp;
	@JsonProperty("password_last_changed")
	private Object passwordLastChanged;
	@JsonProperty("card_blacklisted")
	private String cardBlacklisted;
	@JsonProperty("reason_for_denial")
	private Object reasonForDenial;
	@JsonProperty("allow_manual_fraud_analysis")
	private boolean allowManualFraudAnalysis;
	@JsonProperty("transaction_amount")
	private String transactionAmount;
	@JsonProperty("installments")
	private String installments;
	@JsonProperty("number_of_items")
	private String numberOfItems;
	@JsonProperty("items")
	private List<Item> items = new ArrayList<Item>();
	@JsonProperty("shipping_method")
	private String shippingMethod;
	@JsonProperty("response_time_limit")
	private String responseTimeLimit;
	@JsonProperty("channel")
	private String channel;
	@JsonProperty("bin")
	private String bin;
	@JsonProperty("transaction_time")
	private String transactionTime;
	@JsonProperty("location_payer_lattitude")
	private double locationPayerLattitude;
	@JsonProperty("location_payer_longitude")
	private double locationPayerLongitude;
	@JsonProperty("location_payee_lattitude")
	private double locationPayeeLattitude;
	@JsonProperty("location_payee_longitude")
	private double locationPayeeLongitude;
	@JsonProperty("antifraud_required")
	private String antifraudRequired;
	@JsonProperty("transaction_nsu")
	private String transactionNsu;
	@JsonProperty("decision")
	private String decision;
	@JsonProperty("iata_id")
	private String iataId;
	@JsonProperty("iata_date")
	private String iataDate;
	@JsonProperty("iata_number")
	private String iataNumber;
	@JsonProperty("iata_from")
	private String iataFrom;
	@JsonProperty("iata_to")
	private String iataTo;
	@JsonProperty("iata_passanger_name")
	private String iataPassangerName;
	@JsonProperty("iata_passanger_email")
	private String iataPassangerEmail;
	@JsonProperty("iata_passanger_phone_type")
	private String iataPassangerPhoneType;
	@JsonProperty("iata_passanger_phone_ddd")
	private String iataPassangerPhoneDdd;
	@JsonProperty("iata_passanger_phone_number")
	private String iataPassangerPhoneNumber;	
	@JsonProperty("transaction_nsu_pre")
	private String transactionNsuPre;	
	@JsonProperty("transaction_time_pre")
	private String transactionTimePre;
}
