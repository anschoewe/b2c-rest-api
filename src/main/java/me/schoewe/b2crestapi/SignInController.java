package me.schoewe.b2crestapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/signin"})
public class SignInController
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping(path= "/signup", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> signup(@RequestBody InputClaimsModel input) {
		if(input == null) {
			logger.error("input claims are required");
			throw new IllegalArgumentException("input claims are required");
		}
		
		logger.info(input.toString());
		
        // Run an input validation
        if (input.getFirstName().toLowerCase().equals("test")) {
        	return new ResponseEntity<>(
        			new B2CResponseContent("Test name is not valid, please provide a valid name", HttpStatus.CONFLICT),
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
