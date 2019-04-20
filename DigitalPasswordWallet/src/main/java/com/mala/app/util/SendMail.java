package com.mala.app.util;

import java.net.URLEncoder;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.mala.app.entity.RegisterEntity;

@Component
public class SendMail {

	@Autowired
	private SimpleMailMessage mailMessage;

	@Autowired
	private JavaMailSender mailSource;

	public SendMail() {
		System.out.println("created\t" + this.getClass().getSimpleName());
	}
	
	
	public boolean sendMail(RegisterEntity registerEntity) {
		Boolean mailflag = false;
		String mailSubject = "Password Reset";
		String email = registerEntity.getEmail();
		System.out.println(email);
		try {
			mailMessage.setTo(email);
			mailMessage.setSubject(mailSubject);
			mailMessage.setText("<html>\n" + "<body>\n" + "Hello\n" + "Your Password is:" + registerEntity.getPassword()
					+ "\n" + "For Password Reset click here \n" + "<a href='password change'>\n"
					+ "This is the link</a>\n" + "</body>\n" + "</html>\n");
			System.out.println("start sending mail");
			mailSource.send(mailMessage);
			System.out.println("mail  has been sent");
			return Boolean.TRUE;
		} catch (Exception exp) {
			System.err.println(exp.getMessage());
			exp.printStackTrace();

		}
		return mailflag;
	}

	public boolean sendMimeMail(RegisterEntity registerEntity) {

		Boolean mailflag = false;
		String mailSubject = "Password Reset";
		String email = registerEntity.getEmail();
		System.out.println(email);
		String encryptPsw=registerEntity.getPassword();//It will give encrypted psw
		System.out.println("Encrypted PSW:"+" "+encryptPsw);
		
		

		MimeMessage mimeMessage = this.mailSource.createMimeMessage();
		PasswordMask passwordMask;
		try {
			passwordMask = new PasswordMask();
			String decryptedPsw=passwordMask.decryptPsw(encryptPsw);
			System.out.println("Decrypted PSW:"+" "+decryptedPsw);
			
			mimeMessage.setSubject(mailSubject);
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setTo(registerEntity.getEmail());
			
			//Here i have started new changes
			String name=registerEntity.getUserName();
			System.out.println("User name:"+name);
			//The below one works
			//String msg="<html><p>Hi!</p><p>Your password is: "+decryptedPsw+"</p><p>Your UserName is:"+name+"</p><a href='http://localhost:8080/DigitalPasswordWallet/changePsw?name=${"+name+"}'>Here Change your password</a><p>Thanks</p><p>Digital Password Wallet Group</p></html>";
			String msg="<html><p>Hi!</p><p>Your password is: "+decryptedPsw+"</p><p>Your UserName is:"+name+"</p><a href='http://localhost:8080/DigitalPasswordWallet/changePsw?name="+name+"'>Here Change your password</a><p>Thanks</p><p>Digital Password Wallet Group</p></html>";
			//String msg="<html><p>Hi!</p><p>Your password is: "+decryptedPsw+"</p><a href='http://localhost:8080/DigitalPasswordWallet/changePsw?name='"+URLEncoder.encode(name, "UTF-8")+">Here Change your password</a><p>Thanks</p><p>Digital Password Wallet Group</p></html>";
			
			//this is original line
			//String msg="<html><p>Hi!</p><p>Your password is: "+decryptedPsw+"</p><a href='http://localhost:8080/DigitalPasswordWallet/changePsw>Here Change your password</a><p>Thanks</p><p>Digital Password Wallet Group</p></html>";		
			helper.setText(msg, true);
			System.out.println("start sending mail");
			mailSource.send(mimeMessage);
			System.out.println("mail  has been sent");
			return Boolean.TRUE;
		} catch (Exception exp) {
			System.err.println(exp.getMessage());
			exp.printStackTrace();
		}

		return mailflag;

	}

}
