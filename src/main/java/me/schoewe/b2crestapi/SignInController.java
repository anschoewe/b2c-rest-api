package me.schoewe.b2crestapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/signin"})
public class SignInController
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST } , path = "/doesUserExist")
	public ResponseEntity<?> doesUserExist() {
		logger.info("in doesUserExist");
		logger.info(System.getProperty("buildNum"));
		return new ResponseEntity<>("{ 'msg': 'original' }", HttpStatus.OK);
//		return new ResponseEntity<>("{ 'msg': 'duplicate' }", HttpStatus.CONFLICT);
	}
}
