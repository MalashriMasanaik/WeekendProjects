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
	
  public RegisterEntity() {
	System.out.println("Created\t"+this.getClass().getSimpleName());
	 
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
