package me.schoewe.b2crestapi.models;

import java.io.Serializable;

public class UserModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String lastName;
	private String email;
	private String displayName;
	private String password;
	private String companyId;
	private String id;
	private String otherIdpUserId;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOtherIdpUserId() {
		return otherIdpUserId;
	}
	public void setOtherIdpUserId(String otherIdpUserId) {
		this.otherIdpUserId = otherIdpUserId;
	}
}
