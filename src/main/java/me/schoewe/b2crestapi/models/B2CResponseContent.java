package me.schoewe.b2crestapi.models;

import org.springframework.http.HttpStatus;

public abstract class B2CResponseContent
{
    private String version;
    private int status;
    private String userMessage;

    public B2CResponseContent(String message, HttpStatus status)
    {
        this.userMessage = message;
        this.status = status.value();
        this.version = "1.0.0";
    }

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
}
