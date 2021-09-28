/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kigalicabsapis.resources;

import java.util.Date;
import java.util.List;
import javax.inject.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import kigalicabsapis.dao.CarDao;
import kigalicabsapis.model.Car;

/**
 * REST Web Service
 *
 * @author DolphiX People'S
 */
@Path("cars")
@Singleton
public class CarApi {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CarsResource
     */
    public CarApi() {
    }

    /**
     * Retrieves representation of an instance of kigalicabsapis.resources.CarsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car>  getCars() {
	return CarDao.getCars();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerCar(Car car){
	Car newCar=CarDao.findByPlateNoAndStatus(car.getPlateNo(), car.getStatus());
	if(newCar!=null){
	    if(newCar.getStatus().equals("carParkedIn")){
		
		return Response.status(Response.Status.CONFLICT).build();
	    }else if(newCar.getStatus().equals("carParkedOut")){
		CarDao.insertCar(car);
		return Response.status(Response.Status.CREATED).build();
	    }
	}else {
	    CarDao.insertCar(car);
	    return Response.status(Response.Status.CREATED).build();
	}
	return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
    /**
     * PUT method for updating or creating an instance of CarsResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCar(Car car) {
	Car newCar = CarDao.findByPlateNoAndStatus(car.getPlateNo(), car.getStatus());
	if(newCar!=null){
	    newCar.setBookedTime(car.getBookedTime());
	    newCar.setBookingId(car.getBookingId());
	    newCar.setBookedTime(car.getBookedTime());
	    newCar.setLocation(car.getLocation());
	    newCar.setLocation(car.getLocation());
		CarDao.updateCar(newCar);
		return Response.status(Response.Status.OK).build();
	    
	}else{
	    CarDao.insertCar(car);
	    return Response.status(Response.Status.CREATED).build();
	}
    }
     @PUT
     @Path("pay")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePayCar(Car car) {
	Car newCar = CarDao.findByPlateNoAndStatus(car.getPlateNo(), car.getStatus());
	if (newCar != null) {
	    if (newCar.getStatus().equals("carParkedIn")) {
		Car updatedCar=generateAmountToBePaidAndExitTime(car);
		updatedCar.setStatus("carParkedOut");
		CarDao.updateCar(updatedCar);
		return Response.status(Response.Status.OK).build();
	    } 
	}
	return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
    @DELETE
    @Path("{plateno}")
    public Response removeUser(@PathParam("plateno") String plateno) {
	Car newCar=CarDao.findByPlateNo(plateno);
	if (newCar!=null) {
	    if(newCar.getStatus().equals("carParkedOut")){
		CarDao.deleteCarByPlateNo(newCar.getCarId());
		return Response.status(Response.Status.OK).build();
		
	    }else{
		return Response.status(Response.Status.FORBIDDEN).build();
	    }
	}
	return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("{plateno}")
    public Car getSingleCar(@PathParam("plateno") String plateno) {
	if (CarDao.findByPlateNo(plateno)!=null) {
	   
	    return  CarDao.findByPlateNo(plateno);
	}
	return null;
    }
    @GET
    @Path("edit/{plateno}")
    public Car getEditUserByPlateNo(@PathParam("plateno") String plateno) {
	Car newCar=CarDao.findByPlateNo(plateno);
	if (newCar!=null) {
	    if(newCar.getStatus().equals("carParkedIn")){
		CarDao.deleteCarByPlateNo(newCar.getCarId());
		return CarDao.findByPlateNo(plateno);
	    }
	}
	return null;
    }
    
    private Car generateAmountToBePaidAndExitTime(Car car){
	int INITIALAMOUNT=200;
	Double feesTobePaid=0.0;
	Date date=new Date();
	int exitTime=date.getHours();
	int entryTime=car.getStartTime().getHours();
	double remainTime=0.0;
	if(entryTime>exitTime){
	    double remain=entryTime-exitTime;
	    exitTime=(int)remain*24;
	    
	}
	 remainTime=exitTime-entryTime;
	if(remainTime>0){
	    feesTobePaid=INITIALAMOUNT*remainTime;
	    car.setEndTime(date);
	    car.setPayment(feesTobePaid);
	}else{
	    feesTobePaid=(double)INITIALAMOUNT;
	    car.setEndTime(date);
	    car.setPayment(feesTobePaid);
	}
	
	return car;
    }
     
}
