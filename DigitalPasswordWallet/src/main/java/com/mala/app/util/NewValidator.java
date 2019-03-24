package com.mala.app.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.mala.app.entity.RegisterEntity;
import com.mala.app.service.LoginService;

@Component
public class NewValidator {


	public boolean validEmail(RegisterEntity registerEntity) {
		boolean isValidmail = false;
		String emailId = registerEntity.getEmail();

		try {
			InternetAddress internetAddress = new InternetAddress(emailId);
			internetAddress.validate();
			System.out.println("Entered Valid email Id");
			isValidmail=true;
			
			
		} catch (AddressException e) {
			System.out.println("Entered Invalid email ID");
			System.err.println(e.getMessage());
			
		}
		return isValidmail;

	
	}

	public boolean validatemobileNo(RegisterEntity registerEntity) {
		boolean isValidmobileNo = false;
		if (registerEntity.getMobileNo() != null) {
			System.out.println("Entered Valid Mobile NO");
			isValidmobileNo = true;
		} else {
			System.out.println("Entered Invalid Mobile NO");
		}
		return isValidmobileNo;

	}

	public boolean validateUserName(RegisterEntity registerEntity) {
		int nameSize = 8;
		boolean isValidUserName = false;
		if (registerEntity.getUserName() != null && (registerEntity.getUserName().length()) >= nameSize) {
			System.out.println("Entered Valid User Name");
			isValidUserName = true;
		} else {
			System.out.println("Entered Invalid User Name");

		}
		return isValidUserName;
	}
}
