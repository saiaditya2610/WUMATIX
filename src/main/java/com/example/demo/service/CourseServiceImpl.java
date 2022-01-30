package com.example.demo.service;

import java.util.List;
import java.util.Optional;


import com.example.demo.beans.Course;
import com.example.demo.exceptionhandler.CourseNotFound;
import com.example.demo.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService{

    CourseRepository courserepo;

    
    @Autowired
    public CourseServiceImpl(CourseRepository courserepo) {
        this.courserepo = courserepo;
    }

    @Override
    public ResponseEntity<Optional<Course>> getCourse(long id)
    {
        if(!courserepo.existsById(id))
        {
            String message="Id with value:"+id+" Could not be retrieved as it does not exist";
            throw new CourseNotFound(message);

        }
        else{
        
        return new ResponseEntity<>(courserepo.findById(id),HttpStatus.FOUND);
        }

    }


    @Override
    public ResponseEntity<Course> editCourse(Course course,long id) {
        // TODO Auto-generated method stub

        if(!courserepo.existsById(id))
        {
            String message="cannot edit course as id:"+id+" does not exist";
            throw new CourseNotFound(message);
        
        }
        else{
            course.setId(id);
        courserepo.save(course);
        }
        return new ResponseEntity<>(course,HttpStatus.ACCEPTED);

        
      
    }

    @Override
    public ResponseEntity<String> deleteCourse(long id) {
        // TODO Auto-generated method stub
        if(!courserepo.existsById(id))
        {
            String message="cannot delete as id:"+id+" does not exist";
            throw new CourseNotFound(message);

        }
        courserepo.deleteById(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
        
    }

    @Override
    public ResponseEntity<String> createCourse(Course course) {
        courserepo.save(course);

        return new ResponseEntity<>("Created",HttpStatus.CREATED);
        
    }

    @Override
    public ResponseEntity<List<Course>> getAllCourses() {
        // TODO Auto-generated method stub
        return new ResponseEntity<>(courserepo.findAll(),HttpStatus.FOUND);
    }

    
}
