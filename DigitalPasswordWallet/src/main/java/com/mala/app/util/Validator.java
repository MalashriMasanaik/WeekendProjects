package com.mala.app.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.mala.app.entity.RegisterEntity;
import com.mala.app.service.LoginService;

@Component
public class Validator {
	
	@Autowired
	private LoginService loginService;
	

	public ModelAndView validEmail(RegisterEntity registerEntity) {
		boolean isValid = false;
		int nameSize=8;
		String emailId = registerEntity.getEmail();

		try {
			InternetAddress internetAddress = new InternetAddress(emailId);
			internetAddress.validate();
			System.out.println("Entered Valid email Id");
			if (registerEntity.getMobileNo() != null) {
				System.out.println("Entered Valid Mobile NO");
				if (registerEntity.getUserName() != null  && (registerEntity.getUserName().length()) > nameSize) {
					System.out.println("Entered Valid User Name");
					isValid = true;
					loginService.registerUser(registerEntity);
					return new ModelAndView("success.jsp", "user", registerEntity.getUserName());
				}else{
					System.out.println("Entered Invalid User Name");
					return new ModelAndView("signUp.jsp", "userName", "Please enter valid Name!");
				
				}

			} else {
				System.out.println("Entered Invalid Mobile NO");
				return new ModelAndView("signUp.jsp", "mobileNo", "Please enter valid MobileNo!");
			}

		} catch (AddressException e) {
			System.out.println("Entered Invalid email ID");
			System.err.println(e.getMessage());
			return new ModelAndView("signUp.jsp", "email", "Please enter valid email!");
		}

		//return isValid;

	}

}
