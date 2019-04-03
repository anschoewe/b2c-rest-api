package me.schoewe.b2crestapi.models;

public class SignInOutputClaimsModel {
	private String email;
	private String userProfileId;
	
	public String getEmail() {
		return email;  //TOOD: do we need to return a hashed version of the email?  What format is B2C expecting?
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserProfileId() {
		return userProfileId;
	}
	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}

	
	
}
