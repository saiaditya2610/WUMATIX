package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.beans.Course;

import org.springframework.http.ResponseEntity;



public interface CourseService {
    public ResponseEntity<List<Course>> getAllCourses();
    public ResponseEntity<Optional<Course>> getCourse(long id);
    public ResponseEntity<Course> editCourse(Course course,long id);
    public ResponseEntity<String> deleteCourse(long id);
    public ResponseEntity<String> createCourse(Course course);

    
}
