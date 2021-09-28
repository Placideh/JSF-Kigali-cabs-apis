/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kigalicabsapis.dao;

import java.util.List;
import kigalicabsapis.model.Car;
import kigalicabsapis.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author DolphiX People'S
 */
public class CarDao {
    public static void insertCar(Car car){
	Session session = HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	session.save(car);
	session.getTransaction().commit();
	session.close();
    }
    
    public static void updateCar(Car car){
	Session session = HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	session.update(car);
	session.getTransaction().commit();
	session.close();
    }
    public static List<Car> getCars() {
	Session session = HibernateUtil.getSessionFactory().openSession();
	Query query = session.createQuery("from Car");
	List<Car> list = query.list();
	session.close();
	return list;
    }
    public static void deleteCarByPlateNo(Integer id) {
	String hql="DELETE FROM  Car WHERE carId='"+id+"'";
	Session session = HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	Query query = session.createQuery(hql);
	query.executeUpdate();
	session.getTransaction().commit();
	session.close();
    }
    public static Car findByPlateNoAndStatus(String plateNo,String status ){
	String hql="FROM Car WHERE plateno='"+plateNo+"' AND status='"+status+"'";
	Session session= HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	Query query=session.createQuery(hql);
	Car singleCar=(Car)query.uniqueResult();
	session.getTransaction().commit();
	session.close();
	return singleCar;
    }
    public static Car findByPlateNo(String plateNo){
	String hql="FROM Car WHERE plateno='"+plateNo+"'";
	Session session= HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	Query query=session.createQuery(hql);
	Car singleCar=(Car)query.uniqueResult();
	session.getTransaction().commit();
	session.close();
	return singleCar;
    }
    
}
