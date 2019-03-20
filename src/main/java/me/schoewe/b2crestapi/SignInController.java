package me.schoewe.b2crestapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/signin",  "/"})
public class SignInController
{
	
	@RequestMapping("/")
	public String home() {
		System.out.println("in home");
		System.out.println(System.getProperty("buildNum"));
		return "hello";
	}
	
	@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST } , path = "/doesUserExist")
	public ResponseEntity<?> doesUserExist() {
		System.out.println("in doesUserExist");
		System.out.println(System.getProperty("buildNum"));
		return new ResponseEntity<>("{ 'msg': 'duplicate' }", HttpStatus.CONFLICT);
	}
}
