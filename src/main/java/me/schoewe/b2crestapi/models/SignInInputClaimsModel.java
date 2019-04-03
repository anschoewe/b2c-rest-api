package me.schoewe.b2crestapi.models;

public class SignInInputClaimsModel
{
    private String signInName;
    private String password;
    private String action;
    
   
	public String getSignInName() {
		return signInName;
	}
	
	public void setSignInName(String signInName) {
		this.signInName = signInName;
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
		return "SignInInputClaimsModel [" + getSignInName() + "," + getAction() + "]";
	}
    
    
}
