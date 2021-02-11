package com.anr.protobuffpoc.service;

import com.anr.protobuff.dto.StudentListProto;
import com.anr.protobuff.dto.StudentProto;
import com.anr.protobuffpoc.entity.StudentEntity;
import com.anr.protobuffpoc.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public StudentProto.Student getStudentById(Integer id) {
        return this.studentRepo.findById(id).map(data -> StudentProto.Student.newBuilder()
                .setId(data.getId())
                .setFirstName(data.getFirstName())
                .setLastName(data.getLastName()).build())
                .get();

    }

    @Override
    public StudentListProto.StudentList getAllStudents() {

        var studentsProtos = this.studentRepo.findAll().stream().map(data -> {
            StudentListProto.StudentList.Student student = StudentListProto.StudentList.newBuilder().addStudentsBuilder()
                    .setId(data.getId())
                    .setFirstName(data.getFirstName())
                    .setLastName(data.getLastName()).build();
            return student;

        }).collect(Collectors.toList());

        return StudentListProto.StudentList.newBuilder().addAllStudents(studentsProtos).build();
    }

    @Override
    public StudentProto.Student addStudent(StudentProto.Student student) {
        StudentEntity studentEntity = new StudentEntity(student.getId(), student.getFirstName(), student.getLastName());
        this.studentRepo.save(studentEntity);
        return student;
    }

    @Override
    public String deleteStudentById(Integer id) {
         this.studentRepo.deleteById(id);
        return "deleted successully";
    }
}
