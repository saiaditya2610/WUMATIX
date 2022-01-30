package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.beans.Course;
import com.example.demo.beans.User;
import com.example.demo.service.CourseService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4200/course","*"})
@RestController
public class RestApiController {

    CourseService service;

    UserService userserv;

    @Autowired
    public RestApiController(CourseService service,UserService userserv) {
        this.service = service;
        this.userserv=userserv;
    }


    @RequestMapping(value = "/users", method=RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers()
    {
       return new ResponseEntity<>(userserv.getAllUsers().getBody(),HttpStatus.FOUND);

    }

    @RequestMapping(value ="/course", method = RequestMethod.GET)
    public ResponseEntity<List<Course>> getAll()
    {
        return service.getAllCourses();
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
    public ResponseEntity<String> create(@RequestBody Course course)
    {
        return service.createCourse(course);
    }

   


    @RequestMapping(value = "/course/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Course> changeCourse(@PathVariable("id") long id, @RequestBody Course course)
    {
        return service.editCourse(course,id);

    }



    
    
}
