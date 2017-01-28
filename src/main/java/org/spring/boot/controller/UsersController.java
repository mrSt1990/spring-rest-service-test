package org.spring.boot.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.org.apache.regexp.internal.RE;
import org.hibernate.Query;
import org.hibernate.Session;
import org.spring.boot.entity.User;
import org.spring.boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by mr_St on 28.01.17.
 */
@RestController
@RequestMapping(value = "/users",
    produces = "application/json",
    consumes = "application/json")
public class UsersController {

    private UserRepository userRepository;

    @Autowired
    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public User findUserById(@PathVariable("id") long id) {
        User user = this.userRepository.findOne(id);
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        user.getRoles().size();
        return user;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        userRepository.delete(user);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable("id") long id, @RequestBody User user) {
        if (userRepository.findOne(id) == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        user.setId(id);
        return userRepository.save(user);
    }

}
