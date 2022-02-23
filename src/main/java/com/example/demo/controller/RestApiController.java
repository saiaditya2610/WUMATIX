package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.beans.Course;
import com.example.demo.beans.User;
import com.example.demo.service.CourseService;
import com.example.demo.service.MyUserDetailsService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.Model.JWTRequest;
import com.example.demo.Model.JWTResponse;
import com.example.demo.Utility.JWTUtility;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestApiController {

    CourseService service;

    UserService userserv;
    private AuthenticationManager authenticationManager;
    private MyUserDetailsService userService;


    @Autowired
    public RestApiController(CourseService service,UserService userserv,AuthenticationManager authenticationManager,MyUserDetailsService userService) {
        this.service = service;
        this.userserv=userserv;
        this.userService=userService;
        this.authenticationManager=authenticationManager;
    }  


    @RequestMapping(value = "/users", method=RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers()
    {
       return new ResponseEntity<>(userserv.getAllUsers().getBody(),HttpStatus.FOUND);

    }

    @RequestMapping(value ="/course", method = RequestMethod.GET)
    public List<Course> getAll()
    {
        return service.getAllCourses().getBody();
    }

    @RequestMapping(value= "/course/{id}", method= RequestMethod.GET)
    public ResponseEntity<Optional<Course>> getOne(@PathVariable("id") long id)
    {
        
        return service.getCourse(id);
    }

    @RequestMapping(value ="/course/{id}",method= RequestMethod.DELETE)
    public ResponseEntity<String> delCourse(@PathVariable("id") long id)
    {
        return service.deleteCourse(id);
    }

    @RequestMapping(value ="/course",method= RequestMethod.POST)
    public String create(@RequestBody Course course)
    {
        return service.createCourse(course).getBody();
    }

   


    @RequestMapping(value = "/course/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Course> changeCourse(@PathVariable("id") long id, @RequestBody Course course)
    {
        return service.editCourse(course,id);

    }

    @RequestMapping(value="/authenticate",method = RequestMethod.POST)
    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                JWTUtility.generateToken(userDetails);

        return  new JWTResponse(token);
    }



    
    
}
