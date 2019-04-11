package me.schoewe.b2crestapi.models;

public class SignUpValidateInputClaims
{
    private String email;
    private String firstName;
    private String lastName;
    private String displayName;
    
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	@Override
	public String toString() {
		return "InputClaimsMode [" + getEmail() + ", " + getFirstName() + ", " + getLastName() + "]";
	}
    
    
}
