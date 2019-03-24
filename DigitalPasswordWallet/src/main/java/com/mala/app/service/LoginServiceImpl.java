package com.mala.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mala.app.entity.RegisterEntity;
import com.mala.app.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	/*
	 * @Autowired private NewValidator newValidator;
	 */

	public LoginServiceImpl() {
		System.out.println("Created\t" + this.getClass().getSimpleName());
	}

	@Override
	public void registerUser(RegisterEntity registerEntity) {
		System.out.println("Inside registerUser() of LoginServiceImpl");
		loginRepository.registerUser(registerEntity);
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
