package com.mutualfund.fileupload.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;


// added the comment to check the git working
@RestController
@CrossOrigin
@RequestMapping("/fileUpload")
public class EquityFileController {

	Log logger = LogFactory.getLog(this.getClass());

	@GetMapping("/success")
	public String getHelloworldMessage() {
		
		logger.info("Class Name"+this.getClass().getName());
		return "Authentication Successfull";
	}

}
