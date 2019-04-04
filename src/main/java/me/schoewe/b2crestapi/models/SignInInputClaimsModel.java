package me.schoewe.b2crestapi.models;

public class SignInInputClaimsModel
{
    private String email;
    private String password;
    private String action;
    
   
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	@Override
	public String toString() {
		return "SignInInputClaimsModel [" + getEmail() + "," + getAction() + "]";
	}
    
    
}
