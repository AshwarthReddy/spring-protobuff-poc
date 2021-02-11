package com.anr.protobuffpoc.service;

import com.anr.protobuff.dto.StudentProto;

import java.util.List;

public interface StudentService {
    StudentProto.Student getStudentById(Integer id);

    List<StudentProto.Student> getAllStudents();

    public StudentProto.Student addStudent(StudentProto.Student student);
}
