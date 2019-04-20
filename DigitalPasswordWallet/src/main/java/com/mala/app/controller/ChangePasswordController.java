package com.mala.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
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
import com.mala.app.util.SendMail;

@Controller
public class ChangePasswordController {

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private PasswordMask passwordMask;
	
	
	
	public ChangePasswordController() {
		System.out.println("Created\t" + this.getClass().getSimpleName());
	}
	
	
	@RequestMapping(value="/sign",method=RequestMethod.GET)
	public ModelAndView sign(RegisterEntity entity){
		System.out.println("Inside sign() of ChangePasswordController");
		
		return new ModelAndView("signIn.jsp");	
	}

	@RequestMapping(value="/secure",method=RequestMethod.POST)
	public ModelAndView secureSignIn(RegisterEntity entity,HttpServletRequest request){
		System.out.println("Inside secureSignIn() of ChangePasswordController");
		String securePhase=request.getParameter("securePhase");
		System.out.println("Entered securePhase"+" "+securePhase);
		
		
		entity= loginRepository.getRegisterEntityBySecurePhase(securePhase);
		if(entity!=null){
		System.out.println(entity.getSecurePhase());
		if(securePhase.equals(entity.getSecurePhase())){
			
			System.out.println("Entered correct Secure Phase");
			return new ModelAndView("home.jsp");
		}
		}
			return new ModelAndView("securePage.jsp","securePhase","Incorrect SecurePhase");
		
		
			
	}

	
	@RequestMapping(value="/signIn",method=RequestMethod.POST)
	public ModelAndView signIn(RegisterEntity entity,HttpServletRequest request){
		System.out.println("Inside signIn() of ChangePasswordController");
		String userName=(String) request.getParameter("Uname");
		System.out.println("userName"+" "+userName);
		String password=(String) request.getParameter("PSW");
		System.out.println("password"+" "+password);
		String encryptPSW=passwordMask.encryptPsw(password);
		System.out.println("encryptPSW"+" "+encryptPSW);
		
		entity=loginRepository.getRegisterEntityByName(encryptPSW);
		if(entity!=null){
			if(userName.equals(entity.getUserName())){
				System.out.println("Username and PSW has matched");
				
				return new ModelAndView("securePage.jsp");
				//return new ModelAndView("home.jsp");
			}else{
				return new ModelAndView("signIn.jsp","useraname","Incorrect Username");
			}
			
			
		}else{
			
			return new ModelAndView("signIn.jsp","password","Incorrect password");
		}
		
		
			
	}
	
	
	@RequestMapping(value="/changePsw",method=RequestMethod.GET)
	public ModelAndView changePsw(RegisterEntity ent,HttpServletRequest request){
		System.out.println("Inside changePsw() of ChangePasswordController");
	
		String userName=request.getParameter("name");
		System.out.println("userName:"+userName);
		return new ModelAndView("changePassword.jsp","userName", userName);	
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
			// System.out.println("after DB fetch RegisterEntity details"+" "+entity.toString());
			 if(entity!=null){
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
					return new ModelAndView("changePassword.jsp","PSWMismatch", "password mismatch");
				}
					
		
		}else{
			System.out.println("User  has already changed his PSW and user count is not zero"); 
			return new ModelAndView("changePassword.jsp","alreadychanged", "you  have already changed your PSW");
			
			
		}
		}else{
			System.out.println("You have entered wrong oldPSW");
			return new ModelAndView("changePassword.jsp","wrongPSW", "you  have entered wrong OldPSW"); 
		}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			//return new ModelAndView("changePassword.jsp");
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
		 return new ModelAndView("changeSuccess.jsp","success","you have successfully changed your psw!");
		
	}
}
