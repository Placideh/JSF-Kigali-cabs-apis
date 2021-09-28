/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kigalicabsapis.resources;

import java.sql.Date;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import kigalicabsapis.dao.BookingDao;
import kigalicabsapis.model.Booking;

/**
 * REST Web Service
 *
 * @author DolphiX People'S
 */
@Path("booking")
@Singleton
public class BookingApi {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of BookingApi
     */
    public BookingApi() {
    }

    /**
     * Retrieves representation of an instance of kigalicabsapis.resources.BookingApi
     * @return an instance of java.lang.String
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Booking createBooking(Booking booking){
	BookingDao.saveBooking(booking);
	return BookingDao.findByBookId(booking.getBookId());
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Booking> getBooked() {
	return BookingDao.getBookings();
    }

    /**
     * PUT method for updating or creating an instance of BookingApi
     * @param content representation for the resource
     */
   @GET
    @Path("{bookId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Booking findByBookingJson(@PathParam("bookId") String bookId) {
	if(BookingDao.findByBookId(bookId)!=null){
	    return BookingDao.findByBookId(bookId);
	}
	return null;
    }
     @DELETE
    @Path("{bookId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteBookingJson(@PathParam("bookId") String bookId) {
	if(BookingDao.findByBookId(bookId)!=null){
	    
	    BookingDao.deleteBooking(bookId);
	    return Response.status(Response.Status.OK).build();
	}
	return Response.status(Response.Status.NOT_FOUND).build();
    }
    
   
   
    
}

