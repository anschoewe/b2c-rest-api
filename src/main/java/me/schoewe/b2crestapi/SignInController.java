package me.schoewe.b2crestapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/signin",  "/"})
public class SignInController
{
	
	@RequestMapping("/")
	public String home() {
		return "hello";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/doesUserExist")
	public boolean doesUserExist() {
		return true;
	}
}
