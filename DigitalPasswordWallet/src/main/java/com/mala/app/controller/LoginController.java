package com.mala.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mala.app.entity.RegisterEntity;
import com.mala.app.service.LoginService;
import com.mala.app.util.NewValidator;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private NewValidator newValidator;
	
	
	public LoginController() {
		System.out.println("Created\t"+this.getClass().getSimpleName());
	}

	/*//@RequestMapping(value="/signup",method=RequestMethod.POST)
	public String  registerUser(RegisterEntity registerEntity,Model model){
		System.out.println("Inside registerUser() of LoginController");
	boolean result=	loginService.registerUser(registerEntity);
	model.addAttribute("user", registerEntity.getUserName());
	if(result){
		return "success.jsp";
	}else{
		
		return "signUp.jsp";
	}	
	}*/
	
	
	/*@RequestMapping(value="/signup",method=RequestMethod.POST)
	public ModelAndView  registerUser1(RegisterEntity registerEntity){
		System.out.println("Inside registerUser() of LoginController");
		validator.validEmail(registerEntity);
		
	boolean result=	loginService.registerUser(registerEntity);
	if(result){

		return new ModelAndView("success.jsp", "user", registerEntity.getUserName());
	
	}else{
		
		return new ModelAndView("signUp.jsp", "mobileNo", "Please enter valid MobileNo!");
	}
		
	}
*/	
	
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public ModelAndView  registerUser1(RegisterEntity registerEntity){
		System.out.println("Inside registerUser() of LoginController");
		boolean isValidmail = newValidator.validEmail(registerEntity);
		boolean isValidmobileNo = newValidator.validatemobileNo(registerEntity);
		boolean isValidUserName = newValidator.validateUserName(registerEntity);
		
		if (isValidmail) {
			if(isValidmobileNo){
				if(isValidUserName){
					loginService.registerUser(registerEntity);
					
					return new ModelAndView("success.jsp", "finalMessage", registerEntity.getUserName());
				}
				else{
					
					return new ModelAndView("signUp.jsp", "userName", "Please enter valid Name!"); 
				}
				}else{
					return new ModelAndView("signUp.jsp", "mobileNo", "Please enter valid MobileNo!");
				}
				
			}else{
				
				return new ModelAndView("signUp.jsp", "email", "Please enter valid email!");
			}
			
	}

}

