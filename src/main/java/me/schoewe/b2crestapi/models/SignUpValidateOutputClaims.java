package me.schoewe.b2crestapi.models;

import org.springframework.http.HttpStatus;

public class SignUpValidateOutputClaims extends B2CResponseContent {

	private String loyaltyNumber;
	
	public SignUpValidateOutputClaims(String message, HttpStatus status, String loyaltyNumber) {
		super(message, status);
		this.setLoyaltyNumber(loyaltyNumber);
	}	

	public String getLoyaltyNumber() {
		return loyaltyNumber;
	}

	public void setLoyaltyNumber(String loyaltyNumber) {
		this.loyaltyNumber = loyaltyNumber;
	}
	
	
}
