package com.anr.protobuffpoc.service;

import com.anr.protobuff.dto.StudentListProto;
import com.anr.protobuff.dto.StudentProto;

public interface StudentService {
    StudentProto.Student getStudentById(Integer id);

    StudentListProto.StudentList getAllStudents();

    public StudentProto.Student addStudent(StudentProto.Student student);

    String deleteStudentById(Integer id);
}
