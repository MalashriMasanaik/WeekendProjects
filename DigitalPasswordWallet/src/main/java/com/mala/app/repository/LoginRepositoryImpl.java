package com.mala.app.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mala.app.entity.RegisterEntity;

@Repository
public class LoginRepositoryImpl implements LoginRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public LoginRepositoryImpl() {
		System.out.println("Created\t" + this.getClass().getSimpleName());
	}

	@Override
	public void registerUser(RegisterEntity registerEntity) {
		System.out.println("Inside registerUser() of LoginRepositoryImpl" + sessionFactory.openSession());

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(registerEntity);

			transaction.commit();
		} catch (Exception exp) {
			System.err.println(exp.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}

	}

	@Override
	public Long getUser(String emailId) {
		System.out.println("Inside getUser() of LoginRepositoryImpl");
		Session session = sessionFactory.openSession();
		try {
			String hql = "select count(reg.email) from RegisterEntity reg where reg.email=:EmailId";
			Query query = session.createQuery(hql);
			query.setParameter("EmailId", emailId);
			Long count = (Long) query.uniqueResult();
			System.out.println("User Count" + " " + count);

			return count;
		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			return null;
		} finally {
			session.close();
		}

	}

	
	
	@Override
	public RegisterEntity getRegisterEntityByName(String password) {
		System.out.println("psw" + " " + password);
		// System.out.println(sessionFactory.openSession());
		Session session = sessionFactory.openSession();

		try {

			String hql = "from RegisterEntity reg where reg.password=:psw";
			Query query = session.createQuery(hql);
			query.setParameter("psw", password);
			RegisterEntity entity = (RegisterEntity) query.uniqueResult();
			System.out.println("Repository entity details"+" "+entity.toString());
			System.out.println(entity.getUserName());
			return entity;
		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			exp.printStackTrace();
			return null;
		} finally {

			session.close();
		}

	}

@Override
public void updateNewPSW(RegisterEntity entity) {
	System.out.println("updateNewPSW entity details"+entity.toString());
	Session session = sessionFactory.openSession();
	Transaction transaction = session.beginTransaction();
	try{
		/*String query = "update IrctcDTO irc set irc.traintype='"+traintype+"' where irc.departure='"+departure+"'";
		Query query1 = session.createQuery(query);
		int rowsCount = query1.executeUpdate();
		System.out.println(rowsCount);
		session.close();
*/
		session.saveOrUpdate(entity);
		transaction.commit();


		
		/*String newHql = "update  RegisterEntity reg  set where reg.firstTimeUser="+entity.getFirstTimeUser();
		Query query=session.createQuery(newHql);
		int row=query.executeUpdate();
		System.out.println("row's got affected"+" "+row);
		*//*String hql = "update  RegisterEntity reg  set where reg.email=:EmailId";
		Query query=session.createQuery(hql);
		query.setParameter("EmailId", email);
		RegisterEntity regentity=(RegisterEntity) query.uniqueResult();
		System.out.println("In repo  before update regentity details"+regentity.toString());
	session.update(regentity);
	transaction.commit();
	System.out.println("In repo  after update regentity details"+regentity.toString());
	System.out.println("new PSW saved");*/
	}catch(Exception exp){
		System.err.println(exp.getMessage());
		System.out.println("Transaction rolled back");
		//transaction.rollback();
		
	}finally{
		session.close();
	}

	
}
}
