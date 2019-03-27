package com.mala.app.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="register_table")
public class RegisterEntity implements Serializable{
	
	@Id
	private String email;
	private BigInteger mobileNo;
	private String userName;
	private String userType;
	private Integer firstTimeUser;
	private String securePhase;
	private Integer accountLock;
	private String password;
	
  public RegisterEntity() {
	System.out.println("Created\t"+this.getClass().getSimpleName());
	 
}

public Integer getFirstTimeUser() {
	return firstTimeUser;
}

public void setFirstTimeUser(Integer firstTimeUser) {
	this.firstTimeUser = firstTimeUser;
}

public String getSecurePhase() {
	return securePhase;
}

public void setSecurePhase(String securePhase) {
	this.securePhase = securePhase;
}

public Integer getAccountLock() {
	return accountLock;
}

public void setAccountLock(Integer accountLock) {
	this.accountLock = accountLock;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public BigInteger getMobileNo() {
	return mobileNo;
}

public void setMobileNo(BigInteger mobileNo) {
	this.mobileNo = mobileNo;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}


public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getUserType() {
	return userType;
}

public void setUserType(String userType) {
	this.userType = userType;
}

@Override
public String toString() {
	return "RegisterEntity [email=" + email + ", mobileNo=" + mobileNo + ", userName=" + userName + ", userType="
			+ userType + "]";
}
  
  
	

}
