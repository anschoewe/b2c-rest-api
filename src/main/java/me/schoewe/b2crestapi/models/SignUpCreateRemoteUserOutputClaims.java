package me.schoewe.b2crestapi.models;

import org.springframework.http.HttpStatus;

public class SignUpCreateRemoteUserOutputClaims extends B2CResponseContent {

	public SignUpCreateRemoteUserOutputClaims(String message, HttpStatus status, String otherIdpUserId) {
		super(message, status);
		this.setOtherIdpUserId(otherIdpUserId);
	}
	
	private String otherIdpUserId;

	public String getOtherIdpUserId() {
		return otherIdpUserId;
	}

	public void setOtherIdpUserId(String otherIdpUserId) {
		this.otherIdpUserId = otherIdpUserId;
	}
}
