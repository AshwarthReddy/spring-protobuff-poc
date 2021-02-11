package com.anr.protobuffpoc.controller;

import com.anr.protobuff.dto.StudentProto.Student;
import com.anr.protobuffpoc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/student", produces = "application/x-protobuf")
public class StudentController {


    @Autowired
    private StudentService studentService;


    /**
     * <p>
     *     get the student By Id
     * </p>
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id) {
        Student studentById = studentService.getStudentById(Integer.valueOf(id));
        return studentById;

    }


    /**
     * <p>
     *     save student
     * </p>
     * @param student
     * @return
     */
    @RequestMapping(value = "/add", produces = "application/x-protobuf")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }


}
