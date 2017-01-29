package org.spring.boot.controller;

import org.spring.boot.entity.User;
import org.spring.boot.repository.UserRepository;
import org.spring.boot.validator.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksey Stoyokha on 28.01.17.
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
    public void deleteUser(@PathVariable("id") final long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        userRepository.delete(user);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public MessageDTO addUser(@RequestBody @Valid User user) {
        userRepository.save(user);
        return new MessageDTO(true);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public MessageDTO updateUser(@PathVariable("id") long id,
                           @RequestBody @Valid User user) {
        if (userRepository.findOne(id) == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        user.setId(id);
        userRepository.save(user);
        return new MessageDTO(true);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldError = result.getFieldErrors();
        return processFieldError(fieldError);
    }

    private MessageDTO processFieldError(List<FieldError> fieldErrors) {
        MessageDTO messageDTO = null;
        if (fieldErrors != null) {
            List<String> errorList = new ArrayList<String>();
            for (FieldError fieldError:
                 fieldErrors) {
                errorList.add(fieldError.getDefaultMessage());
            }
            messageDTO = new MessageDTO(false, errorList);
        }

        return messageDTO;
    }

}
