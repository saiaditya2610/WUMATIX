package com.example.demo.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;




@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;


    @NotEmpty(message = "Course Name Cannot Be Empty")

    public String courseName;

    @NotEmpty()
    @Size(min=2,message = "Please enter minimum of 2 digits")
    @Pattern(regexp = "[0-9]+")
    public String courseId;

    @Pattern(regexp = "[a-zA-z ]+")
    @NotEmpty(message = "Name cannot be empty")
    public String studentName;

  





    public String getStudentName() {
        return studentName;
    }
    


    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    public Course()
    {
        
    }

    

    public Course(long id, String courseName, String courseId, String studentName) {
        this.id = id;
        this.courseName = courseName;
        this.courseId = courseId;
        this.studentName = studentName;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getCourseName() {
        return courseName;
    }


    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public String getCourseId() {
        return courseId;
    }


    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    

    
}
