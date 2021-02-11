package com.anr.protobuffpoc.controller;

import com.anr.protobuff.dto.StudentListProto.StudentList;
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
     *  this end point will use for get the student By Id
     * </p>
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id) {
        return this.studentService.getStudentById(Integer.valueOf(id));
    }


    /**
     * <p>
     * This end point will use for get all the students
     * </p>
     *
     * @return
     */
    @GetMapping
    public StudentList getAllStudentList() {
        return this.studentService.getAllStudents();
    }


    /**
     * <p>
     * This End Point wil use for save student record
     * </p>
     *
     * @param student
     * @return
     */
    @RequestMapping(value = "/add", produces = "application/x-protobuf")
    public Student addStudent(@RequestBody Student student) {

        return this.studentService.addStudent(student);
    }

    /**
     * <p>
     * This end will use for delete the student by id
     * </p>
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id) {

        return this.studentService.deleteStudentById(id);
    }


}
