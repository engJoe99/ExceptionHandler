package com.example.demo.rest;


import com.example.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // define a @PostConstruct to load the student data -- only once!!
    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();
        theStudents.add(new Student("KOKO", "YOUSSEF"));
        theStudents.add(new Student("HANA", "AHMED"));
        theStudents.add(new Student("OMAR", "MOHAMED"));

    }

    // define an endpoint for "/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }

    // define an endpoint for "/students/{studentId}" -- return a single student by ID
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        // check the studentId against list size
        if((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("student id not found - " + studentId);
        }

        // just index into the list
        return theStudents.get(studentId);
    }



}
