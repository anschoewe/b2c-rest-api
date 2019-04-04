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
import me.schoewe.b2crestapi.models.InputClaimsModel;
import me.schoewe.b2crestapi.models.OutputClaimsModel;
import me.schoewe.b2crestapi.models.SignInInputClaimsModel;
import me.schoewe.b2crestapi.models.SignInOutputClaimsModel;
import me.schoewe.b2crestapi.models.UserModel;

import org.apache.commons.lang3.StringUtils;

@RestController
@RequestMapping(path = {"/signin"})
public class SignInController
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AzureB2CService b2cService;
	
	@PostMapping(path= "/signin", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> signin(@RequestBody SignInInputClaimsModel input) throws JSONException {
		
		if(!StringUtils.equalsAnyIgnoreCase("signin", input.getAction())) {
			String msg = "Expected action 'signin'.  Invalid action: " + StringUtils.trimToEmpty(input.getAction());
			logger.info(msg);
			throw new IllegalArgumentException(msg);
		}

		logger.info(input.toString());
		
		//TODO: Query other IdP to get existing user details
		
		UserModel user = new UserModel();
		user.setFirstName("Fake");
		user.setLastName("User1");
		user.setDisplayName("Fake User1");
		user.setEmail(input.getEmail());
		user.setPassword(input.getPassword());
		
		String userId = b2cService.createUser(user);

        // Return the output claim(s)
		SignInOutputClaimsModel output = new SignInOutputClaimsModel();
		output.setEmail(input.getEmail());
		output.setUserProfileId(userId);
		
        return new ResponseEntity<>(output, HttpStatus.OK);
	}
	
	@PostMapping(path= "/signup", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> signup(@RequestBody InputClaimsModel input) {
		if(input == null) {
			logger.error("input claims are required");
			throw new IllegalArgumentException("input claims are required");
		}
		
		logger.info(input.toString());
		
        // Run an input validation
        if (input.getFirstName().toLowerCase().equals("kimaya")) {
        	return new ResponseEntity<>(
        			new B2CResponseContent("No 'Kimayas' allowed!", HttpStatus.CONFLICT),
        			HttpStatus.CONFLICT);
        }

        // Create an output claims object and set the loyalty number with a random value
        OutputClaimsModel outputClaims = new OutputClaimsModel();
        outputClaims.setLoyaltyNumber("random123");

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
