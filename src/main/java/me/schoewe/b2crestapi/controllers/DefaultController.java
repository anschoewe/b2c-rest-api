package me.schoewe.b2crestapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = { "", "/" } )
public class DefaultController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping(path = { "", "/" })
	public String home() {
		logger.debug("in home");
		return "hello";
	}

}
