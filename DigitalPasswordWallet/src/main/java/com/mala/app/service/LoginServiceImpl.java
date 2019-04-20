package com.mala.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mala.app.entity.RegisterEntity;
import com.mala.app.repository.LoginRepository;
import com.mala.app.util.PasswordGenerator;
import com.mala.app.util.PasswordMask;
import com.mala.app.util.SendMail;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private SendMail sendMail;

	/*
	 * @Autowired private NewValidator newValidator;
	 */

	public LoginServiceImpl() {
		System.out.println("Created\t" + this.getClass().getSimpleName());
	}

	@Override
	public boolean registerUser(RegisterEntity registerEntity) {
		boolean userExist=false;
		System.out.println("Inside registerUser() of LoginServiceImpl");
		
		Long userCount=loginRepository.getUser(registerEntity.getEmail());
		
		if(userCount == 0){	
			String unencryptedString=PasswordGenerator.generatePassword();
			System.out.println("Newly generated Password without any chrypto"+" "+unencryptedString);
			
			registerEntity.setAccountLock(0);
			registerEntity.setFirstTimeUser(0);
			registerEntity.setSecurePhase(null);
			
			PasswordMask passwordMask;
			try {
				passwordMask = new PasswordMask();
				String encryptedString=passwordMask.encryptPsw(unencryptedString);
				//System.out.println("Encrypted Password"+" "+encryptedString);
				registerEntity.setPassword(encryptedString);
				
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			
			
			loginRepository.registerUser(registerEntity);
			sendMail.sendMimeMail(registerEntity);
			
			return userExist;
			
		
		}else{
			
			return userExist=true;
		
		}
	
	
		
	}

	/*
	 * @Override public boolean registerUser(RegisterEntity registerEntity) {
	 * 
	 * 
	 * System.out.println("Inside registerUser() of LoginServiceImpl");
	 * validator.validEmail(registerEntity); if(isValid){
	 * 
	 * loginRepository.registerUser(registerEntity); return true;
	 * 
	 * }else{ return false; } }
	 * 
	 */
}
