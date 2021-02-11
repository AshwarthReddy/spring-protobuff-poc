package com.anr.protobuffpoc.service;

import com.anr.protobuff.dto.StudentProto;
import com.anr.protobuffpoc.entity.StudentEntity;
import com.anr.protobuffpoc.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<StudentProto.Student> getAllStudents() {
        return this.studentRepo.findAll().stream().map(data -> {
            return StudentProto.Student.newBuilder().setId(data.getId()).setFirstName(data.getFirstName()).setLastName(data.getLastName()).build();
        }).collect(Collectors.toList());
    }

    @Override
    public StudentProto.Student addStudent(StudentProto.Student student) {
        StudentEntity studentEntity = new StudentEntity(student.getId(), student.getFirstName(), student.getLastName());
        this.studentRepo.save(studentEntity);
        return student;
    }

}
