package me.schoewe.b2crestapi.controllers;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.schoewe.b2crestapi.AzureB2CService;
import me.schoewe.b2crestapi.B2CResponseContent;
import me.schoewe.b2crestapi.models.SignUpValidateInputClaims;
import me.schoewe.b2crestapi.models.SignUpValidateOutputClaims;
import me.schoewe.b2crestapi.models.SignInInputClaims;
import me.schoewe.b2crestapi.models.SignInOutputClaims;
import me.schoewe.b2crestapi.models.SignUpCreateRemoteUserInputClaims;
import me.schoewe.b2crestapi.models.SignUpCreateRemoteUserOutputClaims;
import me.schoewe.b2crestapi.models.UserModel;

import org.apache.commons.lang3.StringUtils;

@RestController
@RequestMapping(path = {"/signin"})
public class SignInController
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AzureB2CService b2cService;
	
	/**
	 * If the user exists in the other IdP, then create them in B2C
	 * Then return the the new B2C user Id.
	 * If the user does NOT exist in the other/legacy IdP, it is assumed B2C will create them through the sign-up flow
	 * 
	 * @param input
	 * @return
	 * @throws JSONException
	 */
	@PostMapping(path= "/signin", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> signin(@RequestBody SignInInputClaims input) throws JSONException {
		//////////////////
		//
		// Validations
		//
		//////////////////
		if(input == null) {
			logger.error("input claims are required");
			throw new IllegalArgumentException("input claims are required");
		}
		
		if(!StringUtils.equalsAnyIgnoreCase("signin", input.getAction())) {
			String msg = "Expected action 'signin'.  Invalid action: " + StringUtils.trimToEmpty(input.getAction());
			logger.info(msg);
			throw new IllegalArgumentException(msg);
		}

		
		logger.info(input.toString());
		
		//TODO: Query other IdP to get existing user details
		
		// Example, pretend a user with 'OtherIdp.User1@example.com' email addresses already exists in legacy IdP
		// For all other users, assume they don't exist and therefore we should NOT create the user in B2C
		
		SignInOutputClaims output = new SignInOutputClaims();
		
		if(StringUtils.equalsAnyIgnoreCase(input.getEmail(), "OtherIdp.User1@example.com")) {
			logger.info("Found user in legacy IdP, will create user in B2C");
			
			UserModel user = new UserModel();
			user.setFirstName("OtherIdp");
			user.setLastName("User1");
			user.setDisplayName("OtherIdp User1");
			user.setEmail(input.getEmail());
			user.setOtherIdpUserId("externalIdpId_" + input.getEmail());
			user.setPassword(input.getPassword());
			
			// Create user in B2C since we did NOT find them in B2C but DID find them in the remote IdP
			String userId = b2cService.createUser(user);
	        // Return the output claim(s)
			output.setEmail(input.getEmail());
			output.setUserProfileId(userId);
		} else {
			// user does not exist in legacy IdP, so don't create them in B2C automatically
			logger.info("Did NOT find user in legacy IdP, will NOT create user in B2C");
			
			output.setEmail("");
			output.setUserProfileId("");
		}

        return new ResponseEntity<>(output, HttpStatus.OK);
	}
	
	@PostMapping(path= "/signUpValidate", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> signUpValidate(@RequestBody SignUpValidateInputClaims input) {
		if(input == null) {
			logger.error("input claims are required");
			throw new IllegalArgumentException("input claims are required");
		}
		
		logger.info(input.toString());
		
		//TODO validate user is not already in legacy IdP
		
		//TODO validate user is in CRM system or can bypass this validation

        // Example: Run an input validation and return CONFLICT (409) status if validation fails 
        if (input.getFirstName().toLowerCase().equals("kimaya")) {
        	return new ResponseEntity<>(
        			new B2CResponseContent("No 'Kimayas' allowed!", HttpStatus.CONFLICT),
        			HttpStatus.CONFLICT);
        }

        // Example. Add custom claims to new user
        // Create an output claims object and set the loyalty number with a random value
//        SignUpValidateOutputClaims outputClaims = new SignUpValidateOutputClaims();
//        outputClaims.setLoyaltyNumber("random123");

        // Return the output claim(s)
        return new ResponseEntity<>(new B2CResponseContent("success", HttpStatus.OK), HttpStatus.OK);
	}
	
	@PostMapping(path= "/createRemoteUser", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createRemoteUser(@RequestBody SignUpCreateRemoteUserInputClaims input) {
		if(input == null) {
			logger.error("input claims are required");
			throw new IllegalArgumentException("input claims are required");
		}
		
		logger.info(input.toString());
		
		//TODO create the user in the legacy IdP and get the otherIdPUserId
		
		String otherIdpUserId = "externalIdpId_" + input.getEmail(); //TODO get this from the newly created user in the remote IdP
		
		// If there is a failure creating the remote IdP user, we need to return a 409/conflict status instead of 200/ok
		
		// Reply to B2C with the otherIdPUserId so it can include that in B2C when it creates it's own version of the user
        SignUpCreateRemoteUserOutputClaims outputClaims = new SignUpCreateRemoteUserOutputClaims();
        outputClaims.setOtherIdpUserId(otherIdpUserId);

        // Return the output claim(s)
        return new ResponseEntity<>(outputClaims, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST } , path = "/doesUserExist")
	public ResponseEntity<?> doesUserExist() {
		logger.info("in doesUserExist");
		logger.info(System.getProperty("buildNum"));
		return new ResponseEntity<>("{ \"msg\": \"original\" }", HttpStatus.OK);
//		return new ResponseEntity<>("{ 'msg': 'duplicate' }", HttpStatus.CONFLICT);
	}
}
