package com.example.demo.repository;

import com.example.demo.beans.Course;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Course getByid(long id);
    
}
