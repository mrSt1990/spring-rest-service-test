package resource;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by mr_St on 27.01.17.
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
@Transactional
public class UsersResource {

    private UserRepository userRepository;

    @Autowired
    public UsersResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GET
    public List<User> list() {
        return this.userRepository.findAll();
    }

    @GET
    @Path("{id}")
    public User get(@PathParam("id") long id) {
        User user = this.userRepository.findOne(id);
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        user.getRoles().size();
        return user;
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id) {
        User user = this.userRepository.findOne(id);
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        this.userRepository.delete(user);
    }








}
