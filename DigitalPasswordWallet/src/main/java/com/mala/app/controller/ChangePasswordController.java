package com.mala.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mala.app.entity.RegisterEntity;
import com.mala.app.repository.LoginRepository;
import com.mala.app.repository.LoginRepositoryImpl;
import com.mala.app.util.PasswordMask;

@Controller
public class ChangePasswordController {

	
	

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private PasswordMask passwordMask;
	
	public ChangePasswordController() {
		System.out.println("Created\t" + this.getClass().getSimpleName());
	}

	
	@RequestMapping(value="/changePsw",method=RequestMethod.GET)
	public ModelAndView changePsw(RegisterEntity entity){
		System.out.println("Inside changePsw() of ChangePasswordController");
		return new ModelAndView("changePassword.jsp");	
	}
	
	@RequestMapping(value = "/setPSW", method = RequestMethod.GET)
	public ModelAndView setPassword(HttpServletRequest request) {

		String encryPSW;
		System.out.println("from HttpServletRequest details");
		String oldPSW = request.getParameter("oldPSW");
		System.out.println("Old PSW"+oldPSW);
		String newPSW = request.getParameter("newPSW");
		System.out.println("New PSW"+" "+newPSW);
		String confirmPSW = request.getParameter("confirmPSW");
		System.out.println("Confirm PSW"+" "+confirmPSW);
		String securePhase = request.getParameter("securePhase");
		System.out.println("Secure Phase"+" "+securePhase);
		
		try {
			encryPSW=passwordMask.encryptPsw(oldPSW);
			System.out.println("encryPSW"+" "+encryPSW);
			/*RegisterEntity regEntity=loginRepository.getRegisterEntityByName(encryPSW);
			Integer userCount= regEntity.getFirstTimeUser();
			System.out.println(regEntity.getUserName()+" "+"userCount"+" "+userCount);
		*/
			 RegisterEntity entity=loginRepository.getRegisterEntityByName(encryPSW);
			 //************for sd secenario check entity null check  then go to happy scenario.****** this should return change psw pageonly
			 System.out.println("after DB fetch RegisterEntity details"+" "+entity.toString());
			 Integer userCount= entity.getFirstTimeUser();
			 System.out.println(entity.getUserName()+" "+"userCount"+" "+userCount);
			if(userCount==0){
				System.out.println("User count is zero"+" "+userCount);
				
				if(newPSW.equals(confirmPSW)){
					System.out.println("new PSW"+" "+newPSW);
					System.out.println("Confirm PSW"+" "+confirmPSW);
				
						String newEncryptedString=passwordMask.encryptPsw(newPSW);
						System.out.println("newEncryptedString"+" "+newEncryptedString);
					/*	regEntity.setPassword(newEncryptedString);
						userCount=1;
						regEntity.setFirstTimeUser(1);
						System.out.println(regEntity.toString());
					*/
						entity.setPassword(newEncryptedString);
						userCount=1;
						entity.setFirstTimeUser(1);
						entity.setSecurePhase(securePhase);
						System.out.println("after setting new psw  registerEntity details"+entity.toString());
						//loginRepository.updateNewPSW(regEntity);
						loginRepository.updateNewPSW(entity);
						//System.out.println("after setting new psw  registerEntity details"+entity.toString());
						System.out.println("new PSW has been updated in DB");
				}else{
					
					System.out.println("newPSW != confirmPSW");
				}
					
		
		}else{
			System.out.println("User  has already set his PSW and user count is not zero");
		}
		}
			catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	/*	RegisterEntity regEntity=loginRepository.getRegisterEntityByName(encryPSW);//here im getting nullpointer exception
		Integer userCount= regEntity.getFirstTimeUser();
		System.out.println(regEntity.getUserName()+" "+"userCount"+" "+userCount);
	
		if(userCount==0){
			if(newPSW==confirmPSW){
	*/			
				
				/*try {
					//pass = new PasswordMask();
					//String newEncryptedString=passwordMask.encryptPsw(newPSW);
					String newEncryptedString=passwordMask.encryptPsw(newPSW);
					System.out.println("newEncryptedString"+" "+newEncryptedString);
					regEntity.setPassword(newEncryptedString);
					
					//login.updateNewPSW(regEntity);
					System.out.println("new PSW has been updated in DB");
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}*/
				
			//}
		
		
		
	//	return new ModelAndView("changePassword.jsp");
		 return new ModelAndView("signIn.jsp");
		
	}
}
