package com.mala.app.repository;

import com.mala.app.entity.RegisterEntity;

public interface LoginRepository {
	
	public void registerUser(RegisterEntity registerEntity);
	public Long getUser(String emailId );

}
