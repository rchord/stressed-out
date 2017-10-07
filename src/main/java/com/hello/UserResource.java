package com.hello;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.ImmutableList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserDAO userDAO;

    public UserResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public User createUser(User user) {
        System.out.println("+++++++++++++==..====== " + user.getFirst_name());
        user.setUser_id(userDAO.createUser(user.getFirst_name(), user.getLast_name()));
        return user;
    }

    @Path("/update")
    @PUT
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public User updateUser(User user) {
        userDAO.updateUserById(user.getUser_id(), user.getFirst_name(), user.getLast_name());
        return user;
    }

    @Path("/{userId}")
    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("userId") int userId) {
        return userDAO.findSingleUserById(userId);
    }

    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return ImmutableList.copyOf(userDAO.getAllUsersById());
    }
}
