package com.mala.app.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mala.app.entity.RegisterEntity;
import com.mala.app.util.SendMail;

@Repository
public class LoginRepositoryImpl implements LoginRepository {

	@Autowired
	private SessionFactory factory;

	public LoginRepositoryImpl() {
		System.out.println("Created\t" + this.getClass().getSimpleName());
	}

	@Override
	public void registerUser(RegisterEntity registerEntity) {
		System.out.println("Inside registerUser() of LoginRepositoryImpl");

		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(registerEntity);
			
			transaction.commit();
		} catch (Exception exp) {
			System.err.println(exp.getMessage());
			transaction.rollback();
		}

	}

	public Long getUser(String emailId) {
		System.out.println("Inside getUser() of LoginRepositoryImpl");
		Session session = factory.openSession();
		
		String hql = "select count(reg.email) from RegisterEntity reg where reg.email=:EmailId";
		Query query = session.createQuery(hql);
		query.setParameter("EmailId", emailId);
		Long count =  (Long) query.uniqueResult();
		System.out.println("User Count" + " " + count);

		return count;

	}

}
