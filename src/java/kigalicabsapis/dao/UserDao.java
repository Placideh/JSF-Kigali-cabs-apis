/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kigalicabsapis.dao;

import java.util.List;
import kigalicabsapis.model.User;
import kigalicabsapis.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author DolphiX People'S
 */
public class UserDao {
    public static void registerUser(User user){
	Session session= HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	session.save(user);
	session.getTransaction().commit();
	session.close();
    }
    public static User loginByUsernameAndPassword(String username,String password ){
	String hql="FROM User WHERE username='"+username+"' AND password='"+password+"'";
	Session session= HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	Query query=session.createQuery(hql);
	User singleUser=(User)query.uniqueResult();
	session.getTransaction().commit();
	session.close();
	
	return singleUser;
    }
     public static User findByUsernameAndEmail(String username,String email ){
	String hql="FROM User WHERE username='"+username+"' AND email='"+email+"'";
	Session session= HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	Query query=session.createQuery(hql);
	User singleUser=(User)query.uniqueResult();
	session.getTransaction().commit();
	session.close();
	
	return singleUser;
    }
     public static Long countUsernames(String username ){
	String hql="SELECT COUNT(username)FROM User WHERE username='"+username+"'";
	Session session= HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	Query query=session.createQuery(hql);
	Long usernames=(Long)query.uniqueResult();
	session.getTransaction().commit();
	session.close();
	
	return usernames;
    }
      public static Long countEmails(String email ){
	String hql="SELECT COUNT(email)FROM User WHERE email='"+email+"'";
	Session session= HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	Query query=session.createQuery(hql);
	Long emails=(Long)query.uniqueResult();
	session.getTransaction().commit();
	session.close();
	
	return emails;
    }
    public static void updateUser(User user){
	Session session= HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	session.update(user);
	session.getTransaction().commit();
	session.close();
    }
    public static void deleteUserByUsername(String username){
	Session session = HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	User user=(User)session.get(User.class, username);
	if(user!=null)
	    session.delete(user);
	
	session.getTransaction().commit();
	session.close();
    }
     public static User findByUsername(String username){
	Session session = HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	User user=(User)session.get(User.class, username);
	session.getTransaction().commit();
	session.close();
	return user;
    }
    public static List<User>getUsers(){
	Session session = HibernateUtil.getSessionFactory().openSession();
	Query query=session.createQuery("from User");
	List<User>list=query.list();
	session.close();
	return list;
    }
  
}
