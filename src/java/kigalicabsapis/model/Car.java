/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kigalicabsapis.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author DolphiX People'S
 */
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer carId;
    private String bookingId;
    private String plateNo;
    private String location;
    private Date bookedTime;
    private Date startTime;
    private Date endTime;
    private String status;
    private Double payment;
    @ManyToOne(
	    cascade = CascadeType.ALL
    )
    @JoinColumn(
	    name="username",
	    referencedColumnName = "username"
    )
    private User user;

    public Car() {
    }

    public Car(Integer carId, String bookingId, String plateNo, String location, Date bookedTime, Date startTime, Date endTime, String status, Double payment, User user) {
	this.carId = carId;
	this.bookingId = bookingId;
	this.plateNo = plateNo;
	this.location = location;
	this.bookedTime = bookedTime;
	this.startTime = startTime;
	this.endTime = endTime;
	this.status = status;
	this.payment = payment;
	this.user = user;
    }

    
    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

   

    public Integer getCarId() {
	return carId;
    }

    public void setCarId(Integer carId) {
	this.carId = carId;
    }
    

    public String getBookingId() {
	return bookingId;
    }

    public void setBookingId(String bookingId) {
	this.bookingId = bookingId;
    }

    public String getPlateNo() {
	return plateNo;
    }

    public void setPlateNo(String plateNo) {
	this.plateNo = plateNo;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public Date getBookedTime() {
	return bookedTime;
    }

    public void setBookedTime(Date bookedTime) {
	this.bookedTime = bookedTime;
    }
    

    public Date getStartTime() {
	return startTime;
    }

    public void setStartTime(Date startTime) {
	this.startTime = startTime;
    }

    public Date getEndTime() {
	return endTime;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public Double getPayment() {
	return payment;
    }

    public void setPayment(Double payment) {
	this.payment = payment;
    }

    @Override
    public String toString() {
	return "Car{" + "carId=" + carId + ", bookingId=" + bookingId + ", plateNo=" + plateNo + ", location=" + location + ", bookedTime=" + bookedTime + ", startTime=" + startTime + ", endTime=" + endTime + ", status=" + status + ", payment=" + payment + ", user=" + user + '}';
    }
   

}
