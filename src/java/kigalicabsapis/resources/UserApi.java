/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kigalicabsapis.resources;

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
import kigalicabsapis.dao.UserDao;
import kigalicabsapis.model.User;

/**
 * REST Web Service
 *
 * @author DolphiX People'S
 */
@Path("users")
@Singleton
public class UserApi {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserApi
     */
    public UserApi() {
    }
    @POST
    @Path("user")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(User user){
	if(UserDao.countEmails(user.getEmail())>0){
	    return Response.status(Response.Status.CONFLICT).build();
	}
	    
	if(UserDao.countUsernames(user.getUsername())>0){
	    return Response.status(Response.Status.CONFLICT).build();
	}
	    
	UserDao.registerUser(user);
	return Response.status(Response.Status.CREATED).build();
	
    }
    @PUT
    @Path("user")
    public Response updateUser(User user){
	 UserDao.updateUser(user);
	return Response.status(Response.Status.OK).build();
    }
    @DELETE
    @Path("{username}")
    public Response removeUser(@PathParam("username") String username){
	if(UserDao.countUsernames(username)>0){
	    UserDao.deleteUserByUsername(username);
	    return Response.status(Response.Status.OK).build();
	}
	return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Path("{username}")
    public User getUser(@PathParam("username") String username){
	if(UserDao.countUsernames(username)>0){
	    
	    return UserDao.findByUsername(username);
	}
	return null;
    }
    
    /**
     * Retrieves representation of an instance of kigalicabsapis.resources.UserApi
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
	
	return UserDao.getUsers() ;
    }
    
    @GET
    @Path("{username}/{email}")
    public User getSingleUser(@PathParam("username") String username,@PathParam("email") String email){
	return UserDao.findByUsernameAndEmail(username, email);
    }
    @GET
    @Path("login/{username}/{password}")
    public User getLogIn(@PathParam("username") String username,@PathParam("password") String password){
	return UserDao.loginByUsernameAndPassword(username, password);
    }

    /**
     * PUT method for updating or creating an instance of UserApi
     * @param content representation for the resource
     */
    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
