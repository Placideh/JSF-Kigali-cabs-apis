/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kigalicabsapis.dao;

import java.util.List;
import kigalicabsapis.model.Booking;
import kigalicabsapis.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author DolphiX People'S
 */
public class BookingDao {
    public static void saveBooking(Booking book){
	Session session = HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	session.save(book);
	session.getTransaction().commit();
	session.close();
    }
    public static Booking getBookingId(String bookingId){
	Session session = HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	Booking book = (Booking) session.get(Booking.class, bookingId);
	session.getTransaction().commit();
	session.close();
	return book;
    }
    public static void deleteBooking(String bookingId){
	Session session = HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	Booking book = (Booking) session.get(Booking.class, bookingId);
	if(book!=null) session.delete(book);
	session.getTransaction().commit();
	session.close();
    }
    public static List<Booking> getBookings(){
	Session session = HibernateUtil.getSessionFactory().openSession();
	Query query = session.createQuery("from Booking");
	List<Booking> list = query.list();
	session.close();
	return list;
    }
    public static Booking findByBookId(String bookId){
	Session session = HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	Booking booking = (Booking) session.get(Booking.class, bookId);
	session.getTransaction().commit();
	session.close();
	return booking;
    }
}
