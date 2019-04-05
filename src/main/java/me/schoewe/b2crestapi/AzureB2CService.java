package me.schoewe.b2crestapi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import me.schoewe.b2crestapi.models.UserModel;

@Service
public class AzureB2CService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;
    
	private static final String baseUrl = "https://graph.windows.net/schoeweb2c.onmicrosoft.com";
	private static final String userPath = baseUrl + "/users";
	
	public String createUser(UserModel user) throws JSONException {
		JSONObject request = new JSONObject();
		request.put("accountEnabled", true);
		request.put("creationType", "LocalAccount");
		request.put("givenName", user.getFirstName());
		request.put("surname", user.getLastName());
		request.put("displayName", user.getDisplayName());
//		request.put("extension_3997b396a633402aaf9e1ce908484372_otherIdpUserId", user.getOtherIdpUserId()); //associated with default 'b2c-extensions-app' appId
		request.put("extension_3b433761de7841a5ae638fdf6577329c_otherIdpUserId", user.getOtherIdpUserId()); //associated with WebApp-GraphAPI-DirectoryExtensions appId
		
		JSONArray otherEmails = new JSONArray();
		otherEmails.put(user.getEmail());
		request.put("otherMails", otherEmails);
		
		JSONObject passwordProfile = new JSONObject();
		passwordProfile.put("password", user.getPassword());
		passwordProfile.put("forceChangePasswordNextLogin", false);
		
		JSONArray signinNames = new JSONArray();
		JSONObject name = new JSONObject();
		name.put("type", "emailAddress");
		name.put("value", user.getEmail());
		signinNames.put(name);
		
		request.put("passwordProfile", passwordProfile);
		request.put("signInNames", signinNames);
		
		// set headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		
		logger.info(request.toString(2));

		// send request and parse result
		String objectId = "";
		try {
			ResponseEntity<String> loginResponse = oAuth2RestTemplate.exchange(userPath + "?api-version=1.6", HttpMethod.POST, entity, String.class);
//			if (loginResponse.getStatusCode() == HttpStatus.OK || loginResponse.getStatusCode() == HttpStatus.CREATED) {
			  JSONObject userJson = new JSONObject(loginResponse.getBody());
			  logger.info(userJson.toString(2));
			  objectId = userJson.getString("objectId");
//			}
		}  catch(HttpStatusCodeException e) {
			logger.warn(String.valueOf(e.getRawStatusCode()));
			JSONObject userJson = new JSONObject(e.getResponseBodyAsString());
			logger.warn(userJson.toString(2));
		}
		
		return objectId;
	}
}
