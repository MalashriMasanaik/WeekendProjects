package com.mala.app.repository;

import com.mala.app.entity.RegisterEntity;

public interface LoginRepository {
	
	public void registerUser(RegisterEntity registerEntity);
	public Long getUser(String emailId );
	public RegisterEntity getRegisterEntityByName(String password);
	public void updateNewPSW(RegisterEntity entity);
	public RegisterEntity getRegisterEntityBySecurePhase(String securephase);
}
